package com.coo.s.cloud.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @author boqing.shen
 * @since 1.0.0.0
 */

public abstract class BasicObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6121507204868751924L;

	@Column(name = "_id", label = "Mongo主键ID,如果是null，则表示该对象未被实例化，参加业务计算")
	protected String _id = null;

	@Column(name = "_tsi", label = "系统数据生成时间戳:参见MongoItem")
	public long _tsi = -1;

	@Column(name = "_tsu", label = "系统数据的更新时间戳:参见MongoItem")
	public long _tsu = -1;

	@Column(name = "owner", label = "拥有者账号：对应M端的HOST")
	protected String owner = null;

	@Column(name = "owner_id", label = "拥有者账号ID,关联Account表中的_id")
	protected String owner_id = null;

	@Column(name = "updater", label = "数据更新者账号")
	protected String updater = null;

	@Column(name = "status", label = "状态=0，缺省值")
	protected Integer status = 0;

	/**
	 * 支持是否选中...
	 */
	protected boolean selected = Boolean.FALSE;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public long get_tsi() {
		return _tsi;
	}

	public void set_tsi(Long _tsi) {
		this._tsi = _tsi;
	}

	public long get_tsu() {
		return _tsu;
	}

	public void set_tsu(Long _tsu) {
		this._tsu = _tsu;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 根据一个类对象,获得其Column注解,生成colName:fieldName的Map
	 */
	protected Map<String, String> getFieldMapping() {
		Map<String, String> map = new HashMap<String, String>();
		// 获得所有的Field列表，遍历循环
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Column col = field.getAnnotation(Column.class);
			if (col != null) {
				map.put(col.name(), field.getName());
			}
		}
		return map;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the updater
	 */
	public String getUpdater() {
		return updater;
	}

	/**
	 * @param updater
	 *            the updater to set
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public void set_tsi(long _tsi) {
		this._tsi = _tsi;
	}

	public void set_tsu(long _tsu) {
		this._tsu = _tsu;
	}
}
