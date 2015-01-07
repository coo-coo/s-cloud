package com.coo.s.cloud.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.kingstar.ngbf.s.mongo.MongoItem;
import com.kingstar.ngbf.s.util.GenericsUtil;
import com.kingstar.ngbf.s.util.PubString;

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

	@Column(name = "_id", label = "Mongo主键ID,如果是null，则表示该对象未被实例化，不参加业务计算,否则数据迁移不好办")
	protected String _id = null;

	@Column(name = "_tsi", label = "系统数据生成时间戳:参见MongoItem")
	public long _tsi = -1;

	@Column(name = "_tsu", label = "系统数据的更新时间戳:参见MongoItem")
	public long _tsu = -1;

	@Column(name = "owner", label = "拥有者账号：对应Account的uid和tsi")
	protected String owner = null;

	@Column(name = "updater", label = "数据最近更新者账号：对应Account的uid和tsu")
	protected String updater = null;

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

	public void set_tsi(long _tsi) {
		this._tsi = _tsi;
	}

	public void set_tsu(long _tsu) {
		this._tsu = _tsu;
	}

	// ////////////////////////////////////////////////
	// /////////// 实现一些方法供调用 ///////////
	// ////////////////////////////////////////////////

	/**
	 * 将指定的BasicObject根据@Column注解,生成简单的Map对象 用于将对象生成到MongoItem中 进队有注解的字段进行实现
	 * M端发送对象回来,Server端进行转换[方向:M->S]
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> item = new HashMap<String, Object>();
		// 获得所有的Field列表，遍历循环
		List<Field> fields = GenericsUtil.getClassSimpleFields(this.getClass(),
				true);
		for (Field field : fields) {
			Column col = field.getAnnotation(Column.class);
			if (col != null) {
				String fieldName = field.getName();
				Object value = "";
				try {
					value = PropertyUtils.getProperty(this, fieldName);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 键值...缺省是参照注解,如果没有声明,则按照字段名称来
				String key = col.name();
				if (PubString.isNullOrSpace(key)) {
					key = fieldName;
				}
				item.put(key, value);
			}
		}
		// 放置其它全部属性
		// item.putAll(bo.getAttrs());
		return item;
	}

	/**
	 * 将MI对象的值Merge到BO中[方向:S->M]
	 */
	public void merge(MongoItem mi) {
		// 获得所有的Field列表，遍历循环
		List<Field> fields = GenericsUtil.getClassSimpleFields(this.getClass(),
				true);
		for (Field field : fields) {
			Column col = field.getAnnotation(Column.class);
			if (col != null) {
				try {
					// Type type = field.getGenericType();
					// 根据注解的名称,获得数据库的Key
					Object value = mi.get(col.name());
					PropertyUtils.setProperty(this, field.getName(), value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// 处理专有的框架的字段
		this.set_id(mi.get_id());
		this.set_tsu(getMiLong(mi, "_tsu"));
		this.set_tsi(getMiLong(mi, "_tsi"));
	}

	public static Long getMiLong(MongoItem mi, String key) {
		Object value = mi.get(key);
		return value == null ? 0l : (Long) value;
	}

	public static String getMiStr(MongoItem mi, String key) {
		Object value = mi.get(key);
		return value == null ? "" : value.toString();
	}

}
