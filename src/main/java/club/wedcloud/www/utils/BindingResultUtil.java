package club.wedcloud.www.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class BindingResultUtil {

  public static List<String> fomatResult(BindingResult result) {
    List<String> error = new ArrayList<String>();
    if (result.hasErrors()) {
      List<ObjectError> list = result.getAllErrors();
      for (ObjectError ob : list) {
        error.add(ob.getDefaultMessage());
      }
    }
    return error;
  }

}
