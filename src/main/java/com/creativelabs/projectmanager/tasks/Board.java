package com.creativelabs.projectmanager.tasks;


import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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


        UserList userList1 = new UserList("Team");
        LinkedList<String> test = new LinkedList<>();
        test.add("hej");

        for (int i = 0; i <7; i++ ) {
            User user = new User("developer" +i,"1234","developer" + i + "@example.com");
            userList1.addUser(user);
        }
        System.out.println(userList1.getUsersList().get(0));
        System.out.println(test.get(0));

        System.out.println("test:");
        userList1.getUsersList().stream()
                .map(s -> s.getUsername())
                .forEach(System.out::println);


    }



}