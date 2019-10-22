package club.wedcloud.www.controller;

import club.wedcloud.www.dao.UserInfo;
import club.wedcloud.www.mapper.UserInfoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/v1")
public class UserInfoController {

  @Autowired
  private UserInfoMapper mapper;


  @ApiOperation("用户列表查询")
  @GetMapping("/userlist")
  public List<UserInfo> getUserInfoList() {
    return mapper.findUserList();
  }

  @ApiOperation("新增用户")
  @PostMapping("/adduser")
  public String addUserInfo(@RequestBody UserInfo userInfo) {
    return String.valueOf(mapper.insertIntoUserInfo(userInfo));
  }

  @ApiOperation("修改用户")
  @PutMapping("/updateuser/{id}")
  public String updateUserInfo(@RequestBody UserInfo userInfo, @PathVariable("id") Integer id) {
    userInfo.setId(id);
    return String.valueOf(mapper.updateUserInfo(userInfo));
  }

  @ApiOperation("删除用户")
  @DeleteMapping("/deleteuser/{id}")
  public String deleteUserInfo(@PathVariable(value = "id") Integer id) {
    return String.valueOf(mapper.deletedUserInfo(id));
  }
}
