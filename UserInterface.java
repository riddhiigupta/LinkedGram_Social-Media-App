import java.awt.image.BufferedImage;

/**
 * A program Team Project
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project-- </p>
 *
 * @author Purdue CS
 * @version Mar 31, 2024
 */

public interface UserInterface {
    public void editProfile(String username, String password,
                            String email, String name, String[] connections,
                            String[] followers, BufferedImage profilePicture);

    public String showInfo();

    public boolean checkPwd(String pwdIn);

    public boolean login(String pwdIn);

    public boolean isOnline();
}