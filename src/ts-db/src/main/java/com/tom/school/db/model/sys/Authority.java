package com.tom.school.db.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.tom.school.db.param.sys.AuthorityParameter;

@Entity
@Table(name = "authority")
@Cache(region = "all", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Authority extends AuthorityParameter {

	private static final long serialVersionUID = -3124771334342770383L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id; // ID
	@Column(name = "sort_order")
	private Integer sortOrder; // 排序
	@Column(name = "menu_code", length = 50, nullable = false)
	private String menuCode; // 菜单代号
	@Column(name = "menu_name", length = 50, nullable = false)
	private String menuName; // 菜单显示内容
	@Column(name = "menu_config", length = 200)
	private String menuConfig; // 菜单配置参数
	@Column(name = "buttons", length = 50)
	private String buttons; // 按钮显示项
	@Column(name = "expanded", columnDefinition = "int default 0 not null")
	private Boolean expanded; // 可伸展的
	@Column(name = "checked", columnDefinition = "int")
	private Boolean checked; // 可勾选的
	@Column(name = "leaf", columnDefinition = "int default 0 not null")
	private Boolean leaf; // 是否是叶子项
	@Column(name = "url", length = 100)
	private String ur; // 创建 Tab的路径
	@Column(name = "icon_cls", length = 20)
	private String iconCls; // 按钮样式
	@Column(name = "parent_id")
	private Long parentId; // 父节点ID
	
	public Authority(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuConfig() {
		return menuConfig;
	}

	public void setMenuConfig(String menuConfig) {
		this.menuConfig = menuConfig;
	}

	public String getButtons() {
		return buttons;
	}

	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public String getUr() {
		return ur;
	}

	public void setUr(String ur) {
		this.ur = ur;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", sortOrder=" + sortOrder + ", menuCode=" + menuCode + ", menuName=" + menuName + ", menuConfig=" + menuConfig + ", buttons=" + buttons + ", expanded=" + expanded + ", checked=" + checked + ", leaf=" + leaf + ", ur=" + ur + ", iconCls=" + iconCls + ", parentId=" + parentId + "]";
	}

}
