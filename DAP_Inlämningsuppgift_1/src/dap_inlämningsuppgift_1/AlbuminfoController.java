/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dap_inlämningsuppgift_1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bujamin
 */
public class AlbuminfoController implements Initializable {

    @FXML
    private TableView<Album> albumTable;

    @FXML
    private TableColumn<Album, Integer> albumIdColumn;

    @FXML
    private TableColumn<Album, String> albumNameColumn;

    @FXML
    private TableView<Artist> artistTable;

    @FXML
    private TableColumn<Artist, String> artistFirstNameColumn;

    @FXML
    private TableColumn<Artist, Integer> artistIdColumn;

    @FXML
    private TableColumn<Artist, String> artistLastNameColumn;

    public static ObservableList<Album> ObservableAlbumList;
    public static ObservableList<Artist> ObservableArtistList;

    private static Album selectedAlbum;
    private static Artist selectedArtist;
    private Logics logics = Logics.getInstance();

    
     @FXML
    public void handleArtistFirstNameEditAction(CellEditEvent<Artist, String> t) 
    {   
        ((Artist)t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setArtistFirstName(t.getNewValue());
    }
    
    @FXML
    public void handleArtistLastNameEditAction(CellEditEvent<Artist, String> t) 
    {   
        ((Artist)t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setArtistLastName(t.getNewValue());
    }
    
      @FXML
    public void handleAlbumNameEditAction(CellEditEvent<Album, String> t) 
    {   
        ((Album)t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setAlbumName(t.getNewValue());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableAlbumList = FXCollections.observableArrayList();
        ObservableArtistList = FXCollections.observableArrayList();

        ObservableAlbumList.addAll(Logics.getAlbumArrayList());
        ObservableArtistList.addAll(Logics.getArtistArrayList());

        artistIdColumn.setCellValueFactory(new PropertyValueFactory<Artist, Integer>("artistId"));
        artistFirstNameColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("artistFirstName"));
        artistLastNameColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("artistLastName"));
        albumIdColumn.setCellValueFactory(new PropertyValueFactory<Album, Integer>("albumId"));
        albumNameColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("albumName"));

        //to make cells editable
        //artistFirstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //artistLastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //sets all items in table
        artistTable.setItems(ObservableArtistList);
        albumTable.setItems(ObservableAlbumList);
        
        //to make cells editable
        artistFirstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        artistLastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
       albumNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    @FXML
    private void albumSelectionAction(MouseEvent event) {
        selectedAlbum = albumTable.getSelectionModel().getSelectedItem();
       
        System.out.println("Selected" + selectedAlbum.getAlbumName());
        ObservableArtistList.clear();
        Logics.getAlbumArrayList().stream()
                .filter(i -> i.getAlbumId() == selectedAlbum.getAlbumId())
                .forEach(j -> ObservableArtistList.addAll(j.getArtistArrayList()));

    }
    
    @FXML
    private void returnToFirstScene(ActionEvent event) throws IOException{
    
        Parent root2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root2);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        }

    @FXML
    private void deleteArtistButtonAction(ActionEvent event) {
    selectedArtist = artistTable.getSelectionModel().getSelectedItem();
    
     for (int i = 0; i < Logics.getAlbumArrayList().size(); i++)
            {
                if(selectedAlbum.getAlbumId() == Logics.getAlbumArrayList().get(i).getAlbumId()){
                for (int j = 0; j < Logics.getAlbumArrayList().get(i).getArtistArrayList().size(); j++)
                {
                   if (selectedArtist.getArtistId() == Logics.getAlbumArrayList().get(i).getArtistArrayList().get(j).getArtistId())
                        {
                            Logics.getAlbumArrayList().get(i).getArtistArrayList().remove(Logics.getAlbumArrayList().get(i).getArtistArrayList().get(j));

                        }

                    }
            }
             ObservableArtistList.remove(selectedArtist);

            }
            for(int i= 0; i< Logics.getArtistArrayList().size(); i++){
                if(selectedArtist.getArtistId() == Logics.getArtistArrayList().get(i).getArtistId()){
                    for(int j = 0 ; j < Logics.getArtistArrayList().get(i).getAlbumArrayList().size(); j++){
                        if(selectedAlbum.getAlbumId()== Logics.getArtistArrayList().get(i).getAlbumArrayList().get(j).getAlbumId()){
                            
                           Logics.getArtistArrayList().get(i).getAlbumArrayList().remove(Logics.getArtistArrayList().get(i).getAlbumArrayList().get(j));
                        }
                    }
                }    
           }
    
}

    


}
