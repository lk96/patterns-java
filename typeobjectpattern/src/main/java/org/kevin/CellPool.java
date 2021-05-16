package org.kevin;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class CellPool {
    private static final SecureRandom RANDOM = new SecureRandom();
    public static final String FRUIT = "fruit";
    public static final String CANDY = "candy";
    List<Cell> pool;
    int pointer;
    Candy[] randomCode;

    CellPool(int num) {
        this.pool = new ArrayList<>(num);
        try {
            this.randomCode = assignRandomCandytypes();
        } catch (Exception e) {
            e.printStackTrace();
            //manually initialising this.randomCode
            this.randomCode = new Candy[5];
            randomCode[0] = new Candy("cherry", FRUIT, Candy.Type.REWARD_FRUIT, 20);
            randomCode[1] = new Candy("mango", FRUIT, Candy.Type.REWARD_FRUIT, 20);
            randomCode[2] = new Candy("purple popsicle", CANDY, Candy.Type.CRUSHABLE_CANDY, 10);
            randomCode[3] = new Candy("green jellybean", CANDY, Candy.Type.CRUSHABLE_CANDY, 10);
            randomCode[4] = new Candy("orange gum", CANDY, Candy.Type.CRUSHABLE_CANDY, 10);
        }
        for (int i = 0; i < num; i++) {
            var c = new Cell();
            c.candy = randomCode[RANDOM.nextInt(randomCode.length)];
            this.pool.add(c);
        }
        this.pointer = num - 1;
    }

    Cell getNewCell() {
        var newCell = this.pool.remove(pointer);
        pointer--;
        return newCell;
    }

    void addNewCell(Cell c) {
        c.candy = randomCode[RANDOM.nextInt(randomCode.length)]; //changing candytype to new
        this.pool.add(c);
        pointer++;
    }

    Candy[] assignRandomCandytypes() throws IOException, ParseException {
        var jp = new JsonParser();
        jp.parse();
        var randomCode = new Candy[jp.candies.size() - 2]; //exclude generic types 'fruit' and 'candy'
        var i = 0;
        for (var e = jp.candies.keys(); e.hasMoreElements(); ) {
            var s = e.nextElement();
            if (!s.equals(FRUIT) && !s.equals(CANDY)) {
                //not generic
                randomCode[i] = jp.candies.get(s);
                i++;
            }
        }
        return randomCode;
    }
}