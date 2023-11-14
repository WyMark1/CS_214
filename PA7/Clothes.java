public class Clothes extends Item{
    private int armor;
    public Clothes(String name, double price, int armor){
        super(name,price);
        this.armor = armor;
    }

    public void wear(Player player){
        player.armor += armor;
    }

    public void unEquip(Player player){
        player.armor -= armor;
    }
}
