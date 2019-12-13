/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandling;

/**
 *
 * @author AmocRo_Sd2081
 */
import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class StudentsLogger {

    public static void main(String[] args) {
        System.out.println("Welcome! What's your name?");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine(); // getting name of user to append later to log file

        try {
            File file = getFile();

            // WRITING
            if (file.canWrite()) {
//                FileWriter fw = new FileWriter(file); // this FileWriter will re-write the content of the file at each run
                FileWriter fw = new FileWriter(file, true); // this FileWriter will append to the end of the file at each run
                fw.write(name + " logged in at " + new Date().toString() + "\n");
                fw.close(); // don't forget to close, else nothing will be written!
            }

            // READING
            if (file.canRead()) {
                System.out.println("Current log:");
                Scanner reader = new Scanner(file); // we use a Scanner same as for the console
                while (reader.hasNextLine()) { // checking if there is still lines
                    String line = reader.nextLine(); // retrieving the next line
                    System.out.println(line);
                }
                reader.close();
            }

            // ANOTHER METHOD TO READ
            FileReader fileReader = new FileReader(file);
            int i;
            while ((i = fileReader.read()) != -1) { // the fileRead.read() returns -1 when he reached the end of the file
//                System.out.print(i); // this does not work. Try it!
                System.out.print((char) i); // you need to convert the int to char
            }
            fileReader.close();

            // DELETING
            // commenting this one as we want to append to the log file at every run of the program
//            if (file.delete()){
//                System.out.println("Deleted successfully");
//            } else {
//                System.out.println("could not delete");
//            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static File getFile() throws IOException {
        File file = new File("log.txt"); // this one will create the file at the root of the project
//        File file = new File(".\\src\\log.txt"); // for a relative path inside the project folder
//        File file = new File("C:\\...\\log.txt"); // for absolute path on computer

        // CREATING
        if (file.createNewFile()) {
            // that's where the file is actually created!
            System.out.println("File " + file.getName() + " created!");
        } else {
            // or not because it already exists
            System.out.println("File " + file.getName() + " already exists in " + file.getAbsolutePath());
        }
        return file;
    }
}