import java.io.BufferedReader;
import java.io.File;
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
public class NewsFeed {
    private List<Post> postList;
    private String owner;

    public NewsFeed() {
        this.postList = new ArrayList<>();
    }

    public void addPost(Post post) {
        postList.add(post);
    }

    public void removePost(Post post) {
        postList.remove(post);
    }

    public Post getPost(String title) {
        for (Post post : postList) {
            if (post.getTitle().equals(title)) {
                return post;
            }
        }
        return null;
    }

    public void viewNewsFeed() {
        for (Post post : postList) {
            System.out.println(post);
        }
    }

    public String getOwner() {
        return owner;
    }

    public String toString(){
        StringBuilder newsFeed = new StringBuilder();
        for (Post post : postList) {
            newsFeed.append(post.toString()).append("\n");
        }
        return newsFeed.toString();
    }
}

