package org.formation.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.formation.service.dto.journeecompensation.JourneeCompensationConsumer;
import java.text.ParseException;

public interface FermetureJourneeCompensationConsumer {
    void consumeResponse(JourneeCompensationConsumer journeeCompensationConsumer) throws JsonProcessingException, ParseException;
}
