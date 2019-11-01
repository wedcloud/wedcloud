package club.wedcloud.www.utils;

import java.util.Calendar;

/**
 * 
 * @author xuhb
 * @Description TimeUtils 关于时间的工具类
 * @time 2019年7月17日
 */
public class TimeUtils {
  public static Integer getCalendar(int i) {
    Calendar rightNow = Calendar.getInstance(); // 子类对象
    // // 获取年
    // int year = rightNow.get(Calendar.YEAR);
    // // 获取月
    // int month = rightNow.get(Calendar.MONTH);
    // // 获取日
    // int date = rightNow.get(Calendar.DATE);
    return rightNow.get(i);
  }
}
