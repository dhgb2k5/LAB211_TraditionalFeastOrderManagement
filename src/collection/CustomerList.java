package collection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import model.Customer;
import tool.Acceptable;
import tool.Inputter;

public class CustomerList {

    public static ArrayList<Customer> customers = new ArrayList<>();

    public static void addCustomer() {
        while (true) {

            String id = Inputter.getCustomerID();
            String name = formatName(Inputter.inputName());
            String phoneNumber = Inputter.inputPhoneNumber();
            String email = Inputter.inputEmail();

            customers.add(new Customer(id, name, phoneNumber, email));

            System.out.println("This customer has been registered.");
            writeToFile();

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("Do you want to add new customer (Y/N)?: ");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("n")) {
                    System.out.println("You are back to menu.");
                    return;
                } else if (answer.equalsIgnoreCase("y")) {
                    break;
                } else {
                    System.out.println("Your choice is invalid, try again.");
                }
            }
        }
    }

    public static void updateCustomer() {
        Customer foundCustomer = null;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter customer code to find: ");
            String idToFind = sc.nextLine();
            foundCustomer = findCustomerByCode(idToFind);

            if (foundCustomer == null) {
                System.out.println("This customer does not exist. Try again.");
            } else {
                System.out.println("This customer does not exist, try again.");
                System.out.println("----------------------------------");
                System.out.println("| 1 customer has found:          |");
                System.out.println("----------------------------------");
                showCustomer(idToFind);
                System.out.println("----------------------------------");
                System.out.println("Enter new information to update or press 'ENTER' to skip.");
                break;
            }
        }

        //name
        while (true) {
            System.out.print("- Enter new name to update: ");
            String newName = sc.nextLine();
            if (newName.isEmpty()) {
                System.out.println("Keeping old information.");
                break;
            } else if (Inputter.isValid(newName, Acceptable.NAME_VALID)) {
                foundCustomer.setName(formatName(newName));
                break;
            } else {
                System.out.println("This new name is invalid, try again.");
            }
        }

        //phoneNumber
        while (true) {
            System.out.print("- Enter new phone number to update: ");
            String newPhoneNumber = sc.nextLine();
            if (newPhoneNumber.isEmpty()) {
                System.out.println("Keeping old information.");
                break;
            } else if (Inputter.isValid(newPhoneNumber, Acceptable.PHONE_VALID)) {
                foundCustomer.setPhoneNumber(newPhoneNumber);
                break;
            } else {
                System.out.println("This new phone number is invalid, try again.");
            }
        }

        //email
        while (true) {
            System.out.print("- Enter new email to update: ");
            String newEmail = sc.nextLine();
            if (newEmail.isEmpty()) {
                System.out.println("Keeping old information.");
                break;
            } else if (Inputter.isValid(newEmail, Acceptable.EMAIL_VALID)) {
                foundCustomer.setEmail(newEmail);
                break;
            } else {
                System.out.println("This new email is invalid, try again.");
            }
        }

        System.out.println("This customer has been updated.");
        writeToFile();

        while (true) {
            System.out.print("Do you want to continue to update (Y/N)?: ");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("n")) {
                System.out.println("You are back to main menu.");
                break;
            } else if (answer.equalsIgnoreCase("y")) {
                updateCustomer();
                break;
            } else {
                System.out.println("Your choice is invalid, try again.");
            }
        }
    }

    public static Customer findCustomerByCode(String id) {
        for (Customer customer : customers) {
            if (customer.getCustomerCode().equalsIgnoreCase(id)) {
                return customer;
            }
        }
        return null;
    }

    public static void findCustomerByName() {
        Scanner sc = new Scanner(System.in);

        ArrayList<Customer> toFind = new ArrayList<>();
        System.out.print("- Enter customer name to find: ");
        String name = sc.nextLine();
        for (Customer customer : customers) {
            if (customer.getName().toLowerCase().contains(name)) {
                toFind.add(customer);
            }
        }

        if (toFind.isEmpty()) {
            System.out.println("No one matches criteria.");
        } else {
            System.out.println("Matching Customers: " + name);
            showCustomerList(toFind);
        }
    }

    public static String formatName(String name) {
        String[] parts = name.split("\\s+");
        if (parts.length > 1) {
            name = name.trim().replaceAll("\\s+", " ");
            int lastIndexOfSpace = name.lastIndexOf(" ");
            String tempName = name.substring(lastIndexOfSpace + 1);
            String others = name.substring(0, lastIndexOfSpace);
            return tempName + ", " + others;
        }
        return name;
    }

    public static void showCustomerList(ArrayList<Customer> list) {
        Collections.sort(list);

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("|  Code  | Customer Name         | Phone        | Email               |");
        System.out.println("-----------------------------------------------------------------------");
        for (Customer x : list) {
            System.out.println(x);
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    public static void showCustomer(String id) {
        Customer ct = findCustomerByCode(id);
        System.out.println("Code         : " + ct.getCustomerCode());
        System.out.println("Customer name: " + ct.getName());
        System.out.println("Phone number : " + ct.getPhoneNumber());
        System.out.println("Email        : " + ct.getEmail());
    }

    public static void writeToFile() {
        String filePath = "src/data/customers.dat";
        try (FileOutputStream fos = new FileOutputStream(filePath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile() {
        String filePath = "src/data/customers.dat";
        try (FileInputStream fis = new FileInputStream(filePath);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            customers = (ArrayList<Customer>) ois.readObject();
            System.out.println("Load data from 'customers.dat' successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Can not find 'customers.dat'.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveData() {
        writeToFile();
        System.out.println("Customer data has been successfully saved to 'customers.dat'");
    }
}
