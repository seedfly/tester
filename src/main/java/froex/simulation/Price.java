package froex.simulation;

import java.util.ArrayList;
import java.util.List;

public class Price {
    private int open;
    private int high;
    private int low;
    private int close;

    public Price(double open, double high, double low, double close) {
        this.open = (int) (open * 10000);
        this.high = (int) (high * 10000);
        this.low = (int) (low * 10000);
        this.close = (int) (close * 10000);
    }

    public Price(int open, int high, int low, int close) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public Price() {

    }

    public int getOpen() {
        return open;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }

    public int getClose() {
        return close;
    }

    public static Price[] mergeByPeriod(Price[] prices, int period) {
        List<Price> list = new ArrayList<Price>();

        for (int i = 0; i <= prices.length; i += period) {
            if (i + period >= prices.length) {
                list.add(Price.merge(prices, i, prices.length - 1));
            } else {
                list.add(Price.merge(prices, i, i + period));
            }

        }
        Price[] prices2return = new Price[list.size()];
        list.toArray(prices2return);
        return prices2return;
    }

    private static Price merge(Price[] prices, int start, int end) {
        int open = prices[start].getOpen();
        int close = prices[end].getClose();
        int high = prices[start].getHigh();
        int low = prices[start].getLow();

        for (int i = start; i < end; i++) {
            if (prices[i].getHigh() > high) {
                high = prices[i].getHigh();
            }
            if (prices[i].getLow() < low) {
                low = prices[i].getLow();
            }
        }
        Price price = new Price(open, high, low, close);
        return price;
    }
}
