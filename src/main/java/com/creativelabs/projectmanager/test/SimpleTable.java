package com.creativelabs.projectmanager.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SimpleTable extends Application {

    private TableView table = new TableView();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {



        //Scene scene = new Scene(tabs, 800, 600); // Manage scene size

        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(300);
        stage.setHeight(500);


        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        //vbox.getChildren().addAll(tabs, label, table);

        // Use tab pane with one tab for sizing UI and one tab for alignment UI
        TabPane tabs = new TabPane();
        Tab tabSize = new Tab();
        tabSize.setText("Tasks");
        //tabSize.setContent(sizingSample());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);  // Override default
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(label, 0, 1, 2, 1);
        grid.add(table, 0, 2, 2, 1);

        Tab tabAlign = new Tab();
        tabAlign.setText("Users");
        tabAlign.setContent(grid);

        tabs.getTabs().addAll(tabSize, tabAlign);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        vbox.getChildren().addAll(tabs);




        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}
