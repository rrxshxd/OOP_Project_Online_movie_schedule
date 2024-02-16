package controller;


import models.Movie;
import models.Session;
import models.Ticket;
import repositories.movie_repo;
import repositories.session_repo;
import repositories.ticket_repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "admin";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/cinema";

    public static void main(String[] args) throws Exception {
        try {
            movie_repo movieRepository = new movie_repo(DB_URL, DB_USERNAME, DB_PASSWORD);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("Welcome to the cinema web application");
                System.out.println("1. Show all movies");
                System.out.println("0. Exit");
                System.out.println("****************************************");
                System.out.println("Select option:");

                int command = sc.nextInt();

                switch (command) {
                    case 1:
                        showAllMovies(movieRepository);
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.err.println("Invalid input");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showAllMovies(movie_repo movieRepository) throws Exception {
        List<Movie> movies = movieRepository.getAllMovies();
        for (Movie movie : movies) {
            System.out.println(movie);
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Select movie");
            System.out.println("0. Return");
            System.out.println("****************************************");
            System.out.println("Select option:");

            int command = sc.nextInt();

            switch (command) {
                case 1:
                    selectMovie(movieRepository);
                    break;
                case 0:
                    return;
                default:
                    System.err.println("Invalid input");
            }
        }
    }

    private static void selectMovie(movie_repo movieRepository) throws Exception {
        session_repo sessionRepository = new session_repo(DB_URL, DB_USERNAME, DB_PASSWORD);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter movie ID:");
        int movieId = sc.nextInt();


        Movie movie = movieRepository.getMovieById(movieId);
        if (movie != null) {
            System.out.println(movie);
        } else {
            System.out.println("Movie with id " + movieId + " was not found");
        }

        while (true) {
            System.out.println("1. Select movie");
            System.out.println("2. Show sessions");
            System.out.println("0. Return");
            System.out.println("****************************************");
            System.out.println("Select option:");

            int command = sc.nextInt();


            switch (command) {
                case 1:
                    selectMovie(movieRepository);
                    break;
                case 2:
                    showSessionsById(sessionRepository, movieRepository, movieId);
                    break;
                case 0:
                    showAllMovies(movieRepository);
                default:
                    System.err.println("Invalid input");
            }
        }
    }


    private static void showSessionsById(session_repo sessionRepository, movie_repo movieRepository, int movId) throws Exception {
        List<Session> sessions = sessionRepository.getSessionsByMovieId(movId);
        Scanner sc = new Scanner(System.in);

        for (Session session: sessions) {
            System.out.println(session);
        }

        while (true) {
            System.out.println("1. Select movie");
            System.out.println("2. Show tickets");
            System.out.println("0. Return");
            System.out.println("****************************************");
            System.out.println("Select option:");

            int command = sc.nextInt();

            switch (command) {
                case 1:
                    selectMovie(movieRepository);
                    break;
                case 2:
                    chooseSession(movieRepository, sessionRepository, movId);
                case 0:
                    showAllMovies(movieRepository);
                default:
                    System.err.println("Invalid input");
            }
        }

    }

    public static void chooseSession(movie_repo movieRepository, session_repo sessionRepository, int chosenMovie) throws Exception {
        ticket_repo ticketRepository = new ticket_repo(DB_URL, DB_USERNAME, DB_PASSWORD);
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose the session:");
        int sessionId = sc.nextInt();
        int maxId = sessionRepository.sessionCounter(movieRepository.getMovieNameById(chosenMovie));


        if (sessionId > 0 && maxId >= sessionId) {
            sessionTickets(movieRepository, ticketRepository, sessionId, chosenMovie);
        } else {
            System.out.println("Session with id " + sessionId + " was not found");
        }
    }

    public static void sessionTickets(movie_repo movieRepository, ticket_repo ticketRepository, int sessionId, int movId) throws Exception {
        Scanner sc = new Scanner(System.in);
        List<Ticket> tickets = ticketRepository.getTicketsById(sessionId, movId);
        for (Ticket ticket: tickets) {
            System.out.println(ticket);
        }

        while (true) {
            System.out.println("1. Select movie");
            System.out.println("2. Book ticket");
            System.out.println("0. Return");
            System.out.println("****************************************");
            System.out.println("Select option:");

            int command = sc.nextInt();

            switch (command) {
                case 1:
                    selectMovie(movieRepository);
                    break;
                case 2:
                    bookTicket(ticketRepository, sessionId, movId);
                case 0:
                    showAllMovies(movieRepository);
                default:
                    System.err.println("Invalid input");
            }
        }
    }

    public static void bookTicket(ticket_repo ticketRepository, int sessionId, int movId) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose ticket id:");
        int ticketId = sc.nextInt();

        if (!ticketRepository.isTicketBooked(ticketId, sessionId, movId)) {
            System.out.println("Enter your name:");
            String name = sc.next();
            System.out.println("Enter your surname:");
            String surname = sc.next();

            ticketRepository.bookTicketById(ticketId, sessionId, movId,name + " " + surname);

            System.out.println("Ticket has been booked successfully for you!");
            System.out.println("****************************************");
        } else {
            System.out.println("Ticket with ID " + ticketId + " is already booked.");
            System.out.println("****************************************");
        }

    }

}