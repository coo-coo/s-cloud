package com.coo.s.cloud.job;

import java.util.List;

import org.apache.log4j.Logger;

import com.coo.s.cloud.model.Account;
import com.kingstar.ngbf.s.cache.IRepository;
import com.kingstar.ngbf.s.mongo.MongoItem;
import com.kingstar.ngbf.s.mongo.QueryAttrs;

/**
 * 读取所有的Account信息,放置在MC中,用来提供MContact和Account的信息同步 参见MContactJob
 */
public class AccountJob extends GenericCloudJob {

	private static Logger logger = Logger.getLogger(AccountJob.class);

	@Override
	public void execute() {
		// 放置Vote信息到MC中
		put2MC();
	}

	@Override
	public String getName() {
		return AccountJob.class.getName();
	}

	/**
	 * 放置信息到MC中
	 */
	private void put2MC() {
		// 获得所有条目
		QueryAttrs query = QueryAttrs.blank().desc("_tsi");
		List<MongoItem> items = getMongo().findItems(Account.SET, query);
		logger.debug("account size=" + items.size());
		for (MongoItem mi : items) {
			String mobile = (String) mi.get("mobile");
			String mcKey = "account." + mobile;
			// 放置到MC中
			getMC().put(mcKey, mi, IRepository.HOUR_1);
		}
	}

}
