import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8081;
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static Scanner scanner;
    private static String loggedInUser;

    public Client() {
    }

    public static void main(String[] args) {
        try {
            socket = new Socket(HOST, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(System.in);
            System.out.println("Connected to server.");
            showMenu();
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        String choice;
        do {
            System.out.println("\n=== Login ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            handleChoice(choice);
        } while (!choice.equals("0"));

        try {
            socket.close();
            out.close();
            in.close();
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void handleChoice(String choice) {
        try {
            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "0":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (IOException | PostIncompleteException e) {
            System.err.println("Error handling choice: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void register() throws IOException {
        while (true) {
            System.out.println("Enter username: ");
            String username = scanner.nextLine();
            System.out.println("Enter password: ");
            String password = scanner.nextLine();
            out.println("REGISTER");
            out.println(username);
            out.println(password);
            String response = in.readLine();
            if (response != null && response.equals("Registration successful!")) {
                System.out.println(response);
                createProfile(username);
                break;
            } else {
                System.out.println("Username already exists. Please try again.");
            }
        }
    }


    private static void login() throws IOException, PostIncompleteException {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        out.println("LOGIN");
        out.println(username);
        out.println(password);
        String response = in.readLine();
        if (response.equals("true")) {
            loggedInUser = username;
            System.out.println("Login successful!");
            showSocialMediaMenu();  // Directly call the method to show the social media menu
        } else {
            System.out.println("Login failed. Please try again.");
            showMenu();  // If login fails, show the login menu again
        }
    }

    private static void handleSearchUserResponse(String response) {
        System.out.println(response);
    }



    private static void showSocialMediaMenu() throws IOException, PostIncompleteException {
        String choice;
        do {
            System.out.println("\n=== Social Media Platform Menu ===");
            System.out.println("1. View News Feed");
            System.out.println("2. Create Post");
            System.out.println("3. Comment on Post");
            System.out.println("4. Search User");
            System.out.println("5. Upvote Post");
            System.out.println("6. Downvote Post");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            handleSocialMediaChoice(choice);
        } while (!choice.equals("0")); // Loop until user chooses to logout


    }


    private static void handleSocialMediaChoice(String choice) throws IOException, PostIncompleteException {
        switch (choice) {
            case "1":
                viewNewsFeed();
                break;
            case "2":
                createPost();
                break;
            case "3":
                commentOnPost();
                break;
            case "4":
                String usernameToSearch = scanner.nextLine();
                out.println("SEARCH_USER");
                out.println(usernameToSearch);
                String response = in.readLine();
                handleSearchUserResponse(response);
                break;
            case "5":
                upvotePost();
                break;
            case "6":
                downvotePost();
                break;
            case "0":
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void upvotePost() throws IOException {
        System.out.println("Enter the title of the post you want to upvote: ");
        String postTitle = scanner.nextLine();
        out.println("UPVOTE_POST");
        out.println(postTitle);
        String response = in.readLine();
        System.out.println(response);
    }

    private static void downvotePost() throws IOException {
        System.out.println("Enter the title of the post you want to downvote: ");
        String postTitle = scanner.nextLine();
        out.println("DOWNVOTE_POST");
        out.println(postTitle);
        String response = in.readLine();
        System.out.println(response);
    }
    private static void searchUser() throws IOException {
        System.out.println("Enter the username to search for: ");
        String username = scanner.nextLine();
        out.println("SEARCH_USER");
        out.println(username);
        String response = in.readLine();
        System.out.println(response);
    }


    private static void commentOnPost() {
    }

    // Inside handleSocialMediaChoice("2") method
    private static void createPost() throws IOException, PostIncompleteException {
        // Prompt user to enter post details
        System.out.println("Enter post title:");
        String title = scanner.nextLine();
        System.out.println("Enter post content:");
        String content = scanner.nextLine();
        System.out.println("Enter image URL (press Enter to skip):");
        String imageURL = scanner.nextLine();

        // Create Post object
        Post newPost = new Post(title, content, loggedInUser, false, imageURL, 0, 0);

        // Send post data to server
        out.println("CREATE_POST");
        out.println(newPost.getTitle());
        out.println(newPost.getContent());
        out.println(newPost.getAuthor());
        out.println(newPost.getImageURL());
    }

    private static void createProfile(String username) throws IOException {
        System.out.println("Enter about:");
        String about = scanner.nextLine();
        System.out.println("Enter experience:");
        String experience = scanner.nextLine();
        System.out.println("Enter education:");
        String education = scanner.nextLine();
        System.out.println("Enter awards:");
        String awards = scanner.nextLine();
        System.out.println("Enter skills:");
        String skills = scanner.nextLine();
        System.out.println("Enter status:");
        String status = scanner.nextLine();

        out.println("CREATE_PROFILE");
        out.println(username); // Send the username to associate the profile with the user
        out.println(about);
        out.println(experience);
        out.println(education);
        out.println(awards);
        out.println(skills);
        out.println(status);
        String response = in.readLine();
        System.out.println(response);
    }




    private static void viewNewsFeed() throws IOException, PostIncompleteException {
        out.println("VIEW_NEWS_FEED"); // Send request to the server to retrieve the news feed
        String newsFeedResponse;
        boolean isNewsFeedEnded = false;
        int consecutiveEmptyLines = 0;

        // Read and print each line of the news feed response until two consecutive empty lines are encountered
        while (!isNewsFeedEnded && (newsFeedResponse = in.readLine()) != null) {
            if (newsFeedResponse.isEmpty()) {
                consecutiveEmptyLines++;
                if (consecutiveEmptyLines == 2) {
                    System.out.println("\n");;
                }
                if (consecutiveEmptyLines > 2) {
                    isNewsFeedEnded = true;
                }
            } else {
                System.out.println(newsFeedResponse);
                consecutiveEmptyLines = 0;
            }
        }

        showSocialMediaMenu(); // Show the social media menu after printing the news feed
    }


}
