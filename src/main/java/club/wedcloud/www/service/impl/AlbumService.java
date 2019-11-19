package club.wedcloud.www.service.impl;

import club.wedcloud.www.dao.Album;
import club.wedcloud.www.service.JdbcTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@CacheConfig(cacheNames = {"album"})
@Service
public class AlbumService implements JdbcTemplateService<Album> {

    @Autowired
    @Qualifier("wedcloudJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    @Cacheable(key = "targetClass", unless = "#result == null")
    public List<Album> getList() {
        StringBuilder build = new StringBuilder(
                "SELECT `id`,`album_name` AS albumName,`imgurl`,`status`,`created_at` AS createdAt FROM `album` ");
        return jdbcTemplate.query(build.toString(), new BeanPropertyRowMapper(Album.class));
    }

    @Override
    @Cacheable(key = "'Album:'+#id", unless = "#result == null")
    public Album getInfo(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT `id`,`album_name` AS albumName,`imgurl`,`status`,`created_at` AS createdAt FROM `album` WHERE `id`=? and `cancel`=0",
                new Object[]{id}, BeanPropertyRowMapper.newInstance(Album.class));
    }

    @Override
    public Integer addInfo(Album t) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                // 指定主键,否则会报错
                PreparedStatement preparedStatement = con.prepareStatement(
                        "INSERT INTO album (`album_name`,`imgurl`,`status`,`created_at`,`cancel`) VALUES(?,?,?,NOW(3),0)",
                        new String[]{"id"});
                preparedStatement.setString(1, t.getAlbumName());
                preparedStatement.setString(2, t.getImgurl());
                preparedStatement.setString(3, t.getStatus());
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer updateInfo(Album t) {
        return jdbcTemplate.update(
                "UPDATE `album` SET `album_name`=?,`imgurl`=?,`status`=?,`modified_at`=NOW(3) WHERE `id`=?",
                t.getAlbumName(), t.getImgurl(), t.getStatus(), t.getId());
    }

    @Override
    public Integer deleteInfo(Integer id) {
        return jdbcTemplate.update("UPDATE `album` SET `modified_at`=NOW(3),`cancel`=1 WHERE `id`=?",
                id);
    }

}
