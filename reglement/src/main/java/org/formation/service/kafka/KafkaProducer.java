package org.formation.service.kafka;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendReponse(String topic, Object sentObject) {
        log.info("Send message: " + sentObject);
        kafkaTemplate.send(topic, sentObject);
    }

}
