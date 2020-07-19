package com.creativelabs.projectmanager.testhyperlinkintable;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private BorderPane root;
    private TableView<Item> table;
    private TableColumn<Item, String> nameColumn;
    private TableColumn<Item, Hyperlink> urlColumn;
    private Scene scene;

    private ObservableList<Item> websiteList;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        scene = new Scene(root, 400, 400);

        table = new TableView<Item>();
        nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("websiteName"));

        urlColumn = new TableColumn<>("Address");
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("hyperlink"));
        urlColumn.setCellFactory(new HyperlinkCell());

        table.getColumns().add(nameColumn);
        table.getColumns().add(urlColumn);

        websiteList = FXCollections.observableArrayList();
        websiteList.add(new Item("Google", "www.google.com"));
        websiteList.add(new Item("Facebook", "www.facebook.com"));
        websiteList.add(new Item("Superglobals", "www.superglobals.net"));

        table.setItems(websiteList);

        root.setCenter(table);
        primaryStage.setTitle("www.superglobals.net");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
