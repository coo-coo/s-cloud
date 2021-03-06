package com.coo.s.cloud.job;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.coo.s.cloud.CloudFactory;
import com.coo.s.cloud.model.JobLog;
import com.kingstar.ngbf.s.cache.IRepository;
import com.kingstar.ngbf.s.mongo.INgbfMongoClient;
import com.kingstar.ngbf.s.mongo.MongoItem;
import com.kingstar.ngbf.s.mongo.QueryAttrs;

/**
 * @description
 * @author boqing.shen
 * @date 2014-9-24 下午1:01:22
 * @since 1.0.0.0
 */

public abstract class GenericCloudJob implements Job {

	private static Logger logger = Logger.getLogger(GenericCloudJob.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.debug(this.getName() + " started...");
		// 执行前,记录开始时间
		long startTs = System.currentTimeMillis();
		// 执行
		execute();
		// 记录日志，缺省都认为是成功
		saveLog(startTs);
	}

	private void saveLog(long startTs) {
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("start_ts", startTs);
		item.put("end_ts", System.currentTimeMillis());
		item.put("name", getName());
		item.put("status", JobLog.STATUS_SUCCESS);
		item.put("type", "JOB");
		getMongo().insert(JobLog.SET, item);
	}

	/**
	 * 获得上一次记录的时间
	 * 
	 * @return
	 */
	protected long getLastStartTs() {
		QueryAttrs query = QueryAttrs.blank().add("name", getName())
				.desc("_tsi");
		MongoItem mi = getMongo().findItemOne(JobLog.SET, query);
		long ts = 0l;
		if (mi != null) {
			ts = (Long) mi.get("start_ts");
		}
		return ts;
	}

	/**
	 * 业务执行
	 */
	public abstract void execute();

	/**
	 * 返回Job名称
	 */
	public abstract String getName();

	// ////////////////////////////////////////////

	protected static INgbfMongoClient getMongo() {
		return CloudFactory.getMongo();
	}

	protected static IRepository getMC() {
		return CloudFactory.getMC();
	}

}
