package org.formation.service.kafka;

import org.formation.domain.SoldeCompensation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducer {

    @Value("${channels.soldesCompensation}")
    private  String TOPIC ;

    @Autowired
    private KafkaTemplate<String, List<SoldeCompensation>> kafkaTemplate;

    public void sendReponse(List<SoldeCompensation> soldeCompensations) {
        System.out.println("send message: ");
        kafkaTemplate.send(TOPIC, soldeCompensations);
    }
}
