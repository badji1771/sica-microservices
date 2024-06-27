package org.formation.service.kafka;

import org.formation.dto.JourneeCompensationDTO;
import org.formation.dto.SoldeCompensationDTO;
import org.formation.service.IReglementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class KafkaConsumerIntegrationTest {

    @Mock
    private KafkaProducer producer;

    @Autowired
    KafkaTemplate<Long, SoldeCompensationDTO[]> kafkaTemplate;

    @Autowired
    KafkaConsumer consumer;

    @Autowired
    IReglementService reglementService;

    @Value("${channels.soldesCompensation}")
    String TOPIC;

    @Test
    public void testConsume() throws InterruptedException {

        SoldeCompensationDTO soldeCompensation1 = SoldeCompensationDTO.builder()
                .idSoldeCompense(1)
                .dateOperation(LocalDate.now())
                .banqueId(2)
                .solde(new BigDecimal(1000))
                .etatSoldeCompensation("AT")
                .journeeCompensationId(5)
                .build();
        SoldeCompensationDTO soldeCompensation2 = SoldeCompensationDTO.builder()
                .idSoldeCompense(2)
                .dateOperation(LocalDate.now())
                .banqueId(3)
                .solde(new BigDecimal(100000))
                .etatSoldeCompensation("AT")
                .journeeCompensationId(5)
                .build();
        SoldeCompensationDTO[] soldesCompensation = {soldeCompensation1, soldeCompensation2};

        reglementService.createReglement(soldesCompensation).subscribe(e -> {
            JourneeCompensationDTO journee = JourneeCompensationDTO.builder()
                    .idJourneeCompense(e.getIdJourneeCompense())
                    .etat("A_FERMER")
                    .build();

            producer.sendReponse(TOPIC, journee);
        });

        kafkaTemplate.send(TOPIC, soldesCompensation);

        Thread.sleep(10000);

        List<SoldeCompensationDTO[]> processedMessages = consumer.getProcessedMessages();

        assertThat(processedMessages).contains(soldesCompensation);
    }

}