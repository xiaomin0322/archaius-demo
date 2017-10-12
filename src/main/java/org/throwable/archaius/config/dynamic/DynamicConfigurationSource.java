package org.throwable.archaius.config.dynamic;

import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/10/12 23:10
 */
@Slf4j
public class DynamicConfigurationSource implements PolledConfigurationSource {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	//config.properties配置了每隔十秒会刷新一次
	@Override
	public PollResult poll(boolean initial, Object checkPoint) throws Exception {
		log.info("Fire polling configuration...");
		Map<String, Object> kv = new HashMap<>();
		kv.put("dynamic-currentTime", LocalDateTime.now().format(FORMATTER));  //每次调用会刷新
		return PollResult.createFull(kv);
	}
}
