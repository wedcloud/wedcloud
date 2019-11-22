package club.wedcloud.www.utils;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 
 * @author xuhb
 * @Description ExportExcel 导出Excel
 * @time 2019年11月20日
 */
public class ExportExcel {

  public void excel(HttpServletResponse response) throws IOException {

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
  }

}
