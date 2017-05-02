/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dap_inlämningsuppgift_1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Bujamin
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Artist> artistTable;

    @FXML
    private TableColumn<Artist, String> artistFirstNameColumn;

    @FXML
    private TableColumn<Artist, String> artistLastNameColumn;

    @FXML
    private TableView<Album> albumTable;

    @FXML
    private TableColumn<Album, Integer> albumIdColumn;

    @FXML
    private TableColumn<Album, String> albumNameColumn;

    @FXML
    private TableColumn<Album, String> albumreleaseDateColumn;

    @FXML
    private Button searchBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button artistInfoBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField searchArtistTextField;

    @FXML
    private TextField searchAlbumTextField;

    @FXML
    private TextField addName;

    @FXML
    private TextField addAlbumNameTextField;
    @FXML
    private TextField addAlbumReleaseDateTextField;

    @FXML
    private TextField addId;

    @FXML
    private Label statusLabel;

    @FXML
    private Label artistNameLabel;

    @FXML
    private ImageView imageView;

    private Artist selectedArtist;

    private int counter = 0;
    private Logics logics = Logics.getInstance();

    public static List<Artist> artistArrayList = new ArrayList<>();
    public static List<Album> albumArrayList = new ArrayList<>();

    public static ObservableList<Artist> ObservableArtistList;
    public static ObservableList<Album> ObservableAlbumList;

    private final Album albumImage = new Album();

//    @FXML
//    private void showImageOnClickedItem(MouseEvent event) {
//        selectedArtist = table.getSelectionModel().getSelectedItem();
//        System.out.println("Selected artist: " + selectedArtist.getArtistName());
//        statusLabel.setText("");
//        artistNameLabel.setText(selectedArtist.getArtistName());
//        switch (selectedArtist.getArtistId()) {
//            
//            case 1:
//                albumImage.loadImage("ginuwine", imageView);
//                break;
//
//            case 2:
//                albumImage.loadImage("bobbyV", imageView);
//                break;
//
//            case 3:
//                albumImage.loadImage("gunit", imageView);
//                break;
//
//            case 4:
//                albumImage.loadImage("darin", imageView);
//                break;
//
//            default:
//                albumImage.loadImage("default", imageView);
//                break;
//        }
//        
//    }
//    Delete button clicked
    @FXML
    private void deleteButtonAction(ActionEvent e) {
        
         //resets image
//        imageView.setImage(null);
        //Alert the user for delete confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation");
        alert.setHeaderText("You can not undo this!");
        alert.setGraphic(new ImageView(this.getClass().getResource("warning_1.png").toString()));
        alert.setContentText("Are you sure you want to delete artist" + " " + selectedArtist.getArtistFirstName()+ "?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get()==ButtonType.OK){
            selectedArtist = artistTable.getSelectionModel().getSelectedItem();
            ObservableArtistList.remove(selectedArtist);
            statusLabel.setText(selectedArtist.getArtistFirstName()+ " " + "has been deleted. ");
            
        }else if(selectedArtist.getArtistFirstName()==null){
            statusLabel.setText(" ");
        }
        //ObservableAlbumList.clear();
        Album selectedAlbum = albumTable.getSelectionModel().getSelectedItem();
        for (int i = 0; i < Logics.getArtistArrayList().size(); i++) {
            if (selectedArtist.getArtistId() == Logics.getArtistArrayList().get(i).getArtistId()) {
                for (int j = 0; j < Logics.getArtistArrayList().get(i).getAlbumArrayList().size(); j++) {
                    if (selectedAlbum.getAlbumId() == Logics.getArtistArrayList().get(i).getAlbumArrayList().get(j).getAlbumId()) {
                        Logics.getArtistArrayList().get(i).getAlbumArrayList().remove(selectedAlbum);
                        ObservableAlbumList.remove(selectedAlbum);
                    }

                }

            }
        }

        for (int i = 0; i < Logics.getAlbumArrayList().size(); i++) {
            if (selectedAlbum.getAlbumId() == Logics.getAlbumArrayList().get(i).getAlbumId()) {
                for (int j = 0; j < Logics.getAlbumArrayList().get(i).getArtistArrayList().size(); j++) {
                    if (selectedArtist.getArtistId() == Logics.getAlbumArrayList().get(i).getArtistArrayList().get(j).getArtistId()) {
                        Logics.getAlbumArrayList().get(i).getArtistArrayList().remove(Logics.getAlbumArrayList().get(i).getArtistArrayList().get(j));

                    }

                    // 
                }
            }
        }

        // ObservableArtistList.remove(selectedArtist);
        //resets image
//        imageView.setImage(null);
//        //Alert the user for delete confirmation
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Delete confirmation");
//        alert.setHeaderText("You can not undo this!");
//        alert.setGraphic(new ImageView(this.getClass().getResource("warning_1.png").toString()));
//        alert.setContentText("Are you sure you want to delete artist" + " " + selectedArtist.getArtistName() + "?");
//        Optional<ButtonType> action = alert.showAndWait();
//        if(action.get()==ButtonType.OK){
//            selectedArtist = table.getSelectionModel().getSelectedItem();
//            ObservableArtistList.remove(selectedArtist);
//            statusLabel.setText(selectedArtist.getArtistName() + " " + "has been deleted. ");
//            
//        }else if(selectedArtist.getArtistName()==null){
//            statusLabel.setText(" ");
//        }
//        System.out.println("List after removing: " + ObservableArtistList.size());
    }

    //Add button clicked
