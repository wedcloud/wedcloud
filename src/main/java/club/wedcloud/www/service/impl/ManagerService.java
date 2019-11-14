package club.wedcloud.www.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import club.wedcloud.www.dao.Manager;
import club.wedcloud.www.service.JdbcTemplateService;

@Service
public class ManagerService implements JdbcTemplateService<Manager> {

  @Autowired
  @Qualifier("weduserJdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Manager> getList() {
    return jdbcTemplate.queryForList(
        "SELECT `id`,`user_name` AS userName,`mobile`,`status`,`created_at` AS createdAt,`modified_at` AS modifiedAt FROM `manager` WHERE `deleted_at` IS NULL",
        Manager.class);
  }

  @Override
  public Manager getInfo(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer addInfo(Manager t) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer updateInfo(Manager t) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer deleteInfo(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

}
