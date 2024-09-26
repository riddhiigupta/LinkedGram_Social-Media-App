# Program Overview

## Distribution:
- **Riddhi Gupta**: Submitted Report on Brightspace.
- **Dong Wang**: Submitted Vocareum workspace / Presentation
## How to run the program:
- **Note**: There are four main function: Create Post, Comment on Post, Search User, and Create Profile. You can only choose to run one of the functions at a time due to the communication issue between client and server. If you want to run another function, you need to restart the program.
- **route1**: Register a new account and create a profile. After registration, you will be redirected to the one of the main functions: Create Profile
- **route2**: Login with an existing account. After login, you will be redirected to the main menu.Chose one of the main functions: Create Post, Comment on Post, Search User.
- **non-main function**: Logout, view news feed, upvote post, downvote post. These functions can be accessed as many times as you want without restarting the program.
1. Open the `AppStarter` file.
2. Multiple-client: since `AppStarter` file contains server, so run `ClientGUI` file for another client.


## Key Features:
- **Login and Registration**: Start by entering a username and password. You have the option to either log in with these credentials or register a new account. Registration will redirect you to a profile creation page. Note: The username on the profile registration page must match the one used on the login screen.
- **Profile Creation**: Choose from two avatar pictures for extra credit: "boilermaker" or "cute squirrel". Once registration is complete, the system will redirect back to the login page with the username and password fields pre-filled, ready for you to log in.
- **News Feed**: Displays posts and their comments. If no posts are available, the feed will be empty until you create a post.
- **Create and Comment on Posts**: Create new posts and comment on existing ones. Note: Due to client-server communication, posts and comments may not appear immediately and may require restarting the program to view.
- **Search Users**: Search for users by username to view their avatar and personal information.
- **Voting**: The infrastructure for upvoting and downvoting posts is prepared for future enhancements.
- **Logout**: Allows users to switch accounts without closing the program.
- **TestCode**: For each class, we have included a test class to ensure the correct functionality of the methods.
- **Interface** For each class, we have included an interface to ensure the correct structure of the methods.

## Important Notes:
- **Running the Application**: Execute the `AppStarter` file to launch the application. After opening, you will be directed to the login page.

## Getting Started:
1. **Run the App**: Open the `AppStarter` file to start.
2. **Login/Register**: If you are a new user, fill in your credentials and click on register, follow the prompts to set up your profile, then log in with the new credentials. If you are a returning user, log in directly with your existing credentials.
3. **Navigate the Features**: Use the various features from the main page:
    - **View News Feed**: See the latest posts and comments.
    - **Create Post**: Add new content.
    - **Comment on Post**: Engage with others by commenting on their posts.
    - **Search User**: Find other users and view their profiles.
    - **Logout**: Safely switch between different user accounts.

The following sections provide more detailed information on the instruction of each class

### AppStarter

`AppStarter` is the entry point of our application. It is responsible for starting the server and the client GUI.

Here is a brief overview of the methods in the `AppStarter` class:

- `main(String[] args)`: This is the main method that gets called when you run the `AppStarter` class. It starts the server and the client GUI. If there are any issues while starting the server or the client GUI, it will print an error message and the stack trace to the console.

When you run the `AppStarter` class, it first tries to start the server. If the server starts successfully, it prints "Server started." to the console. If there is an error while starting the server, it prints the error message and the stack trace.

After starting the server, `AppStarter` tries to start the client GUI. If the client GUI starts successfully, it prints "Client GUI started." to the console. If there is an error while starting the client GUI, it prints the error message and the stack trace.

The main goal of the `AppStarter` class is to ensure that the server and client GUI can run simultaneously so that users can start using the application.

### AppStarterTest

`AppStarterTest` is the test class for our application's entry point, `AppStarter`. It is responsible for ensuring the correct functionality of starting the server and the client GUI.

Here is a brief overview of the methods in the `AppStarterTest` class:

