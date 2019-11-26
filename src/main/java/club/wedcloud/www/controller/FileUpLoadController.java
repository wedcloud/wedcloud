package club.wedcloud.www.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
import club.wedcloud.www.dao.FileEntity;
import club.wedcloud.www.utils.ResponseBean;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;

/**
 * @author xuhb
 * @Description FileUpLoadController 上传文件 (后期改为断点续传)
 * @time 2019年11月1日
 */

@RestController
@RequestMapping("/v1")
public class FileUpLoadController {

  @Value("${file.upload.path}")
  private String fileUploadPath;

  @Value("${file.load.path}")
  private String fileloadPath;

  @Autowired
  private AsyncTask asyncTask;

  private static final Logger logger = LoggerFactory.getLogger(FileUpLoadController.class);

  @PostMapping("/fileUp")
  public ResponseEntity<ResponseBean> fileUpLoad(@RequestParam("file") MultipartFile[] fileArr)
      throws Exception {
    List<FileEntity> list = new ArrayList<FileEntity>();
    if (fileArr.length > 0) {
      Calendar cal = Calendar.getInstance();
      String path = fileUploadPath + "/" + cal.get(Calendar.YEAR) + "/"
          + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE);
      File filePath = new File(path);
      if (!filePath.exists() && !filePath.isDirectory()) {
        filePath.mkdirs();
      }
      for (MultipartFile file : fileArr) {
        FileEntity fe = new FileEntity();
        File newfile = new File(path + "//" + file.getOriginalFilename());
        file.transferTo(newfile);
        if (file.getOriginalFilename().indexOf("mp4") > 0
            || file.getOriginalFilename().indexOf("avi") > 0) {
          fe.setTime(ReadVideoTimeMs(newfile));
        }
        fe.setPath(path.replace(fileUploadPath, fileloadPath) + "/" + file.getOriginalFilename());
        list.add(fe);
      }
    }
    return ResponseEntity.ok(ResponseBean.ok(list));
  }

  /**
   * @throws EncoderException
   * @throws InputFormatException
   * @Description: 获取视频时长(时分秒)
   * @author: Hanweihu
   * @date: 2019/7/30 8:35
   * @params: [file]
   * @return: java.lang.String
   */
  public String ReadVideoTimeMs(File newfile) throws InputFormatException, EncoderException {
    Encoder encoder = new Encoder();
    long ms = 0;
    MultimediaInfo info = encoder.getInfo(newfile);
    ms = info.getDuration();
    int ss = 1000;
    int mi = ss * 60;
    int hh = mi * 60;
    int dd = hh * 24;

    long day = ms / dd;
    long hour = (ms - day * dd) / hh;
    long minute = (ms - day * dd - hour * hh) / mi;
    long second = (ms - day * dd - hour * hh - minute * mi) / ss;

    String strHour = hour < 10 ? "0" + hour : "" + hour;// 小时
    String strMinute = minute < 10 ? "0" + minute : "" + minute;// 分钟
    String strSecond = second < 10 ? "0" + second : "" + second;// 秒
    if (strHour.equals("00")) {
      return strMinute + ":" + strSecond;
    } else {
      return strHour + ":" + strMinute + ":" + strSecond;
    }
  }
}
