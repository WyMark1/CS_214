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
            System.out.println("\nEnter a command (1 to enter the store, 4 to exit):");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                store.enter(player);
                Store.storeMenu(scanner, store, player);
                store.exit(player);
            } else if (input.equals("2")){
                List<Item> items = player.getItems();
                for (Item item : items) {
                    System.out.println(item.getName());
                }
            } else if (input.equals("4")) {
                gameStop();
                break;
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
                if(datasplit[0].contains("_")){
                    datasplit[0] = datasplit[0].substring(0, datasplit[0].indexOf("_")) + " " + datasplit[0].substring(datasplit[0].indexOf("_")+1, datasplit[0].length()); 
                }
                Item item = new Item(datasplit[0],Float.parseFloat(datasplit[1]));
                store.addItem(item);
            }
            file.close();
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred.");
        }
    }
}
