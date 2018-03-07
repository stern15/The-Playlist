package playlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Playlist {
    private LinkedList<Song> playlist = new LinkedList<Song>();
    private ArrayList<Album> albums = new ArrayList<Album>();

    public Playlist(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public LinkedList<Song> getPlaylist() {
        return playlist;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public String getArtist(Album album) {
        for (int i = 0; i < getAlbums().size(); i++) {
            if (album.getArtist().equals(getAlbums().get(i).getArtist())) {
                return getAlbums().get(i).getArtist();
            }
        }
        return null;
    }

    public boolean addSongPlaylist(String songPlaylist) {
        for (Album album : getAlbums()) {
            for (Song song : album.getSongs()) {
                if (song.getSongTitle().equals(songPlaylist) ) {
                    if(!playlist.contains(song)){
                        playlist.add(song);
                        return true;
                    }
                    return false;
                }

            }
        }
        return false;
    }
    public boolean removeSongPlaylist(String songPlaylist){
        for (Album album : getAlbums()) {
            for (Song song : album.getSongs()) {
                if (song.getSongTitle().equals(songPlaylist) ) {
                   playlist.remove(song);
                   return true;
                }

            }
        }
        return false;

    }
}


// Current song in the playlist
//Next song in the playlist
//Previous song in the playlist
// Replay current song


