package froex.simulation;

public class Indicator {

    public static double SMA(double[] p, int count) {
        double sum = 0;
        for (int i = 0; i < p.length; i++) {

            sum += p[i];
        }
        return sum / count;
    }

    /**
     * AO = SMA (中间价，5)-SMA（中间价，34）
     */
    public static double AO(Price[] prices, int now) {
        double p1[] = new double[5];
        double p2[] = new double[34];
        for (int i = 0; i < p1.length; i++) {
            if (now - i < 0) {
                break;
            }

            p1[i] = (prices[now - i].getHigh() - prices[now - i].getLow()) / 2;
        }
        for (int i = 0; i < p2.length; i++) {
            if (now - i < 0) {
                break;
            }

            p2[i] = (prices[now - i].getHigh() - prices[now - i].getLow()) / 2;
        }
        return SMA(p1, 5) - SMA(p2, 34);

    }

    /**
     * AC = AO-SMA (AO,5)
     * 
     * @param prices
     * @param now
     * @return
     */
    public static double AC(Price[] prices, int now) {
        double AO = AO(prices, now);
        double AO5[] = new double[5];
        for (int i = 0; i < AO5.length; i++) {
            if (now - i < 0) {
                break;
            }

            AO5[i] = AO(prices, now - i);
        }
        return AO - SMA(AO5, 5);

    }
}
