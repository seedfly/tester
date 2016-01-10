package tools;

import java.util.Random;

public class RandomString {
    private static final String[] meaningStrings1 = { "get", "take", "come",
            "can", "do", "last", "deal", "when", "bring", "compare", "count",
            "run", "go", "read", "red", "blue", "green", "white", "black",
            "rich", "pool", "mild", "wild", "meet", "live" };
    private static final String[] meaningStrings2 = { "chair", "girl", "boy",
            "house", "men", "com", "cam", "cap", "boss", "tree", "leaf", "vip",
            "bbs", "paper", "app", "id", "content", "pow", "shoe", "eye",
            "hair", "apple", "bag", "sis", "hos" };
    private static final String[] meaningStrings3 = { "", "2015", "2000",
            "1992", "2016", "1983" };
    private static final String[] mailPrefix = { "@126.com", "@163.com",
            "@gmail.com", "@sina.com", "@sohu.com", "@yahoo.com" };
    private static final String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String DS_STRING = "123456789";

    public static String getRandomDits(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(DS_STRING.charAt(random.nextInt(DS_STRING.length())));
        }
        return sb.toString();
    }

    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String getMeaningString() {
        Random random = new Random();
        return meaningStrings1[random.nextInt(meaningStrings1.length)]
                + meaningStrings2[random.nextInt(meaningStrings2.length)]
                + meaningStrings3[random.nextInt(meaningStrings3.length)];
    }

    public static String getMailAddress() {
        Random random = new Random();
        return getMeaningString()
                + mailPrefix[random.nextInt(mailPrefix.length)];
    }

    public static void main(String[] args) {
        System.out.println(RandomString.getRandomString(10));
        System.out.println(RandomString.getRandomDits(10));
        System.out.println(RandomString.getMeaningString());
        System.out.println(RandomString.getMailAddress());
    }
}
