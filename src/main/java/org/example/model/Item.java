package org.example.model;

public class Item {
    private final int id;
    private String name;
    private boolean purchased;

    public Item(int id, String currentItemName){
        this.id = id;
        name = currentItemName;
        this.purchased = false;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean getIsPurchased() {
        return purchased;
    }

    public void setName(String currentProductNewName) {
        name = currentProductNewName;
    }
    public void setPurchased() {
        purchased = !purchased;
    }

}
