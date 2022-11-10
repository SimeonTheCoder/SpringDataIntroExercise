package app;

import org.springframework.boot.SpringApplication;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);

        ConsoleRunner runner = new ConsoleRunner();

        try {
            runner.seedDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
