
package com.creativelabs.projectmanager.test;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Test extends Application {


    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Project Manager v.1.00");

        Label labelfirst= new Label("User name");
        Label label= new Label();
        //Label labelfirst= new Label("User name");
        //Label label= new Label();

        Button button= new Button("Submit");
        TextField text= new TextField();
        button.setOnAction(e ->
                {

                    label.setText("The name you entered is " + text.getText());
                }
        );

        VBox layout= new VBox(5);

        layout.getChildren().addAll(labelfirst, text, button, label);

        Scene scene1= new Scene(layout, 300, 250);
        primaryStage.setScene(scene1);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}