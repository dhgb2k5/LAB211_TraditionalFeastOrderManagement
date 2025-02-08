package dispatcher;

import java.util.Scanner;
import menu.Menu;
import collection.CustomerList;
import collection.FeastMenuList;

public class main {
    public static void main(String[] args) {
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        Menu.function();
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
                FeastMenuList.readFromFile();
                FeastMenuList.showAll();
            default:
                break;
        }
    }
}
