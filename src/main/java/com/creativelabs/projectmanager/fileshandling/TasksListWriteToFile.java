package com.creativelabs.projectmanager.fileshandling;

        import com.creativelabs.projectmanager.tasks.TaskList;
        import com.creativelabs.projectmanager.tasks.User;
        import com.creativelabs.projectmanager.tasks.UserList;

        import java.io.FileWriter;   // Import the FileWriter class
        import java.io.IOException;  // Import the IOException class to handle errors
        import java.util.List;
        import java.util.stream.Collectors;

public class TasksListWriteToFile {

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

