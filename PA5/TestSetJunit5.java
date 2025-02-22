import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.Exception;
public class TestSetJunit5 {
    static Store store;
    static Player player;
    static Item item_0;
    static Item item_1;
    static Item item_2;
    static Item item_3;
    @BeforeAll
    static void setup() {
        store = new Store();
        item_0 = new Item("test0", 1.0);
        item_1 = new Item("test1", 1.0);
        item_2 = new Item("test2", 1.0);
        item_3 = new Item("test3", 1.0);
        player = new Player(100.0);
    }
    @Test
    @Tag("buy")
    void buy() {
        store.addItem(item_0);
        store.addItem(item_1);
        store.addItem(item_2);
        store.addItem(item_3);
        store.enter(player);
        store.buyItem(item_0, player);
        store.buyItem(item_1, player);
        store.buyItem(item_2, player);
        store.buyItem(item_3, player);
        // Do the actions
        assertTrue(item_0 == player.getItemByName("test0"), "First item purchased not found in Player's inventory");
        assertTrue(item_1 == player.getItemByName("test1"), "Second item purchased not found in Player's inventory");
        assertTrue(item_2 == player.getItemByName("test2"), "Third item purchased not found in Player's inventory");
    }
    @Test
    @Tag("player_can_sell")
    void player_can_sell() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Item("player_item", 1.0);
        player.acquire(item);
        assertTrue(item == player.getItemByName("player_item"), "Item not found in inventory after acquire");
        // Sell the item
        store.enter(player);
        store.sellItem(item, player);
        assertNull(player.getItemByName("player_item"), "Item still in inventory after sale");
    }

    @Test
    @Tag("player_can_relinquish")
    public void relinquish() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Item("player_item", 1.0);
        store.addItem(item);
        store.enter(player);
        store.buyItem(item, player);
        assertTrue(item == player.getItemByName("player_item"));
        player.relinquishItem(item);
        assertNull(player.getItemByName("player_item"));
    }

    @Test
    @Tag("player_can_consume")
    public void consume() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Potion("player_item", 1.0);
        store.addItem(item);
        store.enter(player);
        store.buyItem(item, player);
        assertTrue(item == player.getItemByName("player_item"));
        player.consume(item);
        assertNull(player.getItemByName("player_item"));
    }

    @Test
    @Tag("player_can_buy_using_escrow")
    public void buyEscrow() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Potion("player_item", 1.0);
        store.addItem(item);
        store.enter(player);
        double beforeBuy = player.getMoney();
        player.buyUsingEscrow(item);
        assertTrue(item == player.getItemByName("player_item"));
        assertTrue(player.getMoney() == beforeBuy - item.getPrice());
        assertTrue(player.getItemByName("player_item") != null);
    }

    @Test
    @Tag("player_can_sell_using_escrow")
    public void sellEscrow() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Potion("player_item", 1.0);
        player.acquire(item);
        store.enter(player);
        double beforeSell = player.getMoney();
        player.sellUsingEscrow(item);
        assertTrue(store.getItemByName(item.getName()) == item);
        assertTrue(player.getMoney() == beforeSell + item.getPrice());
        assertNull(player.getItemByName("player_item"));
    }
}