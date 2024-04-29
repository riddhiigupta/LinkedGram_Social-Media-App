import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A program Team Project
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project-- </p>
 *
 * @author Purdue CS
 * @version Mar 31, 2024
 */
public class NewsFeedTest {

    @Test
    public void testAddPost() throws PostIncompleteException {
        NewsFeed newsFeed = new NewsFeed();
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        newsFeed.addPost(post);
        assertEquals(post, newsFeed.getPost("title"));
    }

    @Test
    public void testRemovePost() throws PostIncompleteException {
        NewsFeed newsFeed = new NewsFeed();
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        newsFeed.addPost(post);
        newsFeed.removePost(post);
        assertNull(newsFeed.getPost("title"));
    }

    @Test
    public void testGetPost() throws PostIncompleteException {
        NewsFeed newsFeed = new NewsFeed();
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        newsFeed.addPost(post);
        assertEquals(post, newsFeed.getPost("title"));
    }

    @Test
    public void testToString() throws PostIncompleteException {
        NewsFeed newsFeed = new NewsFeed();
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        newsFeed.addPost(post);
        String expected = post.toString() + "\n";
        assertEquals(expected, newsFeed.toString());
    }
}