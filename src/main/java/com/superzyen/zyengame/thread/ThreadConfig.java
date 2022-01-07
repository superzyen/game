package com.superzyen.zyengame.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class ThreadConfig{
    /**
     * 配置线程池
     */
    // 声明一个线程池(并指定线程池的名字)
    @Bean("taskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        // 最小线程数量
        poolTaskExecutor.setCorePoolSize(3);
        // 最大线程数据量
        poolTaskExecutor.setMaxPoolSize(1024);
        // 等待队列
        poolTaskExecutor.setQueueCapacity(256);
        // 初始化
        poolTaskExecutor.initialize();
        //允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        poolTaskExecutor.setKeepAliveSeconds(60);
        return poolTaskExecutor;
    }
}
