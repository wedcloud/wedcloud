package club.wedcloud.www.controller;

import club.wedcloud.www.dao.Album;
import club.wedcloud.www.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class AlbumController {
  @Autowired
  private AlbumMapper mapper;


  @GetMapping("/albumlist")
  public ResponseEntity<List<Album>> getAlbumList(
          @RequestParam(value = "albumName", defaultValue = "-1", required = false) String albumName) {
    return ResponseEntity.ok(mapper.findAll(albumName));
  }

  @GetMapping("/album/{id}")
  public ResponseEntity<Album> getAlbum(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(mapper.getAlbum(id));
  }

  @PostMapping("/album")
  public ResponseEntity<String> insertAlbum(@RequestBody Album album) {
    try {
      if (mapper.insertAlbum(album) > 0) {
        return ResponseEntity.ok("新增成功");
      } else {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("新增失败");
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据有误");
    }
  }

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
