package org.formation.sica.gatewaysica.service.api.feign;
import org.formation.sica.gatewaysica.dto.JourneeCompensationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class JourneeCompensationFeignClientFallBack implements JourneeCompensationFeignClient {
    @Override
    public ResponseEntity<JourneeCompensationResponse> getJourneeById(Long id) {
        return ResponseEntity.ok(journeeCompenseResponse());
    }

    @Override
    public ResponseEntity<JourneeCompensationResponse> ouvrirJournee(Long id) {
        return ResponseEntity.ok(journeeCompenseResponse());
    }

    @Override
    public ResponseEntity<JourneeCompensationResponse> fermerJournee(Long id) {
        return ResponseEntity.ok(journeeCompenseResponse());
    }

    @Override
    public ResponseEntity<JourneeCompensationResponse> listerJournee() {
        return ResponseEntity.ok(journeeCompenseResponse());
    }

    private JourneeCompensationResponse journeeCompenseResponse(){
        JourneeCompensationResponse result = new JourneeCompensationResponse();
        result.setLibelle("Aucune Journée");
        result.setTypeCompensationCode("Sans Objet");
        result.setEtat("Sans Objet");
        return result;
    }
}