- `setUp()`: This method is called before each test. It sets up the testing environment and initializes the `AppStarter` instance.

- `tearDown()`: This method is called after each test. It cleans up the testing environment and resets the `AppStarter` instance.

- `testMain()`: This method tests the `main` method of the `AppStarter` class. It checks whether the server and client GUI start successfully.

The main goal of the `AppStarterTest` class is to ensure that the `AppStarter` class functions as expected, allowing the server and client GUI to run simultaneously so that users can start using the application.

### Client

`Client` is a crucial part of our application. It is responsible for handling the client-side operations.

Here is a brief overview of the methods in the `Client` class:

- `main(String[] args)`: This is the main method that gets called when you run the `Client` class. It establishes a connection to the server and displays the login menu.

- `showMenu()`: This method displays the login menu and handles the user's choice.

- `handleChoice(String choice)`: This method handles the user's choice in the login menu.

- `register()`: This method handles the registration process.

- `login()`: This method handles the login process.

- `handleSearchUserResponse(String response)`: This method handles the response from the server when searching for a user.

- `showSocialMediaMenu()`: This method displays the social media menu and handles the user's choice.

- `handleSocialMediaChoice(String choice)`: This method handles the user's choice in the social media menu.

- `upvotePost()`: This method handles the process of upvoting a post.

- `downvotePost()`: This method handles the process of downvoting a post.

- `searchUser()`: This method handles the process of searching for a user.

- `commentOnPost()`: This method handles the process of commenting on a post.

- `handleLogout()`: This method handles the logout process.

- `createPost()`: This method handles the process of creating a post.

- `createProfile(String username)`: This method handles the process of creating a profile.

- `viewNewsFeed()`: This method handles the process of viewing the news feed.


### ClientGUI

`ClientGUI` is a crucial part of our application. It is responsible for handling the client-side operations and providing a graphical user interface for the client.

Here is a brief overview of the methods in the `ClientGUI` class:

- `main(String[] args)`: This is the main method that gets called when you run the `ClientGUI` class. It creates a new instance of `ClientGUI`.

- `ClientGUI()`: This is the constructor of the `ClientGUI` class. It initializes the user interface and connects to the server.

- `connectToServer()`: This method establishes a connection to the server.

- `initializeUI()`: This method sets up the initial user interface for login.

- `loginAction(ActionEvent e)`: This method handles the login process when the login button is clicked.

- `registerAction(ActionEvent e)`: This method handles the registration process when the register button is clicked.

- `showSocialMediaMenu()`: This method sets up the user interface for the social media menu after a successful login.

- `removeTrailingNewline(String filename)`: This method removes trailing newlines from a file.

- `createPostGUI()`: This method sets up the user interface for creating a post.

- `commentOnPost()`: This method handles the process of commenting on a post.

- `createProfileGUI()`: This method sets up the user interface for creating a profile.

- `searchUser()`: This method handles the process of searching for a user.

- `upvotePost()`: This method handles the process of upvoting a post.

- `downvotePost()`: This method handles the process of downvoting a post.

- `logout()`: This method handles the logout process and resets the user interface to the login screen.

- `viewNewsFeed()`: This method sets up the user interface for viewing the news feed.

- `updateNewsFeed(String postDetails)`: This method updates the news feed with new post details.

### ClientHandler

`ClientHandler` is a key class in our application. It is responsible for handling client requests and managing interactions with the server.

Here is a brief overview of the methods in the `ClientHandler` class:

- `ClientHandler(Socket clientSocket, List<User> users, List<NewsFeed> newsFeeds)`: This is the constructor of the `ClientHandler` class. It initializes the client socket, users list, news feeds list, and sets up the input and output streams for the client.

- `run()`: This method is called when a new thread for the client is started. It continuously reads requests from the client and sends responses until the client closes the connection.

