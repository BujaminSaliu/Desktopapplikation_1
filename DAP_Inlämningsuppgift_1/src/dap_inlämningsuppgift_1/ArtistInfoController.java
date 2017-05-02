/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dap_inlämningsuppgift_1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bujamin
 */
public class ArtistInfoController implements Initializable {

    @FXML
    private TableView<Album> table;
    
    @FXML
    private TableColumn<Album, Integer> id;  
    
    @FXML
    private TableColumn<Album, String> name;

    @FXML
    private TableColumn<Album, String> album;

    
    
    @FXML
    private Button backBtn;




@FXML
    private void backToFirstSceneButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));    
        Scene s = new Scene(root);
        Stage stage = (Stage) ( (Node)event.getSource()).getScene().getWindow(); // Hämta knappen, hämta stagen
        stage.setScene(s); // byter ut gamla stage mot nya, sätt en ny stage
        stage.show();
        stage.setTitle("DAP Inlämningsuppgift Bujamin");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
