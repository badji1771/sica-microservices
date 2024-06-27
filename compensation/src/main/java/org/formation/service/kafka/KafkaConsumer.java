package org.formation.service.kafka;

import lombok.extern.java.Log;
import org.formation.domain.SoldeCompensation;
import org.formation.dto.JourneeDto;
import org.formation.service.SoldeCompensationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class KafkaConsumer {

    @Autowired
    SoldeCompensationServiceI soldeCompensationService;

    @Autowired
    KafkaTemplate<Long, List<SoldeCompensation>> kafkaTemplate;

    @Value("${channels.soldesCompensation}")
    String TOPIC;

    @KafkaListener(topics = "#{'${channels.fermetureJournee}'}", groupId = "compensation")
    public void consume(JourneeDto journee) throws Exception {
        log.info("calcul des soldes compense");
        List<SoldeCompensation> soldeCompensationList=soldeCompensationService.calculSoldeCompense(journee);
        kafkaTemplate.send(TOPIC,soldeCompensationList);

    }
}
