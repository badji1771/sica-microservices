package org.formation.service.kafka;

import org.formation.service.dto.journeecompensation.JourneeCompensationConsumer;
import org.formation.utils.Utulitaires;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
public class SendResponseTest {

    @Value("${channels.fermetureJournee_response}")
    private String TOPIC_RESPONSE;

    @Autowired
    private KafkaTemplate<String, JourneeCompensationConsumer> kafkaTemplateResponse;

    @Test
    public void sendResponse(){
        kafkaTemplateResponse.send(TOPIC_RESPONSE, JourneeCompensationConsumer.builder().id(1L).etat(String.valueOf(Utulitaires.ETAT.FERME)).build());
    }

}
