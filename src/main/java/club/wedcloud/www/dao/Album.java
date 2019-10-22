package club.wedcloud.www.dao;

import io.swagger.annotations.Api;
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
public class Album {
  @ApiModelProperty("相簿编号")
  private Integer id;
  @ApiModelProperty("相簿名称")
  private String albumName;
  @ApiModelProperty("相簿显示图像")
  private String imgurl;
  @ApiModelProperty("相簿状态")
  private String status;
  @ApiModelProperty("创建时间")
  private String createdAt;
  @ApiModelProperty("修改时间")
  private String modifiedAt;
  @ApiModelProperty("删除时间")
  private transient String deletedAt;
}
