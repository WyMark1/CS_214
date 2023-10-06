import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {
    private List<Item> inventory;
    private List<Player> players_in_store; 
    

    public Store() {
        inventory = new ArrayList<>();
        players_in_store = new ArrayList<>();
    }

    public void enter(Player player){
        if (check_player_in_store(player) == false){
            players_in_store.add(player);
        } else {
            System.out.println("Player is already in the store.");
        }
    }

    public void exit(Player player){
         if (check_player_in_store(player) == true){
            players_in_store.remove(player);
        } else {
            System.out.println("Player never entered the store.");
        }
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }


    public void displayInventory() {
        System.out.println("Store Inventory:");
        for (Item item : inventory) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
    }

    private boolean check_player_in_store(Player player){
        int index =  players_in_store.indexOf(player);
        if (index == -1) {
            return false;
        } else {
            return true;
        }
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

    public boolean buyItem(Item item, Player player) {
        if (check_player_in_store(player) == false){
            System.out.println("Player needs to enter the store before being able to buy anything");
            return false;
        }
        
        if (inventory.contains(item)) {
            if (player.spendMoney(item.getPrice())) {
                inventory.remove(item);
                player.acquire(item);
                return true;
            }
        } else {
            System.out.println("Item not available in the store.");
        }
        return false;
    }

    public boolean sellItem(Item item, Player player) {
         if (check_player_in_store(player) == false){
            System.out.println("Player needs to enter the store before being able to sell anything");
            return false;
        }
        player.relinquishItem(item);
        player.getMoney(item.getPrice());
        inventory.add(item);
        return true;
    }
    

    public static void storeMenu(Scanner scanner, Store store, Player player) {
        while (true) {
            System.out.println("\nStore Menu:");
            System.out.println("1. Buy an item");
            System.out.println("2. Sell an item");
            System.out.println("3. Display inventory");
            System.out.println("4. Exit store");

            String input = scanner.nextLine();

            if (input.equals("1")) {
                store.displayInventory();
                System.out.println("Enter the name of the item you want to buy:");
                String itemName = scanner.nextLine();
                Item item = store.getItemByName(itemName);
                if (item != null) {
                    if (store.buyItem(item, player)){
                        System.out.println("Item purchased successfully!\nYou have: "+player.getMoney()+" gold left");
                    } else {
                        System.out.println("Could not purchase the desired item.");
                    }
                } else {
                    System.out.println("Item not available in the store.");
                }

            } else if (input.equals("2")) {
                System.out.println("Enter the name of the item you want to sell:");
                String itemName = scanner.nextLine();
                Item item = player.getItemByName(itemName);
                if (item != null) {
                    store.sellItem(item, player);
                    System.out.println("Item sold successfully!\nYou have: "+player.getMoney()+" gold left");
                } else {
                    System.out.println("Item not found in your inventory.");
                }
                
            } else if (input.equals("3")) {
                store.displayInventory();
            } else if (input.equals("4")) {
                System.out.println("Exiting the store...");
                store.exit(player);
                break;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}