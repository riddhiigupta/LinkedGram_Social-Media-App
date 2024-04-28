import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private List<User> following = new ArrayList<>();
    private Profile profile;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void addFollowing(User user) {
        following.add(user);
    }


    public void setName(String newValue) {
        this.username = newValue;
    }


    public void removeFollowing(User connection) {
        following.remove(connection);
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void addFollower(User follower) {
        follower.addFollowing(this);
    }
}