package club.wedcloud.www.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import club.wedcloud.www.dao.UserInfo;
import club.wedcloud.www.mapper.UserInfoMapper;

// @Api
@RestController
@RequestMapping("/v1")
public class UserInfoController {

  @Autowired
  private UserInfoMapper mapper;

  // @ApiOperation("用户列表查询")
  @GetMapping("/userlist")
  public List<UserInfo> getUserInfoList() {
    return mapper.findUserList();
  }

  @PostMapping("/adduser")
  public String addUserInfo(@RequestBody UserInfo userInfo) {
    return String.valueOf(mapper.insertIntoUserInfo(userInfo));
  }

  @PutMapping("/updateuser/{id}")
  public String updateUserInfo(@RequestBody UserInfo userInfo, @PathVariable("id") Integer id) {
    userInfo.setId(id);
    return String.valueOf(mapper.updateUserInfo(userInfo));
  }

  @DeleteMapping("/deleteuser/{id}")
  public String deleteUserInfo(@PathVariable(value = "id") Integer id) {
    return String.valueOf(mapper.deletedUserInfo(id));
  }
}
