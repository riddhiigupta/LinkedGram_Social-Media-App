import java.util.ArrayList;

/**
 * A program Team Project
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project-- </p>
 *
 * @author Purdue CS
 * @version Mar 31, 2024
 */
public class Post implements PostRequirements {
    private String title;
    private String content;
    private String author;
    private int upvotes;
    private int downvotes;
    private boolean hidden;
    private String imageURL;
    private ArrayList<Comment> comments;


    public Post(String title, String content, String author, boolean hidden, String imageURL, int upvotes, int downvotes) throws PostIncompleteException {
        this.title = title;
        this.content = content;
        this.author = author;
        this.hidden = hidden;
        this.imageURL = imageURL;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.comments = new ArrayList<>();

        if (title.isEmpty()) {
            throw new PostIncompleteException("Title is required");
        }
        if (content.isEmpty()) {
            throw new PostIncompleteException("Content is required");
        }
        if (author.isEmpty()) {
            throw new PostIncompleteException("Author is required");
        }
        if (imageURL.isEmpty()) {
            this.imageURL = "No image";
        }
    }

    public void addComment(String writer, String message) throws CommentIncompleteException, PostIncompleteException {
        if (message.isEmpty()) {
            throw new CommentIncompleteException("Message is required");
        }
        if (writer.isEmpty()) {
            throw new CommentIncompleteException("Writer is required");
        }
        Comment comment = new Comment(this, message, writer, false, 0, 0);
        this.comments.add(comment);
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public void showComments() {
        for (int i = 0; i < this.comments.size(); i++) {
            Comment comment = this.comments.get(i);
            System.out.println(comment.toString());
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
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n");
        sb.append("Content: ").append(content).append("\n");
        sb.append("Author: ").append(author).append("\n");
        sb.append("Image URL: ").append(imageURL).append("\n");
        sb.append("Upvotes: ").append(upvotes).append("\n");
        sb.append("Downvotes: ").append(downvotes).append("\n");
        return sb.toString();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getImageUrl() {
        return imageURL;
    }
}
