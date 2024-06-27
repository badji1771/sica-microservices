package org.formation.service.kafka;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import org.formation.dto.JourneeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class KafkaConsumerTest {

	@Autowired
	KafkaTemplate<Long, JourneeDto> kafkaTemplate;
	
	@Value("${channels.fermetureJournee}")
	String TOPIC; 
	
	@Test 
	public void testConsume() throws InterruptedException, ExecutionException {
		
		JourneeDto dto = JourneeDto.builder().id(1l).dateJournee(new Date()).etat("ETAT").build();
		SendResult result = kafkaTemplate.send(TOPIC,dto).get();
		
		assertTrue(result.getRecordMetadata().hasOffset());
	}
}
