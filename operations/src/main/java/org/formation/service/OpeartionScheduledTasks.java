package org.formation.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Log
public class OpeartionScheduledTasks {
	
	private final OperationService operationService ;
	
   public OpeartionScheduledTasks(OperationService operationService) {
		super();
		this.operationService = operationService;
	}

// @Scheduled(cron = "0 0 0 * * *")
  //  @Scheduled(fixedRate = 60000)
    public void runTask() {
         log.info("----------------start traitement de fichier-------------" );
         operationService.genererOperation();
         log.info("----------------end traitement de fichier-------------" );
    }
}