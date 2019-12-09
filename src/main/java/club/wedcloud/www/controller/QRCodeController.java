package club.wedcloud.www.controller;

import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import club.wedcloud.www.utils.QrCodeUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author xuhb
 * @Description QRCodeController 二维码生成
 * @time 2019年12月9日
 */
@Api(tags = "二维码")
@Slf4j
@RestController
@RequestMapping("/v1")
public class QRCodeController {

  @GetMapping("/createQrCode")
  public void createQrCode(HttpServletRequest request, HttpServletResponse response) {
    StringBuffer url = request.getRequestURL();
    try {
      OutputStream os = response.getOutputStream();
      // 从配置文件读取需要生成二维码的连接
      // String requestUrl = GraphUtils.getProperties("requestUrl");
      // requestUrl:需要生成二维码的连接，logoPath：内嵌图片的路径，os：响应输出流，needCompress:是否压缩内嵌的图片
      log.debug("开始生成二维码");
      QrCodeUtils.encode("1234", "D:\\photo\\vedio\\12.jpg", os, true);
      log.debug("二维码生成完成");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
