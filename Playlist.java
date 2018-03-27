package com.challenge;


import java.util.ArrayList;
import java.util.LinkedList;

public class Playlist {
    private LinkedList<Song> playlist = new LinkedList<Song>();
    private ArrayList<Album> albums = new ArrayList<Album>();

    Playlist(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public LinkedList<Song> getPlaylist() {
        return playlist;
    }

    private ArrayList<Album> getAlbums() {
        return albums;
    }
    //return the artist name
    public String getArtist(Album album) {
        for (int i = 0; i < getAlbums().size(); i++) {
            if (album.getArtist().equals(getAlbums().get(i).getArtist())) {
                return getAlbums().get(i).getArtist();
            }
        }
        return null;
    }

    public boolean addSongPlaylist(String songPlaylist) {
        for (Album album : albums) {
            for (Song song : album.getSongs()) {
                //check if the song exist in the playlist
                if (song.getSongTitle().equals(songPlaylist)) {
                    if (!playlist.contains(song)) {
                        playlist.add(song);
                        return true;
                    }
                    return false;
                }

            }
        }
        return false;
    }

    public boolean removeSongPlaylist(String songPlaylist) {
        for (Song song : getPlaylist()) {
            if (song.getSongTitle().equals(songPlaylist)) {
                playlist.remove(song);
                return true;
            }
            return false;

        }

        return false;

    }
}


