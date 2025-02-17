package dispatcher;

import java.util.Scanner;
import menu.Menu;
import collection.CustomerList;
import collection.FeastMenuList;
import collection.FeastOrderList;
import java.util.InputMismatchException;
import tool.Inputter;

public class main {

    public static void main(String[] args) {

        CustomerList.readFromFile();
        FeastOrderList.readFromFile();
        FeastMenuList.readFromFile();
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {

            try {
                Menu.function();
                System.out.print("Enter your function (1-8): ");
                choice = sc.nextInt();
                
                switch (choice) {
                    case 1:
                        CustomerList.addCustomer();
                        break;
                    case 2:
                        CustomerList.updateCustomer();
                        break;
                    case 3:
                        CustomerList.findCustomerByName();
                        break;
                    case 4:
                        FeastMenuList.showAll();
                        break;
                    case 5:
                        FeastOrderList.addOrder();
                        break;
                    case 6:
                        FeastOrderList.updateOrderInformation();
                        break;
                    case 7:
                        CustomerList.saveData();
                        FeastOrderList.saveData();
                        break;
                    case 8:
                        Inputter.displayFile();
                        break;
                    default:
                        System.out.println("Your choice is invalid, must be 1-8, try again!");  
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice, your choice must be an integer (1-8).");
                sc.nextLine();
            }
        } while (choice > 0 && choice < 9);
    }
}
