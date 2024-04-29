import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommentTest {

    @Test
    public void testUpvote() throws PostIncompleteException, CommentIncompleteException {
        Comment comment = new Comment(new Post("title", "content", "author", false, "url", 0, 0), "content", "author", false, 0, 0);
        comment.upvote();
        assertEquals(1, comment.getUpvotes());
    }

    @Test
    public void testDownvote() throws PostIncompleteException, CommentIncompleteException {
        Comment comment = new Comment(new Post("title", "content", "author", false, "url", 0, 0), "content", "author", false, 0, 0);
        comment.downvote();
        assertEquals(1, comment.getDownvotes());
    }

    @Test
    public void testHide() throws PostIncompleteException, CommentIncompleteException {
        Comment comment = new Comment(new Post("title", "content", "author", false, "url", 0, 0), "content", "author", false, 0, 0);
        comment.hide();
        assertTrue(comment.isHidden());
    }

    @Test
    public void testShow() throws PostIncompleteException, CommentIncompleteException {
        Comment comment = new Comment(new Post("title", "content", "author", true, "url", 0, 0), "content", "author", true, 0, 0);
        comment.show();
        assertFalse(comment.isHidden());
    }

    @Test
    public void testSetContent() throws PostIncompleteException, CommentIncompleteException {
        Comment comment = new Comment(new Post("title", "content", "author", false, "url", 0, 0), "content", "author", false, 0, 0);
        comment.setContent("new content");
        assertEquals("new content", comment.getContent());
    }

    @Test
    public void testSetAuthor() throws PostIncompleteException, CommentIncompleteException {
        Comment comment = new Comment(new Post("title", "content", "author", false, "url", 0, 0), "content", "author", false, 0, 0);
        comment.setAuthor("new author");
        assertEquals("new author", comment.getAuthor());
    }

    @Test
    public void testSetHidden() throws PostIncompleteException, CommentIncompleteException {
        Comment comment = new Comment(new Post("title", "content", "author", false, "url", 0, 0), "content", "author", false, 0, 0);
        comment.setHidden(true);
        assertTrue(comment.isHidden());
    }

    @Test
    public void testSetUpvotes() throws PostIncompleteException, CommentIncompleteException {
        Comment comment = new Comment(new Post("title", "content", "author", false, "url", 0, 0), "content", "author", false, 0, 0);
        comment.setUpvotes(10);
        assertEquals(10, comment.getUpvotes());
    }

    @Test
    public void testSetDownvotes() throws PostIncompleteException, CommentIncompleteException {
        Comment comment = new Comment(new Post("title", "content", "author", false, "url", 0, 0), "content", "author", false, 0, 0);
        comment.setDownvotes(10);
        assertEquals(10, comment.getDownvotes());
    }

    @Test
    public void testSetPost() throws PostIncompleteException, CommentIncompleteException {
        Comment comment = new Comment(new Post("title", "content", "author", false, "url", 0, 0), "content", "author", false, 0, 0);
        Post newPost = new Post("new title", "new content", "new author", false, "new url", 0, 0);
        comment.setPost(newPost);
        assertEquals(newPost, comment.getPost());
    }
}