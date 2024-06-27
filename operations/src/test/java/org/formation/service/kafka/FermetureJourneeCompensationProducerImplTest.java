package org.formation.service.kafka;

import org.formation.service.dto.journeecompensation.JourneeCompensationProducer;
import org.formation.utils.Utulitaires;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Date;

@SpringBootTest
class FermetureJourneeCompensationProducerImplTest {
    @Value("${channels.fermetureJournee}")
    private String TOPIC;


    @Autowired
    private KafkaTemplate<String, JourneeCompensationProducer> kafkaTemplate;

    @Test
    public void sendJourneeCompensation() {
        kafkaTemplate.send(TOPIC, JourneeCompensationProducer.builder().typeCompensation("National").id(1L).dateJournee(new Date()).etat(String.valueOf(Utulitaires.ETAT.FERME)).build());
    }


}