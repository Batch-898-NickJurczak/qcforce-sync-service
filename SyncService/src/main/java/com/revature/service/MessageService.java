package com.revature.service;

import java.util.List;

import com.revature.domain.Batch;

/**
 * Used to send data to messaging queues.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
public interface MessageService {
	
	/**
	 * Sends form response data to messaging queue.
	 */
	public void sendData();

	/**
	 * Sends batch data to messaging queue.
	 * @param data a list of batch data.
	 */
	public void sendBatchData(List<Batch> data);
}
