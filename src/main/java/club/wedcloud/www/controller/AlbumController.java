package club.wedcloud.www.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import club.wedcloud.www.mapper.AlbumMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "相簿管理")
@RestController
@RequestMapping("/v1")
public class AlbumController {
  @Autowired
  private AlbumMapper mapper;

  private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);


  @ApiOperation(value = "相簿列表查询")
  @GetMapping("/albumlist")
  public ResponseEntity<List<Album>> getAlbumList(
      @RequestParam(value = "albumName", defaultValue = "-1", required = false) String albumName) {
    return ResponseEntity.ok(mapper.findAll(albumName));
  }

  @ApiOperation(value = "相簿详情查询")
  @GetMapping("/album/{id}")
  public ResponseEntity<Album> getAlbum(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(mapper.getAlbum(id));
  }

  @ApiOperation(value = "新增相簿")
  @PostMapping("/album")
  public ResponseEntity<String> insertAlbum(@RequestBody Album album) {
    try {
      if (mapper.insertAlbum(album) > 0) {
        return ResponseEntity.ok("新增成功");
      } else {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("新增失败");
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据有误");
    }
  }

  @ApiOperation(value = "修改相簿")
  @PutMapping("/album/{id}")
  public ResponseEntity<String> updateAlbum(@PathVariable("id") Integer id,
      @RequestBody Album album) {
    try {
      album.setId(id);
      if (mapper.updateAlbum(album) > 0) {
        return ResponseEntity.ok("修改成功");
      } else {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("修改失败");
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据有误");
    }
  }

  @ApiOperation("删除相簿")
  @DeleteMapping("/album/{id}")
  public ResponseEntity<String> deleteAlbum(@PathVariable("id") Integer id) {
    try {
      if (mapper.deleteAlbum(id) > 0) {
        return ResponseEntity.ok("删除成功");
      } else {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("删除失败");
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据有误");
    }
  }
}
