package club.wedcloud.www.mapper;

import club.wedcloud.www.config.SimpleUpdateExtendedLanguageDriver;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import club.wedcloud.www.dao.UserInfo;
import org.apache.ibatis.annotations.Update;

public interface UserInfoMapper {
  String SELECT_USER_LIST =
      "SELECT `id`,`username`,`password_hash` as passwordHash,`sex`,`age`,`open_id`,`email`,`mobile`,`headimgurl`,`status`,`created_at` as createdAt,`modified_at` as modifiedAt FROM `user_info` WHERE deleted_at IS NULL ";

  String INSERT_USER_INFO =
      "INSERT INTO user_info (`username`,`password_hash`,`sex`,`age`,`open_id`,`email`,`mobile`,`headimgurl`,`status`,`created_at`) "
      + "VALUES(#{username},#{passwordHash},#{sex},#{age},#{openId},#{email},#{mobile},#{headimgurl},#{status},NOW(3))";

  String UPDATE_USER_INFO = "update user_info (#{userInfo}),`modified_at`=NOW(3) where id=#{id}";

  String DELETE_USER_INFO = "update user_info set deleted_at=NOW(3) where id=#{id}";

  @Select(SELECT_USER_LIST)
  List<UserInfo> findUserList();

  @Options(useGeneratedKeys = true)
  @Insert(INSERT_USER_INFO)
  int insertIntoUserInfo(UserInfo userInfo);

  @Update(UPDATE_USER_INFO)
  @Lang(SimpleUpdateExtendedLanguageDriver.class)
  int updateUserInfo(UserInfo userInfo);

  @Delete(DELETE_USER_INFO)
  int deletedUserInfo(Integer id);
}
