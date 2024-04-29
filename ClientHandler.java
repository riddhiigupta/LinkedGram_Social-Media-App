import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A program Team Project
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project-- </p>
 *
 * @author Purdue CS
 * @version Mar 31, 2024
 */
public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private List<User> users;
    private List<NewsFeed> newsFeeds;

    public ClientHandler(Socket clientSocket,
                         List<User> users, List<NewsFeed> newsFeeds) throws IOException {
        this.clientSocket = clientSocket;
        this.users = users;
        this.newsFeeds = newsFeeds;
        this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void run() {
        try {
            boolean isConnectionClosed = false;
            while (!isConnectionClosed) {
                String request = bufferedReader.readLine();
                if (request != null) {
                    String response = handleRequest(request);
                    printWriter.println(response);
                } else {
                    // Client closed the connection
                    isConnectionClosed = true;
                }
            }
        } catch (IOException | PostIncompleteException | ProfileIncompleteException e) {
            System.err.println("Error handling client request: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (printWriter != null) printWriter.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private String handleRequest(String request) throws IOException,
            PostIncompleteException, ProfileIncompleteException {
        String[] requestParts = request.split(" ", 2);
        switch (requestParts[0]) {
            case "REGISTER":
                return handleRegistration();
            case "LOGIN":
                return handleLogin();
            case "VIEW_NEWS_FEED":
                return handleViewNewsFeed();
            // Inside handleRequest method
            case "CREATE_POST":
                handleCreatePost();
                return "Post created successfully!";
            case "UPVOTE_POST":
                return handleUpvotePost();
            case "DOWNVOTE_POST":
                return handleDownvotePost();
            case "COMMENT_ON_POST":
                return handleCommentOnPost();
            case "SEARCH_USER":
                String usernameToSearch = bufferedReader.readLine();
                return handleSearchUser(usernameToSearch);
            case "CREATE_PROFILE":
                String username = bufferedReader.readLine(); // Read the username
                return handleCreateProfile(username);
            case "LOGOUT":
                return handleLogout();
            default:
                return "Unrecognized request: " + request;
        }
    }

    private synchronized String handleRegistration() throws IOException {
        String username = bufferedReader.readLine();
        String password = bufferedReader.readLine();
        if (UserCredentials.isValidCredentials(username, password)) {
            return "Username already exists. Please choose a different username.";
        } else {
            UserCredentials.addCredentials(username, password);
            User newUser = new User(username, password);
            users.add(newUser); // Add the new user to the shared users list
            return "Registration successful!";
        }
    }

    private synchronized String handleLogin() throws IOException {
        String username = bufferedReader.readLine();
        String password = bufferedReader.readLine();
        boolean isValidUser = UserCredentials.isValidCredentials(username, password);

        if (isValidUser) {
            Iterator<User> iterator = users.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (user.getUsername().equals(username)) {
                    break;
                }
            }
            return "true"; // Return "true" to indicate successful login
        } else {
            return "false"; // Return "false" to indicate failed login
        }
    }

    public String handleLogout() {
        return "Logout successful!";
    }

    private String handleViewNewsFeed() {
        StringBuilder newsFeedBuilder = new StringBuilder();
        List<Post> posts = PostFileManager.getAllPosts();
        if (!posts.isEmpty()) {
            for (Post post : posts) {
                newsFeedBuilder.append(post.toString()).append("\n\n"); // Append the entire post string with double newline for separation
            }
        } else {
            return "No posts in the news feed.";
        }
        return newsFeedBuilder.toString();
    }

    // New method to handle post creation
    private void handleCreatePost() throws IOException, PostIncompleteException {
        String title = bufferedReader.readLine();
        String content = bufferedReader.readLine();
        String author = bufferedReader.readLine();
        String imageURL = bufferedReader.readLine();

        // Create Post object
        Post newPost = new Post(title, content, author, false, imageURL, 0, 0);

        // Save the post to a file
        PostFileManager.savePost(newPost);
    }


    private String handleCreateProfile(String username) throws IOException,
            ProfileIncompleteException {
        // Read profile information from the client
        String about = bufferedReader.readLine();  // Read the about field
        String experience = bufferedReader.readLine();  // Read the experience field
        String education = bufferedReader.readLine();  // Read the education field
        String awards = bufferedReader.readLine();  // Read the awards field
        String skills = bufferedReader.readLine();  // Read the skills field
        String status = bufferedReader.readLine();  // Read the status field

        String profileFileName = (username != null ? username : "null") + "_profile.txt";
        try (PrintWriter writer = new PrintWriter(profileFileName)) {
            writer.println("About: " + about);
            writer.println("Experience: " + experience);
            writer.println("Education: " + education);
            writer.println("Awards: " + awards);
            writer.println("Skills: " + skills);
            writer.println("Status: " + status);
            return "Profile created successfully!";
        } catch (IOException e) {
            // Log the exception or print the stack trace
            System.err.println("Error writing profile file: " + e.getMessage());
            e.printStackTrace();
            return "Error creating profile!";
        }
    }


    private synchronized String handleUpvotePost() throws IOException {
        String postTitle = bufferedReader.readLine().trim(); // Read the title and remove leading/trailing whitespace
        List<String> lines = Files.readAllLines(Paths.get("posts.txt"));
        boolean postFound = false;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(";");
            if (parts[0].equals(postTitle)) {
                postFound = true;
                int upvotesIndex = 4; // Index of upvotes count
                int upvotes = Integer.parseInt(parts[upvotesIndex]); // Get current upvotes count
                upvotes++; // Increment upvotes
                parts[upvotesIndex] = String.valueOf(upvotes); // Update upvotes count
                lines.set(i, String.join(";", parts)); // Update the line in the list
                Files.write(Paths.get("posts.txt"), lines); // Write updated lines back to the file
                break;
            }
        }

        return postFound ? "Post upvoted successfully!" : "Post not found!";
    }


    private synchronized String handleDownvotePost() throws IOException {
        String postTitle = bufferedReader.readLine().trim(); // Read the title and remove leading/trailing whitespace
        List<String> lines = Files.readAllLines(Paths.get("posts.txt"));
        boolean postFound = false;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(";");
            if (parts[0].equals(postTitle)) {
                postFound = true;
                int downvotesIndex = 5; // Index of downvotes count
                int downvotes = Integer.parseInt(parts[downvotesIndex]); // Get current downvotes count
                downvotes++; // Increment downvotes
                parts[downvotesIndex] = String.valueOf(downvotes); // Update downvotes count
                lines.set(i, String.join(";", parts)); // Update the line in the list
                Files.write(Paths.get("posts.txt"), lines); // Write updated lines back to the file
                break;
            }
        }

        return postFound ? "Post downvoted successfully!" : "Post not found!";
    }

    private String handleCommentOnPost() throws IOException {
        String postTitle = bufferedReader.readLine();
        String commentAuthor = bufferedReader.readLine();
        String commentContent = bufferedReader.readLine();

        List<String> lines = Files.readAllLines(Paths.get("posts.txt"));
        boolean postFound = false;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(";");
            if (parts[0].equals(postTitle)) {
                postFound = true;
                int commentsIndex = parts.length - 1; // Get the index of comments
                String comments = parts[commentsIndex]; // Get the existing comments
                comments += ";" + commentAuthor + ":" + commentContent; // Append the new comment
                parts[commentsIndex] = comments; // Update comments
                lines.set(i, String.join(";", parts)); // Update the line in the list
                Files.write(Paths.get("posts.txt"), lines); // Write updated lines back to the file
                break;
            }
        }

        return postFound ? "Comment added successfully!" : "Post not found!";
    }

    // Inside the ClientHandler class
    // Inside the ClientHandler class
    private String handleSearchUser(String usernameToSearch) {
        String profileFileName = usernameToSearch + "_profile.txt";
        Path profileFilePath = Paths.get(profileFileName);
        if (Files.exists(profileFilePath)) {
            try {
                // Read all lines from the profile file
                List<String> profileLines = Files.readAllLines(profileFilePath);
                StringBuilder profileBuilder = new StringBuilder();
                for (String line : profileLines) {
                    profileBuilder.append(line).append("\n");
                }
                return profileBuilder.toString();
            } catch (IOException e) {
                return "Error reading user profile for username " + usernameToSearch;
            }
        } else {
            return "User profile not found for username " + usernameToSearch;
        }
    }


}