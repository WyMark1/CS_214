import java.util.ArrayList;
import java.util.List;

class Player {
    private List<Item> body;
    private Item hand; 
    private double money; 
    private List<Item> inventory;
    public int health;
    public int armor;
    public int damage;

    public Player(double money){
        this.money = money;
        this.inventory = new ArrayList<Item>();
        this.body = new ArrayList<Item>();
        health = 100;
        armor = 0;
        damage = 0;
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
        for (Item item : body){
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        if (hand.getName().equalsIgnoreCase(name)) {
                return hand;
            }
        return null; 
    }

    public Item getHeldItem(){ 
        return hand;
    }

    public List<Item> getItemsWorn(){
        return body;
    }

    public List<Item> exposeWearInventory(){
        return getItemsWorn();
    }
    public List<Item> exposeInventory(){
        List<Item> fullInventory = inventory;
        fullInventory.add(hand);
        for (Item item : body){
            fullInventory.add(item);
        }
        return fullInventory;
    }
    public void Eat(Food item){
        item.eat(this);
    }

    public void EatItem(Item item){
        if (item instanceof Food){
            Food food = (Food)item;
            food.eat(this);
        } else{
            System.out.println("Can't be eaten");
        }
    }

    public void Drink(Potion item){
        item.drink(this);
    }

    public void DrinkItem(Item item){
        if (item instanceof Potion){
            Potion potion = (Potion)item;
            potion.drink(this);
        } else {
            System.out.println("You can't drink this");
        }
    }

    public void Wear(Clothes item){ 
        body.add(item);
        item.wear(this);
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

    public void Hold(Weapon item){ 
        if(hand == null){
            hand = item;
            inventory.remove(item);
            item.equip(this);
        } else{
            System.out.println("You are already holding something");
        }
    }

    public void Equip(Item item){ 
        if(item instanceof Clothes){
            Wear((Clothes)item);
        } else if(item instanceof Weapon){
            Hold((Weapon)item);
        } else {
            System.out.println("You can't equip that");
        }
    }

    public void unEquip(Item item){
        if(item instanceof Clothes){
            Clothes clothes = (Clothes)item;
            clothes.unEquip(this);
            body.remove(item);
            inventory.add(item);
        } else if(item instanceof Weapon){
            Weapon weapon = (Weapon)item;
            weapon.unEquip(this);
            hand = null;
            inventory.add(item);
        } else {
            System.out.println("That can't be unequiped");
        }
    }
    
    public void Use(Item item){
        if(item instanceof Clothes || item instanceof Weapon){
            Equip(item);
        } else if (item instanceof Potion || item instanceof Food){
            Consume(item);
        } else {
            System.out.println("You can't use that");
        }
    }

    @Override
    public String toString(){
        String ret = "Money: "+ money+"\nHealth: "+health+"\nArmor: "+armor+"\nDamage: "+damage+"\nItem in hand: "+ hand+"\nItems on body: \n";
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
