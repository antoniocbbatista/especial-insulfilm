package com.example.film.dto;

import com.example.film.entity.Activity;

import java.time.LocalDate;
import java.time.LocalTime;

public record ActivityDTO(Long id, String service, Double price, LocalDate dateService, LocalTime timeService, PartnerDTO partnerDTO) {
    public ActivityDTO(Activity entity) {
        this(entity.getId(), entity.getService(), entity.getPrice(), entity.getDateService(), entity.getTimeService(), new PartnerDTO(entity.getPartner()));
    }
}
