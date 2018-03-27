package com.challenge;


import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Album> albums = new ArrayList<Album>();
    private static Playlist playlist = new Playlist(albums);
    private static Album album;


    public static void main(String[] args) {
        int action = 0;
        boolean flag = true;
        printMenuInstruction();
        while (flag) {
            System.out.println("\n\nEnter your choice :");
            try {
                action = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\n\nThe value you have input is not a valid integer\n\n");
                break;
            }
            scanner.nextLine();


            switch (action) {
                case 0:
                    printMenuInstruction();
                    break;
                case 1:
                    addAlbum();
                    break;
                case 2:
                    removeAlbum();
                    break;
                case 3:
                    updateAlbum();
                    break;
                case 4:
                    printAlbums();
                    break;
                case 5:
                    addSongAlbum();
                    break;
                case 6:
                    removeSongAlbum();
                    break;
                case 7:
                    updateSongAlbum();
                    break;
                case 8:
                    printSongAlbum();
                    break;
                case 9:
                    addSongPlaylist();
                    break;
                case 10:
                    removeSongPlaylist();
                    break;
                case 11:
                    playlistOption();
                    break;
                case 12:
                    printAllPlaylist();
                    break;
                case 13:
                    System.out.println("\n\nQuiting the application...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                    flag = false;
                    break;
                default:
                    System.out.println("\n\nPlease enter a number between 0-10\n\n");
                    break;
            }
        }
    }

    private static void printMenuInstruction() {
        System.out.println("***** The Playlist *****" +
                "\n\n0 - print instruction\n" +
                "\n===== Album =====" +
                "\n\n1 - Add a new album" +
                "\n2 - Remove an album" +
                "\n3 - Update an album title" +
                "\n4 - print albums\n" +
                "\n===== Song =====" +
                "\n\n5 - Add a new song to an album" +
                "\n6 - Remove a song to an album" +
                "\n7 - Update a song title on an album" +
                "\n8 - print list of songs on an album\n" +
                "\n===== Playlist =====" +
                "\n\n9 - Add a song to the playlist" +
                "\n10 - Remove a song to the playlist" +
                "\n11 - Playlist option" +
                "\n12 - Print the playlist" +
                "\n\n13 - Quit the application");

    }

    private static void addAlbum() {
        System.out.println("Enter the album title:");
        String albumTitle = scanner.nextLine().toLowerCase();
        //check if the album exist already
        if (searchAlbum(albumTitle) < 0) {
            System.out.println("Enter the artist's name:");
            String artist = scanner.nextLine();
            album = new Album(albumTitle, artist);
            albums.add(album);
            System.out.println("\n\n\"" + albumTitle + "\" album by " + artist + " successfully added...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        } else {
            System.out.println("\n\n\"" + albumTitle + "\" album already exist");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }

    }

    private static void removeAlbum() {
        System.out.println("Enter the album title:");
        String albumTitle = scanner.nextLine().toLowerCase();
        if (searchAlbum(albumTitle) >= 0) {
            albums.remove(searchAlbum(albumTitle));
            System.out.println("\n\n\"" + albumTitle + "\" album successfully removed");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        } else {
            System.out.println("\n\n\"" + albumTitle + "\" album does not exist");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }

    }

    private static void updateAlbum() {
        System.out.println("Enter the album title:");
        String currentAlbumTitle = scanner.nextLine().toLowerCase();
        if (searchAlbum(currentAlbumTitle) >= 0) {
            System.out.println("Enter the new album title:");
            String newAlbumTitle = scanner.nextLine().toLowerCase();
            int position = searchAlbum(newAlbumTitle);
            if (position < 0) {
                System.out.println("Enter the new name of the artist:");
                String artist = scanner.nextLine();
                album = albums.get(searchAlbum(currentAlbumTitle));
                album.setNewAlbum(newAlbumTitle, artist);
                System.out.println("\n\n\"" + currentAlbumTitle + "\" successfully updated to " + "\"" +
                        newAlbumTitle + "\" by " + artist);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            } else {
                System.out.println("\n\n\"" + currentAlbumTitle + "\" album title already exist");
            }

        } else {
            System.out.println("\n\n\"" + currentAlbumTitle + "\" album does not exist");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    private static void printAlbums() {
        int totalAlbumsDuration = 0;
        //calculate the total duration
        for (int j = 0; j < albums.size(); j++) {
            totalAlbumsDuration += albums.get(j).totalDuration();
        }
        //array that hold hr:min:sec
        int[] hrMinSecTotalAlbum = album.secToHrMinSec(totalAlbumsDuration);
        int hrTotalAlbum = hrMinSecTotalAlbum[0];
        int minTotalAlbum = hrMinSecTotalAlbum[1];
        int secTotalAlbum = hrMinSecTotalAlbum[2];
        System.out.println("\n\nThere is " + albums.size() + " album(s) with a total duration of " +
                hrTotalAlbum + " hour(s) " + minTotalAlbum + " min(s) " + secTotalAlbum + " sec(s)\n\n");
        //list of the albums
        for (int i = 0; i < albums.size(); i++) {
            String albumTitle = albums.get(i).getAlbumTitle();
            String artist = albums.get(i).getArtist();
            int noSongs = albums.get(i).getSongs().size();
            int totalDuration = albums.get(i).totalDuration();
            int[] hrsMinSec = albums.get(i).secToHrMinSec(totalDuration);
            int hrs = hrsMinSec[0];
            int min = hrsMinSec[1];
            int sec = hrsMinSec[2];
            System.out.println((i + 1) + ".  ALBUM: " + albumTitle + "  ARTIST: " + artist + "  NO OF SONGS: " + noSongs +
                    "  DURATION: " + hrs + " hour(s) " + min + " min(s) " + sec + " sec(s)");
        }
        System.out.println();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {

        }
    }

    //return -1 when the album is not found
    private static int searchAlbum(String albumTitle) {
        for (int i = 0; i < albums.size(); i++) {
            Album eachAlbum = albums.get(i);
            if (eachAlbum.getAlbumTitle().equals(albumTitle)) {
                return i;
            }
        }
        return -1;

    }

    private static void addSongAlbum() {
        int durationInSec;
        System.out.println("Enter the album title:");
        String currentAlbumTitle = scanner.nextLine().toLowerCase();

        if (searchAlbum(currentAlbumTitle) >= 0) {

            System.out.println("Enter the song title:");
            String songTitle = scanner.nextLine().toLowerCase();

            //should check if the song already exist in the album
            if (album.searchPositionSong(songTitle) < 0) {


                System.out.println("Enter the song's duration in second:");
                try {
                    durationInSec = scanner.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("\n\nThe value you have input is not a valid integer");
                    scanner.nextLine();
                    return;

                }

                //the duration of the song should not be more than 1hr or a negative number
                if (durationInSec > 0 && durationInSec < 3600) {
                    Album currentAlbum = albums.get(searchAlbum(currentAlbumTitle));
                    currentAlbum.addSongAlbum(songTitle, durationInSec);
                    int[] hrMinSecArray = currentAlbum.secToHrMinSec(durationInSec);
                    int min = hrMinSecArray[1];
                    int sec = hrMinSecArray[2];
                    String artist = currentAlbum.getArtist();

                    System.out.println("\n\n" + songTitle + " with duration of " + min
                            + " min : " + sec + " sec, successfully added to \"" + currentAlbumTitle + "\" album" + " by " + artist);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                } else {
                    System.out.println("\n\nthe song's duration should be an integer between 0 second and 3600 seconds");
                }


            } else {
                System.out.println("\n\n" + songTitle + " already exist in \"" + currentAlbumTitle + "\" album");
            }
        } else {
            System.out.println("\n\n\"" + currentAlbumTitle + "\" album does not exist");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    private static void removeSongAlbum() {
        System.out.println("Enter the album title:");
        String currentAlbumTitle = scanner.nextLine().toLowerCase();
        if (searchAlbum(currentAlbumTitle) >= 0) {
            System.out.println("Enter the song title:");
            String songTitle = scanner.nextLine().toLowerCase();
            if (album.searchPositionSong(songTitle) >= 0) {
                Album currentAlbum = albums.get(searchAlbum(currentAlbumTitle));
                currentAlbum.removeSongAlbum(songTitle);
                System.out.println("\n\n" + songTitle + " successfully deleted from \"" + currentAlbumTitle + "\" album");
            } else {
                System.out.println("\n\n" + songTitle + " does not exist in \"" + currentAlbumTitle + "\" album");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        } else {
            System.out.println("\n\n\"" + currentAlbumTitle + "\" album does not exist");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    private static void updateSongAlbum() {
        int durationInSec = 0;
        System.out.println("Enter the album title:");
        String currentAlbumTitle = scanner.nextLine().toLowerCase();
        if (searchAlbum(currentAlbumTitle) >= 0) {


            for (int i = 0; i < albums.size(); i++) {
                if (currentAlbumTitle.equals(albums.get(i).getAlbumTitle())) {

                    System.out.println("Enter the song title:");
                    String currentSongTitle = scanner.nextLine().toLowerCase();
                    if (albums.get(i).searchPositionSong(currentSongTitle) >= 0) {
                        System.out.println("Enter the new song title:");
                        String newSongTitle = scanner.nextLine().toLowerCase();
                        System.out.println("Enter the song's duration in second:");
                        try {
                            durationInSec = scanner.nextInt();

                        } catch (InputMismatchException e) {
                            System.out.println("\n\nThe value you have input is not a valid integer ");
                            scanner.nextLine();
                            return;
                        }


                        if (durationInSec > 0 && durationInSec < 3600) {

                            Album currentAlbum = albums.get(i);
                            currentAlbum.updateSongAlbum(currentSongTitle, newSongTitle, durationInSec);
                            int[] hrMinSec = album.secToHrMinSec(currentAlbum.totalDuration());
                            int min = hrMinSec[1];
                            int sec = hrMinSec[2];
                            System.out.println("\n\n" + currentSongTitle + " successfully updated to " + newSongTitle + " with duration of " +
                                    min + " min : " + sec + " sec");

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {

                            }

                        } else {
                            System.out.println("\n\nthe song's duration should be an integer between 0 second and 3600 seconds");
                        }


                    } else {
                        System.out.println("\n\n" + currentSongTitle + " does not exist in \"" + currentAlbumTitle + "\" album");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }

                }
            }


        } else {
            System.out.println("\n\n\"" + currentAlbumTitle + "\" album does not exist");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }


    private static void printSongAlbum() {
        System.out.println("Enter the album title:");
        String currentAlbumTitle = scanner.nextLine().toLowerCase();
        if (searchAlbum(currentAlbumTitle) >= 0) {

            for (int i = 0; i < albums.size(); i++) {
                if (currentAlbumTitle.equals(albums.get(i).getAlbumTitle())) {
                    int totalDuration = albums.get(i).totalDuration();
                    int[] hrsMinSecArray = album.secToHrMinSec(totalDuration);
                    int hrs = hrsMinSecArray[0];
                    int min = hrsMinSecArray[1];
                    int sec = hrsMinSecArray[2];
                    System.out.println("\n\nThere is " + albums.get(i).getSongs().size() +
                            " song(s) in \"" + albums.get(i).getAlbumTitle() +
                            "\" album with a total duration of " + hrs + " hour(s) " + min + " min(s) " + sec + " sec(s)" + "\n\n");


                    for (int j = 0; j < albums.get(i).getSongs().size(); j++) {

                        Song currentSong = albums.get(i).getSongs().get(j);
                        String songTitle = currentSong.getSongTitle();
                        String artist = albums.get(i).getArtist();
                        int currentSongDuration = currentSong.getDuration();
                        int songDuration = albums.get(i).getSongs().get(j).getDuration();
                        int[] songDurationMinSec = album.secToHrMinSec(songDuration);
                        int songMin = songDurationMinSec[1];
                        int songSec = songDurationMinSec[2];

                        System.out.println((1 + j) + ".  SONG: " + songTitle + "  ARTIST: " + artist + "  DURATION: " +
                                songMin + " min " + songSec + " sec");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }

            }
        } else {
            System.out.println("\n\n\"" + currentAlbumTitle + "\" album does not exist");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    private static void addSongPlaylist() {
        System.out.println("Enter the song title:");
        String songTitle = scanner.nextLine();
        boolean addedToPlaylist = playlist.addSongPlaylist(songTitle);
        if (addedToPlaylist) {
            System.out.println("\n\n" + songTitle + " successfully added to the playlist");
        } else {
            System.out.println("\n\nFailed to add the song the playlist, " +
                    "The song might have been already added to the playlist or the song does not exist in any of the album");
        }

    }

    private static void removeSongPlaylist() {
        System.out.println("Enter the song title:");
        String songTitle = scanner.nextLine();
        boolean removedFromPlaylist = playlist.removeSongPlaylist(songTitle);
        if (removedFromPlaylist) {
            System.out.println("\n\n" + songTitle + " successfully removed from the playlist");
        } else {
            System.out.println("\n\n" + songTitle + " not found on the playlist");
        }

    }

    private static void playlistOption() {
        ListIterator<Song> listIterator = playlist.getPlaylist().listIterator();
        boolean quit = false;
        boolean goingForward = true;
        if (playlist.getPlaylist().isEmpty()) {
            System.out.println("\n\nNo song in the Playlist");
        } else {
            printPlaylistInstruction();
            while (!quit) {

                int action = 0;
                System.out.println("\n\nEnter your choice :");
                try {
                    action = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\n\nThe value you have input is not a valid integer \n\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        return;
                    }
                }
                scanner.nextLine();


                switch (action) {
                    case 0:
                        printPlaylistInstruction();
                        break;
                    case 1:
                        //check if the iterator is going forward if not,set it to go forward
                        //to avoid the repeat
                        if (!goingForward) {
                            if (listIterator.hasNext()) {
                                listIterator.next();
                            }
                            goingForward = true;
                        }
                        //check if the iterator has next,if yes print the next element
                        if (listIterator.hasNext()) {
                            System.out.println("\n\nThe next song: " + listIterator.next().getSongTitle());
                        } else {
                            System.out.println("\n\nThe End Of The Playlist");
                            goingForward = false;
                        }
                        break;
                    case 2:
                        if (goingForward) {
                            if (listIterator.hasPrevious()) {
                                listIterator.previous();
                            }
                            goingForward = false;
                        }
                        if (listIterator.hasPrevious()) {
                            System.out.println("\n\nThe previous song: " + listIterator.previous().getSongTitle());
                        } else {
                            System.out.println("\n\nThe Start Of The Playlist");
                            goingForward = true;
                        }
                        break;

                    case 3:
                        //check if the iterator is going forward,
                        //if it is check if it has a previous,
                        // print the previous and set goingForward to false
                        if (goingForward) {
                            if(listIterator.hasPrevious()) {
                                System.out.println("\n\nThe repeating current Song: " +
                                        listIterator.previous().getSongTitle());
                                goingForward = false;
                            }
                            else {
                                System.out.println("\n\nThe Start Of The Playlist");
                            }

                        } else {
                            if(listIterator.hasNext()) {
                                System.out.println("\n\nThe repeating current Song: " +
                                        listIterator.next().getSongTitle());
                                goingForward = true;
                            }
                            else {
                                System.out.println("\n\nThe End Of The Playlist");
                            }

                        }
                        break;
                    case 4:
                        System.out.println("\n\nQuiting the playlist menu...\n\n");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        quit = true;
                        printMenuInstruction();
                        break;
                    default:
                        System.out.println("\n\nPlease enter a number between 0-5\n\n");
                        break;
                }
            }
        }
    }

    private static void printAllPlaylist() {
        Iterator<Song> iterator = playlist.getPlaylist().iterator();
        System.out.println("\n\nThere is " + playlist.getPlaylist().size() + " songs in the playlist\n\n");

        if (iterator.hasNext()) {
            for (int i = 0; i < playlist.getPlaylist().size(); i++) {

                Song currentSong = iterator.next();
                String songTitle = currentSong.getSongTitle();
                int durationInSec = currentSong.getDuration();
                int[] hrMinSec = album.secToHrMinSec(durationInSec);
                int min = hrMinSec[1];
                int sec = hrMinSec[2];


                System.out.println((1 + i) + ". SONG: " + songTitle + "  DURATION: " +
                        min + " min : " + sec + " sec");
            }
        }
        System.out.println("\n\n==================================");
    }
    //the playlist menu
    private static void printPlaylistInstruction() {
        System.out.println("\n\n0 - Print the instruction\n" +
                "\n1 - The next song" +
                "\n2 - The previous song" +
                "\n3 - Repeat the current song" +
                "\n4 - Quit the playlist menu");


    }


}