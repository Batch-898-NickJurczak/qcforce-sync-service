package com.revature;

import com.revature.logger.AppLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * QCForce Sync Service Application Main Class
 * 
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class SyncServiceApplication {
	/**
	 * @param args input parameters passed in when running the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SyncServiceApplication.class, args);
		AppLogger.log.info("main: Sync Service Booted Up!");
	} 

}
