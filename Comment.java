/**
 * A program Team Project
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project-- </p>
 *
 * @author Purdue CS
 * @version Mar 31, 2024
 */
public class Comment extends Post implements CommentRequirements {
    private String content;
    private String author;
    private int upvotes;
    private int downvotes;
    private boolean hidden;
    private Post post;


    public Comment(Post post, String content, String author,
                   boolean hidden, int upvotes, int downvotes)
            throws CommentIncompleteException, PostIncompleteException {
        super(post.getTitle(), post.getContent(),
                post.getAuthor(), post.isHidden(), post.getImageURL(), post.getUpvotes(), post.getDownvotes());
        this.content = content;
        this.author = author;
        this.hidden = hidden;
        this.post = post;
        if (content.isEmpty()) {
            throw new CommentIncompleteException("Content is required");
        }
        if (author.isEmpty()) {
            throw new CommentIncompleteException("Author is required");
        }
    }

    @Override
    public void upvote() {
        upvotes++;
    }

    @Override
    public void downvote() {
        downvotes++;
    }

    @Override
    public void hide() {
        hidden = true;
    }

    @Override
    public void show() {
        hidden = false;
    }

    public String toString() {
        return "\nAuthor: " + author + "\nContent: " + content
                + "\nUpvotes: " + upvotes + "\nDownvotes: " + downvotes;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isHidden() {
        return hidden;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public Post getPost() {
        return post;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}