package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {
    public ListView listV;
    public TextField txtField;
    public Button btn;
    public Button prekidBtn;
    public Thread nit;
    public boolean stopSearch;

    public void onClick(ActionEvent actionEvent) {
        String s = txtField.getText();

        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File(System.getProperty("user.home")));
        File f = dc.getInitialDirectory();
/*
        nit = new Thread(() -> {
            btn.getStyleClass().add("dugmeBoja");
            btn.setDisable(true);
            prekidBtn.setDisable(false);
            prekidBtn.getStyleClass().removeAll("dugmeBoja");
            prekidBtn.getStyleClass().add("dugmePrekid");

            for(File file: f.listFiles()){
                if(file.isDirectory()){

                } else if(file.getPath().contains(s)){
                    Platform.runLater(() -> listV.getItems().add(file.getPath()));
                }
            }

            btn.setDisable(false);
            btn.getStyleClass().removeAll("dugmeBoja");
            btn.getStyleClass().add("dugmePrekid");

        });
        nit.start();*/

        stopSearch = false;
        listV.getItems().clear();
        btn.setDisable(true);
        prekidBtn.setDisable(false);



    }

    public void searchRec(File dir){
        for(File file: dir.listFiles()){
            if(stopSearch) break;
            else if(file.isDirectory()){
                searchRec(file);
            }
            else if(file.isFile() && file.getName().contains(txtField.getText())) {
                Platform.runLater(() -> listV.getItems().add(file.getPath()));
            }
        }
    }

    public void prekidClick(ActionEvent actionEvent) {
        stopSearch = false;
    }

    public void prekidEnable(KeyEvent keyEvent) {
        prekidBtn.setDisable(true);
    }
}
