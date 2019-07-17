package club.wedcloud.www.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import club.wedcloud.www.config.SimpleUpdateExtendedLanguageDriver;
import club.wedcloud.www.dao.Album;
import club.wedcloud.www.mapper.Provider.AlbumProvider;

public interface AlbumMapper {
  String SELECT_ALBUM =
      "SELECT `id`,`album_name` AS albumName,`imgurl`,`status`,`created_at` AS createdAt FROM album WHERE id=#{id} and deleted_at IS NULL";

  String INSERT_ALBUM =
      "INSERT INTO album (`album_name`,`imgurl`,`status`,`created_at`) VALUES(#{albumName},#{imgurl},#{status},NOW(3))";

  String UPDATE_ALBUM = "update album (#{album}),`modified_at`=NOW(3) where id=#{id}";

  String DELETE_ALBUM = "update album set deleted_at=NOW(3) where id=#{id}";

  @Select(SELECT_ALBUM)
  Album getAlbum(Integer id);

  @SelectProvider(type = AlbumProvider.class, method = "findAll")
  List<Album> findAll(String ablumName);

  @Options(useGeneratedKeys = true)
  @Insert(INSERT_ALBUM)
  int insertAlbum(Album album);

  @Update(UPDATE_ALBUM)
  @Lang(SimpleUpdateExtendedLanguageDriver.class)
  int updateAlbum(Album album);

  @Delete(DELETE_ALBUM)
  int deleteAlbum(Integer id);

}