- `handleRequest(String request)`: This method handles the client's request based on the request type. It calls the appropriate method to handle the request and returns the response.

- `handleRegistration()`: This method handles the registration process. It reads the username and password from the client, checks if the username already exists, and if not, adds the new user to the users list.

- `handleLogin()`: This method handles the login process. It reads the username and password from the client, checks if the user credentials are valid, and returns a response indicating whether the login was successful.

- `handleLogout()`: This method handles the logout process and returns a response indicating that the logout was successful.

- `handleViewNewsFeed()`: This method handles the request to view the news feed. It reads all the posts from the posts file and returns them as a string.

- `handleCreatePost()`: This method handles the post creation process. It reads the post details from the client, creates a new post, and saves it to the posts file.

- `handleCreateProfile(String username)`: This method handles the profile creation process. It reads the profile details from the client, creates a new profile, and saves it to a profile file.

- `handleUpvotePost()`: This method handles the upvote post process. It reads the post title from the client, finds the post in the posts file, increments the upvotes count, and updates the post in the posts file.

- `handleDownvotePost()`: This method handles the downvote post process. It reads the post title from the client, finds the post in the posts file, increments the downvotes count, and updates the post in the posts file.

- `handleCommentOnPost()`: This method handles the comment on post process. It reads the post title and comment from the client, finds the post in the posts file, adds the comment to the post, and updates the post in the posts file.

- `handleSearchUser(String usernameToSearch)`: This method handles the search user process. It checks if a profile file exists for the searched username, and if so, reads the profile details from the profile file and returns them as a string.

Please note that the actual functionality of these methods may vary depending on the implementation details of your `ClientHandler` class.

### ClientHandlerInterface

`ClientHandlerInterface` is an interface in our application. It defines the contract for handling client requests in the `ClientHandler` class.

Here is a brief overview of the method in the `ClientHandlerInterface`:

- `handleRequest(String command)`: This method is declared to handle the client's request based on the command type. It throws an IOException if an input or output exception occurred.


### ClientInterface

`ClientInterface` is an interface in our application. It defines the basic structure for the `Client` class in our application.

Here is a brief overview of the methods in the `ClientInterface`:

- `run()`: This method is declared to be implemented in any class that implements `ClientInterface`. It is intended to contain the main logic of the client-side application.

- `main(String[] args)`: This is the main method that gets called when you run the class that implements `ClientInterface`. It is intended to create an instance of the client class and start the client-side application.

### Comment

`Comment` is a class in our application that extends the `Post` class and implements the `CommentRequirements` interface. It represents a comment made by a user on a post.

Here is a brief overview of the methods in the `Comment` class:

- `Comment(Post post, String content, String author, boolean hidden, int upvotes, int downvotes)`: This is the constructor of the `Comment` class. It initializes the comment with the given post, content, author, hidden status, upvotes, and downvotes. It throws a `CommentIncompleteException` if the content or author is empty, and a `PostIncompleteException` if the post details are incomplete.

- `upvote()`: This method increments the upvotes count of the comment.

- `downvote()`: This method increments the downvotes count of the comment.

- `hide()`: This method sets the hidden status of the comment to true.

- `show()`: This method sets the hidden status of the comment to false.

- `toString()`: This method returns a string representation of the comment, including the author, content, upvotes, and downvotes.

- `getContent()`, `getAuthor()`, `isHidden()`, `getUpvotes()`, `getDownvotes()`, `getPost()`: These are getter methods that return the content, author, hidden status, upvotes, downvotes, and post of the comment, respectively.

- `setContent(String content)`, `setAuthor(String author)`, `setHidden(boolean hidden)`, `setUpvotes(int upvotes)`, `setDownvotes(int downvotes)`, `setPost(Post post)`: These are setter methods that set the content, author, hidden status, upvotes, downvotes, and post of the comment, respectively.


### CommentIncompleteException

`CommentIncompleteException` is a custom exception class in our application. It is thrown when a comment is being created without the necessary information.

