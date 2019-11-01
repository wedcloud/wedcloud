package club.wedcloud.www;

import java.util.Calendar;
import club.wedcloud.www.utils.TimeUtils;

public class Test {

  public static void main(String[] args) {
    Calendar rightNow = Calendar.getInstance(); // 子类对象
    // 获取年
    int year = rightNow.get(Calendar.YEAR);
    // 获取月
    int month = rightNow.get(Calendar.MONTH);
    // 获取日
    int date = rightNow.get(Calendar.DATE);
    System.out.println(TimeUtils.getCalendar(Calendar.YEAR) + "/"
        + (TimeUtils.getCalendar(Calendar.MONTH) + 1) + "/" + TimeUtils.getCalendar(Calendar.DATE));
  }

}
