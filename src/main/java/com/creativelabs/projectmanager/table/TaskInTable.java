package com.creativelabs.projectmanager.table;

import com.creativelabs.projectmanager.Manager;
import com.creativelabs.projectmanager.fileshandling.TasksListWriteToFile;
import com.creativelabs.projectmanager.tasks.Task;
import javafx.beans.property.SimpleStringProperty;
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

public class TaskInTable {

    private final SimpleStringProperty id;
    private final Hyperlink hyperlink;
    private final SimpleStringProperty title;
    private final SimpleStringProperty type;
    private final SimpleStringProperty status;
    private final SimpleStringProperty assignee;
    private final SimpleStringProperty creator;
    private final SimpleStringProperty created;
    private final SimpleStringProperty deadline;

    public TaskInTable(String id, Hyperlink hyperlink, String title, String type, String status, String assignee, String creator, String created, String deadline) {
        this.id = new SimpleStringProperty(id);
        this.hyperlink = new Hyperlink(id);
        this.title = new SimpleStringProperty(title);
        this.type = new SimpleStringProperty(type);
        this.status = new SimpleStringProperty(status);
        this.assignee = new SimpleStringProperty(assignee);
        this.creator = new SimpleStringProperty(creator);
        this.created = new SimpleStringProperty(created);
        this.deadline = new SimpleStringProperty(deadline);
    }

    public String getId() {
        return id.get();
    }

    public Hyperlink getHyperlink()
    {
        hyperlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                //Manager manager = new Manager();
                //manager.showTask(getId(), getHyperlink(), getTitle(), getType(), getStatus(), getAssignee(), getCreator(), getCreated(), getDeadline());

                //Stage stage = new Stage();
                //manager.editTask(stage);
                //stage.show();
            }
        });
        return hyperlink;
    }

    public void editTask(Stage stage) {

        GridPane grid = new GridPane();

        //Label taskId = new Label("Task number: " + id);
        //taskId.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));

        Label taskTitle = new Label("Task name:");
        taskTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        TextField taskTitleTextField = new TextField();
        taskTitleTextField.setText(getTitle());
        taskTitleTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        taskTitleTextField.setDisable(true);

        Label taskType = new Label("Task type:");
        taskType.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        TextField taskTypeTextField = new TextField();
        taskTypeTextField.setText(getType());
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
                    setTitle(taskTitleTextField.getText());
                    setType(taskTypeTextField.getText());

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
        stage.setTitle("Task " + getId());
        stage.setScene(scene);
        stage.show();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getAssignee() {
        return assignee.get();
    }

    public SimpleStringProperty assigneeProperty() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee.set(assignee);
    }

    public String getCreator() {
        return creator.get();
    }

    public SimpleStringProperty creatorProperty() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator.set(creator);
    }

    public String getCreated() {
        return created.get();
    }

    public SimpleStringProperty createdProperty() {
        return created;
    }

    public void setCreated(String created) {
        this.created.set(created);
    }

    public String getDeadline() {
        return deadline.get();
    }

    public SimpleStringProperty deadlineProperty() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline.set(deadline);
    }

    @Override
    public String toString() {
        return "TaskInTable{" +
                "id=" + id +
                ", hyperlink=" + hyperlink +
                ", title=" + title +
                ", type=" + type +
                ", status=" + status +
                ", assignee=" + assignee +
                ", creator=" + creator +
                ", created=" + created +
                ", deadline=" + deadline +
                '}';
    }
}