Here is a brief overview of the methods in the `CommentIncompleteException` class:

- `CommentIncompleteException(String message)`: This is the constructor of the `CommentIncompleteException` class. It initializes the exception with a custom message that describes why the exception was thrown.

- `getMessage()`: This method returns the detail message string of this throwable.
### CommentRequirements

`CommentRequirements` is an interface in our application. It defines the basic structure for the `Comment` class in our application.

Here is a brief overview of the methods in the `CommentRequirements` interface:

- `upvote()`: This method is declared to be implemented in any class that implements `CommentRequirements`. It is intended to increment the upvotes count of a comment.

- `downvote()`: This method is declared to be implemented in any class that implements `CommentRequirements`. It is intended to increment the downvotes count of a comment.

- `hide()`: This method is declared to be implemented in any class that implements `CommentRequirements`. It is intended to set the hidden status of a comment to true.

- `show()`: This method is declared to be implemented in any class that implements `CommentRequirements`. It is intended to set the hidden status of a comment to false.

### CommentTest

`CommentTest` is a test class in our application. It contains unit tests for the `Comment` class.

Here is a brief overview of the methods in the `CommentTest` class:

- `testUpvote()`: This test method checks if the `upvote()` method in the `Comment` class correctly increments the upvotes count of a comment.

- `testDownvote()`: This test method checks if the `downvote()` method in the `Comment` class correctly increments the downvotes count of a comment.

- `testHide()`: This test method checks if the `hide()` method in the `Comment` class correctly sets the hidden status of a comment to true.

- `testShow()`: This test method checks if the `show()` method in the `Comment` class correctly sets the hidden status of a comment to false.

- `testSetContent()`: This test method checks if the `setContent(String content)` method in the `Comment` class correctly sets the content of a comment.

- `testSetAuthor()`: This test method checks if the `setAuthor(String author)` method in the `Comment` class correctly sets the author of a comment.

- `testSetHidden()`: This test method checks if the `setHidden(boolean hidden)` method in the `Comment` class correctly sets the hidden status of a comment.

- `testSetUpvotes()`: This test method checks if the `setUpvotes(int upvotes)` method in the `Comment` class correctly sets the upvotes count of a comment.

- `testSetDownvotes()`: This test method checks if the `setDownvotes(int downvotes)` method in the `Comment` class correctly sets the downvotes count of a comment.

- `testSetPost()`: This test method checks if the `setPost(Post post)` method in the `Comment` class correctly sets the post of a comment.

### LongRunningTask

`LongRunningTask` is a class in our application that represents a task that takes a long time to complete. It is typically used for operations that are performed in the background, such as downloading a large file or performing a complex calculation.

Here is a brief overview of the methods in the `LongRunningTask` class:

- `LongRunningTask()`: This is the constructor of the `LongRunningTask` class. It initializes the task with the necessary parameters.

- `run()`: This method is called to start the execution of the long-running task. It contains the main logic of the task.

- `cancel()`: This method is called to cancel the execution of the long-running task. It sets a cancellation flag that the `run()` method checks periodically to see if it should stop executing.

- `isCancelled()`: This method returns a boolean indicating whether the task has been cancelled.

- `onCompletion()`: This method is called when the task has completed its execution. It can be overridden in subclasses to provide custom behavior when the task is done.

- `onCancellation()`: This method is called when the task has been cancelled. It can be overridden in subclasses to provide custom behavior when the task is cancelled.

- `onError(Exception e)`: This method is called when an error occurs during the execution of the task. It can be overridden in subclasses to provide custom error handling.

### NewsFeed

`NewsFeed` is a class in our application that represents the news feed where posts are displayed.

Here is a brief overview of the methods in the `NewsFeed` class:

- `NewsFeed()`: This is the constructor of the `NewsFeed` class. It initializes the news feed with an empty list of posts.

