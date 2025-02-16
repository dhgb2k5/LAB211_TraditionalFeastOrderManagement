package collection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import model.FeastMenu;
import model.FeastOrder;
import tool.Inputter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.InputMismatchException;

public class FeastOrderList implements Serializable {

    public static ArrayList<FeastOrder> feastorders = new ArrayList<>();
    public static boolean isSaved = true;

    public static boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateToCheck = LocalDate.parse(date, formatter);
        if (dateToCheck.isAfter(LocalDate.now())) {
            return true;
        } else if (dateToCheck.isEqual(LocalDate.now())) {
            System.out.println("This feast order comes today, cannot modify! Try another one.");
            return false;
        } else {
            System.out.println("Invalid date, try again.");
            return false;
        }
    }

    public static void addOrder() {

        String customerCode = Inputter.findCustomerCode();
        String setMenuCode = Inputter.findSetMenuCode();

        FeastMenu toOrder = FeastMenuList.findFeastMenuByCode(setMenuCode);
        
        double priceOfSet = Double.parseDouble(toOrder.getPrice());
        String setPrice = FeastMenuList.formatPrice(priceOfSet);
        
        
        int quantity = Inputter.quantity();
        String date = Inputter.inputDate();

        String totalCost = FeastMenuList.formatPrice(calculateTotalCost(setMenuCode, quantity));

        int orderID = 0;
        for (FeastOrder fo : feastorders) {
            if (fo.getOrderID() > orderID) {
                orderID = fo.getOrderID();
            }
        }
        orderID++;
        System.out.println(priceOfSet);
        FeastOrder newOrder = new FeastOrder(customerCode, date, setMenuCode, setPrice, quantity, orderID, totalCost);
        feastorders.add(newOrder);

        System.out.println("------------------------------------------------------------------------");
        System.out.println("Customer order information  [Order ID: " + newOrder.getOrderID() + "]");
        System.out.println("------------------------------------------------------------------------");
        CustomerList.showCustomer(customerCode);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Code of Set Menu: " + newOrder.getSetMenuCode());
        System.out.println("Set menu name   : " + toOrder.getName());
        System.out.println("Event date      : " + newOrder.getDate());
        System.out.println("Number of tables: " + newOrder.getNumberOfTable());
        System.out.println("Price           : " + FeastMenuList.formatPrice(Double.parseDouble(toOrder.getPrice())) + " Vnd");
        System.out.println("Ingredients     : \n" + FeastMenuList.formatIngredient(toOrder.getIngredient()));
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Total cost      : " + newOrder.getTotalCost() + " Vnd");
        System.out.println("------------------------------------------------------------------------");
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to place another order? (Y/N)");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                addOrder();
                break;
            } else if (answer.equalsIgnoreCase("N")) {
                System.out.println("You are back to main menu.");
                break;
            } else {
                System.out.println("Invalid choice, try again.");
            }
        }
    }

    public static void updateOrderInformation() {
        int orderID = 0;
        FeastOrder FO;
        Scanner sc = new Scanner(System.in);
        
            while (true) {
                System.out.println("Enter Order ID (integer number) to update:");
                orderID = sc.nextInt();
                sc.nextLine();

                FO = findOrderID(orderID);
                if (FO != null) {
                    if (isValidDate(FO.getDate()) == true) {
                        System.out.println("Enter new information to update or press 'ENTER' to skip.");
                        break;
                    } else if (isValidDate(FO.getDate()) == false) {
                        System.out.println("TRY ANOTHER ONE!");
                    }
                } else {
                    System.out.println("This Order does not exist.");
                }
            }

            //Menu Code
            boolean found = false;
            while (true) {
                System.out.println("Enter new set menu code:");
                String newMenuCode = sc.nextLine();

                if (newMenuCode.isEmpty()) {
                    System.out.println("Keeping old information.");
                } else {
                    for (FeastMenu fm : FeastMenuList.feastMenus) {
                        if (newMenuCode.equalsIgnoreCase(fm.getMenuCode())) {
                            FO.setSetMenuCode(newMenuCode);
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    break;
                } else {
                    System.out.println("Invalid menu set, try again.");
                }
            }
            //Number of tables
            while (true) {
                try {
                System.out.println("Enter new number of tables:");
                int newNumberOfTables = sc.nextInt();
                sc.nextLine();
                if (Integer.toString(newNumberOfTables).isEmpty()) {
                    System.out.println("Keeping old information.");
                    break;
                } else {
                    if (newNumberOfTables > 0) {
                        FO.setNumberOfTable(newNumberOfTables);
                        break;
                    } else {
                        System.out.println("Invalid number, try again.");
                    }
                }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid number, must be an integer greater than 0, try again.");
                    sc.nextLine();
                }
            }
            //Event date
            while (true) {
                System.out.println("Enter new event date: (dd/MM/yyyy)");
                String newDate = sc.nextLine();
                if (newDate.isEmpty()) {
                    System.out.println("Keeping old information.");
                    break;
                } else {
                    if (isValidDate(newDate)) {
                        FO.setDate(newDate);
                        break;
                    } else {
                        System.out.println("Invalid date, try again!");
                    }
                }
            }

            //TotalPrice
            double totalPrice = 0;
            for (FeastMenu fm : FeastMenuList.feastMenus) {
                if (fm.getMenuCode().equalsIgnoreCase(FO.getSetMenuCode())) {
                    totalPrice = FO.getNumberOfTable() * Double.parseDouble(fm.getPrice());
                    break;
                }
            }
            FO.setTotalCost(FeastMenuList.formatPrice(totalPrice));

            System.out.println("All information related to new set has been updated successfully!");
            
            while (true) {
            System.out.println("Do you want to continue with another update? (Y/N)");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                updateOrderInformation();
            } else if (answer.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Invalid stament, try again.");
            }
        }
    }

    public static FeastOrder findOrderID(int id) {
        for (FeastOrder fo : feastorders) {
            if (id == fo.getOrderID()) {
                return fo;
            }
        }
        return null;
    }

    public static void writeToFile() {
        String filePath = "src/data/feast_order_services.dat";
        try (FileOutputStream fos = new FileOutputStream(filePath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(feastorders);
            System.out.println("All registrations are saved to 'feast_order_services.dat' successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile() {
        String filePath = "src/data/feast_order_services.dat";
        try (FileInputStream fis = new FileInputStream(filePath);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            feastorders = (ArrayList<FeastOrder>) ois.readObject();
            System.out.println("Load data from 'feast_order_services.dat' successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Can not find 'feast_order_serviecs.dat'.");
        } catch (IOException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }

    public static void saveData() {
        writeToFile();
        System.out.println("Order data has been successfully saved to 'feast_order_services.dat'");
    }

    public static void showOrderList() {
        Collections.sort(feastorders);

        System.out.println("Orders information:");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("| ID    | Event date   | Customer ID | Set Menu | Price      | Tables  | Cost       |");
        System.out.println("-------------------------------------------------------------------------------------");
        for (FeastOrder fo : feastorders) {
            System.out.println(fo);
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static double calculateTotalCost(String setMenuCode, int quantity) {
        for (FeastMenu fm : FeastMenuList.feastMenus) {
            if (fm.getMenuCode().equalsIgnoreCase(setMenuCode)) {
                return Double.parseDouble(fm.getPrice()) * quantity;
            }
        }
        return 0;
    }

}
