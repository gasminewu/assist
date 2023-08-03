package me.wll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 启动类
 *
 * @since  2023年8月3日
 * @author 武林林
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
@EntityScan
@EnableJpaRepositories
@ComponentScan("me.wll")
public class AssisApplication {
	public static void main(String[] args) {
		SpringApplication.run(AssisApplication.class, args);
	}
}

