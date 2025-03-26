package Project.Utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConfig {
    private static boolean initialized = false;

    public static void initializeFirebase() {
        if (initialized) return; // Prevent multiple initializations
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase-config.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://your-project-id.firebaseio.com") // Replace with your database URL
                    .build();

            FirebaseApp.initializeApp(options);
            initialized = true;
            System.out.println("Firebase Initialized Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize Firebase!");
        }
    }
}