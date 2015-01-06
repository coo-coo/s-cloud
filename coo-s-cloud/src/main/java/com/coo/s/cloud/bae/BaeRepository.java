package com.coo.s.cloud.bae;

import com.baidu.bae.api.memcache.BaeCache;
import com.kingstar.ngbf.s.cache.GenericRepository;

public class BaeRepository extends GenericRepository {

	private BaeCache baeMc = com.baidu.bae.api.factory.BaeFactory.getBaeCache(BaeConfig.CACHE_ID,
			BaeConfig.CACHE_ADDR, BaeConfig.API_KEY, BaeConfig.SECRET_KEY);

	@Override
	public Object getValue(String key) {
		return baeMc.get(key);
	}

	@Override
	public void remove(String key) {
		baeMc.delete(key);
	}

	@Override
	public void put(String key, Object value, int duraSecond) {
		// TODO 单位
		baeMc.add(key, value, duraSecond);
	}
}
