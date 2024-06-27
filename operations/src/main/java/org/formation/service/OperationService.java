package org.formation.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.formation.entities.Banque;
import org.formation.entities.JourneeCompensation;
import org.formation.entities.Operation;
import org.formation.entities.TypeRejet;
import org.formation.mappers.OperationMapper;
import org.formation.repository.JourneeCompensationRepository;
import org.formation.repository.OperationRepository;
import org.formation.repository.TypeCompensationRepository;
import org.formation.repository.TypeOperationRepository;
import org.formation.repository.TypeRejetRepository;
import org.formation.service.dto.operation.OperationDto;
import org.formation.service.dto.operation.OperationRequestDTO;
import org.formation.utils.Utulitaires;
import org.formation.utils.Utulitaires.ETAT;
import org.formation.utils.Utulitaires.SENS_BANQUE;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;

@Service
@Transactional
@Log
public class OperationService {

	private final OperationRepository operationRepository;
	private final BanqueServiceI banqueService;
	private final TypeRejetRepository typeRejetRepository;
	private final TypeOperationRepository typeOperationRepository;
	private final TypeCompensationRepository typeCompensationRepository;
	private final JourneeCompensationRepository jCompensationRepository;

	private final OperationMapper operationMapper;

	public OperationService(OperationRepository operationRepository, BanqueServiceI banqueService,
							TypeRejetRepository typeRejetRepository, TypeOperationRepository typeOperationRepository,
							TypeCompensationRepository typeCompensationRepository, OperationMapper operationMapper,
							JourneeCompensationRepository jCompensationRepository ) {
		super();
		this.operationRepository = operationRepository;
		this.banqueService = banqueService;
		this.typeRejetRepository = typeRejetRepository;
		this.typeOperationRepository = typeOperationRepository;
		this.typeCompensationRepository = typeCompensationRepository;
		this.operationMapper = operationMapper;
		this.jCompensationRepository = jCompensationRepository;
	}

//	public Operation createOperation(Operation operation) {
//		return operationRepository.save(operation);
//	}

	public List<Operation> getAllOperation() {
		return operationRepository.findAll();
	}

	public OperationDto findById(Long id) {
		Optional<Operation> optOperation = operationRepository.findById(id);
		Operation operation = optOperation.orElseThrow(() -> new EntityNotFoundException("Opératoin inconue "));
		return operationMapper.OperationToOperationDto(operation);
	}

	public OperationDto rejetOperation(Long id, String codeRejet)  {
		Optional<Operation> optOperation = operationRepository.findById(id);
		Operation operation = optOperation.orElseThrow(() -> new EntityNotFoundException("Opératoin inconue "));
		TypeRejet typeRejet = typeRejetRepository.findById(codeRejet)
				.orElseThrow(() -> new EntityNotFoundException("Code rejet inconue "));
		operation.setTypeRejet(typeRejet);
		operation.setEtat(Utulitaires.ETAT.REJETE);
		return operationMapper.OperationToOperationDto(operationRepository.save(operation));

	}

