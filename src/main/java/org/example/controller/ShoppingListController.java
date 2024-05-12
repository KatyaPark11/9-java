package org.example.controller;

import net.minidev.json.JSONObject;
import org.example.model.Item;
import org.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingListController {
    @Autowired
    private ItemService productService;

    @PostMapping("/items")
    public HttpStatus createItem(@RequestBody JSONObject jsonItem) {
        productService.addItem(jsonItem.getAsString("name"));
        return HttpStatus.OK;
    }

    @GetMapping("/items")
    public Iterable<Item> getItemList() { return productService.getItems(); }
    @PutMapping("/items/{id}")
    public HttpStatus markItem(@PathVariable int id) {
        productService.purchaseItem(id);
        return HttpStatus.OK;
    }
    @DeleteMapping("/items/{id}")
    public HttpStatus deleteItem(@PathVariable int id) {
        productService.deleteItem(id);
        return HttpStatus.OK;
    }
}
