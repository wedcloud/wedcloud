package club.wedcloud.www.controller;

import club.wedcloud.www.dao.UserInfo;
import club.wedcloud.www.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserInfoController {

  @Autowired
  private UserInfoMapper mapper;

  @GetMapping("/userlist")
  public List<UserInfo> getUserInfoList() {
    return mapper.findUserList();
  }

  @PostMapping("/adduser")
  public String addUserInfo(@RequestBody UserInfo userInfo){
    return String.valueOf(mapper.insertIntoUserInfo(userInfo));
  }

  @PutMapping("/updateuser/{id}")
  public String updateUserInfo(@RequestBody UserInfo userInfo, @PathVariable("id") Integer id){
    userInfo.setId(id);
    return String.valueOf(mapper.updateUserInfo(userInfo));
  }

  @DeleteMapping("/deleteuser/{id}")
  public String deleteUserInfo(@PathVariable(value = "id") Integer id){
    return String.valueOf(mapper.deletedUserInfo(id));
  }
}
