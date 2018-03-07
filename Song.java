package playlist;

public class Song {
    private String songTitle;
    private int durationInSec;

    public Song(String songTitle, int duration) {
        this.songTitle = songTitle;
        this.durationInSec = duration;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public int getDuration() {
        return durationInSec;
    }


    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public void setDurationInSec(int durationInSec) {
        this.durationInSec = durationInSec;
    }
}