- `addPost(Post post)`: This method adds a new post to the news feed. It takes a `Post` object as a parameter and adds it to the list of posts.

- `removePost(Post post)`: This method removes a post from the news feed. It takes a `Post` object as a parameter and removes it from the list of posts.

- `getPosts()`: This method returns the list of posts in the news feed.

- `updatePost(Post oldPost, Post newPost)`: This method updates a post in the news feed. It takes two `Post` objects as parameters: the old post to be replaced and the new post to replace it with.

- `sortPostsByDate()`: This method sorts the posts in the news feed by date, with the most recent posts appearing first.

- `sortPostsByUpvotes()`: This method sorts the posts in the news feed by the number of upvotes, with the posts having the most upvotes appearing first.

### NewsFeedManager

`NewsFeedManager` is a class in our application that manages the retrieval of posts for the news feed.

Here is a brief overview of the methods in the `NewsFeedManager` class:

- `getAllPosts()`: This static method reads from a file named `posts.txt` and returns a list of `Post` objects. Each line in the file is converted to a `Post` object and added to the list. If an error occurs during the reading of the file or the creation of a `Post` object, the error is printed to the console.

- `stringToPost(String line)`: This private static method takes a string as a parameter and returns a `Post` object. The string is expected to be in the format `title;content;author;imageURL;upvotes;downvotes;hidden`. If the string does not have the correct format, a `PostIncompleteException` is thrown.

### NewsFeedManagerTest

`NewsFeedManagerTest` is a test class in our application. It contains unit tests for the `NewsFeedManager` class.

Here is a brief overview of the methods in the `NewsFeedManagerTest` class:

- `testGetAllPosts()`: This test method checks if the `getAllPosts()` method in the `NewsFeedManager` class correctly retrieves all posts. It asserts that the returned list of posts is not empty and that the properties of the first post in the list are not null.

### NewsFeedTest

`NewsFeedTest` is a test class in our application. It contains unit tests for the `NewsFeed` class.

Here is a brief overview of the methods in the `NewsFeedTest` class:

- `testAddPost()`: This test method checks if the `addPost(Post post)` method in the `NewsFeed` class correctly adds a new post to the news feed.

- `testRemovePost()`: This test method checks if the `removePost(Post post)` method in the `NewsFeed` class correctly removes a post from the news feed.

- `testGetPosts()`: This test method checks if the `getPosts()` method in the `NewsFeed` class correctly returns the list of posts in the news feed.

- `testUpdatePost()`: This test method checks if the `updatePost(Post oldPost, Post newPost)` method in the `NewsFeed` class correctly updates a post in the news feed.

- `testSortPostsByDate()`: This test method checks if the `sortPostsByDate()` method in the `NewsFeed` class correctly sorts the posts in the news feed by date, with the most recent posts appearing first.

- `testSortPostsByUpvotes()`: This test method checks if the `sortPostsByUpvotes()` method in the `NewsFeed` class correctly sorts the posts in the news feed by the number of upvotes, with the posts having the most upvotes appearing first.

### Post

`Post` is a class in our application that represents a post made by a user.

Here is a brief overview of the methods in the `Post` class:

- `Post(String title, String content, String author, boolean hidden, String imageURL, int upvotes, int downvotes)`: This is the constructor of the `Post` class. It initializes the post with the given title, content, author, hidden status, image URL, upvotes, and downvotes.

- `getTitle()`: This method returns the title of the post.

- `getContent()`: This method returns the content of the post.

- `getAuthor()`: This method returns the author of the post.

- `isHidden()`: This method returns a boolean indicating whether the post is hidden.

- `getImageURL()`: This method returns the image URL of the post.

- `getUpvotes()`: This method returns the number of upvotes of the post.

- `getDownvotes()`: This method returns the number of downvotes of the post.

- `setTitle(String title)`: This method sets the title of the post.

- `setContent(String content)`: This method sets the content of the post.

