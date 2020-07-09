package com.creativelabs.projectmanager.fileshandling;

import com.creativelabs.projectmanager.tasks.User;
import com.creativelabs.projectmanager.tasks.UserList;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class ListWriteToFile {

    public void listWriteToFile(UserList list){
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/files/userlist1.txt");

            for (int n = 0; n < list.getUsersList().size(); n++) {
                //myWriter.write(list[n] + "\n");
            }
            myWriter.write("John\n");
            myWriter.write("Andy\n");
            //myWriter.write("Andy");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/files/userlist1.txt");
            myWriter.write("John\n");
            myWriter.write("Andy\n");
            //myWriter.write("Andy");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
