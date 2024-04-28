import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
        String[] parts = line.split(";");
        // Assuming format is: title;content;author;imageURL;upvotes;downvotes;hidden
        return new Post(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[6]), parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
    }
}