
package collection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;
import model.NhanVien;
import tool.Inputter;

public class NhanViens {
    public static ArrayList<NhanVien> nvs = new ArrayList<>();
    
    public static void addNhanVien() {
        String id = "NV" + UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4);
        String name = Inputter.inputName();
        String phone = Inputter.inputPhoneNumber();
        String time = Inputter.session();
        
        nvs.add(new NhanVien(id,name,phone,time));
        writeToFile();
    }
    
    public static void showCustomerList(ArrayList<NhanVien> list) {

        System.out.println("--------------------------------------------------------------------");
        System.out.println("| ID     | Nhan Vien           | Phone        | PartTime           |");
        System.out.println("--------------------------------------------------------------------");
        for (NhanVien x : list) {
            System.out.println(x);
        }
        System.out.println("--------------------------------------------------------------------");
    }
    
    public static void writeToFile() {
        String filePath = "src/data/nhanvien.dat";
        try (FileOutputStream fos = new FileOutputStream(filePath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(nvs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile() {
        String filePath = "src/data/nhanvien.dat";
        try (FileInputStream fis = new FileInputStream(filePath);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            nvs = (ArrayList<NhanVien>) ois.readObject();
            System.out.println("Load data from 'nhanvien.dat' successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Can not find 'nhanvien.dat'.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveData() {
        writeToFile();
        System.out.println("Customer data has been successfully saved to 'customers.dat'");
    }
}
