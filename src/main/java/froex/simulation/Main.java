package froex.simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            // TODO Auto-generated method stub
            br = new BufferedReader(
                    new FileReader("/data/qitan/eurusd2015.csv"));
            String str = null;
            List<Price> list = new ArrayList<Price>();
            while ((str = br.readLine()) != null) {
                String[] strs = str.split(",");
                Price price = new Price(Double.parseDouble(strs[2]),
                        Double.parseDouble(strs[3]),
                        Double.parseDouble(strs[4]),
                        Double.parseDouble(strs[5]));
                list.add(price);

            }
            Price[] prices = new Price[list.size()];
            list.toArray(prices);
            Simulator.priceMap.put("M1", prices);
            // Simulator.priceMap.put("M30", Price.mergeByPeriod(prices, 30));
            Simulator.priceMap.put("H1", Price.mergeByPeriod(prices, 60));
            Simulator.priceMap.put("H4", Price.mergeByPeriod(prices, 240));
            for (int w1 = 0; w1 < 200; w1 += 10) {
                for (int w2 = 0; w2 < 200; w2 += 10) {
                    for (int w3 = 0; w3 < 200; w3 += 10) {
                        for (int w4 = 0; w4 < 200; w4 += 10) {
                            Simulator simulator = new Simulator();
                            simulator.x1 = w1;
                            simulator.x2 = w2;
                            simulator.x3 = w3;
                            simulator.x4 = w4;
                            int profit = simulator.run("H4");
                            if (profit > 10000) {
                                System.out.println(String.format(
                                        "profit=%d,x1=%d,x2=%d,x3=%d,x4=%d",
                                        profit, w1, w2, w3, w4));
                            }
                            ;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
