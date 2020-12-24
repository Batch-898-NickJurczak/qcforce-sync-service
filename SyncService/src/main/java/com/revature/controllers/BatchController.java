package com.revature.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.logger.AppLogger;
import com.revature.domain.Batch;
import com.revature.service.MessageService;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Controls all endpoints related to batches.
 * 
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@RestController
@CrossOrigin
public class BatchController {
	/**
	 * Creates an instance of {@link MessageService} used to send data.
	 */
	private MessageService messageService;

	/**
	 * Initializes all services.
	 * 
	 * @param messageService sets from bean of type {@link MessageService}.
	 */
	public BatchController(MessageService messageService) {
		super();
		this.messageService = messageService;
	}

	/**
	 * Uploads data from a JSON request and sends it to a messaging queue.
	 * 
	 * @param data represents the mapped data from a JSON to a list of batches.
	 * @return a Mono state that attempts upload Batch JSON data and send it to a
	 *         message queue.
	 */
	@PostMapping("/batch")
	public Mono<Void> uploadJSON(@RequestBody List<Batch> data) {
		AppLogger.log.info("uploadJSON: uploadJson Called");
		return Mono.fromRunnable(() -> messageService.sendBatchData(data)).subscribeOn(Schedulers.elastic()).then();
	}

}
