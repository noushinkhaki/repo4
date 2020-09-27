package se.pricer.menu;

import java.util.*;

public class MenuReader {

    private static List<MenuItem> list;

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            throw new Exception("Please enter file name and sort order!");
        }
        String fileName = args[0];
        if (fileName.endsWith(".json")) {
            JsonReader jsonReader = new JsonReader();
            list = jsonReader.readFile(fileName);
        } else if (fileName.endsWith(".xml")) {
            XmlReader xmlReader = new XmlReader();
            list = xmlReader.readFile(fileName);
        } else {
            throw new Exception("File name is not valid!");
        }
        String sortOrder = args[1];
        if (sortOrder.equals("asc")) {
            Collections.sort(list);
        } else if (sortOrder.equals("desc")) {
            Collections.sort(list, Collections.reverseOrder());
        } else {
            throw new Exception("Sort order is not valid!");
        }
        list.forEach(item -> print(item));
    }

    private static void print(MenuItem item) {
        System.out.println(item.getName());
        System.out.println(item.getPrice());
        System.out.println(item.getDescription());
        System.out.println(item.getCalories());
        System.out.println("-----------------------------------------------------------------");
    }

}
