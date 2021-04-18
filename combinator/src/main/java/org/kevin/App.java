package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class App {

    public static void main(String[] args) {

        String[] queriesOr = {"many", "Annabel"};
        Finder finder = Finders.expandedFinder(queriesOr);
        List<String> res = finder.find(text());
        log.info("the result of expanded(or) query[{}] is {}", queriesOr, res);

        var queriesAnd = new String[]{"Annabel", "my"};
        finder = Finders.specializedFinder(queriesAnd);
        res = finder.find(text());
        log.info("the result of specialized(and) query[{}] is {}", queriesAnd, res);

        finder = Finders.advancedFinder("it was", "kingdom", "sea");
        res = finder.find(text());
        log.info("the result of advanced query is {}", res);

        res = Finders.filteredFinder(" was ", "many", "child").find(text());
        log.info("the result of filtered query is {}", res);
    }

    private static String text() {
        return
                "It was many and many a year ago,\n"
                        + "In a kingdom by the sea,\n"
                        + "That a maiden there lived whom you may know\n"
                        + "By the name of ANNABEL LEE;\n"
                        + "And this maiden she lived with no other thought\n"
                        + "Than to love and be loved by me.\n"
                        + "I was a child and she was a child,\n"
                        + "In this kingdom by the sea;\n"
                        + "But we loved with a love that was more than love-\n"
                        + "I and my Annabel Lee;\n"
                        + "With a love that the winged seraphs of heaven\n"
                        + "Coveted her and me.";
    }
}
