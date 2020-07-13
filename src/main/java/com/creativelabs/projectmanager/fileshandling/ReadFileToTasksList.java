package com.creativelabs.projectmanager.fileshandling;

import com.creativelabs.projectmanager.tasks.Task;
import com.creativelabs.projectmanager.tasks.TaskList;
import com.creativelabs.projectmanager.tasks.User;
import com.creativelabs.projectmanager.tasks.UserList;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class ReadFileToTasksList {

    public TaskList fileToList (File obj) {

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

            String id = data.substring(0, index);
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
}
