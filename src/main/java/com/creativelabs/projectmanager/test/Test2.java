
package com.creativelabs.projectmanager.test;

import com.creativelabs.projectmanager.tasks.Board;
import com.creativelabs.projectmanager.tasks.Task;
import com.creativelabs.projectmanager.tasks.TaskList;
import com.creativelabs.projectmanager.tasks.User;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Control;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Sample application that shows how the sized of controls can be managed.
 * Sample is for demonstration purposes only, most controls are inactive.
 */
public class Test2 extends Application {

    // Define buttons here for access by multiple methods
    private final Button btnApply = new Button("Apply");
    private final Button btnContinue = new Button("Continue");
    private final Button btnExit = new Button("Exit");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Test2.class, args);
    }

    @Override
    public void start(Stage primaryStage) {

        //users
        User user1 = new User("developer1");
        User user2 = new User("projectmanager1");
        User user3 = new User("developer2");
        User user4 = new User("developer3");
        //tasks
        Task task1 = new Task("Microservice for taking temperature",
                "Write and test the microservice taking\n" +
                        "the temperaure from external service",
                user1,
                user2,
                LocalDate.now().minusDays(20),
                LocalDate.now().plusDays(30));
        Task task2 = new Task("HQLs for analysis",
                "Prepare some HQL queries for analysis",
                user1,
                user2,
                LocalDate.now().minusDays(20),
                LocalDate.now().minusDays(5));
        Task task3 = new Task("Temperatures entity",
                "Prepare entity for temperatures",
                user3,
                user2,
                LocalDate.now().minusDays(20),
                LocalDate.now().plusDays(15));
        Task task4 = new Task("Own logger",
                "Refactor company logger to meet our needs",
                user3,
                user2,
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(25));
        Task task5 = new Task("Optimize searching",
                "Archive data searching has to be optimized",
                user4,
                user2,
                LocalDate.now(),
                LocalDate.now().plusDays(5));
        Task task6 = new Task("Use Streams",
                "use Streams rather than for-loops in predictions",
                user4,
                user2,
                LocalDate.now().minusDays(15),
                LocalDate.now().minusDays(2));
        //taskLists
        TaskList taskListToDo = new TaskList("To do");
        taskListToDo.addTask(task1);
        taskListToDo.addTask(task3);
        TaskList taskListInProgress = new TaskList("In progress");
        taskListInProgress.addTask(task5);
        taskListInProgress.addTask(task4);
        taskListInProgress.addTask(task2);
        TaskList taskListDone = new TaskList("Done");
        taskListDone.addTask(task6);

        //board
        Board project = new Board("Project Weather Prediction");
        project.addTaskList(taskListToDo);
        project.addTaskList(taskListInProgress);
        project.addTaskList(taskListDone);






        // Make Exit button bigger by using larger font for label
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
        primaryStage.setTitle("Sizing and Aligning");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
     * Creates the UI for the sizing sample, which demonstrates ways to manage
     * the size of controls when you don't want the default sizes.
     */
    private Pane sizingSample() {

        //users
        User user1 = new User("developer1");
        User user2 = new User("projectmanager1");
        User user3 = new User("developer2");
        User user4 = new User("developer3");
        //tasks
        Task task1 = new Task("Microservice for taking temperature",
                "Write and test the microservice taking\n" +
                        "the temperaure from external service",
                user1,
                user2,
                LocalDate.now().minusDays(20),
                LocalDate.now().plusDays(30));
        Task task2 = new Task("HQLs for analysis",
                "Prepare some HQL queries for analysis",
                user1,
                user2,
                LocalDate.now().minusDays(20),
                LocalDate.now().minusDays(5));
        Task task3 = new Task("Temperatures entity",
                "Prepare entity for temperatures",
                user3,
                user2,
                LocalDate.now().minusDays(20),
                LocalDate.now().plusDays(15));
        Task task4 = new Task("Own logger",
                "Refactor company logger to meet our needs",
                user3,
                user2,
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(25));
        Task task5 = new Task("Optimize searching",
                "Archive data searching has to be optimized",
                user4,
                user2,
                LocalDate.now(),
                LocalDate.now().plusDays(5));
        Task task6 = new Task("Use Streams",
                "use Streams rather than for-loops in predictions",
                user4,
                user2,
                LocalDate.now().minusDays(15),
                LocalDate.now().minusDays(2));
        //taskLists
        TaskList taskListToDo = new TaskList("To do");
        taskListToDo.addTask(task1);
        taskListToDo.addTask(task3);
        TaskList taskListInProgress = new TaskList("In progress");
        taskListInProgress.addTask(task5);
        taskListInProgress.addTask(task4);
        taskListInProgress.addTask(task2);
        TaskList taskListDone = new TaskList("Done");
        taskListDone.addTask(task6);

        //board
        Board project = new Board("Project Weather Prediction");
        project.addTaskList(taskListToDo);
        project.addTaskList(taskListInProgress);
        project.addTaskList(taskListDone);

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(30, 150, 20, 120));


        Text welcomeUser = new Text(project.getName());

        welcomeUser.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        ListView<String> lvList = new ListView<>();

        ObservableList<String> items = FXCollections.observableArrayList ();
        items.add(task1.getTitle());
        items.add(task2.getTitle());
        items.add(task3.getTitle());


        /*ListView<String> lvList = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "Hot dog", "Hamburger", "French fries",
                "Carrot sticks", "Chicken salad");*/
        lvList.setItems(items);
        lvList.setMaxHeight(Control.USE_PREF_SIZE);
        lvList.setPrefWidth(350.0);

        border.setTop(welcomeUser);
        border.setLeft(lvList);
        border.setRight(createButtonColumn());
        border.setBottom(createButtonRow());  // Uses a tile pane for sizing
//        border.setBottom(createButtonBox());  // Uses an HBox, no sizing

        return border;
    }

    /*
     * Creates the UI for the alignment sample, which demonstrates ways to manage
     * the alignment of controls when you don't want the default alignment.
     */
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



        hbButtons.getChildren().addAll(btnSubmit, btnClear, btnExit2);
        grid.add(hbButtons, 0, 2, 2, 1);

        /* Uncomment the following statements to bottom-align the buttons */
//        hbButtons.setAlignment(Pos.BOTTOM_CENTER);
//        GridPane innergrid = new GridPane();
//        innergrid.setAlignment(Pos.CENTER);
//        innergrid.add(hbButtons, 0, 0);
//        grid.add(innergrid, 0, 2, 2, 1);

        return grid;
    }

    /*
     * Creates a column of buttons and makes them all the same width as the
     * largest button.
     */
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
}