package tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {
    private long mill = System.currentTimeMillis();
    SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd HH:mm");
    SimpleDateFormat sdf2 = new SimpleDateFormat("YY年MM月dd日");

    public String getDataString(long mill) {
        return sdf.format(new Date(mill));
    }

    public String getDataString() {
        return sdf.format(new Date(mill));
    }

    public String getSimpleDataString() {
        return sdf2.format(new Date(mill));
    }

    public void increaseDay() {
        mill += 3600000 * 24;
    }

    public void decreaseDay() {
        mill -= 3600000 * 24;
    }
}
