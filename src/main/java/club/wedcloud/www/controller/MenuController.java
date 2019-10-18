package club.wedcloud.www.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import club.wedcloud.www.dao.Menu;
import club.wedcloud.www.mapper.MenuMapper;

@Api(tags = "菜单管理")
@RestController
@RequestMapping("/v1")
public class MenuController {
  @Autowired
  private MenuMapper mapper;

  @ApiOperation("菜单列表")
  @GetMapping("/menulist")
  public ResponseEntity<List<Menu>> getMenuList() {
    return ResponseEntity.ok(mapper.findMenuList());
  }
}
