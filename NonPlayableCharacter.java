public class NonPlayableCharacter {
    public boolean playable = false;
    public boolean resting = true;
    public String name;
    public static void main(String[] args) {
        NonPlayableCharacter npc1 = new NonPlayableCharacter("Fred");
        if(!npc1.playable){
            if(!npc1.resting){
                switch (npc1.action){
                    case "talking":
                        npc1.resting = false;
                        System.out.println(npc1.name + ": What a marvelous day!");
                        npc1.resting = true;
                        npc1.action = "default";
                        break;
                    case "moving":
                        //insert functionality for moving
                        //this will take several lines
                        //of code
                    case "eating":
                        //insert functionality eating
                        //this will take several lines
                        //of code
                    case "drinking":
                        //insert functionality for drinking
                        //this will take several lines
                        //of code
                        break;
                    default:
                        npc1.function1();
                } //switch
            } //if
        } //if
        //resting state
    } //end of main

    public NonPlayableCharacter(String name){
        this.name = name;
    }

    public String action = "default";

    public void function1(){
        //do nothing
        //or do something 
    }
}