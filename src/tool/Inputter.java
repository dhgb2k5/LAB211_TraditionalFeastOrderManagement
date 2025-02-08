package tool;

import java.util.UUID;
import java.util.Scanner;
import menu.Menu;

public class Inputter {

    public static int choice;
    
    public static boolean isValid(String input, String pattern) {
        return input.matches(pattern);
    }
    
    public static String getCustomerID() {
        String firstLetter = "";
        Menu.chooseEvent();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter your choice (1-3):");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    firstLetter = "C";
                    break;
                case 2:
                    firstLetter = "G";
                    break;
                case 3:
                    firstLetter = "K";
                    break;
                default:
                    System.out.println("Your choice is invalid, try again.");
            }
        } while (choice < 1 || choice > 3);

        String id = UUID.randomUUID().toString().replaceAll("^[0-9]", "").substring(0, 5);
        return firstLetter + id;
    }
    
    public static String inputName() {
        String name = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter name:");
            name = sc.nextLine();
            if (isValid(name, Acceptable.NAME_VALID)) {
                break;
            } else {
                System.out.println("Your name is invalid, try again.");
            }
        }
        return name;
    }
    
    public static String inputPhoneNumber() {
        String phoneNumber = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter phone number:");
            phoneNumber = sc.nextLine();
            if (isValid(phoneNumber, Acceptable.PHONE_VALID)) {
                break;
            } else {
                System.out.println("Your phone number is invalid, try again.");
            }
        }
        return phoneNumber;
    }
    
    public static String inputEmail() {
        String email = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter email:");
            email = sc.nextLine();
            if (isValid(email, Acceptable.NAME_VALID)) {
                break;
            } else {
                System.out.println("Your email is invalid, try again.");
            }
        }
        return email;
    }
}
