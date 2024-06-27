package org.formation.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.formation.service.dto.journeecompensation.JourneeCompensationConsumer;
import org.formation.service.journeecompensation.JourneeCompensationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FermetureJourneeCompensationConsumerImpl implements FermetureJourneeCompensationConsumer{

    private final JourneeCompensationService journeeCompensationService;


    public FermetureJourneeCompensationConsumerImpl(JourneeCompensationService journeeCompensationService) {
        this.journeeCompensationService = journeeCompensationService;
    }

    @KafkaListener(topics = "#{'${channels.fermetureJournee_response}'}", groupId = "reglement")
    public void consumeResponse(JourneeCompensationConsumer journeeCompensationConsumer) {
        log.debug(journeeCompensationConsumer.toString());
        journeeCompensationService.updateEtat(journeeCompensationConsumer);
    }

}
