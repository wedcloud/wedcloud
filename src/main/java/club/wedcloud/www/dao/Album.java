package club.wedcloud.www.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author xuhb
 * @Description Album
 * @time 2019年7月17日
 */
@Data
@ApiModel(description = "相簿实体")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Album {
  @ApiModelProperty("相簿编号")
  private Integer id;
  @ApiModelProperty("相簿名称")
  private String albumName;
  @ApiModelProperty("相簿显示图像")
  private String imgurl;
  @ApiModelProperty("相簿状态 0 启动 1停用")
  private String status;
  @ApiModelProperty("创建时间")
  private String createdAt;
  @ApiModelProperty("修改时间")
  private String modifiedAt;
  @ApiModelProperty("删除状态 0未删除 1已删除")
  private transient Integer cancel;
}
