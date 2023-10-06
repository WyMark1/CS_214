import java.util.ArrayList;
import java.util.List;

class Player {
    private List<Item> body;
    private Item hand; 
    private double money; 
    private List<Item> inventory;
    public int health;

    public Player(double money){
        this.money = money;
        this.inventory = new ArrayList<Item>();
        this.body = new ArrayList<Item>();
        health = 100;
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

    @Deprecated
    public void addItem(Item item){ 
        acquire(item);
    }

    public void acquire(Item item){ 
        inventory.add(item);
    }

    @Deprecated
    public void removeItem(Item item){
        relinquishItem(item); 
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

    public void Eat(Food item){
        item.eat(this);
    }

    public void Drink(Potion item){
        item.drink(this);
    }

    public void Wear(Item item){ 
        body.add(item);
        inventory.remove(item);
    }

    public void Consume(Item item){
        if(item instanceof Potion){
            Drink((Potion)item);
            inventory.remove(item);
        }
        else if(item instanceof Food){
            Eat((Food)item);
            inventory.remove(item);
        } else {
            System.out.println("You can't consume that");
        }
    }

    public void Hold(Item item){ 
        hand = item;
        inventory.remove(item);
    }

    public void Equip(Item item){ 
        body.add(item);
        inventory.remove(item);
    }

    @Override
    public String toString(){
        String ret = "Money: "+ money+"\nHealth: "+health+"\nItem in hand: "+ hand+"\nItems on body: \n";
        for(Item item : body){
            ret += item + "\n";
        }
        ret += "Items in inventory: \n";
        for(Item item : inventory){
            ret += item + "\n";
        }
        return ret;
    }
}       
