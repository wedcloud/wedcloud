package club.wedcloud.www.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import club.wedcloud.www.dao.UserInfo;

public interface UserInfoMapper {
  final static String SELECT_USER_LIST =
      "SELECT `id`,`username`,`password_hash` as passwordHash,`sex`,`age`,`open_id`,`email`,`mobile`,`headimgurl`,`status`,`created_at` as createdAt,`modified_at` as modifiedAt FROM `user_info` WHERE deleted_at IS NULL ";

  @Select(SELECT_USER_LIST)
  List<UserInfo> findUserList();
}
