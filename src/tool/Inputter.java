package tool;

import java.util.UUID;
import java.util.Scanner;
import menu.Menu;
import model.Customer;
import collection.CustomerList;
import collection.FeastMenuList;
import collection.FeastOrderList;
import java.time.DateTimeException;
import model.FeastMenu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class Inputter {

    public static int choice;

    public static boolean isValid(String input, String pattern) {
        return input.matches(pattern);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        for (Customer ct : CustomerList.customers) {
            if (ct.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return false;
            }
        }
        return true;
    }

    public static String getCustomerID() {
        String firstLetter = "";
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                Menu.chooseEvent();
                System.out.print("Enter your choice (1-3): ");
                int choice = sc.nextInt();
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
                        System.out.println("Invalid choice, try again.");
                        continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, must be 1-3, try again.");
                sc.nextLine();
            }
        }
        String id = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4);
        return firstLetter.toUpperCase() + id;
    }

    public static String inputName() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter name: ");
            String name = sc.nextLine();

            if (isValid(name, Acceptable.NAME_VALID)) {
                return name;
            } else {
                System.out.println("Your name is invalid, try again.");
            }
        }
    }

    public static String inputPhoneNumber() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter phone number: ");
            String phoneNumber = sc.nextLine();
            if (isValid(phoneNumber, Acceptable.PHONE_VALID) && isValidPhoneNumber(phoneNumber)) {
                return phoneNumber;
            } else if (isValidPhoneNumber(phoneNumber) == false) {
                System.out.println("Your phone number is dupplicated, try again.");
            } else {
                System.out.println("Your phone number is invalid, try again.");
            }
        }
    }

    public static String inputEmail() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            if (isValid(email, Acceptable.EMAIL_VALID)) {
                return email;
            } else {
                System.out.println("Your email is invalid, try again.");
            }
        }
    }

    public static String findCustomerCode() {
        Scanner sc = new Scanner(System.in);
        String cusCode = "";
        while (true) {
            System.out.print("Enter customer code: ");
            String code = sc.nextLine();
            for (Customer ct : CustomerList.customers) {
                if (code.equalsIgnoreCase(ct.getCustomerCode())) {
                    cusCode = code.toUpperCase();
                    return cusCode;
                }
            }
            if (cusCode.isEmpty()) {
                System.out.println("Invalid code, try again.");
            }
        }
    }

    public static String findSetMenuCode() {
        Scanner sc = new Scanner(System.in);
        String setCode = "";
        while (true) {
            System.out.print("Enter set menu (PW001 - PW006): ");
            String toCheck = sc.nextLine();
            for (FeastMenu fm : FeastMenuList.feastMenus) {
                if (toCheck.equalsIgnoreCase(fm.getMenuCode())) {
                    setCode = fm.getMenuCode();
                    return setCode;
                }
            }

            if (setCode.isEmpty()) {
                System.out.println("Invalid code, try again.");
            }
        }
    }

    public static int quantity() {
        Scanner sc = new Scanner(System.in);
        int table = 0;
        while (true) {
            try {
                System.out.print("Enter the number of tables: ");
                table = sc.nextInt();
                sc.nextLine();
                if (table > 0) {
                    break;
                } else {
                    System.out.println("This information must be greater than 0, try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid number, must be an integer greater than 0, try again.");
                sc.nextLine();
            }
        }
        return table;
    }

    public static String inputDate() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter the date follow the format 'dd/MM/yyyy' to hold the event: ");
                String date = sc.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate inputDate = LocalDate.parse(date, formatter);

                if (inputDate.isAfter(LocalDate.now())) {
                    return date;
                } else if (inputDate.isBefore(LocalDate.now())) {
                    System.out.println("The date must be after today, try again.");
                } else {
                    System.out.println("The date is invalid, try again.");
                }
            } catch (DateTimeException e) {
                System.out.println("Invalid date format, try again.");
            }
        }
    }

    public static String getAnswer() {
        String message = "";
        Scanner sc = new Scanner(System.in);
        message = sc.nextLine();
        return message;
    }

    public static void displayFile() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("=============MENU=============");
                System.out.println("1. Display Customers List");
                System.out.println("2. Display Orders List");
                System.out.println("==============================");
                System.out.print("Choose table to display (1 or 2): ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.println("Customer Information:");
                    CustomerList.showCustomerList(CustomerList.customers);
                    break;
                } else if (choice == 2) {
                    System.out.println("Orders data:");
                    FeastOrderList.showOrderList();
                    break;
                } else {
                    System.out.println("Invalid choice, try again!");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Your choice must be an integer (1 or 2), try again.");
                sc.nextLine();
            }
        }
    }
}
