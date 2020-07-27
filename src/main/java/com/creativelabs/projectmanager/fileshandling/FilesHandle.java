package com.creativelabs.projectmanager.fileshandling;

import com.creativelabs.projectmanager.Manager;
import com.creativelabs.projectmanager.table.TaskInTable;
import com.creativelabs.projectmanager.table.UserInTable;
import com.creativelabs.projectmanager.tasks.Task;
import com.creativelabs.projectmanager.tasks.TaskList;
import com.creativelabs.projectmanager.tasks.User;
import com.creativelabs.projectmanager.tasks.UserList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Hyperlink;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
            writeProjectData.write(projectPath + "\n");
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
        System.out.println("fileToTasksList");

        Scanner myReader = null;
        TaskList taskList1 = new TaskList("Task list 1");
        try {
            myReader = new Scanner(obj);
        } catch (FileNotFoundException e) {
            System.out.println("An error fileToTasksList.");
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
        System.out.println("fileToUsersList");

        Scanner myReader = null;
        UserList userList1 = new UserList("User list");
        try {
            myReader = new Scanner(obj);
        } catch (FileNotFoundException e) {
            System.out.println("An error fileToUsersList.");
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


    public void usersWriteToFile(UserList list) {
        System.out.println("usersWriteToFile");
        Manager manager = new Manager();
        try {
            FileWriter myWriter = new FileWriter(manager.getProjectPath() +  "/" + manager.getProjectName() + "_userslist.txt");

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

    public void tasksWriteToFile(TaskList list, String projectName, String projectPath){
        System.out.println("tasksWriteToFile");
        try {
            FileWriter myWriter = new FileWriter(projectPath +  "/" + projectName +  "_taskslist.txt");

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
            System.out.println("Successfully wrote to the file." + projectPath +  "/" + projectName +  "_taskslist.txt");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public ObservableList<String> convertUsersListToString(UserList list) {
        ObservableList<String> data = FXCollections.observableArrayList();
        System.out.println("convertUsersListToString");
        for(int n = 0; n < list.getUsersList().size(); n++) {
            String nick = list.getUsersList().get(n).getUsername();
            data.add(nick);
        }
        return data;
    }

    public ArrayList<String> convertTaskTypeToString(TaskList list) {
        ArrayList<String> data = new ArrayList<>();
        System.out.println("convertTaskTypeToString");
        for(int n = 0; n < list.getTasks().size(); n++) {
            String type = list.getTasks().get(n).getType();
            data.add(type);
        }
        return data;
    }

    public ObservableList<UserInTable> convertUsersListToObservable(UserList list) {
        ObservableList<UserInTable> data = FXCollections.observableArrayList();
        System.out.println("convertUsersListToObservable");
        for(int n = 0; n < list.getUsersList().size(); n++) {
            String nick = list.getUsersList().get(n).getUsername();
            String password = list.getUsersList().get(n).getPassword();
            String email = list.getUsersList().get(n).getEmail();
            data.add(new UserInTable(nick, password, email));
        }
        return data;
    }
    public ObservableList<TaskInTable> convertTasksListToObservable(TaskList list) {
        ObservableList<TaskInTable> data = FXCollections.observableArrayList();
        System.out.println("convertTasksListToObservable");
        for(int n = 0; n < list.getTasks().size(); n++) {
            String id = String.valueOf(list.getTasks().get(n).getId());
            if (list.getTasks().get(n).getId() < 10) {
                id = "00" + id;
            } else if ((list.getTasks().get(n).getId() >= 10) && (list.getTasks().get(n).getId() < 100)) {
                id = "0" + id;
            }
            Hyperlink hyperlink = new Hyperlink();
            String title = list.getTasks().get(n).getTitle();
            String type = list.getTasks().get(n).getType();
            String status = list.getTasks().get(n).getStatus();
            String assignee = list.getTasks().get(n).getAssignedUser();
            String creator = list.getTasks().get(n).getCreator();
            String created = String.valueOf(list.getTasks().get(n).getCreated());
            String deadline = String.valueOf(list.getTasks().get(n).getDeadline());
            data.add(new TaskInTable(id,hyperlink, title, type,status,assignee,creator,created,deadline));
        }
        return data;
    }
}
