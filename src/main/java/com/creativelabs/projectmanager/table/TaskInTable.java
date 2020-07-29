package com.creativelabs.projectmanager.table;

import com.creativelabs.projectmanager.Manager;
import com.creativelabs.projectmanager.fileshandling.FilesHandle;
import com.creativelabs.projectmanager.tasks.Task;
import com.creativelabs.projectmanager.tasks.TaskList;
import com.creativelabs.projectmanager.tasks.UserList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;

import static com.creativelabs.projectmanager.Manager.*;
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
                //manager.getBoardStage().hide(); //close main board
                //manager.showTask(getId(), getHyperlink(), getTitle(), getType(), getStatus(), getAssignee(), getCreator(), getCreated(), getDeadline());

                //TaskInTable editedTask = new TaskInTable("getId()", getHyperlink(), getTitle(), getType(), getStatus(), getAssignee(), getCreator(), getCreated(), getDeadline());
                //int taskId = Integer.parseInt(getId())-1;
                //dataTasks.set(taskId, editedTask);

                //dataTasks.setAll(editTaskRow());
                /*Stage stage = new Stage();
                manager.editTask(stage);
                stage.show();*/
                editTask();
            }
        });
        return hyperlink;
    }

    public void editTask() {

        Stage stage = new Stage();
        int taskIndex = Integer.parseInt(getId())-1;

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label taskTitle = new Label("Name:");
        taskTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        TextField taskTitleTextField = new TextField();
        taskTitleTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        taskTitleTextField.setText(getTitle());

        Label taskType = new Label("Type:");
        taskType.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        Label statusType = new Label("Status:");
        statusType.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        Label assagneeLabel = new Label("Assignee:");
        assagneeLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        FilesHandle filesHandle = new FilesHandle();
        String path = projectPath +  "/" + projectName + "_userslist.txt";
        File myObj = new File(path);
        UserList userList = filesHandle.fileToUsersList(myObj);

        String pathTasks = projectPath +  "/" + projectName + "_taskslist.txt";
        File myObjTasks = new File(pathTasks);
        TaskList tasksList = filesHandle.fileToTasksList(myObjTasks);


        ObservableList<String> users = filesHandle.convertUsersListToString(userList);
        final ComboBox usersComboBox = new ComboBox(users);
        usersComboBox.setValue(getAssignee());

        ObservableList<String> tasksTypes =
                FXCollections.observableArrayList(
                        "Quest",
                        "3D",
                        "2D",
                        "Level Design",
                        "Scripts",
                        "Music"
                );
        final ComboBox tasksTypesComboBox = new ComboBox(tasksTypes);
        tasksTypesComboBox.setValue(getType());

        ObservableList<String> tasksStatus =
                FXCollections.observableArrayList(
                        "Done",
                        "In progress",
                        "Assigned",
                        "Unassigned"
                );
        final ComboBox tasksStatusComboBox = new ComboBox(tasksStatus);
        tasksStatusComboBox.setValue(getStatus());


        Label creatorLabel = new Label("Creator:");
        creatorLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        TextField creatorTextField = new TextField();
        creatorTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        creatorTextField.setText(getCreator());
        creatorTextField.setDisable(true);

        Label createdLabel = new Label("Created:");
        createdLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        LocalDate created = LocalDate.now();
        TextField createdTextField = new TextField();
        createdTextField.setText(String.valueOf(created));
        createdTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        createdTextField.setDisable(true);

        Label deadlineLabel = new Label("Days for task:");
        deadlineLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        TextField deadlineTextField = new TextField();
        deadlineTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        deadlineTextField.setText("7");

        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        TextField descriptionTextField = new TextField();
        descriptionTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        descriptionTextField.setMinSize(250,50);
        descriptionTextField.setText(tasksList.getTasks().get(taskIndex).getDescription());





        Button btnExitTask = new Button("Exit");
        HBox hbBtnExitApply = new HBox(10);
        hbBtnExitApply.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnExitApply.getChildren().add(btnExitTask);

        btnExitTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                stage.hide();
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

                LocalDate deadline = LocalDate.now().plusDays(Long.parseLong(deadlineTextField.getText()));
                String deadlineDate = String.valueOf(deadline);

                if (taskTitleTextField.getText().length() < 3) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Fill all fields!");
                } else {

                    TaskInTable editedTaskInTable = new TaskInTable(getId(), getHyperlink(), taskTitleTextField.getText(), tasksTypesComboBox.getValue().toString(), tasksStatusComboBox.getValue().toString(), usersComboBox.getValue().toString(), getCreator(), getCreated(), deadlineDate);

                    dataTasks.set(taskIndex, editedTaskInTable);

                    Task editedTask = new Task(taskIndex+1, taskTitleTextField.getText(), descriptionTextField.getText(), tasksTypesComboBox.getValue().toString(), tasksStatusComboBox.getValue().toString(), usersComboBox.getValue().toString(), getCreator(), LocalDate.parse(getCreated()), LocalDate.parse(deadlineDate));
                    tasksList.setTask(taskIndex, editedTask);

                    //System.out.println(tasksList);
                    filesHandle.tasksWriteToFile(tasksList, projectName, projectPath);

                    stage.hide();

                }


            }
        });


        grid.add(taskTitle, 1, 0);
        grid.add(taskTitleTextField, 1, 1);
        grid.add(taskType, 1, 2);
        grid.add(tasksTypesComboBox, 1, 3);
        grid.add(statusType, 5, 0);
        grid.add(tasksStatusComboBox, 5, 1);
        grid.add(assagneeLabel, 5, 2);
        grid.add(usersComboBox, 5, 3);
        grid.add(creatorLabel, 5, 4);
        grid.add(creatorTextField, 5, 5);
        grid.add(createdLabel, 11, 0);
        grid.add(createdTextField, 11, 1);
        grid.add(deadlineLabel, 11, 2);
        grid.add(deadlineTextField, 11, 3);
        grid.add(descriptionLabel, 1, 7);
        grid.add(descriptionTextField, 1, 8);



        grid.add(btnSaveTask, 11, 9);
        //grid.add(btnExitTask, 11, 9);


        Scene scene = new Scene(grid, 800, 600); // Manage scene size
        stage.setTitle("Edit task " + getId());
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
