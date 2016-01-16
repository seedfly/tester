package simpleGa;

import java.util.Random;

import froex.simulation.Simulator;

public class Individual {

    static int geneLength = 32;
    // 基因序列
    private byte[] genes = new byte[geneLength];
    // 个体的 适应值
    private int fitness = 0;

    // 创建一个随机的 基因个体
    public void generateIndividual() {
        Random random = new Random(System.currentTimeMillis());

        random.nextBytes(genes);

    }

    /* Getters and setters */
    // Use this if you want to create individuals with different gene lengths
    public static void setDefaultGeneLength(int length) {
        geneLength = length;
    }

    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    /* Public methods */
    public int size() {
        return genes.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        StringBuilder geneString = new StringBuilder("");
        for (int i = 0; i < size(); i++) {
            geneString.append(getGene(i)).append(",");
        }
        return geneString.toString();
    }

    public void copyGene(Simulator simulator) {
        simulator.x = new byte[genes.length];
        for (int i = 0; i < genes.length; i++) {
            simulator.x[i] = genes[i];
        }
    }
}