import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class Game {
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
    }
    public void exposeGameSetup(){
        System.out.println("Makes difficulty NA for now Then makes store then calls makeItems then makes player");
    }

    public void exposeGamePlay(){
        System.out.println("Shows the store inventory then allows the user to interacte with the store menu");
    }

    public void exposeGameStop(){
        System.out.println("For now just prints exiting program");
    }

    public void gamePlay(){
        store.displayInventory();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter a command (1 to enter the store, 2 to view Inventory, 3 to Consume an Item, 4 to exit, 5 to View All stats):");
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
                player.Consume(player.getItemByName(input));
            } else if (input.equals("4")) {
                gameStop();
                break;
            } else if (input.equals("5")) {
                System.out.println(player);
            } else {
                System.out.println("Invalid command!");
            }
        }

        scanner.close();
    }

    public void gameStop(){
        System.out.println("Exiting the program...");
    }

    public static void makeItems(Store store){
        try{
            File items = new File("Items.txt");
            Scanner file = new Scanner(items);
            while(file.hasNextLine()){
                String data = file.nextLine();
                String[] datasplit = data.split(" ");
                if(datasplit[2].equals("Potion")){
                    Item potion = new Potion(datasplit[0], Double.parseDouble(datasplit[1]));
                    System.out.println(potion instanceof Potion);
                    store.addItem(potion);
                } else if(datasplit[2].equals("Food")){
                    Item food = new Food(datasplit[0], Double.parseDouble(datasplit[1]), Integer.parseInt(datasplit[3]));
                    System.out.println(food instanceof Food);
                    store.addItem(food);
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
