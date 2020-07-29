package com.creativelabs.projectmanager;

import com.creativelabs.projectmanager.table.EditingCell;
import com.creativelabs.projectmanager.table.UserInTable;
import com.creativelabs.projectmanager.tasks.*;
import com.creativelabs.projectmanager.fileshandling.*;
import com.creativelabs.projectmanager.table.TaskInTable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static java.time.LocalDate.now;
import static javafx.geometry.HPos.LEFT;
import static javafx.geometry.HPos.RIGHT;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;


public class Manager extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    private final Stage signStage = new Stage();
    private final Stage boardStage = new Stage();
    private final String nick = "nick";
    private final String password = "1234";
    private final String email = "test@example.com";
    private int taskNumber = 0;
    private User admin = new User(nick, password, email);
    public static String projectName = "zw2";
    public static String projectPath = "C:/ZW2";


    public String getProjectName() {
        return projectName;
    }

    public String getProjectPath() {
        return projectPath;
    }
    public String getNick() {
        return nick;
    }
    public String getPassword() {
        return password;
    }
    public Stage getBoardStage() {
        return boardStage;
    }

    // Define buttons here for access by multiple methods
    private final Button btnApply = new Button("Apply");
    private final Button btnContinue = new Button("Continue");
    private final Button btnExit = new Button("Exit");

    FilesHandle filesHandle = new FilesHandle();
    String path;
    UserList userList;
    TableView tableUsers = new TableView();

    String pathTasks;
    TaskList tasksList;


    private ObservableList<UserInTable> usersData;
    public static ObservableList<TaskInTable> dataTasks;
    TableView tableTasks = new TableView();
    Tab tabStats = new Tab();
    Tab tabTasks = new Tab();
    Tab tabUsers = new Tab();

    final HBox hb = new HBox();


    public void signUser() {

        // Use tab pane with one tab for sizing UI and one tab for alignment UI
        TabPane tabs = new TabPane();
        Tab tabSignIn = new Tab();
        tabSignIn.setText("Sign in");
        tabSignIn.setContent(tabSignIn());

        Tab tabRegister = new Tab();
        tabRegister.setText("Register");
        tabRegister.setContent(tabRegister());

        tabs.getTabs().addAll(tabRegister, tabSignIn);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabs, 350, 300); // Manage scene size

        signStage.setTitle("Project manager");
        signStage.setScene(scene);
        signStage.show();
    }
    private Pane tabSignIn() {

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


        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((userTextField.getText().length() < 3) || (pwBox.getText().length() < 3)) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Enter username and password!");
                } else {
                    signStage.hide();


                    Stage stage = new Stage();
                    createProject(stage);
                }

                /*primaryStage.hide();
                Stage newBoard = new Stage();
                createBoard();*/

            }
        });

        return grid;
    }

    private Pane tabRegister() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

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

        Button btn = new Button("Register");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);
        grid.setColumnSpan(actiontarget, 2);
        grid.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((userTextField.getText().length() < 3) || (pwBox.getText().length() < 3) || (emailBox.getText().length() < 3)) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Enter username, password and mail!");
                } else {
                    signStage.hide();
                    admin = new User(userTextField.getText(), pwBox.getText(), emailBox.getText());

                    Stage stage = new Stage();
                    createProject(stage);
                }

            }
        });

        return grid;
    }

    public void createTask(Stage stage) {

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label taskTitle = new Label("Name:");
        taskTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        TextField taskTitleTextField = new TextField();
        taskTitleTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        taskTitleTextField.setText("task 1");

        Label taskType = new Label("Type:");
        taskType.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        Label statusType = new Label("Status:");
        statusType.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        Label assagneeLabel = new Label("Assignee:");
        assagneeLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        path = projectPath +  "/" + projectName + "_userslist.txt";
        File myObj = new File(path);
        userList = filesHandle.fileToUsersList(myObj);
        ObservableList<String> users = filesHandle.convertUsersListToString(userList);
        final ComboBox usersComboBox = new ComboBox(users);
        usersComboBox.setValue(userList.getUsersList().get(0).getUsername());

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
        tasksTypesComboBox.setValue("Quest");

        ObservableList<String> tasksStatus =
                FXCollections.observableArrayList(
                        "Done",
                        "In progress",
                        "Assigned"
                );
        final ComboBox tasksStatusComboBox = new ComboBox(tasksStatus);
        tasksStatusComboBox.setValue("Assigned");


        Label creatorLabel = new Label("Creator:");
        creatorLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        TextField creatorTextField = new TextField();
        creatorTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        creatorTextField.setText(userList.getUsersList().get(0).getUsername());

        Label createdLabel = new Label("Created:");
        createdLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

        LocalDate created = now();
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
        descriptionTextField.setText("This is example task.");





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
                String convertTaskNumber;

                LocalDate deadline = now().plusDays(Long.parseLong(deadlineTextField.getText()));
                String deadlineDate = String.valueOf(deadline);

                if (taskTitleTextField.getText().length() < 3) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Fill all fields!");
                } else {

                    if (tasksList.getTasks().size() > 0) {
                        taskNumber = tasksList.getTasks().get(tasksList.getTasks().size()-1).getId();
                    }
                    taskNumber += 1;

                    convertTaskNumber = String.valueOf(taskNumber);
                    if (taskNumber < 10) {
                        convertTaskNumber = "00" + convertTaskNumber;
                    } else if ((taskNumber >= 10) && (taskNumber < 100)) {
                        convertTaskNumber = "0" + convertTaskNumber;
                    }
                    Hyperlink hyperlink = new Hyperlink(convertTaskNumber);

                    dataTasks.add(new TaskInTable(
                            convertTaskNumber,
                            hyperlink,
                            taskTitleTextField.getText(),
                            tasksTypesComboBox.getValue().toString(),
                            tasksStatusComboBox.getValue().toString(),
                            usersComboBox.getValue().toString(),
                            creatorTextField.getText(),
                            createdTextField.getText(),
                            deadlineDate));

                    tasksList.addTask(new Task(taskNumber, taskTitleTextField.getText(), descriptionTextField.getText(),  tasksTypesComboBox.getValue().toString(),tasksStatusComboBox.getValue().toString(), usersComboBox.getValue().toString(), creatorTextField.getText(), created, deadline));
                    filesHandle.tasksWriteToFile(tasksList, projectName, projectPath);
                    tabStats.setContent(tabStats());

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



        grid.add(btnCreateTask, 11, 9);
        //grid.add(btnExitTask, 11, 9);


        Scene scene = new Scene(grid, 800, 600); // Manage scene size
        stage.setTitle("Create task");
        stage.setScene(scene);
        stage.show();
    }


    public void createBoard() {

        btnExit.setStyle("-fx-font-size: 15pt;");

        // Use tab pane with one tab for sizing UI and one tab for alignment UI
        TabPane tabs = new TabPane();

        tabTasks.setText("Tasks");
        tabTasks.setContent(tabTasks());

        tabUsers.setText("Users");
        tabUsers.setContent(tabUsers());

        tabStats.setText("Stats");
        tabStats.setContent(tabStats());

        tabs.getTabs().addAll(tabTasks, tabUsers, tabStats);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabs, 1000, 600); // Manage scene size

        boardStage.setTitle("Project " + projectName);
        boardStage.setScene(scene);
        boardStage.show();
    }

    private Pane tabStats() {


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);  // Override default
        grid.setHgap(10);
        grid.setVgap(10);

        pathTasks = projectPath +  "/" + projectName + "_taskslist.txt";
        File myObjTasks = new File(pathTasks);
        TaskList tasksList = filesHandle.fileToTasksList(myObjTasks);
        ArrayList<String> tasksType = filesHandle.convertTaskTypeToString(tasksList);

        int taskQuestAmount = 0;
        int task3dAmount = 0;
        int task2dAmount = 0;
        int taskLevelDesignAmount = 0;
        int taskScriptsAmount = 0;
        int taskMusicAmount = 0;

        for (String task : tasksType) {
            //System.out.println(task);
            if (task.equals("Quest")) {
                taskQuestAmount = taskQuestAmount + 1;
            }if (task.equals("3D")) {
                task3dAmount = task3dAmount + 1;
            }if (task.equals("2D")) {
                task2dAmount = task2dAmount + 1;
            }if (task.equals("Level Design")) {
                taskLevelDesignAmount = taskLevelDesignAmount + 1;
            }if (task.equals("Scripts")) {
                taskScriptsAmount = taskScriptsAmount + 1;
            }if (task.equals("Music")) {
                taskMusicAmount = taskMusicAmount + 1;
            }

        }

        ObservableList<PieChart.Data> taskTypePieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Quest", taskQuestAmount),
                        new PieChart.Data("3D", task3dAmount),
                        new PieChart.Data("2D", task2dAmount),
                        new PieChart.Data("Level Design", taskLevelDesignAmount),
                        new PieChart.Data("Scripts", taskScriptsAmount),
                        new PieChart.Data("Music", taskMusicAmount));
        final PieChart taskTypeChart = new PieChart(taskTypePieChartData);
        taskTypeChart.setTitle("Task types");

        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : taskTypeChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            //System.out.println("clicked");
                            caption.setTranslateX(e.getSceneX()-40);
                            caption.setTranslateY(e.getSceneY()-40);
                            caption.setText(String.valueOf(data.getPieValue()));
                        }
                    });
        }


        grid.add(taskTypeChart, 3, 2, 2, 1);
        grid.add(caption, 2, 0, 2, 1);

        return grid;
    }

    private Pane tabTasks() {


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);  // Override default
        grid.setHgap(10);
        grid.setVgap(10);

        pathTasks = projectPath +  "/" + projectName + "_taskslist.txt";
        File myObjTasks = new File(pathTasks);
        tasksList = filesHandle.fileToTasksList(myObjTasks);
        dataTasks = filesHandle.convertTasksListToObservable(tasksList);

        final Label label = new Label("Tasks");
        label.setFont(new Font("Arial", 20));

        final Button createTask = new Button("Create task");
        createTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage newTaskStage = new Stage();
                createTask(newTaskStage);

                /*
                Hyperlink hyperlink = new Hyperlink();
                TaskInTable taskInTable = new TaskInTable("101", hyperlink, "task 5", "Music", "Done", "dev1", "dev2", "2000", "2001");
                dataTasks.set(0, taskInTable);

                dataTasks.add(new TaskInTable(
                        "100",
                        hyperlink,
                        "taskTitleTextField.getText()",
                        "tasksTypesComboBox.getValue().toString()",
                        "tasksStatusComboBox.getValue().toString()",
                        "usersComboBox.getValue().toString()",
                        "creatorTextField.getText()",
                        "createdTextField.getText()",
                        "deadlineDate"));*/
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

        tableTasks.setEditable(false);
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };

        TableColumn idCol = new TableColumn("Id");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<TaskInTable, String>("hyperlink"));




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

        path = projectPath +  "/" + projectName + "_userslist.txt";
        File myObj = new File(path);
        UserList userList = filesHandle.fileToUsersList(myObj);
        usersData = filesHandle.convertUsersListToObservable(userList);

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

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                /*//change data
                UserInTable user = new UserInTable ("developer0", "pass", "email");
                usersData.set(0, user);*/
                usersData.add(new UserInTable(
                        addFirstName.getText(),
                        addLastName.getText(),
                        addEmail.getText()));
                userList.addUser(new User(addFirstName.getText(), addLastName.getText(), addEmail.getText()));
                filesHandle.usersWriteToFile(userList);

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

        return grid;
    }


    public void createProject(Stage stage) {


        GridPane gridCreateProject = new GridPane();
        gridCreateProject.setAlignment(Pos.TOP_CENTER);
        gridCreateProject.setHgap(10);
        gridCreateProject.setVgap(10);
        gridCreateProject.setPadding(new Insets(100, 25, 25, 25));
        Scene sceneCreateNewProject = new Scene(gridCreateProject, 500, 475);

        Text createProject = new Text("Create new project");
        createProject.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridCreateProject.add(createProject, 0, 0, 2, 1);

        Label projectNameLabel = new Label("Project name:");
        gridCreateProject.add(projectNameLabel, 0, 1);

        TextField projectNameLabelTextField = new TextField();
        gridCreateProject.add(projectNameLabelTextField, 1, 1);

        Label projectPathLabel = new Label("Select folder:");
        gridCreateProject.add(projectPathLabel, 0, 2);

        TextField projectPathLabelTextField = new TextField();
        projectPathLabelTextField.setText("C:/ZW2");
        gridCreateProject.add(projectPathLabelTextField, 1, 2);


        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("C:/ZW2"));

        Button buttonSelectDirectory = new Button("Select Directory");
        buttonSelectDirectory.setOnAction(e -> {
            File selectedDirectory = directoryChooser.showDialog(stage);
            projectPathLabelTextField.setText(selectedDirectory.getAbsolutePath());
            //System.out.println(selectedDirectory.getAbsolutePath());
        });
        gridCreateProject.add(buttonSelectDirectory, 3, 2);

        Button btnCreateProject = new Button("Create");
        HBox hbBtnApply = new HBox(10);
        hbBtnApply.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnApply.getChildren().add(btnCreateProject);
        gridCreateProject.add(hbBtnApply, 1, 3);

        final Text actiontarget = new Text();
        gridCreateProject.add(actiontarget, 0, 3);
        gridCreateProject.setColumnSpan(actiontarget, 2);
        gridCreateProject.setHalignment(actiontarget, LEFT);
        actiontarget.setId("actiontarget");

        btnCreateProject.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((projectNameLabelTextField.getText().length() < 3) || (projectPathLabelTextField.getText().length() < 3)) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Enter project name and destination!");
                } else {
                    projectName = projectNameLabelTextField.getText();
                    projectPath = projectPathLabelTextField.getText();

                    System.out.println(projectName);
                    System.out.println(projectPath);

                    filesHandle.createProjectFolder(projectName, projectPath, admin);

                    stage.hide();
                    Stage newBoard = new Stage();
                    createBoard();
                }

            }
        });

        Text selectProject = new Text("Select project");
        selectProject.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridCreateProject.add(selectProject, 0, 8, 2, 1);

        Label selectprojectNameLabel = new Label("Project name:");
        gridCreateProject.add(selectprojectNameLabel, 0, 9);

        TextField projectSelectLabelTextField = new TextField();
        gridCreateProject.add(projectSelectLabelTextField, 1, 9);

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(
                new File(projectPath)
        );

        final Button openProjectButton = new Button("Select Project File...");
        gridCreateProject.add(openProjectButton, 3, 9);

        openProjectButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = fileChooser.showOpenDialog(stage);
                        projectSelectLabelTextField.setText(file.getAbsolutePath());

                        try {
                            projectName = Files.readAllLines(Paths.get(file.getAbsolutePath())).get(0);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try {
                            projectPath = Files.readAllLines(Paths.get(file.getAbsolutePath())).get(1);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                        System.out.println(projectName);
                        System.out.println(projectPath);

                    }
                });

        Button btnSelectProject = new Button("Open");
        HBox btnSelectProjectApply = new HBox(10);
        btnSelectProjectApply.setAlignment(Pos.BOTTOM_RIGHT);
        btnSelectProjectApply.getChildren().add(btnSelectProject);
        gridCreateProject.add(btnSelectProjectApply, 1, 10);

        btnSelectProject.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        stage.hide();
                        Stage newBoard = new Stage();
                        createBoard();
                    }
                });





        stage.setTitle("Welcome " + getNick() + " " + getPassword());
        stage.setScene(sceneCreateNewProject);
        stage.show();


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

        Stage newBoard = new Stage();
        //createBoard();
        //createNewProject(newBoard);
        signUser();












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


