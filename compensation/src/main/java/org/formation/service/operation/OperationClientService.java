package org.formation.service.operation;

import jakarta.annotation.Resource;
import lombok.extern.java.Log;
import org.formation.dto.BanqueDto;
import org.formation.dto.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@Log
public class
OperationClientService implements OperationClientServiceI {

    @Resource
    RestTemplate operationRestTemplate;


    private final CircuitBreakerFactory cbFactory;

    private Operation[] operations= new Operation[0];

    public OperationClientService(CircuitBreakerFactory cbFactory) {
        this.cbFactory = cbFactory;
    }
    @Override
    public List<Operation> getAllOperationByIdJournee(Long idCompense)throws Exception {
        log.info("Recupartion des operations de la journée");
        try {

            operations= cbFactory.create("getOperationsParJourneeCompense")
                    .run(() -> operationRestTemplate
                                    .getForObject( "/api/operations/journee/{idCompense}", Operation[].class,idCompense),
                            t -> {
                                log.warning("FALLBACK " + t);
                                return operations;
                            });
            return Arrays.asList(operations);
        }
        catch (Exception e){

            return Arrays.asList(operations);
        }
    }
}
