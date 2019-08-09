package com.open.iot.common.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * 
* @ClassName: AsycTaskExecutorConfig 
* @Description: 
*  * 线程池配置、启用异步
* @author huy
* @date 2019年6月16日 下午12:39:00 
*
 */
@EnableAsync(proxyTargetClass = true)
@Configuration
public class AsycTaskExecutorConfig {

	/** Set the ThreadPoolExecutor's core pool size.
     * 线程池维护线程的最小数量.
     * */
    private int corePoolSize = 10;
    /** Set the ThreadPoolExecutor's maximum pool size.
     *线程池维护线程的最大数量
     * */
    private int maxPoolSize = 200;
    /** Set the capacity for the ThreadPoolExecutor's BlockingQueue.
     *队列最大长度
     * */
    private int queueCapacity = 10;
	
	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("MyExecutor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
	}
}
