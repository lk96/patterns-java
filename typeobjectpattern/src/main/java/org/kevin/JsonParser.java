package org.kevin;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

public class JsonParser {
    Hashtable<String, Candy> candies;

    JsonParser() {
        this.candies = new Hashtable<>();
    }

    void parse() throws IOException, ParseException {
        var parser = new JSONParser();
        var workingDirectory = new File("").getAbsolutePath();
        var filePath = List.of("src", "main", "java", "org", "kevin", "candy.json");
        var absolutePath = workingDirectory + File.separator + String.join(File.separator, filePath);
        var jo = (JSONObject) parser.parse(new FileReader(absolutePath));
        var a = (JSONArray) jo.get("candies");
        for (var o : a) {
            var candy = (JSONObject) o;
            var name = (String) candy.get("name");
            var parentName = (String) candy.get("parent");
            var t = (String) candy.get("type");
            var type = Candy.Type.CRUSHABLE_CANDY;
            if (t.equals("rewardFruit")) {
                type = Candy.Type.REWARD_FRUIT;
            }
            var points = Integer.parseInt((String) candy.get("points"));
            var c = new Candy(name, parentName, type, points);
            this.candies.put(name, c);
        }
        setParentAndPoints();
    }

    void setParentAndPoints() {
        for (var e = this.candies.keys(); e.hasMoreElements(); ) {
            var c = this.candies.get(e.nextElement());
            if (c.parentName == null) {
                c.parent = null;
            } else {
                c.parent = this.candies.get(c.parentName);
            }
            if (c.getPoints() == 0 && c.parent != null) {
                c.setPoints(c.parent.getPoints());
            }
        }
    }

}