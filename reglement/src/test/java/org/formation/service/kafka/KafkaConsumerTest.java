package org.formation.service.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.formation.dto.SoldeCompensationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;


import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"${channels.soldesCompensation}", "${channels.fermetureJournee_response}"})
public class KafkaConsumerTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private KafkaTemplate<String, SoldeCompensationDTO[]> kafkaTemplate;


    private Consumer<String, String> journeeConsumer;

    @Value("${channels.fermetureJournee_response}")
    String RETURN_TOPIC;

    @Value("${channels.soldesCompensation}")
    String TOPIC;

    @BeforeEach
    public void setUp() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("reglement", "true", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
        journeeConsumer = cf.createConsumer();
        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(journeeConsumer, RETURN_TOPIC);

    }

    @Test
    public void testKafkaConsumer() throws InterruptedException {
        // Given
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

        // When
        kafkaTemplate.send(TOPIC, soldesCompensation);

        Thread.sleep(10000);

        ConsumerRecord<String, String> record = KafkaTestUtils.getSingleRecord(journeeConsumer, RETURN_TOPIC);
        assertThat(record).isNotNull();
    }
}

