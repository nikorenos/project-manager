package com.creativelabs.projectmanager.test;

import com.creativelabs.projectmanager.fileshandling.UsersListWriteToFile;
import com.creativelabs.projectmanager.fileshandling.ReadFileToUsersList;
import com.creativelabs.projectmanager.table.EditingCell;
import com.creativelabs.projectmanager.table.UserInTableHyperlink;
import com.creativelabs.projectmanager.tasks.User;
import com.creativelabs.projectmanager.tasks.UserList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class TableViewSample extends Application {

    ReadFileToUsersList readFile = new ReadFileToUsersList();
    String path = "src/main/resources/files/userlist1.txt";
    File myObj = new File(path);
    UserList userList = readFile.fileToList(myObj);

    Hyperlink nick = new Hyperlink("test");
    String password = "pass";
    String email = "email";
    UserInTableHyperlink user = new UserInTableHyperlink(nick, password, email);
    Hyperlink link = new Hyperlink("test");



    public ObservableList<UserInTableHyperlink> convertListToObservable(UserList list) {
        ObservableList<UserInTableHyperlink> data = FXCollections.observableArrayList();

        data.add(new UserInTableHyperlink(link, password, email));
        data.add(new UserInTableHyperlink(link, password, email));
        data.add(new UserInTableHyperlink(link, password, email));

        return data;
    }

    private TableView<UserInTableHyperlink> table = new TableView<UserInTableHyperlink>();
    private final ObservableList<UserInTableHyperlink> data = convertListToObservable(userList);

    final HBox hb = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(550);

        GridPane grid = new GridPane();

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };

        Callback<TableColumn, TableCell> cellLink =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };

        TableColumn firstNameCol = new TableColumn("Username");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<UserInTableHyperlink, String>("hyperlink"));


        link.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("test");
            }
        });



        TableColumn lastNameCol = new TableColumn("Password");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<UserInTableHyperlink, String>("lastName"));
        //lastNameCol.setCellFactory(cellFactory);


        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<UserInTableHyperlink, String>("email"));
        emailCol.setCellFactory(cellFactory);
        emailCol.setOnEditCommit(
                new EventHandler<CellEditEvent<UserInTableHyperlink, String>>() {
                    @Override
                    public void handle(CellEditEvent<UserInTableHyperlink, String> t) {
                        ((UserInTableHyperlink) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setEmail(t.getNewValue());
                    }
                }
        );

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("Username");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Password");
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");





        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb, link);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }



}