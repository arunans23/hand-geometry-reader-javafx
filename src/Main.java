import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application{

    UserLab userLab;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        userLab = UserLab.get();
        primaryStage.setTitle("Hand-Geometry-Recogniser");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 500, Color.WHITE);

        primaryStage.setOnCloseRequest( e -> {
            MysqlCon.closeConnection();
            primaryStage.close();
        });

        TabPane tabPane = new TabPane();

        BorderPane borderPane = new BorderPane();

        Tab addTab = new Tab();
        addTab.setText("Add profile");

        GridPane addGridPane = new GridPane();
        addGridPane.setPadding(new Insets(10, 10, 10, 10));
        addGridPane.setVgap(8);
        addGridPane.setHgap(10);

        Label nameLabel = new Label("Fullname");
        GridPane.setConstraints(nameLabel, 1, 0);

        TextField nameText = new TextField();
        GridPane.setConstraints(nameText, 2, 0);

        Label indexLabel = new Label("Index No");
        GridPane.setConstraints(indexLabel, 1, 1);

        TextField indexText = new TextField();
        GridPane.setConstraints(indexText, 2, 1);

        Label tLlabel = new Label("Thumb Length");
        GridPane.setConstraints(tLlabel, 0, 2);

        TextField tLtext = new TextField();
        GridPane.setConstraints(tLtext, 1, 2);

        Label iFLlabel = new Label("Index Finger Length");
        GridPane.setConstraints(iFLlabel, 0, 3);

        TextField iFLtext = new TextField();
        GridPane.setConstraints(iFLtext, 1, 3);

        Label mFLlabel = new Label("Middle FInger Length");
        GridPane.setConstraints(mFLlabel, 0, 4);

        TextField mFLtext = new TextField();
        GridPane.setConstraints(mFLtext, 1, 4);

        Label rFLlabel = new Label("Ringer Finger Length");
        GridPane.setConstraints(rFLlabel, 0, 5);

        TextField rFLtext = new TextField();
        GridPane.setConstraints(rFLtext, 1, 5);

        Label pFlabel = new Label("Pinkie Length");
        GridPane.setConstraints(pFlabel, 0, 6);

        TextField pLtext = new TextField();
        GridPane.setConstraints(pLtext, 1, 6);

        Label tWlabel = new Label("Thumb Width");
        GridPane.setConstraints(tWlabel, 2, 2);

        TextField tWtext = new TextField();
        GridPane.setConstraints(tWtext, 3, 2);

        Label iFWlabel = new Label("Index Finger Width");
        GridPane.setConstraints(iFWlabel, 2, 3);

        TextField iFWtext = new TextField();
        GridPane.setConstraints(iFWtext, 3, 3);

        Label mFWlabel = new Label("Middle FInger Width");
        GridPane.setConstraints(mFWlabel, 2, 4);

        TextField mFWtext = new TextField();
        GridPane.setConstraints(mFWtext, 3, 4);

        Label rFWlabel = new Label("Ringer Finger Width");
        GridPane.setConstraints(rFWlabel, 2, 5);

        TextField rFWtext = new TextField();
        GridPane.setConstraints(rFWtext, 3, 5);

        Label pWlabel = new Label("Pinkie Width");
        GridPane.setConstraints(pWlabel, 2, 6);

        TextField pWtext = new TextField();
        GridPane.setConstraints(pWtext, 3, 6);

        Button addButton = new Button("Add User");
        GridPane.setConstraints(addButton, 0, 7);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userLab.addUser(new User(nameText.getText(), indexText.getText(),
                        Integer.parseInt(tLtext.getText()), Integer.parseInt(iFLtext.getText()),
                        Integer.parseInt(mFLtext.getText()), Integer.parseInt(rFLtext.getText()),
                        Integer.parseInt(pLtext.getText()), Integer.parseInt(tWtext.getText()),
                        Integer.parseInt(iFWtext.getText()), Integer.parseInt(mFWtext.getText()),
                        Integer.parseInt(rFWtext.getText()), Integer.parseInt(pWtext.getText())));
            }
        });

        addGridPane.getChildren().addAll(   nameLabel, nameText, indexLabel, indexText,
                                            tLlabel, iFLlabel, mFLlabel, rFLlabel, pFlabel,
                                            tLtext,iFLtext, mFLtext, rFLtext, pLtext,
                                            tWlabel, iFWlabel, mFWlabel, rFWlabel, pWlabel,
                                            tWtext, iFWtext, mFWtext, rFWtext, pWtext,
                                            addButton);



        HBox hbox1 = new HBox();
        hbox1.getChildren().add(addGridPane);
        hbox1.setAlignment(Pos.CENTER);
        addTab.setContent(hbox1);
        addTab.setClosable(false);
        tabPane.getTabs().add(addTab);

        Tab searchTab = new Tab();
        searchTab.setText("Search profile");
        HBox hbox2 = new HBox();
        hbox2.getChildren().add(new Label("Search for profile here"));
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
