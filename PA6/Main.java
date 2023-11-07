//PA 6
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Main {
    static Logger logger = LogManager.getLogger(Log4jExample.class);  

    public static void main(String[] args) {
        Game game = new Game(true,100.0);
        game.gamePlay();
        logger.debug("DEBUG!");
        logger.info("INFO!!");
        logger.warn("WARNING!!!");
        logger.fatal("FATAL!!!!");
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

}