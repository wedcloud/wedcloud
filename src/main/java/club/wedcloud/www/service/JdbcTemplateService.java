package club.wedcloud.www.service;

import java.util.List;

/**
 * 
 * @author xuhb
 * @Description JdbcTemplateService 定义jbdc驱动接口
 * @time 2019年11月13日
 */
public interface JdbcTemplateService<T> {

  /**
   * 
   * @Description 查询所有数据列表
   * @return
   */
  public List<T> getList();

  /**
   * 
   * @Description 单查详情
   * @return
   */
  public T getInfo(Integer id);

  /**
   * 
   * @Description 新增
   * @param t
   * @return
   */
  public Integer addInfo(T t);

  /**
   * 
   * @Description 修改
   * @param t
   * @return
   */
  public Integer updateInfo(T t);

  /**
   * 
   * @Description 删除
   * @param id
   * @return
   */
  public Integer deleteInfo(Integer id);
}