- `setAuthor(String author)`: This method sets the author of the post.

- `setHidden(boolean hidden)`: This method sets the hidden status of the post.

- `setImageURL(String imageURL)`: This method sets the image URL of the post.

- `setUpvotes(int upvotes)`: This method sets the number of upvotes of the post.

- `setDownvotes(int downvotes)`: This method sets the number of downvotes of the post.

### PostFileManager

`PostFileManager` is a class in our application that manages the file operations related to `Post` objects.

Here is a brief overview of the methods in the `PostFileManager` class:

- `PostFileManager()`: This is the constructor of the `PostFileManager` class. It initializes the file manager with the necessary parameters.

- `savePost(Post post)`: This method saves a `Post` object to a file. It takes a `Post` object as a parameter and writes it to a file in a specific format.

- `loadPosts()`: This method loads all `Post` objects from a file. It reads from a file, converts each line to a `Post` object, and returns a list of `Post` objects.

- `deletePost(Post post)`: This method deletes a `Post` object from a file. It takes a `Post` object as a parameter, finds it in the file, and removes it.

- `updatePost(Post oldPost, Post newPost)`: This method updates a `Post` object in a file. It takes two `Post` objects as parameters: the old post to be replaced and the new post to replace it with.

### PostFileManagerTest

`PostFileManagerTest` is a test class in our application. It contains unit tests for the `PostFileManager` class.

Here is a brief overview of the methods in the `PostFileManagerTest` class:

- `testSavePost()`: This test method checks if the `savePost(Post post)` method in the `PostFileManager` class correctly saves a `Post` object to a file.

- `testLoadPosts()`: This test method checks if the `loadPosts()` method in the `PostFileManager` class correctly loads all `Post` objects from a file.

- `testDeletePost()`: This test method checks if the `deletePost(Post post)` method in the `PostFileManager` class correctly deletes a `Post` object from a file.

- `testUpdatePost()`: This test method checks if the `updatePost(Post oldPost, Post newPost)` method in the `PostFileManager` class correctly updates a `Post` object in a file.

### PostTest

`PostTest` is a test class in our application. It contains unit tests for the `Post` class.

Here is a brief overview of the methods in the `PostTest` class:

- `testSetTitle()`: This test method checks if the `setTitle(String title)` method in the `Post` class correctly sets the title of a post.

- `testSetContent()`: This test method checks if the `setContent(String content)` method in the `Post` class correctly sets the content of a post.

- `testSetAuthor()`: This test method checks if the `setAuthor(String author)` method in the `Post` class correctly sets the author of a post.

- `testSetHidden()`: This test method checks if the `setHidden(boolean hidden)` method in the `Post` class correctly sets the hidden status of a post.

- `testSetImageURL()`: This test method checks if the `setImageURL(String imageURL)` method in the `Post` class correctly sets the image URL of a post.

- `testSetUpvotes()`: This test method checks if the `setUpvotes(int upvotes)` method in the `Post` class correctly sets the number of upvotes of a post.

- `testSetDownvotes()`: This test method checks if the `setDownvotes(int downvotes)` method in the `Post` class correctly sets the number of downvotes of a post.

### Profile

`Profile` is a class in our application that represents a user's profile.

Here is a brief overview of the methods in the `Profile` class:

- `Profile(String username, String avatar, String personalInformation)`: This is the constructor of the `Profile` class. It initializes the profile with the given username, avatar, and personal information.

- `getUsername()`: This method returns the username of the profile.

- `getAvatar()`: This method returns the avatar of the profile.

- `getPersonalInformation()`: This method returns the personal information of the profile.

- `setUsername(String username)`: This method sets the username of the profile.

- `setAvatar(String avatar)`: This method sets the avatar of the profile.

- `setPersonalInformation(String personalInformation)`: This method sets the personal information of the profile.

### ProfileTest

