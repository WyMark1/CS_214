public class Escrow {
    private Item item;
    private double funds;

    public Escrow(){
        item = null;
        funds = 0.0;
    }

    public void requestMoney(double money){
        funds = money;
    }

    public void requestItem(Item item){
        this.item = item;
    }

    public void escrowMoney(double money){
        funds = money;
    }

    public void escrowItem(Item item){
        this.item = item;
    }
    public double returnMoney(){
        return funds;
    }
    public Item returnItem(){
        return item;
    }
    public double receiveMoney(){
        double ret = funds;
        funds = 0;
        return ret;
    }

    public Item receiveItem(){
        Item ret = item;
        item = null;
        return ret;
    }
        
}
