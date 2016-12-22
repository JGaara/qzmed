package com.zehin.vpaas.generic;

import java.util.Date;
import java.util.Properties;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.zehin.vpaas.common.util.SpringBeanUtil;

@EnableScheduling
public class DynamicScheduler implements SchedulingConfigurer {
    private String cron;
    
    public DynamicScheduler() {
    	Properties p = (Properties)SpringBeanUtil.getBean("propertyConfigurer");
		cron = p.getProperty("crontab").replace("_", " ");
		
    }
	@Override
	public void configureTasks(ScheduledTaskRegistrar reg) {
		reg.addTriggerTask(new Runnable() {  
            @Override  
            public void run() {  
            	ConversionTask task = SpringBeanUtil.getBean("conversionTask");
            	task.convert();
            }  }, new Trigger() {
    			@Override
    			public Date nextExecutionTime(TriggerContext triggerContext) {
    				System.out.println("now cron: " + cron);
    				CronTrigger trigger = new CronTrigger(cron);
                    Date nextExec = trigger.nextExecutionTime(triggerContext);
                    return nextExec;
    			}
    		});		
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	
	
}