`ProfileTest` is a test class in our application. It contains unit tests for the `Profile` class.

Here is a brief overview of the methods in the `ProfileTest` class:

- `testSetUsername()`: This test method checks if the `setUsername(String username)` method in the `Profile` class correctly sets the username of a profile.

- `testSetAvatar()`: This test method checks if the `setAvatar(String avatar)` method in the `Profile` class correctly sets the avatar of a profile.

- `testSetPersonalInformation()`: This test method checks if the `setPersonalInformation(String personalInformation)` method in the `Profile` class correctly sets the personal information of a profile.

### Server

`Server` is a class in our application that manages the server-side operations.

Here is a brief overview of the methods in the `Server` class:

- `Server()`: This is the constructor of the `Server` class. It initializes the server with the necessary parameters and creates an initial `NewsFeed` instance.

- `run()`: This method starts the server. It sets up the server socket and starts listening for client connections. It also adds any initial credentials and creates a `ClientHandler` for each client connection.

- `main(String[] args)`: This is the main method that starts the server. It creates an instance of the `Server` class and starts a new thread for it.

### User

`User` is a class in our application that represents a user in the system.

Here is a brief overview of the methods in the `User` class:

- `User(String username, String password)`: This is the constructor of the `User` class. It initializes the user with the given username and password.

- `getUsername()`: This method returns the username of the user.

- `getPassword()`: This method returns the password of the user.

- `getFollowing()`: This method returns a list of users that this user is following.

- `addFollowing(User user)`: This method adds a user to the list of users that this user is following.

- `setName(String newValue)`: This method sets the username of the user.

- `removeFollowing(User connection)`: This method removes a user from the list of users that this user is following.

- `getProfile()`: This method returns the profile of the user.

- `setProfile(Profile profile)`: This method sets the profile of the user.

- `addFollower(User follower)`: This method adds a follower to this user. It does this by adding this user to the follower's list of following users.

### UserCredentials

`UserCredentials` is a class in our application that manages the user credentials.

Here is a brief overview of the methods in the `UserCredentials` class:

- `isValidCredentials(String username, String password)`: This static method checks if the provided username and password match the stored credentials. It returns true if the credentials are valid, and false otherwise.

- `addCredentials(String username, String password)`: This static method adds a new set of credentials to the stored credentials. It takes a username and password as parameters.

- `loadCredentialsFromFile()`: This static method loads the stored credentials from a file. It is called when the class is loaded.

- `saveCredentialsToFile()`: This private static method saves the current credentials to a file. It is called whenever a new set of credentials is added.

### UserTest

`UserTest` is a test class in our application. It contains unit tests for the `User` class.

Here is a brief overview of the methods in the `UserTest` class:

- `testGetUsername()`: This test method checks if the `getUsername()` method in the `User` class correctly returns the username of a user.

- `testGetPassword()`: This test method checks if the `getPassword()` method in the `User` class correctly returns the password of a user.

- `testGetFollowing()`: This test method checks if the `getFollowing()` method in the `User` class correctly returns a list of users that this user is following.

- `testAddFollowing()`: This test method checks if the `addFollowing(User user)` method in the `User` class correctly adds a user to the list of users that this user is following.

- `testSetName()`: This test method checks if the `setName(String newValue)` method in the `User` class correctly sets the username of a user.

- `testRemoveFollowing()`: This test method checks if the `removeFollowing(User connection)` method in the `User` class correctly removes a user from the list of users that this user is following.

- `testGetProfile()`: This test method checks if the `getProfile()` method in the `User` class correctly returns the profile of a user.

- `testSetProfile()`: This test method checks if the `setProfile(Profile profile)` method in the `User` class correctly sets the profile of a user.

- `testAddFollower()`: This test method checks if the `addFollower(User follower)` method in the `User` class correctly adds a follower to this user.

Please replace the placeholders with the actual details of your `UserTest` class.# LinkedGram
