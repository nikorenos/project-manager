package com.creativelabs.projectmanager.test;

import com.creativelabs.projectmanager.fileshandling.UsersListWriteToFile;
import com.creativelabs.projectmanager.fileshandling.ReadFileToUsersList;
import com.creativelabs.projectmanager.table.EditingCell;
import com.creativelabs.projectmanager.table.UserInTable;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;

public class TableViewSample extends Application {

    ReadFileToUsersList readFile = new ReadFileToUsersList();
    String path = "src/main/resources/files/userlist1.txt";
    File myObj = new File(path);
    UserList userList = readFile.fileToList(myObj);

    public ObservableList<UserInTable> convertListToObservable(UserList list) {
        ObservableList<UserInTable> data = FXCollections.observableArrayList();
        for(int n = 0; n < list.getUsersList().size(); n++) {
            String nick = list.getUsersList().get(n).getUsername();
            String password = list.getUsersList().get(n).getPassword();
            String email = list.getUsersList().get(n).getEmail();
            data.add(new UserInTable(nick, password, email));
        }
        return data;
    }

    private TableView<UserInTable> table = new TableView<UserInTable>();
    private final ObservableList<UserInTable> data = convertListToObservable(userList);

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

        TableColumn firstNameCol = new TableColumn("Username");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<UserInTable, String>("firstName"));
        firstNameCol.setCellFactory(cellFactory);
        firstNameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<UserInTable, String>>() {
                    @Override
                    public void handle(CellEditEvent<UserInTable, String> t) {
                        ((UserInTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setFirstName(t.getNewValue());
                    }
                }
        );


        TableColumn lastNameCol = new TableColumn("Password");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<UserInTable, String>("lastName"));
        lastNameCol.setCellFactory(cellFactory);
        lastNameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<UserInTable, String>>() {
                    @Override
                    public void handle(CellEditEvent<UserInTable, String> t) {
                        ((UserInTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setLastName(t.getNewValue());
                    }
                }
        );

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<UserInTable, String>("email"));
        emailCol.setCellFactory(cellFactory);
        emailCol.setOnEditCommit(
                new EventHandler<CellEditEvent<UserInTable, String>>() {
                    @Override
                    public void handle(CellEditEvent<UserInTable, String> t) {
                        ((UserInTable) t.getTableView().getItems().get(
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

        final Button addButton = new Button("Add");
        UsersListWriteToFile usersListWriteToFile = new UsersListWriteToFile();
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new UserInTable(
                        addFirstName.getText(),
                        addLastName.getText(),
                        addEmail.getText()));
                userList.addUser(new User(addFirstName.getText(), addLastName.getText(), addEmail.getText()));
                usersListWriteToFile.writeToFile(userList);

                addFirstName.clear();
                addLastName.clear();
                addEmail.clear();
            }
        });

        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }



}