//    @FXML
//    private void addButtonAction(ActionEvent e){
//        TextInputDialog dialog = new TextInputDialog();
//        dialog.setTitle("Add an artist");
//        
//        dialog.setContentText("Please artist name:");
//
//        // Traditional way to get the response value.
//        Optional<String> result = dialog.showAndWait();
//        if (result.isPresent()) {
//            selectedArtist = table.getSelectionModel().getSelectedItem();
//        ObservableArtistList.add(selectedArtist);
//            System.out.println("Artist name: " + result.get());
//            Artist artist = new Artist();
//            ObservableArtistList.add(artist);
//            int row = ObservableArtistList.size();
//            artist.setId(row++);
//             System.out.println(artist.getId());
//            // Select the new row
//            table.requestFocus();
//            table.getSelectionModel().select(row);
//
//            table.getFocusModel().focus(row);
//

//            ObservableArtistList.add(new Artist(Integer.parseInt(addId.getText()), addName.getText(), addAlbum.getText()));
//            statusLabel.setText("Artist: " + addName.getText() + " was added!");
//            addName.clear();
//                addAlbum.clear();
//                addId.clear();
//                
//                System.out.println(addName.getText());
//            }
//            }    
    @FXML
    public void handleArtistFirstNameEditAction(CellEditEvent<Artist, String> t) {
        ((Artist) t.getTableView().getItems().get(
                t.getTablePosition().getRow())).setArtistFirstName(t.getNewValue());
    }

    @FXML
    public void handleArtistLastNameEditAction(CellEditEvent<Artist, String> t) {
        ((Artist) t.getTableView().getItems().get(
                t.getTablePosition().getRow())).setArtistLastName(t.getNewValue());
    }

    @FXML
    public void handleAlbumNameEditAction(CellEditEvent<Album, String> t) {
        ((Album) t.getTableView().getItems().get(
                t.getTablePosition().getRow())).setAlbumName(t.getNewValue());
    }

