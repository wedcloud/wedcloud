package club.wedcloud.www.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import club.wedcloud.www.dao.Menu;
import club.wedcloud.www.mapper.MenuMapper;

@RestController
@RequestMapping("/v1")
public class MenuController {
  @Autowired
  private MenuMapper mapper;

  @GetMapping("/menulist")
  public ResponseEntity<List<Menu>> getMenuList() {
    return ResponseEntity.ok(mapper.findMenuList());
  }
}
