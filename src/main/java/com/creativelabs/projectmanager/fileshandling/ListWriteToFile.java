package com.creativelabs.projectmanager.fileshandling;

import com.creativelabs.projectmanager.tasks.User;
import com.creativelabs.projectmanager.tasks.UserList;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.List;
import java.util.stream.Collectors;

public class ListWriteToFile {

    public void writeToFile(UserList list){
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/files/userlist1.txt");

            List<String> temporaryList = list.getUsersList().stream()
                    .map(s -> s.getUsername())
                    .collect(Collectors.toList());
            for (String userName : temporaryList) {
                myWriter.write(userName + "\n");
            }

            //myWriter.write("John\n");
            //myWriter.write("Andy\n");

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
