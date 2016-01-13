package froex.simulation;

public class Order {
    private int lot;
    private int buyOrSell;
    private int price;

    public Order(int lot, int buyOrSell, int price) {
        super();
        this.lot = lot;
        this.buyOrSell = buyOrSell;
        this.price = price;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    public int getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(int buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
