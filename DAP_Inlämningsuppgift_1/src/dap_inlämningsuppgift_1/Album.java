
package dap_inlämningsuppgift_1;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Bujamin
 */
public class Album {
    private final IntegerProperty albumId;
    private final StringProperty albumName;
    private final IntegerProperty albumReleaseDate;
    private final ArrayList<Artist> artistArrayList = new ArrayList<>();

    public ArrayList<Artist> getArtistArrayList() {
        return artistArrayList;
    }

    //Default constructor
    public Album(){
        this.albumId = new SimpleIntegerProperty();
        this.albumName = new SimpleStringProperty();
        this.albumReleaseDate = new SimpleIntegerProperty();
    }
    
    //Constructor
    public Album(int albumId, String albumName, int albumReleaseDate){
        this.albumId = new SimpleIntegerProperty(albumId);
        this.albumName = new SimpleStringProperty(albumName);
        this.albumReleaseDate = new SimpleIntegerProperty(albumReleaseDate);
    }
    
    //getter
    public final int getAlbumId() {
        return albumId.get();
    }

    public final String getAlbumName() {
        return albumName.get();
    }

    public final int getAlbumReleaseDate() {
        return albumReleaseDate.get();
    }
    
    //setter
    public final void setAlbumId(int newId){
        albumId.set(newId);
    }
    
    public final void setAlbumName(String newAlbumName){
        albumName.set(newAlbumName);
    }
    
    public final void setAlbumreleaseDate(int newReleaseDate){
        albumReleaseDate.set(newReleaseDate);
    }
    
    public void loadImage(String picName, ImageView img) {
        Image image = new Image(getClass().getResourceAsStream(picName + ".jpg"));
        img.setImage(image);
    }
}
