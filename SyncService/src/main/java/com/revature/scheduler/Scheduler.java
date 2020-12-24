package com.revature.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.revature.logger.AppLogger;
import com.revature.service.MessageService;

/**
 * Schedules a synchronization at a specified interval (default : twenty four hours). 
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@Component
public class Scheduler {
	
	/**
	 * Instance of messaging service
	 */
	private MessageService messageService;

	/**
	 * The fixed delay of each sync
	 */
	private final String fixedDelay;

	/**
	 * @param messageService MessageService bean
	 */
	public Scheduler(MessageService messageService,
					 @Value("${sync-service.scheduler.fixedDelay}") String fixedDelay) {
		this.messageService = messageService;
		this.fixedDelay = fixedDelay;
	}

	/**
	 * Triggers a synchronization with the data source. Default: 86400000 milliseconds
	 */
	@Scheduled(fixedDelayString = "${sync-service.scheduler.fixedDelay}")
	public void triggerSync() {
		messageService.sendData();
		AppLogger.log.info("triggerSync: scheduled sync with rate of: " + fixedDelay + " milliseconds");
	}

}
