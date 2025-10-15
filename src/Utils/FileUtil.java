package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    
    /**
     * Saves a list of objects to a file using serialization
     */
    public static <T> void saveToFile(List<T> objects, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(objects);
        }
    }
    
    /**
     * Loads a list of objects from a file using deserialization
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> loadFromFile(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<T>) ois.readObject();
        }
    }
    
    /**
     * Saves text content to a file
     */
    public static void saveTextToFile(String content, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(content);
        }
    }
    
    /**
     * Loads text content from a file
     */
    public static String loadTextFromFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            return "";
        }
        
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    
    /**
     * Creates directory if it doesn't exist
     */
    public static void ensureDirectoryExists(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    
    /**
     * Checks if file exists
     */
    public static boolean fileExists(String filename) {
        return new File(filename).exists();
    }
}
