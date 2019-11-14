package club.wedcloud.www.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户实体")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Manager {
  @ApiModelProperty("主键ID")
  private Integer id;
  @ApiModelProperty("用户名")
  private String userName;
  @ApiModelProperty("手机号")
  private String mobile;
  @ApiModelProperty("状态")
  private Integer status;
  @ApiModelProperty("创建时间")
  private String createdAt;
  @ApiModelProperty("修改时间")
  private String modifiedAt;
  @ApiModelProperty("删除状态 0未删除 1已删除")
  private transient Integer cancel;
}
