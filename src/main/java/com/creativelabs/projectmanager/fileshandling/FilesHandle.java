package com.creativelabs.projectmanager.fileshandling;

import com.creativelabs.projectmanager.tasks.Task;
import com.creativelabs.projectmanager.tasks.TaskList;
import com.creativelabs.projectmanager.tasks.User;
import com.creativelabs.projectmanager.tasks.UserList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FilesHandle {

    public void createProjectFolder(String projectName, String projectPath, User admin) {
        UserList userList = new UserList(projectName);
        String projectDataPath = projectPath +  "/" + projectName + "_projectdata.txt";
        String tasksDataPath = projectPath +  "/" + projectName + "_taskslist.txt";
        String usersDataPath = projectPath +  "/" + projectName + "_userslist.txt";
        System.out.println(projectDataPath);
        System.out.println(usersDataPath);
        System.out.println(tasksDataPath);

        try {
            //create file with users data
            FileWriter writeProjectData = new FileWriter(projectDataPath);
            //create file with users data
            writeProjectData.write(projectName + "\n");
            writeProjectData.write(projectDataPath + "\n");
            writeProjectData.write(tasksDataPath + "\n");
            writeProjectData.write(usersDataPath + "\n");
            writeProjectData.close();
            System.out.println("Project data successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error with users list occurred.");
            e.printStackTrace();
        }

        try {
            //create file with users data
            FileWriter writeUsers = new FileWriter(usersDataPath);
            //create file with users data
            writeUsers.write(admin.getUsername() + " " + admin.getPassword() + " " + admin.getEmail());
            writeUsers.close();
            System.out.println("Users data successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error with users list occurred.");
            e.printStackTrace();
        }
        try {

            //create empty file with tasks data
            FileWriter writeTasks = new FileWriter(tasksDataPath);
            writeTasks.write("");
            writeTasks.close();
            System.out.println("Tasks data successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error with tasks list occurred.");
            e.printStackTrace();
        }

    }

    public TaskList fileToTasksList (File obj) {

        Scanner myReader = null;
        TaskList taskList1 = new TaskList("Task list 1");
        try {
            myReader = new Scanner(obj);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String findStr = "#";
            int index = data.indexOf(findStr);

            Integer id = Integer.valueOf(data.substring(0, index));
            data = data.substring(index +1, data.length());

            String title = data.substring(0, data.indexOf(findStr));

            data = data.substring(data.indexOf(findStr) +1, data.length());
            String description = data.substring(0, data.indexOf(findStr));

            data = data.substring(data.indexOf(findStr) +1, data.length());
            String type = data.substring(0, data.indexOf(findStr));

            data = data.substring(data.indexOf(findStr) +1, data.length());
            String status = data.substring(0, data.indexOf(findStr));

            data = data.substring(data.indexOf(findStr) +1, data.length());
            String assignedUser = data.substring(0, data.indexOf(findStr));

            data = data.substring(data.indexOf(findStr) +1, data.length());
            String creator = data.substring(0, data.indexOf(findStr));

            data = data.substring(data.indexOf(findStr) +1, data.length());
            String created = data.substring(0, data.indexOf(findStr));

            data = data.substring(data.indexOf(findStr) +1, data.length());
            String deadline = data.substring(0, data.length());

            LocalDate date1 = LocalDate.parse(created);
            LocalDate date2 = LocalDate.parse(deadline);


            Task task = new Task(id, title, description, type, status, assignedUser, creator,date1,date2);
            taskList1.addTask(task);
                /*System.out.println(id);
                System.out.println(title);
                System.out.println(description);
                System.out.println(type);
                System.out.println(status);
                System.out.println(assignedUser);
                System.out.println(creator);
                System.out.println(created);
                System.out.println(deadline);*/
        }
        myReader.close();

        return taskList1;
    }

    public UserList fileToUsersList (File obj) {

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


    public void writeToFile(UserList list) {
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/files/userlist1.txt");

            List<String> temporaryList = list.getUsersList().stream()
                    .map(s -> s.getUsername() + " " + s.getPassword() + " " + s.getEmail())
                    .collect(Collectors.toList());
            for (String userName : temporaryList) {
                myWriter.write(userName + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToFile(TaskList list){
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/files/tasklist0.txt");

            List<String> temporaryList = list.getTasks().stream()
                    .map(s -> s.getId() + "#" + s.getTitle() +  "#" + s.getDescription() + "#"
                            + s.getType()  + "#" + s.getStatus() + "#" + s.getAssignedUser()
                            + "#" + s.getCreator() + "#" + s.getCreated() + "#" + s.getDeadline()
                    )
                    .collect(Collectors.toList());
            for (String task : temporaryList) {
                myWriter.write(task + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
