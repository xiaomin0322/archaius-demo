package org.throwable.archaius.config.dynamic;

import com.netflix.config.*;

/**
 * @author throwable
 * @version v1.0
 * @description 支持动态配置和定时刷新, 默认是每60s刷新一次, 可以配置archaius.fixedDelayPollingScheduler.delayMills修改刷新间隔
 * 需要引入commons-configuration依赖
 * @since 2017/10/12 23:07
 */
public class DynamicConfigurationDemo {

	public static void main(String[] args) throws Exception {
		//通过系统变量配置刷新间隔和初始延时加载时间
		System.setProperty("archaius.fixedDelayPollingScheduler.initialDelayMills", "0");  //初始延时0毫秒
		System.setProperty("archaius.fixedDelayPollingScheduler.delayMills", "10000");
		PolledConfigurationSource source = new DynamicConfigurationSource();
		//其实FixedDelayPollingScheduler本身的构造函数也可以设置延时加载和定时调度间隔,使用构造配置会覆盖系统配置
		AbstractPollingScheduler scheduler = new FixedDelayPollingScheduler();
		DynamicConfiguration configuration = new DynamicConfiguration(source, scheduler);
		ConfigurationManager.install(configuration);
		DynamicStringProperty time = DynamicPropertyFactory.getInstance().getStringProperty("dynamic-currentTime", "10086");
		System.out.println("Before sleep --> " + time.get());
		Thread.sleep(10000);  //睡10s,让配置刷新
		System.out.println("After sleep --> " + time.get());
		Thread.sleep(Integer.MAX_VALUE);
	}
}
