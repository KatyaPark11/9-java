package org.example.service;

import org.example.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ItemService {
    private final ArrayList<Item> items = new ArrayList<Item>();
    private int idCounter = 0;

    public Item addItem(String name) {
        Item newItem = new Item(idCounter, name);
        items.add(newItem);
        idCounter++;
        return newItem;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public void purchaseItem(int id) {
        getItemById(id).setPurchased();
    }

    public void deleteItem(int id) {
        items.remove(getItemById(id));
    }

    public Item getItemById(int id){
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .get();
    }
}
