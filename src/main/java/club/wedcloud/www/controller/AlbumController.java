package club.wedcloud.www.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import club.wedcloud.www.dao.Album;
import club.wedcloud.www.service.impl.AlbumService;
import club.wedcloud.www.utils.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "相簿管理")
@RestController
@RequestMapping("/v1")
public class AlbumController {

  @Autowired
  private AlbumService service;

  private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);
  @ApiOperation(value = "相簿列表查询")
  @GetMapping("/albumlist")
  public ResponseEntity<ResponseBean> getAlbumList(
      @RequestParam(value = "albumName", defaultValue = "-1", required = false) String albumName) {
    return ResponseEntity.ok(ResponseBean.ok(service.getList()));
  }

  @ApiOperation(value = "相簿详情查询")
  @GetMapping("/album/{id}")
  public ResponseEntity<ResponseBean> getAlbum(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(ResponseBean.ok(service.getInfo(id)));
  }

  @ApiOperation(value = "新增相簿")
  @PostMapping("/album")
  @Transactional
  public ResponseEntity<ResponseBean> insertAlbum(@RequestBody Album album) {
    return ResponseEntity.ok(ResponseBean.ok(service.addInfo(album)));
  }

  @ApiOperation(value = "修改相簿")
  @PutMapping("/album/{id}")
  @Transactional
  public ResponseEntity<ResponseBean> updateAlbum(@PathVariable("id") Integer id,
      @RequestBody Album album) {
    album.setId(id);
    return ResponseEntity.ok(ResponseBean.ok(service.updateInfo(album)));
  }

  @ApiOperation("删除相簿")
  @DeleteMapping("/album/{id}")
  @Transactional
  public ResponseEntity<ResponseBean> deleteAlbum(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(ResponseBean.ok(service.deleteInfo(id)));
  }
}
