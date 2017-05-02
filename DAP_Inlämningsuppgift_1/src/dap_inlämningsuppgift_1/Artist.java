/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dap_inlämningsuppgift_1;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Bujamin
 */
public class Artist {
    private final IntegerProperty artistId;
    private final StringProperty artistFirstName;
    private final StringProperty artistLastName;
    private final ArrayList<Album> albumArrayList = new ArrayList<>();

    public Artist() {
    this(0,null, null);
    }
    


    //Constructor
    public Artist(int artistId, String artistFirstName, String artistLastName){
        this.artistId = new SimpleIntegerProperty(artistId);
        this.artistFirstName = new SimpleStringProperty(artistFirstName);
        this.artistLastName = new SimpleStringProperty(artistLastName);
    }



    //getters
    
    public final int getArtistId(){
        return artistId.get();
    }
    public final String getArtistFirstName(){
        return artistFirstName.get();
    }    
    
    public final String getArtistLastName(){
        return artistLastName.get();
    }
    

    //setters    
    public final void setArtistId(int newArtistId){
        artistId.set(newArtistId);
    }
    
    public  final void setArtistFirstName(String newArtistFirstName){
        artistFirstName.set(newArtistFirstName);
    }
    
    public final void setArtistLastName(String newArtistLastName){
        artistLastName.set(newArtistLastName);
    }
    
    //property getters
    
    public IntegerProperty getIdProperty(){
        return artistId;
    }
    
    public StringProperty getArtistFirstNameProperty(){
        return artistFirstName;
    }   
    
    public StringProperty getArtistLastNameProperty(){
        return artistLastName;
    } 

    public ArrayList<Album> getAlbumArrayList() {
        return albumArrayList;
    }
    
    public void setAlbumArrayList(Album newAlbum){
        albumArrayList.add(newAlbum);
    }
    
}

