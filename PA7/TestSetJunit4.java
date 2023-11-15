import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
@RunWith(JUnit4.class)
public class TestSetJunit4 {
    static Store store;
    static Player player;
    static Item item0;
    static Item item1;
    static Item item2;
    static Item item3;
    @Before
    public void setup() {
        store = new Store();
        item0 = new Item("test0", 1.0);
        item1 = new Item("test1", 1.0);
        item2 = new Item("test2", 1.0);
        item3 = new Item("test3", 1.0);
        player = new Player(100.0);
    }

    @Test
    public void testBuy() {
        store.addItem(item0);
        store.addItem(item1);
        store.addItem(item2);
        store.addItem(item3);
        store.enter(player);
        store.buyItem(item0, player);
        store.buyItem(item1, player);
        store.buyItem(item2, player);
        store.buyItem(item3, player);
        assertSame(item0, player.getItemByName("test0"));
        assertSame(item1, player.getItemByName("test1"));
        assertSame(item2, player.getItemByName("test2"));
    }

    @Test
    public void testPlayerCantBuy() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Item("player_item", 1.0);
        store.enter(player);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        store.buyItem(item, player);
        String expected = "Item not available in the store.\nCould not purchase the desired item.";
        assertTrue(outContent.toString().contains(expected));
    }

    @Test
    public void testBuyNotInStore() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Item("player_item", 1.0);
        store.addItem(item);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        store.buyItem(item, player);
        assertTrue(outContent.toString().contains("Player needs to enter the store before being able to buy anything"));
    }

    @Test
    public void testPlayerCanSell() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Item("player_item", 1.0);
        player.acquire(item);
        assertSame(item, player.getItemByName("player_item"));
        store.enter(player);
        store.sellItem(item, player);
        assertNull(player.getItemByName("player_item"));
    }

    @Test
    public void testPlayerCantSell() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Item("player_item", 1.0);
        player.acquire(item);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        store.sellItem(item, player);
        assertTrue(outContent.toString().contains("Player needs to enter the store before being able to sell anything"));
    }

    @Test
    public void relinquish() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Item("player_item", 1.0);
        store.addItem(item);
        store.enter(player);
        store.buyItem(item, player);
        assertSame(item, player.getItemByName("player_item"));
        player.relinquishItem(item);
        assertNull(player.getItemByName("player_item"));
    }
    @Test
    public void consume() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Potion("player_item", 1.0);
        store.addItem(item);
        store.enter(player);
        store.buyItem(item, player);
        assertSame(item, player.getItemByName("player_item"));
        player.consume(item);
        assertNull(player.getItemByName("player_item"));
    }

    @Test
    public void buyEscrow() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Potion("player_item", 1.0);
        store.addItem(item);
        store.enter(player);
        double beforeBuy = player.getMoney();
        player.buyUsingEscrow(item);
        assertSame(item, player.getItemByName("player_item"));
        assertEquals(player.getMoney(), beforeBuy - item.getPrice(), 0.01);
    }

    @Test
    public void buyEscrowNoMoney() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Potion("player_item", 1000.0);
        store.addItem(item);
        store.enter(player);
        double beforeBuy = player.getMoney();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        player.buyUsingEscrow(item);
        assertTrue(outContent.toString().contains("You don't have enough money"));
        assertEquals(beforeBuy, player.getMoney(), 0.01);
    }

    @Test
    public void buyEscrowNoItem() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Potion("player_item", 10.0);
        store.enter(player);
        double beforeBuy = player.getMoney();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        player.buyUsingEscrow(item);
        assertTrue(outContent.toString().contains("That item dosen't exist!"));
        assertEquals(beforeBuy, player.getMoney(), 0.01);
    }

    @Test
    public void sellEscrow() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Potion("player_item", 1.0);
        player.acquire(item);
        store.enter(player);
        double beforeSell = player.getMoney();
        player.sellUsingEscrow(item);
        assertSame(store.getItemByName(item.getName()), item);
        assertEquals(player.getMoney(), beforeSell + item.getPrice(), 0.01);
        assertNull(player.getItemByName("player_item"));
    }

    @Test
    public void sellEscrowNoItem() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Potion("player_item", 1.0);
        store.enter(player);
        double beforeSell = player.getMoney();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        player.sellUsingEscrow(item);
        assertTrue(outContent.toString().contains("You don't have that Item"));
    }

    @Test
    public void sellEscrowNoMoney() {
        Store store = new Store(100.0);
        Player player = new Player(100.0);
        Item item = new Potion("player_item", 1000.0);
        player.acquire(item);
        store.enter(player);
        double beforeSell = player.getMoney();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        player.sellUsingEscrow(item);
        assertTrue(outContent.toString().contains("The store doesn't have enough money"));
        assertTrue(player.getItemByName(item.getName()) != null);
    }
}