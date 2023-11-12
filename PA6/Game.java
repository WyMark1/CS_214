import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Game {
    private static final Logger logger = LogManager.getLogger(Game.class);
    private String difficulty;
    private Store store;
    private Player player;

    public Game(boolean makeItems, double startMoney){
        difficulty  = "NA";
        store = new Store();
        if(makeItems){
            makeItems(store);
        }
        player = new Player(startMoney);
        ///player.acquire(new Item("NA", 100.0));
        logger.info("Game Started");
    }

    public void gamePlay(){
        store.displayInventory();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter a command (1 to enter the store, 2 to view Inventory, 3 to Consume an Item, 4 to exit,\n 5 to Equip an item, 6 to unequip an Item, 7 to View All stats):");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                store.enter(player);
                Store.storeMenu(scanner, store, player);
                store.exit(player);
            } else if (input.equals("2")){
                List<Item> inventory = player.getItems();
                for(Item item : inventory){
                    System.out.println(item);
                }
            } else if (input.equals("3")){
                System.out.println("Write the name of the Item to be consumed");
                input = scanner.nextLine();
                player.consume(player.getItemByName(input));
            } else if (input.equals("4")) {
                gameStop();
                break;
            } else if (input.equals("5")) {
                System.out.println("Write the name of the Item to be Equiped");
                input = scanner.nextLine();
                player.equip(player.getItemInInventory(input));
            } else if (input.equals("6")) {
                System.out.println("Write the name of the Item to be unequiped");
                input = scanner.nextLine();
                player.unEquip(player.getItemByName(input));
            } else if (input.equals("7")) {
                System.out.println(player);
            } else if (input.equals("8")) {
                input = scanner.nextLine();
                player.relinquishItem(player.getItemByName(input));
            } else {
                System.out.println("Invalid command!");
            }
        }

        scanner.close();
    }

    public void gameStop(){
        System.out.println("Exiting the program...");
    }

    public Player getPlayer(){
        return player;
    }
    public static void makeItems(Store store){
        try{
            File items = new File("Items.txt");
            Scanner file = new Scanner(items);
            while(file.hasNextLine()){
                String data = file.nextLine();
                String[] datasplit = data.split(" ");
                if(datasplit[0].contains("_")){
                    datasplit[0] = datasplit[0].substring(0, datasplit[0].indexOf("_")) + " " + datasplit[0].substring(datasplit[0].indexOf("_")+1, datasplit[0].length()); 
                }
                if(datasplit[2].equals("Potion")){
                    Item potion = new Potion(datasplit[0], Double.parseDouble(datasplit[1]));
                    store.addItem(potion);
                } else if(datasplit[2].equals("Food")){
                    Item food = new Food(datasplit[0], Double.parseDouble(datasplit[1]), Integer.parseInt(datasplit[3]));
                    store.addItem(food);
                } else if(datasplit[2].equals("Weapon")){
                    Item weapon = new Weapon(datasplit[0], Double.parseDouble(datasplit[1]), Integer.parseInt(datasplit[3]));
                    store.addItem(weapon);
                } else if(datasplit[2].equals("Clothes")){
                    Item clothes = new Clothes(datasplit[0], Double.parseDouble(datasplit[1]), Integer.parseInt(datasplit[3]));
                    store.addItem(clothes);
                } else{
                    Item item = new Item(datasplit[0], Double.parseDouble(datasplit[1]));
                    store.addItem(item);
                }
            }
            file.close();
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred.");
        }
    }
}
