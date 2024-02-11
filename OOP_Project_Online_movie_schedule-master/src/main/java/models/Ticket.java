package models;

public class Ticket {
    private int id;
    private int ticketId;
    private int sessionId;
    private String movieName;
    private boolean isBooked;

    public Ticket() {
    }

    public Ticket(int id, int ticketId, int sessionId, String movieName, boolean isBooked) {
        this.id = id;
        this.ticketId = ticketId;
        this.sessionId = sessionId;
        this.movieName = movieName;
        this.isBooked = isBooked;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getMovieName(){
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public boolean getIsBooked(){
        return isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketId=" + ticketId +
                ", sessionId=" + sessionId +
                ", movieName='" + movieName + '\'' +
                ", isBooked=" + isBooked +
                '}';
    }
}
