package froex.simulation;

import java.util.HashMap;
import java.util.Map;

public class Simulator {
    public int x1, x2, x3, x4, x5, x6, x7;
    public byte x[];
    public static Map<String, Price[]> priceMap = new HashMap<String, Price[]>();
    static {
        priceMap.put("M1", new Price[1]);
        priceMap.put("M30", new Price[1]);
        priceMap.put("H1", new Price[1]);
        priceMap.put("H4", new Price[1]);
    }
    private int withdraw;
    private int profit;
    private int balance = 10000;
    private int point = 0;
    private Price[] prices;
    private Order order;

    private double b; // buy or sell

    public int run(String period) {
        prices = priceMap.get(period);
        open(1, 1, prices[34].getOpen()); // first order

        for (int time = 34; time < prices.length; time++) {
            Price price = prices[time];
            int val = worth(order, price);

            if (val < 5000) {
                return val;
            }
            ; // use open price?
            b = decide(time);

            if (b > 0) {
                if (order.getBuyOrSell() > 0) {
                    continue;
                } else {
                    close(order, price.getOpen());
                    open(1, 1, price.getOpen());
                }
            } else {
                if (order.getBuyOrSell() < 0) {
                    continue;
                } else {
                    close(order, price.getOpen());
                    open(1, -1, price.getOpen());
                }
            }

        }
        return balance;
    }

    private double decide(int time) {

        double a[] = new double[7];
        for (int i = 0; i < x.length / 2; i++) {
            a[i] = Indicator.AC(prices, time - (x[x.length / 2 + i] % 20) - 20);
        }

        double res = 0.0;
        for (int i = 0; i < x.length / 2; i++) {
            res = res + (x[i] - 128) * a[i];
        }
        return res;
    }

    private int worth(Order order, Price price) {
        if (order == null) {
            return balance;
        }
        if (order.getBuyOrSell() > 0) {
            return this.balance - (order.getPrice() - price.getLow());
        } else {
            return this.balance - (price.getHigh() - order.getPrice());
        }

    }

    private void open(int lot, int buyOrSell, int price) {
        this.order = new Order(lot, buyOrSell, price);
        balance -= point;
    }

    private void close(Order order, int price) {
        this.balance = this.balance + order.getBuyOrSell()
                * (price - order.getPrice());
    }
}
