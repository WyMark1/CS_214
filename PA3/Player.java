import java.util.ArrayList;
import java.util.List;

class Player {
    private List<Item> body;
    private Item hand; 
    private double money; 
    private List<Item> inventory; 
    public Player(double money){
        this.money = money;
        this.inventory = new ArrayList<Item>();
        this.body = new ArrayList<Item>();
    }
    public double getMoney(){ 
        return this.money;
    }
    public List<Item> getItems(){ 
        return inventory;
    }
    public boolean spendMoney(double price){ 
        if(this.money-price>=0){
            this.money -= price;
            return true;
        }
        return false;
    }
    public void getMoney(double amt){ 
        this.money += amt;
    }
    public void acquire(Item item){ 
        inventory.add(item);
    }
    public void relinquishItem(Item item){ 
        inventory.remove(item);
    }
    public Item getItemByName(String name) {
        
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null; 
    }
    public Item getHeldItem(){ 
        return hand;
    }
    public List<Item> getItemsWorn(){
        return body;
    }
    public void Consume(Item item){
        inventory.remove(item);
    }
    public void Wear(Item item){ 
        body.add(item);
        inventory.remove(item);
    }
    public void Hold(Item item){ 
        hand = item;
        inventory.remove(item);
    }
    public void Equip(Item item){ 
        body.add(item);
        inventory.remove(item);
    }
}       
