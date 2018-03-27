package com.challenge;


import java.util.ArrayList;

public class Album {
    private String albumTitle;
    private String artist;
    private SongList songList;

    public Album(String albumTitle, String artist) {
        this.albumTitle = albumTitle;
        this.artist = artist;
        this.songList = new SongList();
    }
    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getArtist() {
        return artist;
    }
    public ArrayList<Song> getSongs() {
        return this.songList.getSongs();
    }

    public void addSongAlbum(String songTitle, int durationInSec) {
        this.songList.addSongAlbum(songTitle,durationInSec);
    }
    public void removeSongAlbum(String songTitle) {
        this.songList.removeSongAlbum(songTitle);
    }

    public void updateSongAlbum(String currentSongTitle, String newSongTitle, int newDurationInSec) {
        this.songList.updateSongAlbum(currentSongTitle,newSongTitle,newDurationInSec);
    }

    public int searchPositionSong(String songTitle) {
        return this.songList.searchPositionSong(songTitle);
    }

    public int totalDuration() {
        return this.songList.totalDuration();
    }

    public void setNewAlbum(String albumTitle, String artist) {
        this.albumTitle = albumTitle;
        this.artist = artist;
    }

    //return hr:min:sec from total duration in second
    public int[] secToHrMinSec(int durationInSec) {
        int min, sec, hrs, remainder;


        hrs = durationInSec / 3600;
        remainder = durationInSec - (hrs * 3600);
        min = remainder / 60;
        remainder = remainder - (min * 60);
        sec = remainder;

        int[] hrsMinSec = {hrs, min, sec};
        return hrsMinSec;
    }
    //a inner class that hold the ArrayList of songs in the album
    private class SongList {
        private ArrayList<Song> songs;

        private SongList() {
            songs = new ArrayList<Song>();
        }

        private ArrayList<Song> getSongs() {
            return songs;
        }

        private void addSongAlbum(String songTitle, int durationInSec) {
            songs.add(new Song(songTitle, durationInSec));
        }
        private void removeSongAlbum(String songTitle) {
            int songPosition = searchPositionSong(songTitle);
            songs.remove(songPosition);
        }

        private void updateSongAlbum(String currentSongTitle, String newSongTitle, int newDurationInSec) {
            int positionCurrentSong = searchPositionSong(currentSongTitle);
            Song currentSong = songs.get(positionCurrentSong);
            currentSong.setDurationInSec(newDurationInSec);
            currentSong.setSongTitle(newSongTitle);
        }
        //return the index of the song in the arrayList
        private int searchPositionSong(String songTitle) {
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getSongTitle().equals(songTitle)) {
                    return i;
                }
            }
            return -1;
        }
        //add all the duration of songs in the album
        private int totalDuration() {
            int totalDuration = 0;
            ArrayList<Song> songs = getSongs();
            for (int i = 0; i < songs.size(); i++) {
                totalDuration += songs.get(i).getDuration();
            }
            return totalDuration;
        }
    }


}
