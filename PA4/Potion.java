public class Potion extends Item{
    private String name;
    private double price;
    
    public Potion(String name, double price){
        super(name,price);
        this.name = name;
        this.price = price;
    }

    public void drink(Player player){
        if(name.equals("Strength_Potion")){
            System.out.println("You Feel Stronger");
        } else if(name.equals("Speed_Potion")){
            System.out.println("You feel fast");
        } else if(name.equals("Health_Potion")){
            System.out.println("You feel better");
            player.health += 10;
        } else if(name.equals("Poison_Potion")){
            System.out.println("You feel ill");
            player.health -= 10;
        }
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }
}
