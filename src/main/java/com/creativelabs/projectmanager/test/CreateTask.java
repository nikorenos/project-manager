package com.creativelabs.projectmanager.test;

import com.creativelabs.projectmanager.tasks.Task;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class CreateTask extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    /*Task task = new Task("Microservice for taking temperature",
            "Write and test the microservice taking\n" +
                    "the temperaure from external service",
            user1,
            user2,
            LocalDate.now().minusDays(20),
            LocalDate.now().plusDays(30));*/

    public void createNewProject(Stage createNewProject) {

        GridPane gridCreateProject = new GridPane();
        gridCreateProject.setAlignment(Pos.CENTER);
        gridCreateProject.setHgap(10);
        gridCreateProject.setVgap(10);
        gridCreateProject.setPadding(new Insets(25, 25, 25, 25));


        Text welcomeUser = new Text("Create new project");

        welcomeUser.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridCreateProject.add(welcomeUser, 0, 0, 2, 1);

        Label projectName = new Label("Project name:");
        gridCreateProject.add(projectName, 0, 1);

        TextField projectNameTextField = new TextField();
        gridCreateProject.add(projectNameTextField, 1, 1);
        Scene sceneCreateNewProject = new Scene(gridCreateProject, 400, 475);

        Button btnCreateProject = new Button("Create");
        HBox hbBtnApply = new HBox(10);
        hbBtnApply.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnApply.getChildren().add(btnCreateProject);
        gridCreateProject.add(hbBtnApply, 1, 4);

        final Text actiontarget = new Text();
        gridCreateProject.add(actiontarget, 0, 6);
        gridCreateProject.setColumnSpan(actiontarget, 2);
        gridCreateProject.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");

        /*btnCreateProject.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (projectNameTextField.getText().length() < 3) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Enter project name!");
                } else {
                    project = projectNameTextField.getText();
                    System.out.println(project);
                    createNewProject.hide();
                    Stage newBoard = new Stage();
                    createBoard(newBoard);
                }

            }
        });

        createNewProject.setTitle("Welcome " + getNick() + " " + getPassword());
        createNewProject.setScene(sceneCreateNewProject);
        createNewProject.show();*/


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
