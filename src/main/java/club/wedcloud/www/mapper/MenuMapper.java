package club.wedcloud.www.mapper;

import club.wedcloud.www.dao.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {

    String MENU_LIST =
            "SELECT `id`,`menu_name` AS menuName,`menu_url` AS menuUrl,`menu_icon` AS menuIcon,`menu_level` AS menuLevel,`menu_parent` AS menuParent,`status` FROM menu";

    @Select(MENU_LIST)
    List<Menu> findMenuList();
}
