package org.haozf.basic.user.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.haozf.basic.user.service.IUserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTask {

	@Resource
	private IUserService userService;

	/**
	 * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下） cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23)
	 * *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT) 注意： 30 * * * * *
	 * 表示每分钟的第30秒执行，而（*斜杠30）表示每30秒执行
	 * 
	 * */
	@Scheduled(cron = "*/5 * * * * *")
	public void firstTask() {
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println("It is first task!时间：" + now);
	}
	
}
