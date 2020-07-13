package com.creativelabs.projectmanager.fileshandling;

import com.creativelabs.projectmanager.tasks.User;
import com.creativelabs.projectmanager.tasks.UserList;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFileToUsersList {

    public UserList fileToList (File obj) {

        Scanner myReader = null;
        UserList userList1 = new UserList("User list");
        try {
            myReader = new Scanner(obj);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String findStr = " ";
            int index = data.indexOf(findStr);

            String nick = data.substring(0, index);
            data = data.substring(index +1, data.length());

            String password = data.substring(0, data.indexOf(findStr));
            String email = data.substring(data.indexOf(findStr) +1, data.length());
            User user = new User(nick,password,email);
            userList1.addUser(user);
                /*System.out.println(nick);
                System.out.println(password);
                System.out.println(email);*/
        }
        myReader.close();

    return userList1;
    }

}

