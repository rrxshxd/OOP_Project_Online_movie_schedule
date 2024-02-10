package controller;

import models.Movie;
import repositories.movie_repo;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "Azeghi54963";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) throws Exception {
        try {
            movie_repo movieRepository = new movie_repo(DB_URL, DB_USERNAME, DB_PASSWORD);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("1. Show all movies");
                System.out.println("0. Exit");

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
            System.out.println(movie.getId() + " " + movie.getName() + " " + movie.getGenre() + " " + movie.getReleaseYear());
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Select movie");
            System.out.println("0. Return");

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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter movie ID");
        int movieId = sc.nextInt();

        Movie movie = movieRepository.getMovieById(movieId);
        if (movie != null) {
            System.out.println(movie.getId() + " " + movie.getName() + " " + movie.getGenre() + " " + movie.getReleaseYear());
        } else {
            System.out.println("Movie with id " + movieId + " was not found");
        }
    }
}
