import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class PostFileManagerTest {

    @Test
    public void testSavePost() throws PostIncompleteException {
        Post post = new Post("title", "content", "author", false, "url", 0, 0, "comments");
        PostFileManager.savePost(post);
        List<Post> posts = PostFileManager.getAllPosts();
        assertTrue(posts.stream().anyMatch(p -> p.getTitle().equals(post.getTitle())));
    }

    @Test
    public void testGetAllPosts() {
        List<Post> posts = PostFileManager.getAllPosts();
        // 这里我们假设posts.txt文件中至少有一个帖子
        assertFalse(posts.isEmpty());

        // 检查第一个帖子的属性
        Post firstPost = posts.get(0);
        assertNotNull(firstPost.getTitle());
        assertNotNull(firstPost.getContent());
        assertNotNull(firstPost.getAuthor());
        assertNotNull(firstPost.getImageURL());
        assertNotNull(firstPost.getUpvotes());
        assertNotNull(firstPost.getDownvotes());
        assertNotNull(firstPost.isHidden());
    }
}