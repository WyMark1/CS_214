import java.util.List;
//PA 4

public class Main {
    public static void main(String[] args) {
        Game game = new Game(true,300.0);
        game.gamePlay();
    }

    public static void exposeGameSetup(){
        System.out.println("Makes difficulty NA for now Then makes the store then calls makeItems then makes a player");
    }

    public static void exposeGamePlay(){
        System.out.println("Shows the store inventory then allows the user to interact with the store menu or interact with the player");
    }

    public static void exposeGameStop(){
        System.out.println("For now just prints exiting program");
    }

    public static void exposeCommonMethodConsume(){
        System.out.println("It checks if the item is a potion or a food item and then calls drink if a potion and eat if a food item");
    }

    public static void exposeCommonMethodEquip(){
        System.out.println("It checks if the item is Clothes or a Weapon the calls hold if it is a weapon and wear if it is clothes");
    }

    public static void exposeCommonMethodUse(){
        System.out.println("It checks if the item is either somthing that can be consumed or equiped then calls the appropriate method. If neither then prints");
    }

}