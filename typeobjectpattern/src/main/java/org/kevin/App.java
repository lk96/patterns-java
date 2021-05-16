package org.kevin;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;

import java.io.IOException;

@Slf4j
public class App {

    public static void main(String[] args) throws IOException, ParseException {
        var givenTime = 50; //50ms
        var toWin = 500; //points
        var pointsWon = 0;
        var numOfRows = 3;
        var start = System.currentTimeMillis();
        var end = System.currentTimeMillis();
        var round = 0;
        while (pointsWon < toWin && end - start < givenTime) {
            round++;
            var pool = new CellPool(numOfRows * numOfRows + 5);
            var cg = new CandyGame(numOfRows, pool);
            if (round > 1) {
                log.info("Refreshing..");
            } else {
                log.info("Starting game..");
            }
            cg.printGameStatus();
            end = System.currentTimeMillis();
            cg.round((int) (end - start), givenTime);
            pointsWon += cg.totalPoints;
            end = System.currentTimeMillis();
        }
        log.info("Game Over");
        if (pointsWon >= toWin) {
            log.info("" + pointsWon);
            log.info("You win!!");
        } else {
            log.info("" + pointsWon);
            log.info("Sorry, you lose!");
        }
    }
}
