package club.wedcloud.www;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Test {

  public static void main(String[] args) throws Exception {
    Workbook wk = new HSSFWorkbook();
    // 创建一个sheet页
    Sheet sh = wk.createSheet("第一个sheet页");
    for (int i = 0; i < 10; i++) {
      // 创建行
      Row row = sh.createRow(i);
      for (int j = 0; j < 5; j++) {
        // 创建行的单元格
        row.createCell(j).setCellValue(j);;
      }
    }

    URL url = new URL("https://imgsport.hrgai360.com/images/fms/virtualFigure//-122962306.png");
    // 打开链接
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    // 设置请求方式为"GET"
    conn.setRequestMethod("GET");
    // 超时响应时间为50秒
    conn.setConnectTimeout(50 * 1000);
    // 通过输入流获取图片数据
    InputStream inStream = conn.getInputStream();
    // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
    byte[] byteData = readInputStream(inStream);
    HSSFPatriarch patriarch = (HSSFPatriarch) sh.createDrawingPatriarch();
    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 10, 1, (short) 11, 2);
    patriarch.createPicture(anchor, wk.addPicture(byteData, HSSFWorkbook.PICTURE_TYPE_JPEG));
    FileOutputStream out = new FileOutputStream("D:\\Users\\User\\Desktop\\2\\用Poi搞出来的工作薄.xls");
    wk.write(out);
    out.close();
  }

  private static byte[] readInputStream(InputStream inStream) throws Exception {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    // 创建一个Buffer字符串
    byte[] buffer = new byte[1024];
    // 每次读取的字符串长度，如果为-1，代表全部读取完毕
    int len = 0;
    // 使用一个输入流从buffer里把数据读取出来
    while ((len = inStream.read(buffer)) != -1) {
      // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
      outStream.write(buffer, 0, len);
    }
    // 关闭输入流
    inStream.close();
    // 把outStream里的数据写入内存
    return outStream.toByteArray();
  }
}
