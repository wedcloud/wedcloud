package club.wedcloud.www.utils;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 
 * @author xuhb
 * @Description JFreeChartUtil 绘图
 * @time 2019年11月19日
 */
public class JFreeChartUtil {

  /**
   * 
   * @Description 柱状图
   * @throws IOException
   */
  public static void getHistogram() throws IOException {
    DefaultCategoryDataset ds = new DefaultCategoryDataset();
    ds.setValue(12, "sport", "10");
    ds.setValue(4, "sport", "20");
    ds.setValue(19, "sport", "30");
    ds.setValue(23, "sport", "40");
    ds.setValue(11, "sport", "50");
    ds.setValue(43, "sport", "60");
    ds.setValue(36, "sport", "70");
    ds.setValue(45, "sport", "80");
    ds.setValue(65, "sport", "90");
    ds.setValue(12, "sport", "100");

    Font font = new Font("宋体", Font.BOLD, 20);

    JFreeChart chart = ChartFactory.createBarChart("标题", "分数", "人数(人)", ds,
        PlotOrientation.VERTICAL, true, true, true);

    // 设置整个图片的标题字体
    chart.getTitle().setFont(font);

    // 设置提示条字体
    font = new Font("宋体", Font.BOLD, 15);
    chart.getLegend().setItemFont(font);

    // 得到绘图区
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    // 得到绘图区的域轴(横轴),设置标签的字体
    plot.getDomainAxis().setLabelFont(font);
    // 设置横轴标签项字体
    plot.getDomainAxis().setTickLabelFont(font);
    // 设置范围轴(纵轴)字体
    plot.getRangeAxis().setLabelFont(font);
    // 存储成图片
    // 设置chart的背景图片
    chart.setBackgroundImage(ImageIO.read(new File("D:\\Users\\User\\Desktop\\ss.jpg")));
    plot.setForegroundAlpha(1.0f);
    ChartUtils.saveChartAsPNG(new File("D:\\Users\\User\\Desktop\\ss1.png"), chart, 500, 500);
  }
}
