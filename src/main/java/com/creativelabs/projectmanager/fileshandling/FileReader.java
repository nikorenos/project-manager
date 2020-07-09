package com.creativelabs.projectmanager.fileshandling;

import com.creativelabs.projectmanager.tasks.User;
import com.creativelabs.projectmanager.tasks.UserList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

    public void readFile() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("files/project0.txt").getFile());

        Stream<String> fileLines = Files.lines(Paths.get(file.getPath()));
        fileLines.forEach(System.out::println);

    }

    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader();
        fileReader.readFile();

        UserList userList1 = new UserList("Team");

        for (int i = 0; i <5; i++ ) {
            User user = new User("developer3");
            userList1.addUser(user);
        }
        System.out.println(userList1);

    }
}
