package bbs;

import tools.DateTools;

public class BbsRunner {

	public static void main(String[] args) {
		try {
			DateTools dateTools = new DateTools();
			Publisher pub = new Publisheriqiyi();
			pub.publish(dateTools.getSimpleDataString());
			
			pub = new Publisheriqiyi();
			pub.publish(dateTools.getSimpleDataString());
			
			pub = new Publisher115();
			pub.publish(dateTools.getSimpleDataString());
			
			pub = new Publisherbaidu();
			pub.publish(dateTools.getSimpleDataString());
			
			pub = new PublisherXunlei();
			pub.publish(dateTools.getSimpleDataString());
			
			pub = new Publisheryouku();
			pub.publish(dateTools.getSimpleDataString());
			
			pub = new PublisherSohu();
			pub.publish(dateTools.getSimpleDataString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
