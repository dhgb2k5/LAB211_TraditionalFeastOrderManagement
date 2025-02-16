package collection;

import java.util.ArrayList;
import model.FeastMenu;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;

public class FeastMenuList {

    public static ArrayList<FeastMenu> feastMenus = new ArrayList<>();

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
                    feastMenus.add(feastMenu);
                }
            }
            System.out.println("Load data from 'FeastMenu.csv' successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Can not find 'FeastMenu.csv' file.");
        } catch (IOException e) {
            System.out.println("Cannot read data from feastMenu.csv. Please check it.");
        }
        Collections.sort(feastMenus);
        return feastMenus;
    }
    
    public static String formatIngredient(String text) {
        text = text.replaceAll("\"", "");
        text = text.replaceAll("\\#", "\n");
        return text;
    }

    public static void showAll() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("List of Set Menus for ordering party:");
        System.out.println("------------------------------------------------------------------------");
        for (FeastMenu fm : feastMenus) {
            System.out.println("Code       : " + fm.getMenuCode());
            System.out.println("Name       : " + fm.getName());
            System.out.println("Price      : " + formatPrice(Double.parseDouble(fm.getPrice())));
            System.out.println("Ingredients: \n" + formatIngredient(fm.getIngredient()));
            System.out.println("------------------------------------------------------------------------");
        }
    }
    
    public static String formatPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String finalPrice = decimalFormat.format(price);
        return finalPrice;
    }
    
    public static FeastMenu findFeastMenuByCode(String setMenuCode) {
        for (FeastMenu fm : feastMenus) {
            if (setMenuCode.equalsIgnoreCase(fm.getMenuCode())) {
                return fm;
            }
        }
        System.out.println("LỒN DANH");
        return null;
    }
}
