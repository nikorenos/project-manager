package com.creativelabs.projectmanager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.HPos.RIGHT;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Manager extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    String nick = "nick";
    public String getNick() {
        return nick;
    }


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

        createNewProject.setTitle("Welcome " + getNick());
        createNewProject.setScene(sceneCreateNewProject);
        createNewProject.show();


    }

    @Override
    public void start(Stage primaryStage) throws Exception {



        primaryStage.setTitle("Project manager");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Hello!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);
        grid.setColumnSpan(actiontarget, 2);
        grid.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");



        ////////////////////////////scene 2///////////////////
        Stage stage2 = new Stage();

        GridPane gridCreateProject = new GridPane();
        gridCreateProject.setAlignment(Pos.CENTER);
        gridCreateProject.setHgap(10);
        gridCreateProject.setVgap(10);
        gridCreateProject.setPadding(new Insets(25, 25, 25, 25));


        Text welcomeUser = new Text("Welcome " + getNick());

        welcomeUser.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridCreateProject.add(welcomeUser, 0, 0, 2, 1);

        Label projectName = new Label("Project name:");
        gridCreateProject.add(projectName, 0, 1);

        TextField projectNameTextField = new TextField();
        gridCreateProject.add(projectNameTextField, 1, 1);


        Button btnApply = new Button("Apply");
        HBox hbBtnApply = new HBox(10);
        hbBtnApply.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnApply.getChildren().add(btnApply);
        gridCreateProject.add(hbBtnApply, 1, 4);



        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (userTextField.getText().length() < 3) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Enter username and password!");
                } else {
                    nick = userTextField.getText();
                    System.out.println(nick);
                    primaryStage.hide();
                    //stage2.show();
                    //primaryStage.setScene(scene2);
                    Stage createNewProject = new Stage();
                    createNewProject(createNewProject);
                }

            }
        });


        btnApply.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (userTextField.getText().length() < 3) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText(nick);
                } else {
                    nick = userTextField.getText();
                    System.out.println(nick);
                    //primaryStage.hide();
                    //stage2.show();
                    //primaryStage.setScene(scene2);
                }

            }
        });





        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();






        /*StackPane stackPane = new StackPane();
        Button button = new Button("Ok");
        stackPane.getChildren().add(button);

        Scene scene = new Scene (stackPane, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Project Manager v.1.00");
        primaryStage.show();*/

        /*primaryStage.setWidth(200); //nadrzdne ustawianie rozmiaru apki
        primaryStage.setHeight(200);
        primaryStage.setResizable(false);//blokada zmiany rozmiaru
        primaryStage.setFullScreen(true);//włączenie apki w fullscreen

        primaryStage.setX(0);//sterowanie miejscem uruchomienia apki
        primaryStage.setY(0);
        primaryStage.initStyle(StageStyle.DECORATED);//styl
        primaryStage.setOpacity(0.5);//alpha*/



        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/StackPaneWindow.fxml"));
        StackPane stackPane = loader.load();

        Scene scene = new Scene(stackPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Project Manager v.1.00");
        primaryStage.show();*/




    }
}
