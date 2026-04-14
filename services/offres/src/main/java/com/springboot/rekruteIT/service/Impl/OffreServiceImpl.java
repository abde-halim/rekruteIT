package com.springboot.rekruteIT.service.Impl;

import com.springboot.rekruteIT.client.RecruteurServiceClient;
import com.springboot.rekruteIT.entity.Offre;
import com.springboot.rekruteIT.exceptions.RekruteITException;
import com.springboot.rekruteIT.exceptions.RessourceNotFoundException;
import com.springboot.rekruteIT.payload.OffreDTO;
import com.springboot.rekruteIT.payload.OffreDto;
import com.springboot.rekruteIT.payload.OffreResponse;
import com.springboot.rekruteIT.repository.OffreRepository;
import com.springboot.rekruteIT.service.OffreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class OffreServiceImpl implements OffreService {

    private OffreRepository offreRepository;
    private RecruteurServiceClient recruteurServiceClient;
    private ModelMapper mapper;

    @Override
    public OffreResponse getAllOffres(int pageNo, int pageSize, String sortBy, String sortDir, String region) {
        log.info("Fetching all offres: pageNo={}, pageSize={}", pageNo, pageSize);

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Specification<Offre> spec = Specification.where(null);

        if (region != null && !region.isBlank()) {
            Specification<Offre> regionSpec = (root, query, builder) -> builder.equal(builder.lower(root.get("region")),
                    region.toLowerCase());
            spec = spec.and(regionSpec);
        }

        Page<Offre> posts = offreRepository.findAll(spec, pageable);
        List<Offre> listOfPosts = posts.getContent();

        List<OffreDto> content = listOfPosts.stream()
                .map(post -> mapper.map(post, OffreDto.class))
                .collect(Collectors.toList());

        OffreResponse offreResponse = new OffreResponse();
        offreResponse.setContent(content);
        offreResponse.setPageNo(posts.getNumber());
        offreResponse.setPageSize(posts.getSize());
        offreResponse.setTotalElements(posts.getTotalElements());
        offreResponse.setTotalPages(posts.getTotalPages());
        offreResponse.setLast(posts.isLast());

        return offreResponse;
    }

    @Override
    public OffreDto createOffre(int recruteurId, OffreDto offreDTO) {
        log.info("Creating offre for recruteur: {}", recruteurId);

        try {
            // Validate recruteur via REST call
            boolean recruteurExists = recruteurServiceClient.existsById((long) recruteurId);

            if (!recruteurExists) {
                log.warn("Recruteur not found: {}", recruteurId);
                throw new RessourceNotFoundException("Recruteur", "id", String.valueOf(recruteurId));
            }

            Offre offre = mapper.map(offreDTO, Offre.class);
            offre.setRecruteurId((long) recruteurId);

            Offre newOffre = offreRepository.save(offre);
            log.info("Offre created with id: {}", newOffre.getId());

            return mapper.map(newOffre, OffreDto.class);

        } catch (Exception e) {
            log.error("Error creating offre: {}", e.getMessage());
            throw new RekruteITException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to create offre: " + e.getMessage());
        }
    }

    @Override
    public List<OffreDto> getOffreByRecruteurId(int recruteurId) {
        log.info("Fetching offres for recruteur: {}", recruteurId);

        try {
            boolean recruteurExists = recruteurServiceClient.existsById((long) recruteurId);

            if (!recruteurExists) {
                log.warn("Recruteur not found: {}", recruteurId);
                throw new RessourceNotFoundException("Recruteur", "id", String.valueOf(recruteurId));
            }

            List<Offre> offres = offreRepository.findByRecruteurId((long) recruteurId);
            return offres.stream()
                    .map(offre -> mapper.map(offre, OffreDto.class))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Error fetching offres: {}", e.getMessage());
            return List.of();
        }
    }

    @Override
    public OffreDto getOffreById(int recruteurtId, int offreID) {
        log.info("Fetching offre {} for recruteur {}", offreID, recruteurtId);

        try {
            boolean recruteurExists = recruteurServiceClient.existsById((long) recruteurtId);
            if (!recruteurExists) {
                throw new RessourceNotFoundException("Recruteur", "id", String.valueOf(recruteurtId));
            }

            Offre offre = offreRepository.findById(offreID)
                    .orElseThrow(() -> new RessourceNotFoundException("Offre", "id", String.valueOf(offreID)));

            if (!offre.getRecruteurId().equals((long) recruteurtId)) {
                log.warn("Offre {} does not belong to recruteur {}", offreID, recruteurtId);
                throw new RekruteITException(HttpStatus.BAD_REQUEST, "Offre doesn't belong to this recruteur");
            }

            return mapper.map(offre, OffreDto.class);

        } catch (Exception e) {
            log.error("Error fetching offre: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public OffreDto getOffre(int offreID) {
        log.info("Fetching offre: {}", offreID);

        Offre offre = offreRepository.findById(offreID)
                .orElseThrow(() -> new RessourceNotFoundException("Offre", "id", String.valueOf(offreID)));

        return mapper.map(offre, OffreDto.class);
    }

    @Override
    public OffreDto updateOffre(int recruteurtId, int offreID, OffreDto offreDto) {
        log.info("Updating offre {} for recruteur {}", offreID, recruteurtId);

        try {
            boolean recruteurExists = recruteurServiceClient.existsById((long) recruteurtId);
            if (!recruteurExists) {
                throw new RessourceNotFoundException("Recruteur", "id", String.valueOf(recruteurtId));
            }

            Offre offre = offreRepository.findById(offreID)
                    .orElseThrow(() -> new RessourceNotFoundException("Offre", "id", String.valueOf(offreID)));

            if (!offre.getRecruteurId().equals((long) recruteurtId)) {
                throw new RekruteITException(HttpStatus.BAD_REQUEST, "Offre doesn't belong to this recruteur");
            }

            offre.setTitre(offreDto.getTitre());
            offre.setDescription(offreDto.getDescription());
            offre.setExperience(offreDto.getExperience());
            offre.setFormation(offreDto.getFormation());
            offre.setConnaissances(offreDto.getConnaissances());
            offre.setContrat(offreDto.getContrat());
            offre.setSalaire(offreDto.getSalaire());
            offre.setMission(offreDto.getMission());
            offre.setNbr_postes(offreDto.getNbr_postes());
            offre.setSpecialite(offreDto.getSpecialite());
            offre.setVille(offreDto.getVille());
            offre.setRegion(offreDto.getRegion());
            offre.setAdd_notes(offreDto.getAdd_notes());
            offre.setLanguages(offreDto.getLanguages());

            Offre updated = offreRepository.save(offre);
            log.info("Offre {} updated successfully", offreID);

            return mapper.map(updated, OffreDto.class);

        } catch (Exception e) {
            log.error("Error updating offre: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteOffre(int recruteurtId, int OffreID) {
        log.info("Deleting offre {} for recruteur {}", OffreID, recruteurtId);

        try {
            boolean recruteurExists = recruteurServiceClient.existsById((long) recruteurtId);
            if (!recruteurExists) {
                throw new RessourceNotFoundException("Recruteur", "id", String.valueOf(recruteurtId));
            }

            Offre offre = offreRepository.findById(OffreID)
                    .orElseThrow(() -> new RessourceNotFoundException("Offre", "id", String.valueOf(OffreID)));

            if (!offre.getRecruteurId().equals((long) recruteurtId)) {
                throw new RekruteITException(HttpStatus.BAD_REQUEST, "Offre doesn't belong to this recruteur");
            }

            offreRepository.delete(offre);
            log.info("Offre {} deleted successfully", OffreID);

        } catch (Exception e) {
            log.error("Error deleting offre: {}", e.getMessage());
            throw e;
        }
    }

}
