import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
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
        player.buyUsingEscrow(item, store);
        assertSame(item, player.getItemByName("player_item"));
        assertEquals(player.getMoney(), beforeBuy - item.getPrice(), 0.01);
    }

    @Test
    public void sellEscrow() {
        Store store = new Store();
        Player player = new Player(100.0);
        Item item = new Potion("player_item", 1.0);
        player.acquire(item);
        store.enter(player);
        double beforeSell = player.getMoney();
        player.sellUsingEscrow(item, store);
        assertSame(store.getItemByName(item.getName()), item);
        assertEquals(player.getMoney(), beforeSell + item.getPrice(), 0.01);
        assertNull(player.getItemByName("player_item"));
    }
}