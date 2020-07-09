package com.creativelabs.projectmanager.fileshandling;

import com.creativelabs.projectmanager.tasks.User;
import com.creativelabs.projectmanager.tasks.UserList;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    public static void main(String[] args) {
        try {
            File myObj = new File("src/main/resources/files/userlist1.txt");
            Scanner myReader = new Scanner(myObj);
            UserList userList1 = new UserList("Team");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                User user = new User(data);
                userList1.addUser(user);
                System.out.println(userList1);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

