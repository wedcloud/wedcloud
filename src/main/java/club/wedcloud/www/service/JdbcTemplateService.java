package club.wedcloud.www.service;

import java.util.List;

/**
 * @author xuhb
 * @Description JdbcTemplateService 定义jbdc驱动接口
 * @time 2019年11月13日
 */
public interface JdbcTemplateService<T> {

    /**
     * @return
     * @Description 查询所有数据列表
     */
    public List<T> getList();

    /**
     * @return
     * @Description 单查详情
     */
    public T getInfo(Integer id);

    /**
     * @param t
     * @return
     * @Description 新增
     */
    public Integer addInfo(T t);

    /**
     * @param t
     * @return
     * @Description 修改
     */
    public Integer updateInfo(T t);

    /**
     * @param id
     * @return
     * @Description 删除
     */
    public Integer deleteInfo(Integer id);
}
