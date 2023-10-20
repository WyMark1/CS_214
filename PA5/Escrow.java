public class Escrow {
    private static double escrowMoney = 0;
    private static Item escrowItem = null;
    public static Item requestItem = null;

    public static void requestItem(Item item){
        requestItem = item;
    }

    public static void escrowMoney(double money){
        escrowMoney = money;
    }

    public static void escrowItem(Item item){
        escrowItem = item;
    }

    public static double returnMoney(){
        return escrowMoney;
    }

    public static Item returnItem(){
        return escrowItem;
    }
    
    public static double receiveMoney(){
        double ret = escrowMoney;
        escrowMoney = 0;
        return ret;
    }

    public static Item receiveItem(){
       Item ret = escrowItem;
       escrowItem = null;
        return ret;
    }
        
}
