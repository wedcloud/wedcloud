package club.wedcloud.www.dao;

/**
 * 
 * @author xuhb
 * @Description Menu
 * @time 2019年7月17日
 */
public class Menu {
  private Integer id;
  private String menuName;
  private String menuUrl;
  private String menuIcon;
  private Integer menuLevel;
  private Integer menuParent;
  private Integer status;
  private transient String createdAt;
  private transient String modifiedAt;
  private transient String deletedAt;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public String getMenuUrl() {
    return menuUrl;
  }

  public void setMenuUrl(String menuUrl) {
    this.menuUrl = menuUrl;
  }

  public String getMenuIcon() {
    return menuIcon;
  }

  public void setMenuIcon(String menuIcon) {
    this.menuIcon = menuIcon;
  }

  public Integer getMenuLevel() {
    return menuLevel;
  }

  public void setMenuLevel(Integer menuLevel) {
    this.menuLevel = menuLevel;
  }

  public Integer getMenuParent() {
    return menuParent;
  }

  public void setMenuParent(Integer menuParent) {
    this.menuParent = menuParent;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(String modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public String getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(String deletedAt) {
    this.deletedAt = deletedAt;
  }

}
