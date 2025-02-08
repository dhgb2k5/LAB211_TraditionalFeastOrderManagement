
package model;

public class FeastMenu implements Comparable<FeastMenu>{
    private String code;
    private String name;
    private String price;
    private String ingredient;

    public FeastMenu() {}

    public FeastMenu(String code, String name, String price, String ingredient) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.ingredient = ingredient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        return String.format("%s,%s,%s,%s", code,name,price,ingredient);
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
