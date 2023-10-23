public class Potion extends Item{
    private String name;
    
    public Potion(String name, double price){
        super(name,price);
        this.name = name;
    }

    public void drink(Player player){
        if(name.equals("Strength Potion")){
            System.out.println("You Feel Stronger");
        } else if(name.equals("Speed Potion")){
            System.out.println("You feel fast");
        } else if(name.equals("Health Potion")){
            System.out.println("You feel better");
            player.health += 10;
        } else if(name.equals("Poison Potion")){
            System.out.println("You feel ill");
            player.health -= 10;
        }
    }
}
