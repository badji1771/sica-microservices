package org.formation.service.banque;

import org.formation.dto.BanqueDto;

import java.text.ParseException;
import java.util.List;

public interface BanqueClientServiceI {
    List<BanqueDto> getAllBanque()throws Exception;
}
