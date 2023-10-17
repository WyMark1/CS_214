public class Weapon extends Item{
    private int damage;
        
    public Weapon(String name, double price, int damage){
        super(name,price);
        this.damage = damage;
    }
    
    public void equip(Player player){
        player.damage += damage;
    }

    public void unEquip(Player player){
        player.damage -= damage;
    }
    
}


