public class Food extends Item{
        private String name;
        private int healAmount;
        
        public Food(String name, double price, int healAmount){
            super(name,price);
            this.name = name;
            this.healAmount = healAmount;
        }
    
        public void eat(Player player){
            if (name.contains("Poison")){
                System.out.println("Tastes horrible");
                player.health -= healAmount;
            } else{
                System.out.println("Tastes pretty good");
                player.health += healAmount;
            }
        }
}
