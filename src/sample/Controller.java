package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {
    public ListView listV;
    public TextField txtField;

    public void onClick(ActionEvent actionEvent) {
        String s = txtField.getText();

        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File(System.getProperty("user.home")));
        File f = dc.getInitialDirectory();

        new Thread(() -> {
            for(File file: f.listFiles()){
                if(file.getPath().contains(s)){
                    Platform.runLater(() -> listV.getItems().add(file));
                }
            }
        }).start();

    }
}
