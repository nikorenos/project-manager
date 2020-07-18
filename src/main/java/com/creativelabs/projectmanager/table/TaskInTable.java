package com.creativelabs.projectmanager.table;

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
                Stage stage = new Stage();
                editTask(stage);
            }
        });
        return hyperlink;
    }

    public void editTask(Stage stage) {

        GridPane grid = new GridPane();

        Label taskId = new Label("Task number: " + getId());
        taskId.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));


        Label taskName = new Label("Task name: " + getTitle());
        taskName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        TextField taskNameTextField = new TextField();
        taskNameTextField.setText(getTitle());
        //taskNameTextField.setEditable(false);

        TextField taskTypeTextField = new TextField();
        taskTypeTextField.setText(getType());
        taskTypeTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        taskTypeTextField.setDisable(true);


        Button btnCreateTask = new Button("Create");
        HBox hbBtnApply = new HBox(10);
        hbBtnApply.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnApply.getChildren().add(btnCreateTask);


        grid.add(taskId, 2, 3);
        grid.add(taskName, 2, 5);
        grid.add(taskNameTextField, 3, 6);
        grid.add(taskTypeTextField, 3, 7);

        Scene scene = new Scene(grid, 600, 400); // Manage scene size
        stage.setTitle("Task");
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
