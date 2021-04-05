package com.xidian.iot.datapush.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Quartz Configuration.
 *
 * @since 1.0.0 2017年11月23日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Configuration
public class QuartzConfiguration {

    private static final int TIME = 20; // 更新频率

    // JobDetail
    @Bean
    public JobDetail QueryDataJobDetail() {
        return JobBuilder.newJob(QueryDataJob.class).withIdentity("QueryDataJob")
                .storeDurably().build();
    }

    // Trigger
    @Bean
    public Trigger QueryDataJobSyncTrigger() {

        SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(TIME).repeatForever();

        return TriggerBuilder.newTrigger().forJob(QueryDataJobDetail())
                .withIdentity("QueryDataSyncTrigger").withSchedule(schedBuilder).build();
    }
}
