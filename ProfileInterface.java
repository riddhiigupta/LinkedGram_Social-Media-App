/**
 * A program Team Project
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project-- </p>
 *
 * @author Purdue CS
 * @version Mar 31, 2024
 */

public interface ProfileInterface {

    public void addUser(User user);

    public void deleteUser(User user);

    public void addExperience(String experience);

    public void addFollower(String follower, User user);

    public void removeFollower(String follower, User user);

    public void addConnection(String connection, User user);

    public void removeConnection(String connection, User user);
}