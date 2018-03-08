package com.zgs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Shmily
 * @email shmily_zgs@163.com
 * @date 2018/2/28 11:57
 */
public class MiniApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/zgs/layout/main.fxml"));
        primaryStage.setTitle("MiniApp");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/zgs/css/icon.jpg")));
        Scene scene=new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
