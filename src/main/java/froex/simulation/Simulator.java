package froex.simulation;

import java.util.HashMap;
import java.util.Map;

import froex.simulation.FunctionBinaryTree.Func;

public class Simulator {
	public byte x[] = {37,-112,91,52,78,-17,65,64,-122,36,91,-115,-114,78,-99,-115,3,0,62,121,36,121,-23,120,55,121,-70,-115,55,21,-70,36};
//	 public byte x[];
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
	private int point = 27;
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

	private double decide_old(int time) {

		double a[] = new double[7];
		for (int i = 0; i < x.length / 2; i++) {
			a[i] = Indicator.AC(prices, time - (x[x.length / 2 + i] % 20) - 20);
		}

		double res = 0.0;
		for (int i = 0; i < x.length / 2; i++) {
			res = res + (x[i]) * a[i];
		}
		return res;
	}

	private double decide1(int time) {

		double res = 1;
		try {
			res = prices[time - 0].getClose() * 1.5 - (prices[time - 1].getClose() + 0.5 * prices[time - 1].getOpen())
					+ (prices[time - 9].getHigh() - prices[time - 29].getLow()) * 0.5
							/ (prices[time - 30].getClose() - prices[time - 11].getClose());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 6 ? 1 : -1;
	}

	private double decide(int time) {
		byte[] y = new byte[x.length - 1];
		for (int i = 0; i < y.length; i++) {
			y[i] = x[i + 1];
		}
		FunctionBinaryTree functree = new FunctionBinaryTree(y);
		for (int i = y.length / 2; i < y.length; i++) {
			int shift = Math.abs((int) (functree.treeNodes[i].getData() / 5));
			int type = Math.abs((int) (functree.treeNodes[i].getData() % 5));
			switch (type) {
			case 0:
				functree.treeNodes[i].setData(prices[time - shift].getOpen());
				break;
			case 1:
				functree.treeNodes[i].setData(prices[time - shift].getHigh());
				break;
			case 2:
				functree.treeNodes[i].setData(prices[time - shift].getLow());
				break;
			case 3:
				functree.treeNodes[i].setData(prices[time - shift].getClose());
				break;
			case 4:
				functree.treeNodes[i].setData(0);
				break;
			default:
				break;
			}
		}
		double res = functree.getFunctionRes();
		return res > x[0] ? 1 : -1;
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
		this.balance = this.balance + order.getBuyOrSell() * (price - order.getPrice());
	}
}
