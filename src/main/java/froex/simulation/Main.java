package froex.simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import simpleGa.Algorithm;
import simpleGa.Population;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            // TODO Auto-generated method stub
            br = new BufferedReader(
                    new FileReader("/data/qitan/eurusd2015.csv"));
            // br = new BufferedReader(new FileReader("E:/eurusd2015.csv"));
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

            ;
            // 初始化一个种群
            Population myPop = new Population(100, true);

            int generationCount = 0;
            while (myPop.getFittest().getFitness() < 50000) {
                generationCount++;
                System.out.println("Generation: " + generationCount
                        + " Fittest: " + myPop.getFittest().getFitness());
                myPop = Algorithm.evolvePopulation(myPop);
            }
            System.out.println("Solution found!");
            System.out.println("Generation: " + generationCount);
            System.out.println("Final Fittest Genes:");
            System.out.println(myPop.getFittest());

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
