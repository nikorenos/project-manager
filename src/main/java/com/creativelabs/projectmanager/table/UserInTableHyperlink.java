package com.creativelabs.projectmanager.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserInTableHyperlink {


    private final Hyperlink hyperlink;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;

    public UserInTableHyperlink(Hyperlink hyperlink, String lName, String email) {
        this.hyperlink = new Hyperlink(lName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
    }

    public Hyperlink getHyperlink()
    {
        hyperlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                GridPane grid = new GridPane();
                Stage stage = new Stage();
                Scene scene = new Scene(grid, 600, 400); // Manage scene size
                stage.setTitle("Create task");
                stage.setScene(scene);
                stage.show();
            }
        });
        return hyperlink;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String fName) {
        lastName.set(fName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String fName) {
        email.set(fName);
    }

    public void openStage() {
        hyperlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("test");
            }
        });
    }
}

