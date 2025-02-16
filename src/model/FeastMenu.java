
package model;

import java.io.Serializable;

public class FeastMenu implements Comparable<FeastMenu>, Serializable {
    private String menuCode;
    private String name;
    private String price;
    private String ingredient;

    public FeastMenu() {}

    public FeastMenu(String menuCode, String name, String price, String ingredient) {
        this.menuCode = menuCode;
        this.name = name;
        this.price = price;
        this.ingredient = ingredient;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", menuCode,name,price,ingredient);
    }
    
    @Override
    public int compareTo(FeastMenu other) {
        if (Double.valueOf(this.getPrice()) < Double.valueOf(other.getPrice())) {     
            return -1;
        } else if (Double.valueOf(this.getPrice()) > Double.valueOf(other.getPrice())) {
            return 1;
        } else {
            return 0;   
        }
    }
    
    
    
    
}
