package org.throwable.archaius.config;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import lombok.extern.slf4j.Slf4j;

/**
 * @author throwable
 * @version v1.0
 * @description 默认会直接加载和读取classpath下的config.properties
 * @since 2017/10/12 22:43
 */
@Slf4j
public class DefaultConfigurationDemo {

	public static void main(String[] args){
		//config.properties下name=doge
		DynamicStringProperty name = DynamicPropertyFactory.getInstance().getStringProperty("name","throwable");
		System.out.println(name.get());
	}
}
