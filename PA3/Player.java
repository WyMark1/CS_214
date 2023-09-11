import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Item> body;
    private Item hand; // What is in the players hand
    private double money; // Player's total money
    private List<Item> inventory; // Player's inventory
    public Player(double money){
        this.money = money;
        this.inventory = new ArrayList<Item>();
        this.body = new ArrayList<Item>();
    }
    public double getMoney(){ // returns the amount of money player has
        return this.money;
    }
    public List<Item> getItems(){ // returns the list of items player has
        return inventory;
    }
    public boolean removeMoney(double price){ // deducts price amount of money from player returns false if player dosen't have enough money
        if(this.money-price>=0){
            this.money -= price;
            return true;
        }
        return false;
    }
    public void addMoney(double amt){ // add amt money to total money
        this.money += amt;
    }
    public void addItem(Item item){ // add an item to the inventory
        inventory.add(item);
    }
    public void removeItem(Item item){ // removes an item from inventory
        inventory.remove(item);
    }
    public Item getItemByName(String name) {
        // Iterate through the player's items and return the item with the matching name
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null; // Item not found in the player's inventory
    }
    public Item getHeldItem(){ 
        return hand;
    }
    public List<Item> getItemsWorn(){
        return body;
    }
    public void Eat(Item item){
        inventory.remove(item);
    }
    public void Drink(Item item){
        inventory.remove(item);
    }
    public void Wear(Item item){ // Puts item on body and removes from inventory
        body.add(item);
        inventory.remove(item);
    }
    public void Hold(Item item){ // Puts item in hand and removes from inventory
        hand = item;
        inventory.remove(item);
    }
}                       