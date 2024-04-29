import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostTest {

    @Test
    public void testAddComment() throws CommentIncompleteException, PostIncompleteException {
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        post.addComment("author", "message");
        assertEquals(1, post.getComments().size());
    }

    @Test
    public void testUpvote() throws PostIncompleteException {
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        post.upvote();
        assertEquals(1, post.getUpvotes());
    }

    @Test
    public void testDownvote() throws PostIncompleteException {
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        post.downvote();
        assertEquals(1, post.getDownvotes());
    }

    @Test
    public void testHide() throws PostIncompleteException {
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        post.hide();
        assertTrue(post.isHidden());
    }

    @Test
    public void testShow() throws PostIncompleteException {
        Post post = new Post("title", "content", "author", true, "url", 0, 0);
        post.show();
        assertFalse(post.isHidden());
    }

    @Test
    public void testSetTitle() throws PostIncompleteException {
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        post.setTitle("new title");
        assertEquals("new title", post.getTitle());
    }

    @Test
    public void testSetContent() throws PostIncompleteException {
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        post.setContent("new content");
        assertEquals("new content", post.getContent());
    }

    @Test
    public void testSetAuthor() throws PostIncompleteException {
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        post.setAuthor("new author");
        assertEquals("new author", post.getAuthor());
    }

    @Test
    public void testSetImageUrl() throws PostIncompleteException {
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        post.setImageURL("new url");
        assertEquals("new url", post.getImageURL());
    }

    @Test
    public void testSetUpvotes() throws PostIncompleteException {
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        post.setUpvotes(10);
        assertEquals(10, post.getUpvotes());
    }

    @Test
    public void testSetDownvotes() throws PostIncompleteException {
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        post.setDownvotes(10);
        assertEquals(10, post.getDownvotes());
    }

    @Test
    public void testSetHidden() throws PostIncompleteException {
        Post post = new Post("title", "content", "author", false, "url", 0, 0);
        post.setHidden(true);
        assertTrue(post.isHidden());
    }
}