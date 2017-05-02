/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dap_inlämningsuppgift_1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bujamin
 */
public class Logics {
    
    private static Logics instance; //andra steg instansiera
    private static List<Artist> artistArrayList;
    private static List<Album> albumArrayList;
    
    private Logics(){ // första steg default konstruktor
                   
         artistArrayList = new ArrayList<>();
         albumArrayList = new ArrayList<>();
        Album album1 = new Album(1, "Thriller", 1990);
        Album album2 = new Album(2, "21", 2016);
        Album album3 = new Album(3, "The Bodyguard", 1993);
        Album album4 = new Album(4, "Supernatural", 1993);
        
        Artist artist1 = new Artist(1, "Michael", "Jackson");
        artist1.getAlbumArrayList().add(album1);
               
        Artist artist2 = new Artist(2, "Adele", " ");
        artist2.getAlbumArrayList().add(album2);
        
        Artist artist3 = new Artist(3, "Whitney", "Houston");
        artist3.getAlbumArrayList().add(album3);
        
        Artist artist4 = new Artist(4, "Santana", " ");
        artist4.getAlbumArrayList().add(album4);
        
        artistArrayList.add(artist1);
        artistArrayList.add(artist2);
        artistArrayList.add(artist3);
        artistArrayList.add(artist4);
        
        album1.getArtistArrayList().add(artist1);
        album2.getArtistArrayList().add(artist2);
        album2.getArtistArrayList().add(artist1);
        album3.getArtistArrayList().add(artist3);
        album4.getArtistArrayList().add(artist4);
     
        albumArrayList.add(album1);
        albumArrayList.add(album2);
        albumArrayList.add(album3);
        albumArrayList.add(album4);
        
    }
    
   
    public static Logics getInstance(){
        if(instance==null){
            instance = new Logics();
            }
        return instance;
    }

    public static List<Artist> getArtistArrayList() {
        return artistArrayList;
    }

    public static List<Album> getAlbumArrayList() {
        return albumArrayList;
    }
    
    public static void addAlbums(Album newAlbum){
        albumArrayList.add(newAlbum);
    }
    
}
