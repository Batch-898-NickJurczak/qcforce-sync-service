package com.revature.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.logger.AppLogger;
import com.revature.service.MessageService;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Controls all endpoints related to Forms.
 * 
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@RestController
@CrossOrigin
public class SyncController {
	/**
	 * Creates an instance of {@link MessageService} used to send data.
	 */
	private MessageService messageService;

	/**
	 * Initializes all services.
	 * 
	 * @param messageService sets from bean of type {@link MessageService}.
	 */
	public SyncController(MessageService messageService) {
		super();
		this.messageService = messageService;
	}

	/**
	 * Triggers Sync process to retrieve new data form Google Sheets.
	 * 
	 * @return a Mono state that attempts to trigger the sync process.
	 */
	@PostMapping("/sync")
	public Mono<Void> triggerSyncService() {
		AppLogger.log.info("triggerSyncService: triggerSyncService Called");
		return Mono.fromRunnable(() -> messageService.sendData()).subscribeOn(Schedulers.elastic()).then();
	}

}
