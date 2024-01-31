package com.example.film.dto;

import com.example.film.entity.Partner;

public record PartnerDTO(Long id, String partnerName) {

    public PartnerDTO(Partner entity){
        this(entity.getId(), entity.getPartnerName());
    }
}
