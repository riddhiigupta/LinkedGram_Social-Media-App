import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
public class NewsFeedManager {
    private static final String POSTS_FILE = "posts.txt";

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

    private static Post stringToPost(String line) throws PostIncompleteException {
        String[] parts = line.split(";");
        // Assuming format is: title;content;author;imageURL;upvotes;downvotes;hidden
        return new Post(parts[0], parts[1],
                parts[2], Boolean.parseBoolean(parts[6]),
                parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
    }
}