//     @FXML
//    public void handleAlbumReleaseDateEditAction(CellEditEvent<Album, String> t) 
//    {   
//        ((Album)t.getTableView().getItems().get(
//                    t.getTablePosition().getRow())).setAlbumName(t.getNewValue());
//    }
    @FXML
    private void artistInfoButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Artist info.fxml"));
        Scene s = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Hämta knappen, hämta stagen
        stage.setScene(s); // byter ut gamla stage mot nya, sätt en ny stage
        stage.show();
        stage.setTitle("DAP Inlämningsuppgift Bujamin");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableArtistList = FXCollections.observableArrayList();
        ObservableAlbumList = FXCollections.observableArrayList();

        ObservableArtistList.addAll(Logics.getArtistArrayList());

        ObservableAlbumList.addAll(Logics.getAlbumArrayList());

        artistFirstNameColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("artistFirstName"));
        artistLastNameColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("artistLastName"));
        albumIdColumn.setCellValueFactory(new PropertyValueFactory<Album, Integer>("albumId"));
        albumNameColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("albumName"));
        albumreleaseDateColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("albumReleaseDate"));

        //to make cells editable
        artistFirstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        artistLastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        albumNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //sets all items in table
        artistTable.setItems(ObservableArtistList);
        albumTable.setItems(ObservableAlbumList);

        //Search artist
        searchArtistTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableArtistList.clear();
            Logics.getArtistArrayList().stream()
                    .filter(i -> i.getArtistFirstName().toLowerCase().startsWith(searchArtistTextField.getText().toLowerCase())
                            || i.getArtistLastName().toLowerCase().startsWith(searchArtistTextField.getText().toLowerCase()))
                    .forEach(j -> ObservableArtistList.addAll(j));

        });

        searchAlbumTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableAlbumList.clear();
            Logics.getAlbumArrayList().stream()
                    .filter(i -> i.getAlbumName().toLowerCase().startsWith(searchAlbumTextField.getText().toLowerCase()))
                    .forEach(j -> ObservableAlbumList.addAll(j));

        });
    }

    @FXML
    private void insertAlbumButtonAction() {
        boolean check = false;
        boolean check2 = false;
        boolean notExisted = true;
        Album album = new Album();
        int albumId = 0;
        int release = Integer.parseInt(addAlbumReleaseDateTextField.getText());
        //List<Album> addAlbum = new ArrayList<>();
        ObservableAlbumList.clear();

        //if the album exists already in the AlbumArrayList
        for (int i = 0; i < Logics.getAlbumArrayList().size(); i++) {
            if (addAlbumNameTextField.getText().equalsIgnoreCase(Logics.getAlbumArrayList().get(i).getAlbumName())
                    && release == Logics.getAlbumArrayList().get(i).getAlbumReleaseDate()) {
                albumId = Logics.getAlbumArrayList().get(i).getAlbumId();
                check = true;
            }
        }
        if (check) {
            for (int i = 0; i < Logics.getArtistArrayList().size(); i++) {
                //to check the if the album exists already to use similar id and not new one
                if (Logics.getArtistArrayList().get(i).getArtistId() == selectedArtist.getArtistId()) {

                    for (int j = 0; j < Logics.getArtistArrayList().get(i).getAlbumArrayList().size(); j++) {

                        //check if the movie does exist in the selected artist
                        if (addAlbumNameTextField.getText().equalsIgnoreCase(Logics.getArtistArrayList().get(i).getAlbumArrayList().get(j).getAlbumName())
                                && release == Logics.getArtistArrayList().get(i).getAlbumArrayList().get(j).getAlbumReleaseDate()) {
                            //ObservableAlbumList.add(Logics.getArtistArrayList().get(i).getAlbumArrayList().get(j));
                            //message
                            // break;
                        } else {
                            check2 = true;
                        }
                    }

                }

            }

            //if the movie doesn't exist in the selected actor it will go to other actors
            //but if it does exist in other actor's movieArrayList
            if (check2) {
                ObservableAlbumList.clear();
                album = new Album(albumId, addAlbumNameTextField.getText(), release);
                for (int i = 0; i < Logics.getArtistArrayList().size(); i++) {
                    if (Logics.getArtistArrayList().get(i).getArtistId() == selectedArtist.getArtistId()) {

                        Logics.getArtistArrayList().get(i).getAlbumArrayList().add(album);
                        ObservableAlbumList.addAll(Logics.getArtistArrayList().get(i).getAlbumArrayList());

                        break;
                    }

                }

                //Adding the person in to the new movie arrayList
                for (int i = 0; i < Logics.getArtistArrayList().size(); i++) {
                    if (Logics.getArtistArrayList().get(i).getArtistId() == albumId) {
                        Logics.getAlbumArrayList().get(i).getArtistArrayList().add(new Artist(selectedArtist.getArtistId(), selectedArtist.getArtistFirstName(), selectedArtist.getArtistLastName()));
                    }

                }
            }
        } else {
            ObservableAlbumList.clear();
            //When a new music to be added 
            //warningMessage.setText("");
            //movieObservableList.clear();
            int newAlbumId = (Logics.getAlbumArrayList().size() + 1);
            album = new Album(newAlbumId, addAlbumNameTextField.getText(), release);
            for (int i = 0; i < Logics.getArtistArrayList().size(); i++) {

                //to check the added movie in the selected person
                if (Logics.getArtistArrayList().get(i).getArtistId() == selectedArtist.getArtistId()) {
                    //logic.getAllArtistsArrayList().get(i).getMovieArrayList().add(mc);
                    Logics.addAlbums(album);
                    Logics.getArtistArrayList().get(i).setAlbumArrayList(album);
                    //logic.getAllArtistsArrayList().get(i).setMovieArrayList(album);
                    //logic.addArtistInMusicArray(actorId, parts[0], parts[1], actorAge);
                    //movieArrayList.addAll(logic.getAllArtistsArrayList().get(i).getMovieArrayList());
                    ObservableAlbumList.addAll(Logics.getArtistArrayList().get(i).getAlbumArrayList());
                }

            }

            //Adding the person in to the new movie arrayList
            for (int i = 0; i < Logics.getAlbumArrayList().size(); i++) {
                if (Logics.getAlbumArrayList().get(i).getAlbumId() == newAlbumId) {
                    Logics.getAlbumArrayList().get(i).getArtistArrayList().add(new Artist(selectedArtist.getArtistId(), selectedArtist.getArtistFirstName(), selectedArtist.getArtistLastName()));
                }
            }

        }
    }

//    private void loadImage(String picName) {
//        Image image = new Image(getClass().getResourceAsStream(picName + ".jpg"));
//        imageView.setImage(image);
//    }
    @FXML
    private void ArtistSelectionAction(MouseEvent event) {
        selectedArtist = artistTable.getSelectionModel().getSelectedItem();
        ObservableAlbumList.clear();
        Logics.getArtistArrayList().stream()
                .filter(i -> i.getArtistId() == selectedArtist.getArtistId())
                .forEach(j -> ObservableAlbumList.addAll(j.getAlbumArrayList()));

//        for (int i = 0; i <artistArrayList.size(); i++) {
//            if(selectedArtist.getArtistId()==artistArrayList.get(i).getArtistId()){
//                for (int j = 0; j < artistArrayList.get(i).getAlbumArrayList().size(); j++) {
//                    ObservableAlbumList.add(artistArrayList.get(i).getAlbumArrayList().get(j));
//                }
//                
//            }
//            
//        }
    }

    @FXML
    private void showAlbumInfo(ActionEvent event) throws IOException {

        Parent root2 = FXMLLoader.load(getClass().getResource("Albuminfo.fxml"));
        Scene scene = new Scene(root2);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
