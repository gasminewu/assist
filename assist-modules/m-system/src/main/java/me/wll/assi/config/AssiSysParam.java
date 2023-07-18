package me.wll.assi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sysparams")
public class AssiSysParam {
	/**
	 * 云踏勘登录首页
	 */
	private String yunkantanurl;

}
