package playlist;

import java.util.ArrayList;

public class Album {
    private String albumTitle;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String albumTitle,String artist) {
        this.songs = new ArrayList<Song>();
        this.albumTitle = albumTitle;
        this.artist = artist;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getArtist() {
        return artist;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setNewAlbum(String albumTitle,String artist) {
        this.albumTitle = albumTitle;
        this.artist = artist;
    }
    public void addSongAlbum(String songTitle,int durationInSec){
        songs.add(new Song(songTitle,durationInSec));
    }
    public void removeSongAlbum(String songTitle){
        int songPosition = searchPositionSong(songTitle);
        songs.remove(songPosition);
    }
    public void updateSongAlbum(String currentSongTitle,String newSongTitle,int newDurationInSec){
        int positionCurrentSong = searchPositionSong(currentSongTitle);
        Song currentSong = songs.get(positionCurrentSong);
        currentSong.setDurationInSec(newDurationInSec);
        currentSong.setSongTitle(newSongTitle);
    }

    public int searchPositionSong(String songTitle){
        for(int i=0;i<songs.size();i++){
            if(songs.get(i).getSongTitle().equals(songTitle)){
                return i;
            }
        }
        return -1;
    }

    public int totalDuration(){
        int totalDuration=0;
        ArrayList<Song> songs = getSongs();
        for(int i=0;i<songs.size();i++){
            totalDuration+=songs.get(i).getDuration();
        }
        return totalDuration;
    }

    public int[] secToHrMinSec(int durationInSec){
        int min,sec,hrs,remainder;


        hrs = durationInSec/3600;
        remainder= durationInSec-(hrs*3600);
        min = remainder / 60;
        remainder = remainder-(min*60);
        sec = remainder;

        int[] hrsMinSec = {hrs,min,sec};
        return hrsMinSec;
    }



}
