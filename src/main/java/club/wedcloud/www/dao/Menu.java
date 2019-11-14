package club.wedcloud.www.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author xuhb
 * @Description Menu
 * @time 2019年7月17日
 */
@Data
@ApiModel(description = "菜单实体")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu {
  @ApiModelProperty("主键ID")
  private Integer id;
  @ApiModelProperty("菜单名称")
  private String menuName;
  @ApiModelProperty("菜单URL")
  private String menuUrl;
  @ApiModelProperty("菜单图标")
  private String menuIcon;
  @ApiModelProperty("菜单级别")
  private Integer menuLevel;
  @ApiModelProperty("父级菜单")
  private Integer menuParent;
  @ApiModelProperty("菜单状态 0启用 1停用")
  private Integer status;
  @ApiModelProperty("创建时间")
  private transient String createdAt;
  @ApiModelProperty("更新时间")
  private transient String modifiedAt;
  @ApiModelProperty("删除状态 0未删除 1已删除")
  private transient Integer cancel;
}
