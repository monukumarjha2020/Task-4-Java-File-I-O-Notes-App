
import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Notes App ===");
            System.out.println("1. Write Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> writeNote(sc);
                case 2 -> readNotes();
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // Write note using FileWriter
    private static void writeNote(Scanner sc) {
        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) { // append = true
            fw.write(note + System.lineSeparator());
            System.out.println("Note saved!");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    // Read notes using BufferedReader
    private static void readNotes() {
        System.out.println("\n--- Your Notes ---");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean hasData = false;
            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
                hasData = true;
            }
            if (!hasData) {
                System.out.println("(No notes yet)");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found. Write some first!");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }
}
