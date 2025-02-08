package collection;

import java.util.ArrayList;
import model.FeastMenu;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

public class FeastMenuList {

    public static ArrayList<FeastMenu> feastMenuList = new ArrayList<>();

    public static FeastMenu dataToObject(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length > 3) {
            return new FeastMenu(parts[0].trim(),
                    parts[1].trim(),
                    parts[2].trim(),
                    parts[3].trim());
        } else {
            return null;
        }
    }

    public static ArrayList<FeastMenu> readFromFile() {
        String pathFile = "src/data/FeastMenu.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                FeastMenu feastMenu = dataToObject(line);
                if (feastMenu != null) {
                    feastMenuList.add(feastMenu);
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read data from feastMenu.csv. Please check it.");
        }
        Collections.sort(feastMenuList);
        return feastMenuList;
    }

    public static void showAll() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("List of Set Menus for ordering party:");
        System.out.println("------------------------------------------------------------------------");
        for (FeastMenu x : feastMenuList) {
            System.out.println("Code       : " + x.getCode());
            System.out.println("Name       : " + x.getName());
            System.out.println("Price      : " + x.getPrice());
            System.out.println("Ingredients: \n" + formatIngredient(x.getIngredient()));
            System.out.println("------------------------------------------------------------------------");
        }
    }

    public static String formatIngredient(String text) {
        text = text.replaceAll("\"", "");
        text = text.replaceAll("\\#", "\n");
        return text;
    }
}
