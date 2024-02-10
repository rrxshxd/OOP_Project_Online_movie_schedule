package models;

import java.time.LocalDateTime;

public class Session {
    private int id;
    private int movieId;
    private LocalDateTime time;

    public Session() {
    }

    public Session(int movieId, LocalDateTime time) {
        this.movieId = movieId;
        this.time = time;
    }

    public Session(int id, int movieId, LocalDateTime time) {
        this.id = id;
        this.movieId = movieId;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
