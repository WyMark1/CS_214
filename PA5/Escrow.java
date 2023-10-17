public class Escrow {
    private Item item;
    private int funds;

    public Escrow(){
        item = null;
        funds = 0;
    }

    public void requestMoney(int money){
        funds = money;
    }

    public void requestItem(Item item){
        this.item = item;
    }

    public void escrowMoney(int money){
        funds = money;
    }

    public void escrowItem(Item item){
        this.item = item;
    }
    public int returnMoney(){
        return funds;
    }
    public Item returnItem(){
        return item;
    }
    public int receiveMoney(){
        int ret = funds;
        funds = 0;
        return ret;
    }

    public Item receiveItem(){
        Item ret = item;
        item = null;
        return ret;
    }
        
}
