package com.creativelabs.projectmanager.testhyperlinkintable;

import com.creativelabs.projectmanager.Manager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;

import static javafx.geometry.HPos.RIGHT;

public class Item {
    private String websiteName;
    private Hyperlink hyperlink;

    public Item(String websiteName, String websiteUrl) {
        this.websiteName = websiteName;
        this.hyperlink = new Hyperlink(websiteUrl);
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public Hyperlink getHyperlink() {

        /*hyperlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage stage = new Stage();
                editTask(stage);
            }
        });*/

        return hyperlink;
    }

    public void editTask(Stage stage) {

        GridPane grid = new GridPane();

        //Label taskId = new Label("Task number: " + id);
        //taskId.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));

        Label taskTitle = new Label("Task name:");
        taskTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        TextField taskTitleTextField = new TextField();
        taskTitleTextField.setText(getWebsiteName());
        taskTitleTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        taskTitleTextField.setDisable(true);

        Label taskType = new Label("Task type:");
        taskType.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        TextField taskTypeTextField = new TextField();
        taskTypeTextField.setText("getType");
        taskTypeTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        taskTypeTextField.setDisable(true);


        Button btnEditTask = new Button("Edit");

        btnEditTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                taskTitleTextField.setDisable(false);
                taskTypeTextField.setDisable(false);
                grid.getChildren().remove(btnEditTask);
            }
        });

        Button btnSaveTask = new Button("Save");
        HBox hbBtnApply = new HBox(10);
        hbBtnApply.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnApply.getChildren().add(btnSaveTask);


        //info when text is too short
        final Text actiontarget = new Text();
        grid.setColumnSpan(actiontarget, 2);
        grid.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");

        btnSaveTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String convertTaskNumber;
                LocalDate created = LocalDate.now();
                String createdDate = String.valueOf(created);

                LocalDate deadline = LocalDate.now().plusDays(10);
                String deadlineDate = String.valueOf(deadline);

                if ((taskTitleTextField.getText().length() < 3)
                        && (taskTypeTextField.getText().length() < 3))
                {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Fill all fields!");
                } else {
                    setWebsiteName(taskTitleTextField.getText());
                    System.out.println(taskTitleTextField.getText());

                    /*TaskInTable editedTask = new TaskInTable(id,hyperlink, taskTitleTextField.getText(), taskTypeTextField.getText(),"status","assignee","creator","deadline", "deadline");
                    dataTasks.set(Integer.parseInt(id)-1, editedTask);
                    System.out.println("Edited task");*/

                    /*dataTasks.add(new TaskInTable(
                            id,
                            hyperlink,
                            taskTitleTextField.getText(),
                            "test",
                            "test",
                            "test",
                            "test",
                            createdDate,
                            deadlineDate));


                    tasksList.addTask(new Task(taskNumber, taskTitleTextField.getText(), "test", "test", "test", "test", "test", created, deadline));
                    TasksListWriteToFile tasksListWriteToFile = new TasksListWriteToFile();
                    tasksListWriteToFile.writeToFile(tasksList);*/

                    stage.hide();
                }


            }
        });

        //grid.add(taskId, 2, 3);
        grid.add(taskTitle, 3, 5);
        grid.add(taskTitleTextField, 3, 6);
        grid.add(taskType, 3, 8);
        grid.add(taskTypeTextField, 3, 9);
        grid.add(btnEditTask, 3, 22);
        grid.add(btnSaveTask, 5, 22);
        grid.add(actiontarget, 5, 25);

        Scene scene = new Scene(grid, 800, 600); // Manage scene size
        stage.setTitle("Task ");
        stage.setScene(scene);
        stage.show();
    }

    public void setHyperlink(String websiteUrl) {
        this.hyperlink = new Hyperlink(websiteUrl);
    }
}