	public List<Operation> getOperationsParJourneeCompense(Long idCompense, OperationRequestDTO operationRequestDTO) {
		if (operationRequestDTO == null ||  (operationRequestDTO !=null && operationRequestDTO.getTypeOperation() !=null &&
				operationRequestDTO.getCodeBanque() !=null)
		)
		{
			return operationRepository.getOperationsParJourneeCompense(idCompense);
		} else
			if (operationRequestDTO.getCodeBanque() != null && !operationRequestDTO.getCodeBanque().isEmpty()
				&& operationRequestDTO.getTypeOperation() != null && !operationRequestDTO.getTypeOperation().isEmpty()) {
			return (operationRequestDTO.getSensBanque().name().isEmpty()
					|| operationRequestDTO.getSensBanque().name().isBlank()
					|| operationRequestDTO.getSensBanque().name().equals(SENS_BANQUE.S.name()))
					? operationRepository.getOperationsParJourneeCompenseBanqueSourceTypeOperation(idCompense,
					operationRequestDTO.getCodeBanque(), operationRequestDTO.getTypeOperation())
					: operationRepository.getOperationsParJourneeCompenseBanqueDestinationTypeOperation(
					idCompense, operationRequestDTO.getCodeBanque(), operationRequestDTO.getTypeOperation());
		}
			else if (operationRequestDTO.getCodeBanque() != null && !operationRequestDTO.getCodeBanque().isEmpty()
				&& (operationRequestDTO.getTypeOperation() == null || operationRequestDTO.getTypeOperation().isEmpty())) {
			return (operationRequestDTO.getSensBanque().name().isEmpty()
					|| operationRequestDTO.getSensBanque().name().isBlank()
					|| operationRequestDTO.getSensBanque().name().equals(SENS_BANQUE.S.name()))
					? operationRepository.getOperationsParJourneeCompenseBanqueSource(idCompense,
					operationRequestDTO.getCodeBanque())
					: operationRepository.getOperationsParJourneeCompenseBanqueDestination(idCompense,
					operationRequestDTO.getCodeBanque());
		} else if (operationRequestDTO.getTypeOperation() != null && !operationRequestDTO.getTypeOperation().isEmpty()
				&& (operationRequestDTO.getCodeBanque() == null || operationRequestDTO.getCodeBanque().isEmpty())) {
			return operationRepository.getOperationsParJourneeCompenseTypeOperation(idCompense,
					operationRequestDTO.getTypeOperation());
		} else
			return new ArrayList<>();
	}

	public void genererOperation() {
		fileToOperation("src/main/resources/ICOM1");
	}


	public List<Operation> fileToOperation(String directory) {
        List<Operation> operations = null;
        try {
            operations = new ArrayList<>();
            List<Banque> banques = banqueService.getBanques();
            log.info(banques.toString());

            JourneeCompensation jCompensation = jCompensationRepository.findAll().get(0);
            Operation operation = null;
            Set<String> fileNames = listDirectory(directory);
            for (String fn : fileNames) {
                Banque banqueD = null;
                Banque banqueS = null;
                log.info(" file name  " + fn);
                List<String> lines = getFile(directory + "/" + fn);
                for (String line : lines) {


                    log.info(" line " + line);
                    if (line.startsWith("FREM")) continue;


                    if (line.startsWith("EREM")) {
                        banqueS = banqueService.getBanqueBycode(banques, line.substring(20, 25));
                        continue;
                    }

                    if (line.startsWith("ELOT")) {
                        banqueD = banqueService.getBanqueBycode(banques, line.substring(15, 20));
                        continue;
                    }

                    operation = new Operation();

                    operation.setJourneeCompensation(jCompensation);
                    operation.setTypeOperation(typeOperationRepository.findByCode(line.substring(0, 3)));
                    operation.setBanqueDestination(banqueD);
                    operation.setCodeBanqueDestination(banqueD.getCode());
                   // operation.setBanqueDestination(banqueD);
                   // operation.setBanqueSource(banqueS);
                    operation.setCodeBanqueSource(banqueS.getCode());
                    //operation.setBanqueSource(banqueS);
                    operation.setReference(line.substring(3, 11));
                    operation.setMontant(new BigDecimal(line.substring(61, 77)));
                    operation.setDateCreation(new Date());
                    operation.setDateModifictaion(new Date());
                    operation.setDateReglement(new Date());
                    operation.setEtat(ETAT.ACCEPTE);
                    log.info(" line " + operation.toString());
                    operations.add(operation);
                    operationRepository.save(operation);
                }

            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return operations;
    }

	public Set<String> listDirectory(String directory) {
		return Stream.of(new File(directory).listFiles()).filter(file -> !file.isDirectory()).map(File::getName)
				.collect(Collectors.toSet());
	}

	public List<String> getFile(String fileName) throws Exception {

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			return stream.collect(Collectors.toList());

		} catch (IOException e) {
			log.info(e.getMessage());
			throw e;
		}

	}
}
