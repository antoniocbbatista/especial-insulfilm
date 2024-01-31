package com.example.film.service;

import com.example.film.dto.PartnerDTO;
import com.example.film.entity.Partner;
import com.example.film.repository.PartnerRepository;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    @Transactional
    public PartnerDTO save(PartnerDTO partnerDTO){
        Partner entity = new Partner();
        entity.setPartnerName(partnerDTO.partnerName());
        entity = partnerRepository.save(entity);
        return new PartnerDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<PartnerDTO> findAll(){
        List<Partner> partner = partnerRepository.findAll();
        return partner.stream().map(PartnerDTO::new).toList();
    }

    @Transactional
    public PartnerDTO update(Long id, PartnerDTO partnerDTO) {
        Partner partner = partnerRepository.getReferenceById(id);
        partner.setPartnerName(partnerDTO.partnerName());
        partner = partnerRepository.save(partner);
        return new PartnerDTO(partner);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {partnerRepository.deleteById(id);}
}
