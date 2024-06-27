package org.formation.service.kafka;

import lombok.Getter;
import lombok.extern.java.Log;
import org.formation.dto.JourneeCompensationDTO;
import org.formation.dto.SoldeCompensationDTO;
import org.formation.service.IReglementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class KafkaConsumer {

    private final IReglementService reglementService;

    private final KafkaProducer producer;

    @Autowired
    KafkaTemplate<Long, JourneeCompensationDTO> kafkaTemplate;

    @Value("${channels.fermetureJournee_response}")
    String TOPIC;

    // Method to retrieve processed messages
    @Getter
    private final List<SoldeCompensationDTO[]> processedMessages = new ArrayList<>();


    public KafkaConsumer(IReglementService reglementService, KafkaProducer producer) {
        this.reglementService = reglementService;
        this.producer = producer;
    }

    @KafkaListener(topics = "#{'${channels.soldesCompensation}'}", groupId = "reglement")
    public void consume(SoldeCompensationDTO[] soldeCompensations) {
        log.info("Génération des fichiers de règlement");
        reglementService.createReglement(soldeCompensations).subscribe(e -> {
            JourneeCompensationDTO journee = JourneeCompensationDTO.builder()
                    .idJourneeCompense(e.getIdJourneeCompense())
                    .etat("A_FERMER")
                    .build();

            producer.sendReponse(TOPIC, journee);
        });

        processedMessages.add(soldeCompensations);
    }

    public void clearProcessedMessages() {
        processedMessages.clear();
    }

}


