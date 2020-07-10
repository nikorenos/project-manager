package com.creativelabs.projectmanager.tasks;

import com.creativelabs.projectmanager.fileshandling.ListWriteToFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class Board {
    private final List<TaskList> taskLists = new ArrayList<>();
    private final String name;

    public Board(final String name) {
        this.name = name;
    }

    public void addTaskList(TaskList taskList) {
        taskLists.add(taskList);
    }

    public boolean removeTaskList(TaskList taskList) {
        return taskLists.remove(taskList);
    }

    public List<TaskList> getTaskLists() {
        return new ArrayList<>(taskLists);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Board{" + "\n" +
                "name='" + name + '\'' + ",\n" +
                "taskLists=" + taskLists + "\n" +
                '}';
    }


    public static void main(String[] args) {

        //users
        User user1 = new User("developer1");
        User user2 = new User("projectmanager1");
        User user3 = new User("developer2");
        User user4 = new User("developer3");
        //tasks
        Task task1 = new Task("Microservice for taking temperature",
                "Write and test the microservice taking\n" +
                        "the temperaure from external service",
                user1,
                user2,
                LocalDate.now().minusDays(20),
                LocalDate.now().plusDays(30));
        Task task2 = new Task("HQLs for analysis",
                "Prepare some HQL queries for analysis",
                user1,
                user2,
                LocalDate.now().minusDays(20),
                LocalDate.now().minusDays(5));
        Task task3 = new Task("Temperatures entity",
                "Prepare entity for temperatures",
                user3,
                user2,
                LocalDate.now().minusDays(20),
                LocalDate.now().plusDays(15));
        Task task4 = new Task("Own logger",
                "Refactor company logger to meet our needs",
                user3,
                user2,
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(25));
        Task task5 = new Task("Optimize searching",
                "Archive data searching has to be optimized",
                user4,
                user2,
                LocalDate.now(),
                LocalDate.now().plusDays(5));
        Task task6 = new Task("Use Streams",
                "use Streams rather than for-loops in predictions",
                user4,
                user2,
                LocalDate.now().minusDays(15),
                LocalDate.now().minusDays(2));
        //taskLists
        TaskList taskListToDo = new TaskList("To do");
        taskListToDo.addTask(task1);
        taskListToDo.addTask(task3);
        TaskList taskListInProgress = new TaskList("In progress");
        taskListInProgress.addTask(task5);
        taskListInProgress.addTask(task4);
        taskListInProgress.addTask(task2);
        TaskList taskListDone = new TaskList("Done");
        taskListDone.addTask(task6);

        //board
        Board project = new Board("Project Weather Prediction");
        project.addTaskList(taskListToDo);
        project.addTaskList(taskListInProgress);
        project.addTaskList(taskListDone);

        //System.out.println(project);
        System.out.println(task1.getTitle() + " " + task1.getDescription());

        UserList userList1 = new UserList("Team");
        LinkedList<String> test = new LinkedList<>();
        test.add("hej");

        for (int i = 0; i <15; i++ ) {
            User user = new User("developer" + i);
            userList1.addUser(user);
        }
        System.out.println(userList1.getUsersList().get(0));
        System.out.println(test.get(0));

        System.out.println("test:");
        userList1.getUsersList().stream()
                .map(s -> s.getUsername())
                .forEach(System.out::println);

        ListWriteToFile listWriteToFile = new ListWriteToFile();
        listWriteToFile.writeToFile(userList1);
    }



}