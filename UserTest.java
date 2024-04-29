import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A program Team Project
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project-- </p>
 *
 * @author Purdue CS
 * @version Mar 31, 2024
 */
public class UserTest {

    @Test
    public void testGetUsername() {
        User user = new User("username", "password");
        assertEquals("username", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        User user = new User("username", "password");
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testGetFollowing() {
        User user1 = new User("username1", "password1");
        User user2 = new User("username2", "password2");
        user1.addFollowing(user2);
        assertTrue(user1.getFollowing().contains(user2));
    }

    @Test
    public void testAddFollowing() {
        User user1 = new User("username1", "password1");
        User user2 = new User("username2", "password2");
        user1.addFollowing(user2);
        assertTrue(user1.getFollowing().contains(user2));
    }

    @Test
    public void testSetName() {
        User user = new User("username", "password");
        user.setName("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    @Test
    public void testRemoveFollowing() {
        User user1 = new User("username1", "password1");
        User user2 = new User("username2", "password2");
        user1.addFollowing(user2);
        user1.removeFollowing(user2);
        assertFalse(user1.getFollowing().contains(user2));
    }

    @Test
    public void testGetProfile() throws ProfileIncompleteException {
        User user = new User("username", "password");
        Profile profile = new Profile("about", new ArrayList<>(), "education", "awards", "skills", "status");
        user.setProfile(profile);
        assertEquals(profile, user.getProfile());
    }

    @Test
    public void testSetProfile() throws ProfileIncompleteException {
        User user = new User("username", "password");
        Profile profile = new Profile("about", new ArrayList<>(), "education", "awards", "skills", "status");
        user.setProfile(profile);
        assertEquals(profile, user.getProfile());
    }

    @Test
    public void testAddFollower() {
        User user1 = new User("username1", "password1");
        User user2 = new User("username2", "password2");
        user1.addFollower(user2);
        assertTrue(user2.getFollowing().contains(user1));
    }
}
