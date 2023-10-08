//PA 3

public class Main {
    public static void main(String[] args) {
        Game game = new Game(true,300.0);
        game.gamePlay();
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
}