import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hand-Geometry-Recogniser");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 500, Color.WHITE);

        TabPane tabPane = new TabPane();

        BorderPane borderPane = new BorderPane();

        Tab addTab = new Tab();
        addTab.setText("Add profile");
        HBox hbox1 = new HBox();
        hbox1.getChildren().add(new Label("Add Profile Here!!!"));
        hbox1.setAlignment(Pos.CENTER);
        addTab.setContent(hbox1);
        addTab.setClosable(false);
        tabPane.getTabs().add(addTab);

        Tab searchTab = new Tab();
        searchTab.setText("Search profile");
        HBox hbox2 = new HBox();
        hbox2.getChildren().add(new Label("Search for a profile here!!!"));
        hbox2.setAlignment(Pos.CENTER);
        searchTab.setContent(hbox2);
        searchTab.setClosable(false);
        tabPane.getTabs().add(searchTab);


        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
