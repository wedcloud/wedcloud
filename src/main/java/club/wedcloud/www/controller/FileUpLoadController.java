package club.wedcloud.www.controller;

import java.io.File;
import java.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import club.wedcloud.www.async.AsyncTask;

/**
 * 
 * @author xuhb
 * @Description FileUpLoadController 异步上传文件
 * @time 2019年11月1日
 */

@RestController
@RequestMapping("/v1")
public class FileUpLoadController {

  @Value("${file.upload.path}")
  private String fileUploadPath;

  @Autowired
  private AsyncTask asyncTask;

  private static final Logger logger = LoggerFactory.getLogger(FileUpLoadController.class);

  @PostMapping("/fileUp")
  public ResponseEntity<String> fileUpLoad(@RequestParam("file") MultipartFile[] fileArr) {
    if (fileArr.length > 0) {
      Calendar cal = Calendar.getInstance();
      String path = fileUploadPath + "/" + cal.get(Calendar.YEAR) + "/"
          + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE);
      File filePath = new File(path);
      if (!filePath.exists() && !filePath.isDirectory()) {
        filePath.mkdirs();
      }
      for (MultipartFile file : fileArr) {
        try {
          file.transferTo(new File(path + "/" + file.getOriginalFilename()));
        } catch (Exception e) {
          logger.error("FileUpLoadController fileUpLoad error:{}", e);
          return ResponseEntity.ok("异常");
        }
      }
    }
    return ResponseEntity.ok("{\"code\":\"100000\",\"message\":\"成功\"}");
  }
}
