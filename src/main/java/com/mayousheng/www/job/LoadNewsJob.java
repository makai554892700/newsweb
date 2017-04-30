package com.mayousheng.www.job;

import com.mayousheng.www.utils.WangYiNewsUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by marking on 2017/4/30.
 */
public class LoadNewsJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        WangYiNewsUtils.getInstance().loadNews();
    }

}
