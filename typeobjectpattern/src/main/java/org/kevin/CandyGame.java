package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CandyGame {

    Cell[][] cells;
    CellPool pool;
    int totalPoints;

    CandyGame(int num, CellPool pool) {
        this.cells = new Cell[num][num];
        this.pool = pool;
        this.totalPoints = 0;
        for (var i = 0; i < num; i++) {
            for (var j = 0; j < num; j++) {
                this.cells[i][j] = this.pool.getNewCell();
                this.cells[i][j].positionX = j;
                this.cells[i][j].positionY = i;
            }
        }
    }

    static String numOfSpaces(int num) {
        return " ".repeat(Math.max(0, num));
    }

    void printGameStatus() {
        log.info("");
        for (Cell[] cell : cells) {
            for (var j = 0; j < cells.length; j++) {
                var candyName = cell[j].candy.name;
                if (candyName.length() < 20) {
                    var totalSpaces = 20 - candyName.length();
                    log.info(numOfSpaces(totalSpaces / 2) + cell[j].candy.name
                            + numOfSpaces(totalSpaces - totalSpaces / 2) + "|");
                } else {
                    log.info(candyName + "|");
                }
            }
            log.info("");
        }
        log.info("");
    }

    List<Cell> adjacentCells(int y, int x) {
        var adjacent = new ArrayList<Cell>();
        if (y == 0) {
            adjacent.add(this.cells[1][x]);
        }
        if (x == 0) {
            adjacent.add(this.cells[y][1]);
        }
        if (y == cells.length - 1) {
            adjacent.add(this.cells[cells.length - 2][x]);
        }
        if (x == cells.length - 1) {
            adjacent.add(this.cells[y][cells.length - 2]);
        }
        if (y > 0 && y < cells.length - 1) {
            adjacent.add(this.cells[y - 1][x]);
            adjacent.add(this.cells[y + 1][x]);
        }
        if (x > 0 && x < cells.length - 1) {
            adjacent.add(this.cells[y][x - 1]);
            adjacent.add(this.cells[y][x + 1]);
        }
        return adjacent;
    }

    boolean continueRound() {
        for (var i = 0; i < this.cells.length; i++) {
            if (this.cells[cells.length - 1][i].candy.getType().equals(Candy.Type.REWARD_FRUIT)) {
                return true;
            }
        }
        for (var i = 0; i < this.cells.length; i++) {
            for (var j = 0; j < this.cells.length; j++) {
                if (!this.cells[i][j].candy.getType().equals(Candy.Type.REWARD_FRUIT)) {
                    var adj = adjacentCells(i, j);
                    for (Cell cell : adj) {
                        if (this.cells[i][j].candy.name.equals(cell.candy.name)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    void handleChange(int points) {
        log.info("+" + points + " points!");
        this.totalPoints += points;
        printGameStatus();
    }

    void round(int timeSoFar, int totalTime) {
        var start = System.currentTimeMillis();
        var end = System.currentTimeMillis();
        while (end - start + timeSoFar < totalTime && continueRound()) {
            for (var i = 0; i < this.cells.length; i++) {
                var points = 0;
                var j = this.cells.length - 1;
                while (this.cells[j][i].candy.getType().equals(Candy.Type.REWARD_FRUIT)) {
                    points = this.cells[j][i].candy.getPoints();
                    this.cells[j][i].crush(pool, this.cells);
                    handleChange(points);
                }
            }
            for (var i = 0; i < this.cells.length; i++) {
                var j = cells.length - 1;
                var points = 0;
                while (j > 0) {
                    points = this.cells[j][i].interact(this.cells[j - 1][i], this.pool, this.cells);
                    if (points != 0) {
                        handleChange(points);
                    } else {
                        j = j - 1;
                    }
                }
            }
            for (Cell[] cell : this.cells) {
                var j = 0;
                var points = 0;
                while (j < cells.length - 1) {
                    points = cell[j].interact(cell[j + 1], this.pool, this.cells);
                    if (points != 0) {
                        handleChange(points);
                    } else {
                        j = j + 1;
                    }
                }
            }
            end = System.currentTimeMillis();
        }
    }

}