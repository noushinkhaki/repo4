package se.pricer.menu;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    private List<MenuItem> list;

    public List<MenuItem> readFile(String fileName) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            Object object = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject breakfastMenu = (JSONObject) jsonObject.get("breakfast_menu");
            JSONArray food = (JSONArray) breakfastMenu.get("food");
            list = new ArrayList<>();
            food.forEach(item -> parseMenuItems((JSONObject) item));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void parseMenuItems(JSONObject item) {
        MenuItem menu = new MenuItem();
        menu.setName((String) item.get("name"));
        menu.setPrice((String) item.get("price"));
        menu.setDescription((String) item.get("description"));
        menu.setCalories((String) item.get("calories"));
        list.add(menu);
    }
}
