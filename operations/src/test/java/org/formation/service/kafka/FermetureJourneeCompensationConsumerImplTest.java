package org.formation.service.kafka;
import org.formation.service.dto.journeecompensation.JourneeCompensationConsumer;
import org.formation.utils.Utulitaires;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
@SpringBootTest
class FermetureJourneeCompensationConsumerImplTest {

    @Value("${channels.fermetureJournee_response}")
    private String TOPIC;

    @Autowired
    KafkaTemplate<Long, JourneeCompensationConsumer> kafkaTemplate;


    @Test
    public void consumeResponse() throws InterruptedException {
        kafkaTemplate.send(TOPIC,JourneeCompensationConsumer.builder().id(1L).etat(String.valueOf(Utulitaires.ETAT.FERME)).build());
        Thread.sleep(60000L);
    }
}