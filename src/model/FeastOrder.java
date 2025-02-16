
package model;
import java.io.Serializable;

public class FeastOrder implements Comparable<FeastOrder>, Serializable{
    private String customerCode;
    private String setMenuCode;
    private int numberOfTable;
    private String date;
    private int orderID;
    private String setPrice;
    private String totalCost;
    

    public FeastOrder() {
        super();
    }

    public FeastOrder(String customerCode, String date, String setMenuCode, String setPrice,int numberOfTable, int orderID,  String totalCost) {
        this.customerCode = customerCode;
        this.setMenuCode = setMenuCode;
        this.numberOfTable = numberOfTable;
        this.setPrice = setPrice;
        this.date = date;
        this.orderID = orderID;
        this.totalCost = totalCost;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
    public String getSetMenuCode() {
        return setMenuCode;
    }

    public void setSetMenuCode(String setMenuCode) {
        this.setMenuCode = setMenuCode;
    }

    public int getNumberOfTable() {
        return numberOfTable;
    }

    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getSetPrice() {
        return setPrice;
    }

    public void setSetPrice(String setPrice) {
        this.setPrice = setPrice;
    }
    
    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }
    
    @Override
    public String toString() {
        return String.format("| %-5s | %-12s | %-11s | %-8s | %-10s | %-7s | %-10s |",
                getCustomerCode(),getDate(),getCustomerCode(),getSetMenuCode(),getSetPrice(),getNumberOfTable(),getTotalCost());
    }

    @Override
    public int compareTo(FeastOrder o) {
        int resultEachComparison = this.date.compareTo(o.date);
        //if - else
        return resultEachComparison;
    }
}
