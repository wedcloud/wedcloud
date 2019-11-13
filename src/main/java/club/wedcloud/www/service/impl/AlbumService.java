package club.wedcloud.www.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import club.wedcloud.www.dao.Album;
import club.wedcloud.www.service.JdbcTemplateService;

@CacheConfig(cacheNames = {"album"})
@Service
public class AlbumService implements JdbcTemplateService<Album> {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  @Cacheable(key = "targetClass", unless = "#result == null")
  public List<Album> getList() {
    StringBuilder build = new StringBuilder(
        "SELECT `id`,`album_name` AS albumName,`imgurl`,`status`,`created_at` AS createdAt FROM album");
    return jdbcTemplate.query(build.toString(), new BeanPropertyRowMapper(Album.class));
  }

  @Override
  @Cacheable(key = "'Album:'+#id", unless = "#result == null")
  public Album getInfo(Integer id) {
    return jdbcTemplate.queryForObject(
        "SELECT `id`,`album_name` AS albumName,`imgurl`,`status`,`created_at` AS createdAt FROM album WHERE id=? and deleted_at IS NULL",
        new Object[] {id}, BeanPropertyRowMapper.newInstance(Album.class));
  }

  @Override
  public Integer addInfo(Album t) {
    String sql =
        "INSERT INTO album (`album_name`,`imgurl`,`status`,`created_at`) VALUES(?,?,?,NOW(3))";
    return jdbcTemplate.update(sql, t.getAlbumName(), t.getImgurl(), t.getStatus());
  }

  @Override
  public Integer updateInfo(Album t) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer deleteInfo(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

}
