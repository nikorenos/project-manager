package com.creativelabs.projectmanager;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.HPos.RIGHT;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
    String project = "Test";
    String password = "1234";

    public String getNick() {
        return nick;
    }
    public String getProject() {
        return project;
    }
    public String getPassword() {
        return password;
    }

    public void createBoard(Stage createNewBoard) {

        Button btnApply = new Button("Apply");
        Button btnContinue = new Button("Continue");
        Button btnExit = new Button("Exit");

        btnExit.setStyle("-fx-font-size: 15pt;");

        // Use tab pane with one tab for sizing UI and one tab for alignment UI
        TabPane tabs = new TabPane();
        Tab tabSize = new Tab();
        tabSize.setText("Tasks");
        tabSize.setContent(sizingSample());

        Tab tabAlign = new Tab();
        tabAlign.setText("Users");
        tabAlign.setContent(alignmentSample());

        tabs.getTabs().addAll(tabSize, tabAlign);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabs, 800, 600); // Manage scene size

        createNewBoard.setTitle("Project " + getProject());
        createNewBoard.setScene(scene);
        createNewBoard.show();




        //createNewBoard.setScene(sceneCreateNewProject);
        //createNewBoard.show();


    }

    private Pane sizingSample() {

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(50, 50, 20, 20));

        ListView<String> lvList = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "Hot dog", "Hamburger", "French fries",
                "Carrot sticks", "Chicken salad");
        lvList.setItems(items);
        lvList.setMaxHeight(Control.USE_PREF_SIZE);
        lvList.setPrefWidth(150.0);

        border.setLeft(lvList);
        //border.setRight(createButtonColumn());
        //border.setBottom(createButtonRow());  // Uses a tile pane for sizing
//        border.setBottom(createButtonBox());  // Uses an HBox, no sizing

        return border;
    }

    private Pane alignmentSample() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);  // Override default
        grid.setHgap(10);
        grid.setVgap(12);

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

        Label lblName = new Label("User name:");
        TextField tfName = new TextField();
        Label lblPwd = new Label("Password:");
        PasswordField pfPwd = new PasswordField();

        hbButtons.getChildren().addAll(btnSubmit, btnClear, btnExit2);
        grid.add(lblName, 0, 0);
        grid.add(tfName, 1, 0);
        grid.add(lblPwd, 0, 1);
        grid.add(pfPwd, 1, 1);
        grid.add(hbButtons, 0, 2, 2, 1);

        /* Uncomment the following statements to bottom-align the buttons */
//        hbButtons.setAlignment(Pos.BOTTOM_CENTER);
//        GridPane innergrid = new GridPane();
//        innergrid.setAlignment(Pos.CENTER);
//        innergrid.add(hbButtons, 0, 0);
//        grid.add(innergrid, 0, 2, 2, 1);

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


        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((userTextField.getText().length() < 3) && (pwBox.getText().length() < 3)) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Enter username and password!");
                } else {
                    nick = userTextField.getText();
                    password = pwBox.getText();
                    //System.out.println(nick);
                    primaryStage.hide();
                    //stage2.show();
                    //primaryStage.setScene(scene2);
                    Stage createNewProject = new Stage();
                    createNewProject(createNewProject);
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
