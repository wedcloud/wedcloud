package club.wedcloud.www.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import club.wedcloud.www.dao.UserInfo;
import club.wedcloud.www.mapper.UserInfoMapper;

@RestController
@RequestMapping("/v1")
public class UserInfoController {

  @Autowired
  private UserInfoMapper mapper;

  @GetMapping("/userlist")
  public List<UserInfo> getUserInfoList() {
    return mapper.findUserList();
  }
}
