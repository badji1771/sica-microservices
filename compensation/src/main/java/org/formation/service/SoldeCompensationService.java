package org.formation.service;

import lombok.extern.java.Log;
import org.formation.domain.SoldeCompensation;
import org.formation.dto.BanqueDto;
import org.formation.dto.JourneeDto;
import org.formation.dto.operation.Operation;
import org.formation.repository.SoldeCompensationRepository;
import org.formation.service.banque.BanqueClientServiceI;
import org.formation.service.operation.OperationClientServiceI;
import org.formation.util.Utilitaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
@Log
public class SoldeCompensationService  implements  SoldeCompensationServiceI{

    @Autowired
    SoldeCompensationRepository soldeCompensationRepository;

    @Autowired
    BanqueClientServiceI banqueClientService;

    @Autowired
    OperationClientServiceI operationClientService;
    @Override
    public List<SoldeCompensation> findByDateCompenseAndTypeOperation(String dateCompense, String typeOperation) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCompenseF = dateFormat.parse(dateCompense);
        if(typeOperation!=null && !typeOperation.isEmpty()){

            return soldeCompensationRepository.findByDateCompenseAndTypeOperation(dateCompenseF,typeOperation);

        }
        else{
            return  soldeCompensationRepository.findByDateCompense(dateCompenseF);
        }

    }

    @Override
    public List<SoldeCompensation> findByDateCompenseAndTypeOperationAndCodeBanque(String dateCompense, String typeOperation, String codeBanque) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCompenseF = dateFormat.parse(dateCompense);
        if(typeOperation!=null && !typeOperation.isEmpty()){

            return soldeCompensationRepository.findByDateCompenseAndTypeOperationAndCodeBanque(dateCompenseF,typeOperation,codeBanque);

        }
        else{
            return  soldeCompensationRepository.findByDateCompenseAndCodeBanque(dateCompenseF,codeBanque);
        }
    }

    @Override
    public BigDecimal getMontantByBanqueAndTypeOperationAndStatusAndSens(String codeBanque, String typeOperation, String etat, String sens, List<Operation> operationList) throws ParseException {
        log.info("Recupartion du montant solde");
        BigDecimal resultat = operationList.stream()
                .filter(operationDto -> {
                    if(sens.equals("EMIS")){
                        return  codeBanque.equals(operationDto.getBanqueSource().getCode());
                    }
                    else {
                        return  codeBanque.equals(operationDto.getBanqueDestination().getCode());
                    }

                })
                .filter(operationDto -> typeOperation.equals(operationDto.getTypeOperation().getCode()))
                .filter(operationDto -> etat.equals(operationDto.getEtat().name()))
                .map(Operation::getMontant)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return resultat;
    }


    @Override
    public SoldeCompensation saveSoldeCompensation(SoldeCompensation soldeCompensation)throws ParseException {
        log.info("Enregistrment du solde de compensation");
        return soldeCompensationRepository.save(soldeCompensation);
    }

    @Override
    public List<SoldeCompensation>  saveAll(List<SoldeCompensation> soldeCompensationList) throws ParseException {
        log.info("Enregistrment du solde de compensation");
        return soldeCompensationRepository.saveAll(soldeCompensationList);
    }

    @Override
    public List<SoldeCompensation> calculSoldeCompense(JourneeDto journee) throws JsonProcessingException, Exception {
    	 log.info("calcule des solde compense");
         List<SoldeCompensation> soldeCompensationList = new ArrayList<SoldeCompensation>();
         /*
          Recuperer la liste des banques
         */
       //  List<BanqueDto> banqueDtoList = getAllBanque();
        List<BanqueDto> banqueDtoList = banqueClientService.getAllBanque();
         /*
         chercher les operations de la journée
          */
         List<Operation> operationDtoList = operationClientService.getAllOperationByIdJournee(journee.getId());
         //pour chaque banque regrouper par type d'operation
         /*      cheques emis payés:
                 cheques reçus rejetes +
                 cheques reçus payés -
                 cheques emis rejetes -
                 virement émis payés -
                 virements reçus payés */

         for (BanqueDto banqueDto: banqueDtoList){
             BigDecimal montantSolde= new BigDecimal(BigInteger.ZERO);
             BigDecimal montantSoldeVir= new BigDecimal(BigInteger.ZERO);
             BigDecimal montantSoldeCh= new BigDecimal(BigInteger.ZERO);
             BigDecimal mtChqEmisPaye =getMontantByBanqueAndTypeOperationAndStatusAndSens(banqueDto.getCodeBanque(),
                     "025","PAYE","EMIS",operationDtoList);
             BigDecimal mtChqEmisRjete =getMontantByBanqueAndTypeOperationAndStatusAndSens(banqueDto.getCodeBanque(),
                     "025","REJETE","EMIS",operationDtoList);
             BigDecimal mtChqRecuPaye =getMontantByBanqueAndTypeOperationAndStatusAndSens(banqueDto.getCodeBanque(),
                     "025","PAYE","RECU",operationDtoList);
             BigDecimal mtChqRecuRjete =getMontantByBanqueAndTypeOperationAndStatusAndSens(banqueDto.getCodeBanque(),
                     "025","REJETE","RECU",operationDtoList);
             BigDecimal mtVirEmisPaye =getMontantByBanqueAndTypeOperationAndStatusAndSens(banqueDto.getCodeBanque(),
                     "015","PAYE","EMIS",operationDtoList);
             BigDecimal mtVirRecuPaye =getMontantByBanqueAndTypeOperationAndStatusAndSens(banqueDto.getCodeBanque(),
                     "015","PAYE","RECU",operationDtoList);
             montantSolde= montantSolde.add(mtChqRecuRjete).add(mtVirRecuPaye).add(mtChqEmisPaye)
                                       .subtract(mtChqRecuPaye).subtract(mtChqEmisRjete).subtract(mtVirEmisPaye);
             montantSoldeVir= montantSoldeVir.add(mtVirRecuPaye).subtract(mtVirEmisPaye);
             montantSoldeCh= montantSoldeCh.add(mtChqRecuRjete).add(mtChqEmisPaye)
                                           .subtract(mtChqRecuPaye).subtract(mtChqEmisRjete);
             String dateJourne = Utilitaire.dateToStringSql(journee.getDateJournee());
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             SoldeCompensation soldeCompensation = SoldeCompensation.builder()
                     .nuJourneeCompense(journee.getId())
                     .dateCompense(dateFormat.parse(dateJourne))
                     .codeBanque(banqueDto.getCodeBanque())
                     .soldeCompense(montantSolde)
                     .etat("CALCULE")
                     .build();
             SoldeCompensation soldeCompensationVir = SoldeCompensation.builder()
                     .nuJourneeCompense(journee.getId())
                     .dateCompense(dateFormat.parse(dateJourne))
                     .codeBanque(banqueDto.getCodeBanque())
                     .soldeCompense(montantSoldeVir)
                     .etat("CALCULE")
                     .typeOperation("015")
                     .build();
             SoldeCompensation soldeCompensationCh = SoldeCompensation.builder()
                     .nuJourneeCompense(journee.getId())
                     .dateCompense(dateFormat.parse(dateJourne))
                     .codeBanque(banqueDto.getCodeBanque())
                     .soldeCompense(montantSoldeCh)
                     .etat("CALCULE")
                     .typeOperation("025")
                     .build();
             List<SoldeCompensation> resultat = saveAll(
                                   List.of(soldeCompensation,soldeCompensationCh,soldeCompensationVir));
             soldeCompensationList.addAll(resultat);
         }

         return soldeCompensationList;

     }
    
}
