package models;

import java.time.LocalDateTime;

public class Session {
    private int id;
    private int movieId;
    private int sessionId;
    private LocalDateTime time;
    private String movieName;

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

    public String getMovieName(){
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getSessionId(){
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return getSessionId() + " | " + getMovieName() + " | " + getTime();
    }
}
