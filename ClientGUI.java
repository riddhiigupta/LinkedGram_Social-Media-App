import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientGUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

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
            if ("Registration successful!".equals(response)) {
                JOptionPane.showMessageDialog(frame, "Registration successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "Registration failed: " + response);
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
        createPostButton.addActionListener(e -> createPost());
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


    private void createPost() {
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
            out.println("CREATE_POST");
            out.println(title);
            out.println(content);
            out.println(imageURL);
            try {
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
        // Implementation to comment on post
        JOptionPane.showMessageDialog(frame, "Commenting on post...");
    }

    private void searchUser() {
        // Implementation to search user
        JOptionPane.showMessageDialog(frame, "Searching user...");
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
        // Implementation to logout
        JOptionPane.showMessageDialog(frame, "Logging out...");
    }

    private void viewNewsFeed() {
        // Implementation to view news feed
        JOptionPane.showMessageDialog(frame, "Viewing news feed...");
    }

    public static void main(String[] args) {
        new ClientGUI();
    }
}
