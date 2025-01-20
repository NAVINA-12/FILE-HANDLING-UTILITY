import java.io.*;
import java.util.Scanner;

public class FileHandlingUtility {

    public static void readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("File content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("Content written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void modifyFile(String filePath, String modifier) {
        File inputFile = new File(filePath);
        File tempFile = new File("tempFile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + modifier);
                writer.newLine();
            }
            System.out.println("File modified successfully.");

        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
            return;
        }

        // Safely replace original file
        if (!inputFile.delete()) {
            System.out.println("Failed to delete the original file. Check permissions.");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Failed to rename the temp file. Check file system restrictions.");
        } else {
            System.out.println("File updated successfully.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("File Handling Utility");
        System.out.println("1. Read a File");
        System.out.println("2. Write to a File");
        System.out.println("3. Modify a File");
        System.out.print("Choose an option (1/2/3): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        switch (choice) {
            case 1:
                readFile(filePath);
                break;
            case 2:
                System.out.print("Enter content to write: ");
                String content = scanner.nextLine();
                writeFile(filePath, content);
                break;
            case 3:
                System.out.print("Enter text to append as modification: ");
                String modifier = scanner.nextLine();
                modifyFile(filePath, modifier);
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }
}
