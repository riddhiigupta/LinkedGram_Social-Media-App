import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientGUI {
    JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private static String loggedInUser;
    private boolean useAlternateImage = false;

    public ClientGUI() {
        initializeUI();
        connectToServer();
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 8081);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Cannot connect to server: " + e.getMessage());
        }
    }

    private void initializeUI() {
        frame = new JFrame("Social Media Platform Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); // Adjust the size of the frame
        frame.setLayout(new GridBagLayout()); // Use GridBagLayout for more control over component positioning

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for the login panel as well
        GridBagConstraints gbc = new GridBagConstraints(); // Create a GridBagConstraints object to specify constraints for components

        gbc.insets = new Insets(10, 10, 10, 10); // Set margins around components

        // Add image at the top of the login panel
        ImageIcon imageIcon = new ImageIcon("lawson-small.png");
        Image image = imageIcon.getImage(); // transform it into Image

        // Get the original width and height
        int originalWidth = imageIcon.getIconWidth();
        int originalHeight = imageIcon.getIconHeight();

        // Calculate the new width and height
        int newWidth = 200; // This can be any value you want
        int newHeight = (newWidth * originalHeight) / originalWidth; // Keep the aspect ratio

        Image newImage = image.getScaledInstance(newWidth, newHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newImage);  // transform it back to ImageIcon
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(imageIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        loginPanel.add(imageLabel, gbc);

        gbc.gridwidth = 1; // Reset to default

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(10); // Specify the width of the text field
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(10); // Specify the width of the password field
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::loginAction);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0; // Add this line
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this::registerAction);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0; // Add this line
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(registerButton, gbc);

        frame.add(loginPanel); // Add the login panel to the frame
        frame.pack(); // Pack the frame to fit the preferred size of its components
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    private void loginAction(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        out.println("LOGIN");
        out.println(username);
        loggedInUser = username;
        out.println(password);
        try {
            String response = in.readLine();
            if ("true".equals(response)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                showSocialMediaMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Login failed!");
            }
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(frame, "Error logging in: " + ioException.getMessage());
        }
    }

    private void registerAction(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        out.println("REGISTER");
        out.println(username);
        out.println(password);
        try {
            String response = in.readLine();
            JOptionPane.showMessageDialog(frame, response); // Show the server's response in a dialog
            if ("Registration successful!".equals(response)) {
                createProfileGUI(); // Call createProfileGUI method after successful registration
            }
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(frame, "Error registering: " + ioException.getMessage());
        }
    }

    private void showSocialMediaMenu() {
        // Clear the existing UI and set up a new one for social media features
        frame.getContentPane().removeAll();
        JPanel socialMediaPanel = new JPanel();
        socialMediaPanel.setLayout(new BoxLayout(socialMediaPanel, BoxLayout.Y_AXIS)); // Set layout to BoxLayout
        frame.add(socialMediaPanel);

        // Add buttons for different social media actions
        JButton viewFeedButton = new JButton("View News Feed");
        viewFeedButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Set alignment to center
        viewFeedButton.addActionListener(e -> viewNewsFeed());
        socialMediaPanel.add(viewFeedButton);

        JButton createPostButton = new JButton("Create Post");
        createPostButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Set alignment to center
        createPostButton.addActionListener(e -> createPostGUI());
        socialMediaPanel.add(createPostButton);

        JButton commentOnPostButton = new JButton("Comment on Post");
        commentOnPostButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Set alignment to center
        commentOnPostButton.addActionListener(e -> commentOnPost());
        socialMediaPanel.add(commentOnPostButton);

        JButton searchUserButton = new JButton("Search User");
        searchUserButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Set alignment to center
        searchUserButton.addActionListener(e -> searchUser());
        socialMediaPanel.add(searchUserButton);

        JButton upvotePostButton = new JButton("Upvote Post");
        upvotePostButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Set alignment to center
        upvotePostButton.addActionListener(e -> upvotePost());
        socialMediaPanel.add(upvotePostButton);

        JButton downvotePostButton = new JButton("Downvote Post");
        downvotePostButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Set alignment to center
        downvotePostButton.addActionListener(e -> downvotePost());
        socialMediaPanel.add(downvotePostButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Set alignment to center
        logoutButton.addActionListener(e -> logout());
        socialMediaPanel.add(logoutButton);

        frame.revalidate();
        frame.repaint();
    }

    private void removeTrailingNewline(String filename) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filename, "rw");
        long length = file.length();
        if (length != 0) {
            do {
                length -= 1;
                file.seek(length);
                byte b = file.readByte();
                if (b != 10 && b != 13) {
                    file.setLength(length + 1);
                    break;
                }
            } while (length > 0);
        }
        file.close();
    }

    private void createPostGUI() {
        // Clear the existing UI and set up a new one for creating a post
        frame.getContentPane().removeAll();
        JPanel createPostPanel = new JPanel();
        createPostPanel.setLayout(new BoxLayout(createPostPanel, BoxLayout.Y_AXIS)); // Set layout to BoxLayout
        frame.add(createPostPanel);

        // Add text fields for post title, content and image URL
        JTextField titleField = new JTextField(20);
        titleField.setMaximumSize(titleField.getPreferredSize()); // Limit the height of the text field
        createPostPanel.add(new JLabel("Post Title:"));
        createPostPanel.add(titleField);

        JTextArea contentField = new JTextArea(5, 20);
        contentField.setLineWrap(true);
        contentField.setWrapStyleWord(true);
        JScrollPane contentScrollPane = new JScrollPane(contentField);
        createPostPanel.add(new JLabel("Post Content:"));
        createPostPanel.add(contentScrollPane);

        JTextField imageURLField = new JTextField(20);
        imageURLField.setMaximumSize(imageURLField.getPreferredSize()); // Limit the height of the text field
        createPostPanel.add(new JLabel("Image URL (optional):"));
        createPostPanel.add(imageURLField);

        // Add a "Submit" button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String title = titleField.getText();
            String content = contentField.getText();
            String imageURL = imageURLField.getText();
            if(imageURL.isEmpty()) {
                imageURL = "No image";
            }
            Post newPost;
            try {
                newPost = new Post(title, content, loggedInUser, false, imageURL, 0, 0);
            } catch (PostIncompleteException ex) {
                throw new RuntimeException(ex);
            }
            out.println("CREATE_POST");
            out.println(newPost.getTitle());
            out.println(newPost.getContent());
            out.println(newPost.getAuthor());
            out.println(newPost.getImageURL());
            out.println();
            try {
                removeTrailingNewline("posts.txt");
                String response = in.readLine();
                if ("Post created successfully!".equals(response)) {
                    JOptionPane.showMessageDialog(frame, "Post created successfully!");
                    showSocialMediaMenu();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to create post: " + response);
                }
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, "Error creating post: " + ioException.getMessage());
            }
        });
        createPostPanel.add(submitButton);

        frame.revalidate();
        frame.repaint();
    }

    private void commentOnPost() {
        String postTitle = JOptionPane.showInputDialog(frame, "Enter the title of the post you want to comment on:");
        String commentContent = JOptionPane.showInputDialog(frame, "Enter your comment:");
        if (postTitle != null && !postTitle.isEmpty() && commentContent != null && !commentContent.isEmpty()) {
            out.println("COMMENT_ON_POST");
            out.println(postTitle);
            out.println(loggedInUser);
            out.println(commentContent);
            try {
                String response = in.readLine();
                if ("Comment added successfully!".equals(response)) {
                    JOptionPane.showMessageDialog(frame, "Comment added successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to add comment: " + response);
                }
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, "Error adding comment: " + ioException.getMessage());
            }
        }
    }

    private void createProfileGUI() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField usernameField = new JTextField();
        JTextField aboutField = new JTextField();
        JTextField experienceField = new JTextField();
        JTextField educationField = new JTextField();
        JTextField awardsField = new JTextField();
        JTextField skillsField = new JTextField();
        JTextField statusField = new JTextField();
        JTextField photoField = new JTextField();

        panel.add(new JLabel("Username(same as the one you just entered): "));
        panel.add(usernameField);
        panel.add(new JLabel("About:"));
        panel.add(aboutField);
        panel.add(new JLabel("Experience:"));
        panel.add(experienceField);
        panel.add(new JLabel("Education:"));
        panel.add(educationField);
        panel.add(new JLabel("Awards:"));
        panel.add(awardsField);
        panel.add(new JLabel("Skills:"));
        panel.add(skillsField);
        panel.add(new JLabel("Status:"));
        panel.add(statusField);
        panel.add(new JLabel("Photo (for example: boilermaker! or a cute squirrel)"));
        panel.add(photoField);


        int result = JOptionPane.showConfirmDialog(null, panel, "Create Profile",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            out.println("CREATE_PROFILE");
            out.println(usernameField.getText());
            out.println(aboutField.getText());
            out.println(experienceField.getText());
            out.println(educationField.getText());
            out.println(awardsField.getText());
            out.println(skillsField.getText());
            out.println(statusField.getText());

            try {
                String response = in.readLine();
                JOptionPane.showMessageDialog(frame, response); // Show the server's response in a dialog
                if ("Profile created successfully!".equals(response)) {
                    // After creating the profile, automatically log in twice
                    out.println("LOGIN");
                    out.println(usernameField.getText());
                    out.println(passwordField.getPassword());
                }
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, "Error creating profile: " + ioException.getMessage());
            }
        }
    }

    private void searchUser() {
        String usernameToSearch = JOptionPane.showInputDialog(frame, "Enter username to search:");
        if (usernameToSearch != null && !usernameToSearch.isEmpty()) {
            out.println("SEARCH_USER");
            out.println(usernameToSearch);
            try {
                String response = in.readLine();
                if (response.startsWith("User profile not found")) {
                    JOptionPane.showMessageDialog(frame, response);
                } else {
                    StringBuilder profileInfo = new StringBuilder();
                    String existingProfile = in.readLine();
                    String education = in.readLine();
                    String awards = in.readLine();
                    String skills = in.readLine();
                    String status = in.readLine();

                    // Check for extra newline
                    while (in.ready()) {
                        in.readLine();
                    }

                    profileInfo.append("Username: ").append(usernameToSearch).append("\n");
                    profileInfo.append(response).append("\n");
                    profileInfo.append(existingProfile).append("\n");
                    profileInfo.append(education).append("\n");
                    profileInfo.append(awards).append("\n");
                    profileInfo.append(skills).append("\n");
                    profileInfo.append(status).append("\n");

                    // Load the image
                    ImageIcon imageIcon;
                    if (useAlternateImage) {
                        imageIcon = new ImageIcon("squirrel.jpg");
                    } else {
                        imageIcon = new ImageIcon("boilermaker!.svg.png");
                    }
                    useAlternateImage = !useAlternateImage; // Switch the image for the next time


                    Image image = imageIcon.getImage(); // transform it into Image
                    Image newImage = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newImage);  // transform it back to ImageIcon
                    JLabel imageLabel = new JLabel(imageIcon);
                    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the image

                    // Create a panel to hold the image and text
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    panel.add(imageLabel);

                    // Create a label for each piece of profile info and center it
                    JLabel usernameLabel = new JLabel("Username: " + usernameToSearch);
                    usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
                    panel.add(usernameLabel);

                    JLabel responseLabel = new JLabel(response);
                    responseLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
                    panel.add(responseLabel);

                    JLabel existingProfileLabel = new JLabel(existingProfile);
                    existingProfileLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
                    panel.add(existingProfileLabel);

                    JLabel educationLabel = new JLabel(education);
                    educationLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
                    panel.add(educationLabel);

                    JLabel awardsLabel = new JLabel(awards);
                    awardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
                    panel.add(awardsLabel);

                    JLabel skillsLabel = new JLabel(skills);
                    skillsLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
                    panel.add(skillsLabel);

                    JLabel statusLabel = new JLabel(status);
                    statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text
                    panel.add(statusLabel);

                    JOptionPane.showMessageDialog(frame, panel, "User profile for " + usernameToSearch, JOptionPane.PLAIN_MESSAGE);
                }
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, "Error searching user: " + ioException.getMessage());
            }
        }
    }
    private void upvotePost() {
        // Implementation to upvote post
        JOptionPane.showMessageDialog(frame, "Upvoting post...");
    }

    private void downvotePost() {
        // Implementation to downvote post
        JOptionPane.showMessageDialog(frame, "Downvoting post...");
    }

    private void logout() {
        // Send a logout request to the server
        out.println("LOGOUT");
        try {
            String response = in.readLine();
            if ("Logout successful!".equals(response)) {
                // Clear the user session information
                loggedInUser = null;
                // Clear the existing UI and show the login UI
                frame.getContentPane().removeAll();
                initializeUI();
                frame.revalidate();
                frame.repaint();
                JOptionPane.showMessageDialog(frame, "Logged out successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to logout: " + response);
            }
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(frame, "Error logging out: " + ioException.getMessage());
        }
    }

    private void viewNewsFeed() {
        frame.getContentPane().removeAll();
        frame.setSize(800, 600); // Set a larger size for the news feed
        JPanel newsFeedPanel = new JPanel();
        newsFeedPanel.setLayout(new BoxLayout(newsFeedPanel, BoxLayout.Y_AXIS));
        frame.add(newsFeedPanel);

        new SwingWorker<Void, JPanel>() {
            @Override
            protected Void doInBackground() throws Exception {
                out.println("VIEW_NEWS_FEED"); // Send request to the server to retrieve the news feed
                String response;
                int i = 0;
                while ((response = in.readLine()) != null) {
                    String title = response;
                    String content = in.readLine();
                    String author = in.readLine();
                    String imageURL = in.readLine();
                    String upvotes = in.readLine();
                    String downvotes = in.readLine();
                    StringBuilder commentsBuilder = new StringBuilder();
                    in.mark(1000); // Mark the current position
                    String line = in.readLine(); // Read the next line
                    while (line != null && !line.startsWith("Downvotes:") && !line.startsWith("Upvotes:") && !line.startsWith("Image URL:") && !line.startsWith("Title:") && !line.startsWith("Content:")) {
                        commentsBuilder.append(line).append("\n");
                        in.mark(1000); // Mark the current position
                        line = in.readLine(); // Read the next line
                    }
                    in.reset();
                    String comments = commentsBuilder.toString();
                    System.out.println("Comment: " + comments);


                    JPanel postPanel = new JPanel();
                    postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                    postPanel.setBorder(BorderFactory.createTitledBorder(title + " by " + author));
                    postPanel.setPreferredSize(new Dimension(1000, 600));

                    // Ensure the content label handles long text correctly by wrapping text
                    JLabel contentLabel = new JLabel("<html><body style='width: 950px'>" + content + "</body></html>");
                    contentLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Increase the font size
                    postPanel.add(contentLabel);

                    if (!imageURL.isEmpty()) {
                        postPanel.add(new JLabel(imageURL));
                    }

                    JLabel upvotesLabel = new JLabel(upvotes);
                    upvotesLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Increase the font size
                    postPanel.add(upvotesLabel);

                    JLabel downvotesLabel = new JLabel(downvotes);
                    downvotesLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Increase the font size
                    postPanel.add(downvotesLabel);

                    // If there are comments, add them to the post panel
                    if (!comments.trim().isEmpty()) {
                        JLabel commentsLabel = new JLabel(comments);
                        commentsLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Increase the font size
                        postPanel.add(commentsLabel);
                    }

                    publish(postPanel);
                    i++;
                }
                return null;
            }

            @Override
            protected void process(List<JPanel> chunks) {
                for (JPanel panel : chunks) {
                    newsFeedPanel.add(panel);
                }
                frame.revalidate(); // Usually call revalidate and repaint after modifying components
                frame.repaint();
            }

            @Override
            protected void done() {
                JButton backButton = new JButton("Back");
                backButton.addActionListener(e -> showSocialMediaMenu());
                newsFeedPanel.add(backButton);
                frame.revalidate();
                frame.repaint();
            }
        }.execute();
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showSocialMediaMenu());
        newsFeedPanel.add(backButton);

        frame.revalidate();
        frame.repaint();
    }

    public void updateNewsFeed(String postDetails) {

        JLabel label = new JLabel(postDetails);
        JPanel panel = new JPanel();
        panel.add(label);
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }
    public static void main(String[] args) {
        new ClientGUI();
    }
}
