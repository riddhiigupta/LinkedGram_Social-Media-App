import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserCredentials {
    private static final String FILE_NAME = "user_credentials.txt";
    private static Map<String, String> credentials = new HashMap<>();

    static {
        try {
            loadCredentialsFromFile();
        } catch (IOException e) {
            System.err.println("Error loading user credentials from file: " + e.getMessage());
        }
    }

    public static boolean isValidCredentials(String username, String password) {
        String storedPassword = credentials.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    public static void addCredentials(String username, String password) {
        credentials.put(username, password);
        saveCredentialsToFile();
    }

    static void loadCredentialsFromFile() throws IOException {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    credentials.put(parts[0], parts[1]);
                }
            }
            reader.close();
        }
    }

    private static void saveCredentialsToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            for (Map.Entry<String, String> entry : credentials.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving user credentials to file: " + e.getMessage());
        }
    }
}