import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * A program Team Project
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project-- </p>
 *
 * @author Purdue CS
 * @version Mar 31, 2024
 */
public class PostFileManager {
    private static final String POSTS_FILE = "posts.txt";

    public static void savePost(Post post) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(POSTS_FILE, true))) {
            writer.println(postToString(post));
            System.out.println("Post saved to file: " + post.getTitle()); // Add this line for logging
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(POSTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Post post = stringToPost(line);
                posts.add(post);
                System.out.println("Post: " + post.toString());
            }
        } catch (IOException | PostIncompleteException e) {
            e.printStackTrace();
        }
        return posts;
    }

    private static String postToString(Post post) {
        // Convert Post object to string format for saving to file
        return String.format("%s;%s;%s;%s;%d;%d;%s", post.getTitle(), post.getContent(), post.getAuthor(),
                post.getImageURL(), post.getUpvotes(), post.getDownvotes(), post.isHidden());
    }

    private static Post stringToPost(String line) throws PostIncompleteException {
        String[] parts = line.split(";|:");
        if (parts.length == 6) {
            return new Post(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[6]), parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
        }
        // Assuming format is: title;content;author;imageURL;upvotes;downvotes;hidden
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i < parts.length; i++) {
            if (i % 2 != 0 && i + 1 < parts.length) {
                sb.append(parts[i + 1]);
                sb.append("  --");
            }
            if (i < parts.length - 1) {
                sb.append(parts[i]);
                sb.append("  |  ");
            }
        }
        StringBuilder result = new StringBuilder();
        int start = 0;
        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '|') {
                count++;
                if (count % 2 == 1) {
                    result.append(sb.substring(start, i + 1));
                } else {
                    start = i + 1;
                }
            }
        }
        if (count % 2 == 1) {
            result.append(sb.substring(start));
        }
        int lastIndexOfPipe = result.lastIndexOf("|");
        if (lastIndexOfPipe != -1) {
            int secondLastIndexOfPipe = result.lastIndexOf("|", lastIndexOfPipe - 1);
            if (secondLastIndexOfPipe != -1) {
                result.delete(secondLastIndexOfPipe, result.length());
            }
        }
        sb = result;
        return new Post(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[6]), parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), sb.toString());
    }
}