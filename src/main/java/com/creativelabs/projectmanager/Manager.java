package com.creativelabs.projectmanager;

import com.creativelabs.projectmanager.fileshandling.ReadFileToTasksList;
import com.creativelabs.projectmanager.fileshandling.UsersListWriteToFile;
import com.creativelabs.projectmanager.fileshandling.ReadFileToUsersList;
import com.creativelabs.projectmanager.table.EditingCell;
import com.creativelabs.projectmanager.table.UserInTable;
import com.creativelabs.projectmanager.table.TaskInTable;
import com.creativelabs.projectmanager.tasks.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.HPos.RIGHT;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class Manager extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    private String nick = "nick";
    private String project = "Test";
    private String password = "1234";
    private UserList userList1 = new UserList("Team");

    public String getNick() {
        return nick;
    }
    public String getProject() {
        return project;
    }
    public String getPassword() {
        return password;
    }

    // Define buttons here for access by multiple methods
    private final Button btnApply = new Button("Apply");
    private final Button btnContinue = new Button("Continue");
    private final Button btnExit = new Button("Exit");

    ReadFileToUsersList readFileToUsersList = new ReadFileToUsersList();
    String path = "src/main/resources/files/userlist1.txt";
    File myObj = new File(path);
    UserList userList = readFileToUsersList.fileToList(myObj);

    ReadFileToTasksList readFileToTasksList = new ReadFileToTasksList();
    String pathTasks = "src/main/resources/files/tasklist0.txt";
    File myObjTasks = new File(pathTasks);
    TaskList tasksList = readFileToTasksList.fileToList(myObjTasks);


    private ObservableList<UserInTable> usersData = convertUsersListToObservable(userList);
    private final ObservableList<TaskInTable> dataTasks = convertTasksListToObservable(tasksList);

    final HBox hb = new HBox();

    public ObservableList<UserInTable> convertUsersListToObservable(UserList list) {
        ObservableList<UserInTable> data = FXCollections.observableArrayList();
        for(int n = 0; n < list.getUsersList().size(); n++) {
            String nick = list.getUsersList().get(n).getUsername();
            String password = list.getUsersList().get(n).getPassword();
            String email = list.getUsersList().get(n).getEmail();
            data.add(new UserInTable(nick, password, email));
        }
        return data;
    }
    public ObservableList<TaskInTable> convertTasksListToObservable(TaskList list) {
        ObservableList<TaskInTable> data = FXCollections.observableArrayList();
        for(int n = 0; n < list.getTasks().size(); n++) {
            String id = "00" + list.getTasks().get(n).getId();
            String title = list.getTasks().get(n).getTitle();
            String type = list.getTasks().get(n).getType();
            String status = list.getTasks().get(n).getStatus();
            String assignee = list.getTasks().get(n).getAssignedUser();
            String creator = list.getTasks().get(n).getCreator();
            String created = String.valueOf(list.getTasks().get(n).getCreated());
            String deadline = String.valueOf(list.getTasks().get(n).getDeadline());
            data.add(new TaskInTable(id, title, type,status,assignee,creator,created,deadline));
        }
        return data;
    }


    public void createBoard(Stage createNewBoard) {

        btnExit.setStyle("-fx-font-size: 15pt;");

        // Use tab pane with one tab for sizing UI and one tab for alignment UI
        TabPane tabs = new TabPane();
        Tab tabSize = new Tab();
        tabSize.setText("Tasks");
        tabSize.setContent(tabTasks());

        Tab tabAlign = new Tab();
        tabAlign.setText("Users");
        tabAlign.setContent(tabUsers());

        tabs.getTabs().addAll(tabSize, tabAlign);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabs, 1000, 600); // Manage scene size

        createNewBoard.setTitle("Project " + getProject());
        createNewBoard.setScene(scene);
        createNewBoard.show();
    }

    public void createTask(Stage stage) {

        GridPane grid = new GridPane();
               

        Label taskName = new Label("Task name:");
        taskName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        TextField taskNameTextField = new TextField();


        Button btnCreateTask = new Button("Create");
        HBox hbBtnApply = new HBox(10);
        hbBtnApply.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnApply.getChildren().add(btnCreateTask);


        //info when text is too short
        final Text actiontarget = new Text();
        grid.setColumnSpan(actiontarget, 2);
        grid.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");

        btnCreateTask.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (taskNameTextField.getText().length() < 3) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Enter project name!");
                } else {
                    dataTasks.add(new TaskInTable(
                            taskNameTextField.getText(),
                            "test",
                            "test",
                            "test",
                            "test",
                            "test",
                            "test",
                            "test"));
                    //userList.addUser(new User(addFirstName.getText(), addLastName.getText(), addEmail.getText()));
                    //usersListWriteToFile.writeToFile(userList);

                    stage.hide();
                }


            }
        });

        grid.add(taskName, 2, 5);
        grid.add(taskNameTextField, 3, 6);
        grid.add(hbBtnApply, 3, 7);
        grid.add(actiontarget, 2, 12);

        Scene scene = new Scene(grid, 600, 400); // Manage scene size
        stage.setTitle("Create task");
        stage.setScene(scene);
        stage.show();
    }

    private Pane tabTasks() {

        TableView tableTasks = new TableView();


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);  // Override default
        grid.setHgap(10);
        grid.setVgap(10);

        final Label label = new Label("Tasks");
        label.setFont(new Font("Arial", 20));

        final Button createTask = new Button("Create task");
        UsersListWriteToFile usersListWriteToFile = new UsersListWriteToFile();
        createTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage newTaskStage = new Stage();
                createTask(newTaskStage);
                newTaskStage.show();
            }
        });

        Hyperlink link = new Hyperlink();
        final String url = "http://zlotewrota.org";
        link.setText(url);
        link.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    java.awt.Desktop.getDesktop().browse(URI.create(url));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        tableTasks.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };

        TableColumn idCol = new TableColumn("Id");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<TaskInTable, String>("id"));
        idCol.setCellFactory(cellFactory);
        idCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TaskInTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TaskInTable, String> t) {
                        ((TaskInTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setId(t.getNewValue());
                    }
                }
        );


        TableColumn titleCol = new TableColumn("Title");
        titleCol.setMinWidth(100);
        titleCol.setCellValueFactory(
                new PropertyValueFactory<TaskInTable, String>("title"));
        titleCol.setCellFactory(cellFactory);
        titleCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TaskInTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TaskInTable, String> t) {
                        ((TaskInTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setTitle(t.getNewValue());
                    }
                }
        );

        TableColumn typeCol = new TableColumn("Type");
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(
                new PropertyValueFactory<TaskInTable, String>("type"));
        typeCol.setCellFactory(cellFactory);
        typeCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TaskInTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TaskInTable, String> t) {
                        ((TaskInTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setType(t.getNewValue());
                    }
                }
        );

        TableColumn statusCol = new TableColumn("Status");
        statusCol.setMinWidth(100);
        statusCol.setCellValueFactory(
                new PropertyValueFactory<TaskInTable, String>("status"));
        statusCol.setCellFactory(cellFactory);
        statusCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TaskInTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TaskInTable, String> t) {
                        ((TaskInTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setStatus(t.getNewValue());
                    }
                }
        );

        TableColumn assigneeCol = new TableColumn("Assignee");
        assigneeCol.setMinWidth(100);
        assigneeCol.setCellValueFactory(
                new PropertyValueFactory<TaskInTable, String>("assignee"));
        assigneeCol.setCellFactory(cellFactory);
        assigneeCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TaskInTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TaskInTable, String> t) {
                        ((TaskInTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setAssignee(t.getNewValue());
                    }
                }
        );

        TableColumn creatorCol = new TableColumn("Creator");
        creatorCol.setMinWidth(100);
        creatorCol.setCellValueFactory(
                new PropertyValueFactory<TaskInTable, String>("creator"));
        creatorCol.setCellFactory(cellFactory);
        creatorCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TaskInTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TaskInTable, String> t) {
                        ((TaskInTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCreator(t.getNewValue());
                    }
                }
        );

        TableColumn createdCol = new TableColumn("Created");
        createdCol.setMinWidth(150);
        createdCol.setCellValueFactory(
                new PropertyValueFactory<TaskInTable, String>("created"));
        createdCol.setCellFactory(cellFactory);
        createdCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TaskInTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TaskInTable, String> t) {
                        ((TaskInTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCreated(t.getNewValue());
                    }
                }
        );

        TableColumn deadlineCol = new TableColumn("Deadline");
        deadlineCol.setMinWidth(150);
        deadlineCol.setCellValueFactory(
                new PropertyValueFactory<TaskInTable, String>("deadline"));
        deadlineCol.setCellFactory(cellFactory);
        deadlineCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TaskInTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TaskInTable, String> t) {
                        ((TaskInTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setDeadline(t.getNewValue());
                    }
                }
        );

        tableTasks.setItems(dataTasks);
        tableTasks.getColumns().addAll(idCol, titleCol, typeCol, statusCol, assigneeCol, creatorCol, createdCol, deadlineCol);

        grid.add(label, 3, 1, 2, 1);
        grid.add(createTask, 3, 4, 2, 1);
        grid.add(link, 3, 7, 2, 1);
        grid.add(tableTasks, 3, 2, 2, 1);

        return grid;
    }


    private Pane tabUsers() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);  //Pos.CENTER
        grid.setHgap(10);
        grid.setVgap(10);


        TableView tableUsers = new TableView();
        usersData = convertUsersListToObservable(userList);

        final Label label = new Label("List of users");
        label.setFont(new Font("Arial", 20));

        tableUsers.setEditable(true);
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
                new EventHandler<TableColumn.CellEditEvent<UserInTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<UserInTable, String> t) {
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
                new EventHandler<TableColumn.CellEditEvent<UserInTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<UserInTable, String> t) {
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
                new EventHandler<TableColumn.CellEditEvent<UserInTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<UserInTable, String> t) {
                        ((UserInTable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setEmail(t.getNewValue());
                    }
                }
        );

        tableUsers.setItems(usersData);
        tableUsers.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

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
                usersData.add(new UserInTable(
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


        grid.add(label, 3, 1, 2, 1);
        grid.add(tableUsers, 3, 2, 2, 1);
        grid.add(hb, 4, 4, 2, 1);



        //((Group) scene.getRoot()).getChildren().addAll(vbox);


        /*
        // Use column constraints to set properties for columns in the grid
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);  // Override default
        grid.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.LEFT);  // Override default
        grid.getColumnConstraints().add(column2);

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.setAlignment(Pos.CENTER);  // Aligns HBox and controls in HBox


        Button btnSubmit = new Button("Submit");
        Button btnClear = new Button("Clear");
        Button btnExit2 = new Button("Exit");
        btnSubmit.setStyle("-fx-font-size: 15pt;");



        hbButtons.getChildren().addAll(btnSubmit, btnClear, btnExit2);
        grid.add(hbButtons, 0, 2, 2, 1);*/


        return grid;
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

        btnCreateProject.setOnAction(new EventHandler<ActionEvent>() {

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
        createNewProject.show();


    }

    private VBox createButtonColumn() {

        Button btnAdd = new Button("Add");
        Button btnDelete = new Button("Delete");
        Button btnMoveUp = new Button("Move Up");
        Button btnMoveDown = new Button("Move Down");

        // Comment out the following statements to see the default button sizes
        btnAdd.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnDelete.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMoveUp.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMoveDown.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMoveDown.setMinWidth(Control.USE_PREF_SIZE);

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 20, 10, 20));

        vbButtons.getChildren().addAll(
                btnAdd, btnDelete, btnMoveUp, btnMoveDown);

        return vbButtons;
    }

    /*
     * Creates a row of buttons and makes them all the same size.
     */
    private TilePane createButtonRow() {

        // Let buttons grow, otherwise they will be different sizes based
        // on the length of the label
        btnApply.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnContinue.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnExit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        tileButtons.setHgap(10.0);
        tileButtons.setVgap(8.0); // In case window is reduced and buttons
        // require another row
        tileButtons.getChildren().addAll(btnApply, btnContinue, btnExit);

        return tileButtons;
    }

    /*
     * Creates a row of buttons with the default sizes.
     */
    private HBox createButtonBox() {

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10);
        hbButtons.setPadding(new Insets(20, 10, 20, 0));
        hbButtons.getChildren().addAll(btnApply, btnContinue, btnExit);

        return hbButtons;
    }



    @Override
    public void start(Stage primaryStage) throws Exception {



        primaryStage.setTitle("Project manager");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Hello");
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

        Label email = new Label("Email:");
        grid.add(email, 0, 3);

        TextField emailBox = new TextField();
        grid.add(emailBox, 1, 3);

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


        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((userTextField.getText().length() < 3) || (pwBox.getText().length() < 3) || (emailBox.getText().length() < 3)) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Enter username, password and mail!");
                } else {
                    primaryStage.hide();

                    UsersListWriteToFile usersListWriteToFile = new UsersListWriteToFile();
                    userList.addUser(new User(userTextField.getText(),pwBox.getText(),emailBox.getText()));
                    usersListWriteToFile.writeToFile(userList);

                    Stage createNewProject = new Stage();
                    createNewProject(createNewProject);
                }

                /*primaryStage.hide();
                Stage newBoard = new Stage();
                createBoard(newBoard);*/

            }
        });



        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        //primaryStage.show();

        Stage newBoard = new Stage();
        createBoard(newBoard);









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


