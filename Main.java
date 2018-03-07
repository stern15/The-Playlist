package playlist;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Album> albums = new ArrayList<Album>();

    private static Album album;

    public static void main(String[] args) {
        int action = 0;
        LinkedList<Song> playlist = new LinkedList<Song>();
        boolean flag = true;
        printMenuInstruction();
        while (flag) {
            System.out.println("\n\nEnter your choice :");
            try {
                action = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("The value you have input is not a valid integer\n\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    return;
                }
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
                    addSongPlaylist(playlist);
                    break;
                //            case 10:
                //                removeSongPlaylist();
                //                break;
                case 11:
                    printAllPlaylist(playlist);
                    break;
                case 12:
                    printMenuInstruction();
                    break;
                case 13:
                    flag = false;
                    break;
                default:
                    System.out.println("\nPlease enter a number between 0-10\n\n");
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
                "\n11 - Print all the playlist" +
                "\n12 - Open the playlist instruction\n" +
                "\n\n13 - Quit the application");

    }

    private static void addAlbum() {
        System.out.println("Enter the album title:");
        String albumTitle = scanner.nextLine().toLowerCase();
        if (searchAlbum(albumTitle) < 0) {
            System.out.println("Enter the artist's name:");
            String artist = scanner.nextLine();
            album = new Album(albumTitle, artist);
            albums.add(album);
            System.out.println("\n\n\"" + albumTitle + "\" album by " + artist + " successfully added...\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        } else {
            System.out.println("\"" + albumTitle + "\" album already exist\n");
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
            System.out.println("\"" + albumTitle + "\" album successfully removed\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        } else {
            System.out.println("\"" + albumTitle + "\" album does not exist\n");
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
                System.out.println("\"" + currentAlbumTitle + "\" successfully updated to " + "\"" +
                        newAlbumTitle + "\" by " + artist + "\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            } else {
                System.out.println("\"" + currentAlbumTitle + "\" album title already exist\n");
            }

        } else {
            System.out.println("\"" + currentAlbumTitle + "\" album does not exist\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    private static void printAlbums() {
        int totalAlbumsDuration = 0;
        for (int j = 0; j < albums.size(); j++) {
            totalAlbumsDuration += albums.get(j).totalDuration();
        }
        int[] hrMinSecTotalAlbum = album.secToHrMinSec(totalAlbumsDuration);
        int hrTotalAlbum = hrMinSecTotalAlbum[0];
        int minTotalAlbum = hrMinSecTotalAlbum[1];
        int secTotalAlbum = hrMinSecTotalAlbum[2];
        System.out.println("There is " + albums.size() + " album(s) with a total duration of " +
                hrTotalAlbum + " hour(s) " + minTotalAlbum + " min(s) " + secTotalAlbum + " sec(s)\n");

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
                    "  DURATION: " + hrs + " hour(s) " + min + ": min(s) " + sec + ": sec(s)");
        }
        System.out.println();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {

        }
    }


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
        int durationInSec = 0;
        System.out.println("Enter the album title:");
        String currentAlbumTitle = scanner.nextLine().toLowerCase();

        if (searchAlbum(currentAlbumTitle) >= 0) {

            System.out.println("Enter the song title:");
            String songTitle = scanner.nextLine().toLowerCase();

            if (album.searchPositionSong(songTitle) < 0) {


                System.out.println("Enter the song's duration in second:");
                try {
                    durationInSec = scanner.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("\nThe value you have input is not a valid integer\n\n");
                    scanner.nextLine();
                    return;

                }


                if (durationInSec > 0 && durationInSec < 3600) {
                    Album currentAlbum = albums.get(searchAlbum(currentAlbumTitle));
                    currentAlbum.addSongAlbum(songTitle, durationInSec);
                    int[] hrMinSecArray = currentAlbum.secToHrMinSec(durationInSec);
                    int min = hrMinSecArray[1];
                    int sec = hrMinSecArray[2];
                    String artist = currentAlbum.getArtist();

                    System.out.println("\n\n" + "\"" + songTitle + "\"" + " with duration of " + min
                            + " min : " + sec + " sec, successfully added to \"" + currentAlbumTitle + "\" album" + " by " + artist + "\n");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }

                } else {
                    System.out.println("\n\nthe song's duration should be an integer between 0 second and 3600 seconds\n");
                }


            } else {
                System.out.println("\n" + songTitle + " already exist in \"" + currentAlbumTitle + "\" album\n");
            }
        } else {
            System.out.println("\"" + currentAlbumTitle + "\" album does not exist\n");
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
                System.out.println(songTitle + " successfully deleted from \"" + currentAlbumTitle + "\" album\n");
            } else {
                System.out.println(songTitle + " does not exist in \"" + currentAlbumTitle + "\" album\n");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        } else {
            System.out.println("\"" + currentAlbumTitle + "\" album does not exist\n");
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

            System.out.println("Enter the song title:");
            String currentSongTitle = scanner.nextLine().toLowerCase();
            if (album.searchPositionSong(currentSongTitle) >= 0) {
                System.out.println("Enter the new song title:");
                String newSongTitle = scanner.nextLine().toLowerCase();
                System.out.println("Enter the song's duration in second:");
                try {
                    durationInSec = scanner.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("The value you have input is not a valid integer \n\n");
                    scanner.nextLine();
                    return;
                }


                if (durationInSec > 0 && durationInSec < 3600) {

                    Album currentAlbum = albums.get(searchAlbum(currentAlbumTitle));
                    currentAlbum.updateSongAlbum(currentSongTitle, newSongTitle, durationInSec);
                    int[] hrMinSec = currentAlbum.secToHrMinSec(durationInSec);
                    int min = hrMinSec[1];
                    int sec = hrMinSec[2];
                    System.out.println(currentSongTitle + " successfully updated to " + newSongTitle + " with duration of " +
                            min + " min : " + sec + " sec\n");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }

                } else {
                    System.out.println("the song's duration should be an integer between 0 second and 3600 seconds\n");
                }


            } else {
                System.out.println(currentSongTitle + " does not exist in \"" + currentAlbumTitle + "\" album\n");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        } else {
            System.out.println("\"" + currentAlbumTitle + "\" album does not exist\n");
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
            int totalDuration = album.totalDuration();
            int[] hrsMinSecArray = album.secToHrMinSec(totalDuration);
            int hrs = hrsMinSecArray[0];
            int min = hrsMinSecArray[1];
            int sec = hrsMinSecArray[2];
            System.out.println("There is " + album.getSongs().size() +
                    " song(s) in \"" + album.getAlbumTitle() +
                    "\" album with a total duration of " + hrs + " hour(s) " + min + " min(s) " + sec + " sec(s)" + "\n");


            for (int i = 0; i < album.getSongs().size(); i++) {

                Song currentSong = album.getSongs().get(i);
                String songTitle = currentSong.getSongTitle();
                String artist = album.getArtist();
                int currentSongDuration = currentSong.getDuration();
                Album currentAlbum = albums.get(searchAlbum(currentAlbumTitle));
                hrsMinSecArray = currentAlbum.secToHrMinSec(currentSongDuration);
                min = hrsMinSecArray[1];
                sec = hrsMinSecArray[2];

                System.out.println((1 + i) + ".  SONG: " + songTitle + "  ARTIST: " + artist + "  DURATION: " +
                        min + " min " + sec + " sec");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        } else {
            System.out.println("\"" + currentAlbumTitle + "\" album does not exist\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }

    }


    private static void printPlaylistInstruction() {
        System.out.println("===== Playlist Menu =====" +
                "\n\n0 - Print playlist instruction" +
                "\n1 - Current song" +
                "\n2 - Next song" +
                "\n3 - Previous song" +
                "\n4 - Replay current song" +
                "\n5 - Quit playlist instruction\n\n");
    }

    private static void addSongPlaylist(LinkedList<Song> playlist) {

        System.out.println("Enter the album title:");
        String currentAlbumTitle = scanner.nextLine().toLowerCase();
        if (searchAlbum(currentAlbumTitle) >= 0) {
            System.out.println("Enter the song title:");
            String songTitle = scanner.nextLine().toLowerCase();
            int songPosition = album.searchPositionSong(songTitle);
            if (songPosition >= 0) {
                Song currentSong = album.getSongs().get(songPosition);


                    playlist.add(currentSong);
                    System.out.println("\n\"" + songTitle + "\" successfully added to the playlist");


            } else {
                System.out.println(songTitle + " does not exist in \"" + currentAlbumTitle + "\" album\n");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        } else {
            System.out.println("\"" + currentAlbumTitle + "\" album does not exist\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }


    }

    //remove a song to a playlist
    private static void printAllPlaylist(LinkedList<Song> playlist) {
        Iterator<Song> iterator = playlist.iterator();
        System.out.println("\nYou have " + playlist.size() + " songs in the playlist\n\n");

        while (iterator.hasNext()) {
            for (int i = 0; i < playlist.size(); i++) {

                Song currentSong = iterator.next();
                String songTitle = currentSong.getSongTitle();
                int durationInSec = currentSong.getDuration();
                String artist = searchAlbumArtist(currentSong);
                int[] hrMinSec = album.secToHrMinSec(durationInSec);
                int min = hrMinSec[1];
                int sec = hrMinSec[2];


                System.out.println((1 + i) + ". SONG: " + songTitle + "  ARTIST: " + artist + "  DURATION: " +
                        min + " min : " + sec + " sec");
            }
        }
        System.out.println("\n==================================");
    }


    private static String searchAlbumArtist(Song song) {
        for (int i = 0; i < albums.size(); i++) {
            String songTitle = albums.get(i).getSongs().get(i).getSongTitle();
            if (songTitle.equals(song.getSongTitle())) {
                String artist = albums.get(i).getArtist();
                return artist;
            }
            return null;
        }
        return null;
    }
}