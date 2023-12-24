package com.wentware.tttgame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("STARTING APPLICATION, solving TTT Game");
        Game game = new Game();
        GameSolver solver = new RandomGameSolver(game);
        solver.solve();
        logger.info("\n{}",game.drawBoard());
        logger.info("APPLICATION FINISHED");

    }
}
