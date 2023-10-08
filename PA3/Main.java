//PA 3

public class Main {
    public static void main(String[] args) {
        Game game = new Game(true,300.0);
        game.gamePlay();
    }

    public static void exposeGameSetup(){
        System.out.println("Makes difficulty NA for now Then makes the store then calls makeItems then makes a player");
    }

    public static void exposeGamePlay(){
        System.out.println("Shows the store inventory then allows the user to interact with the store menu");
    }

    public static void exposeGameStop(){
        System.out.println("For now just prints exiting program");
    }

}