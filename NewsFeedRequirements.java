/**
 * A program Team Project
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project-- </p>
 *
 * @author Purdue CS
 * @version Mar 31, 2024
 */
public interface NewsFeedRequirements {

    void addPost(Post post);

    void removePost(Post post);

    void hidePost(Post post);

    void showPost(Post post);

    void displayNewsFeed();

    void displayPost(Post post);

    void editPost(Post post, String title, String content, String imageURL);
}
