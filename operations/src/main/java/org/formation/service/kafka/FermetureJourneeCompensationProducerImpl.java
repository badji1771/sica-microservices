package org.formation.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.formation.service.dto.journeecompensation.JourneeCompensationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FermetureJourneeCompensationProducerImpl implements FermetureJourneeCompensationProducer{

    @Value("${channels.fermetureJournee}")
    private String TOPIC;


    @Autowired
    private KafkaTemplate<String, JourneeCompensationProducer> kafkaTemplate;

    public void sendJourneeCompensation(JourneeCompensationProducer journeeCompensationProducer) {
        log.debug(journeeCompensationProducer.toString());
        kafkaTemplate.send(TOPIC, journeeCompensationProducer);
    }

}
