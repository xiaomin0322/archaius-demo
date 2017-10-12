package org.throwable.archaius.config;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author throwable
 * @version v1.0
 * @description 其实所有的配置加载可以通过ConfigurationManager实现
 * @since 2017/10/12 22:49
 */
@Slf4j
public class CustomConfigurationDemo {

	public static void main(String[] args) throws Exception {
		String file = "custom.properties";
		log.debug("Load custom file...");
		//其实在这之前,config.properties也已经被加载,如果此时custom.properties也定义一个name的k-v,会添加到一个ConcurrentMapConfiguration中
		//读取的时候会顺序读取,命中会马上返回,因此会返回了config.properties的name=doge
		ConfigurationManager.loadPropertiesFromResources(file);
		//custom.properties下age=28
		DynamicIntProperty age = DynamicPropertyFactory.getInstance().getIntProperty("age", 10086);
		System.out.println(age.get());
	}
}
