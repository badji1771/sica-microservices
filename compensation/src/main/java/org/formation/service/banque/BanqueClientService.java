package org.formation.service.banque;

import jakarta.annotation.Resource;
import lombok.extern.java.Log;
import org.formation.dto.BanqueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@Log
public class BanqueClientService  implements BanqueClientServiceI{

    @Resource
    RestTemplate banqueRestTemplate;


    private final CircuitBreakerFactory cbFactory;

    private BanqueDto[] banques= new BanqueDto[0];

    public BanqueClientService(CircuitBreakerFactory cbFactory) {
        this.cbFactory = cbFactory;
    }
    @Override
    public List<BanqueDto> getAllBanque()throws Exception {
        log.info("Recupartion de la liste des banques");
        try {

            banques= cbFactory.create("loadAlBanque")
                    .run(() -> banqueRestTemplate
                                    .getForObject( "/api/banques", BanqueDto[].class),
                            t -> {
                                log.warning("FALLBACK " + t);
                                return banques;
                            });
            return Arrays.asList(banques);
        }
        catch (Exception e){

            return Arrays.asList(banques);
        }
    }
}
