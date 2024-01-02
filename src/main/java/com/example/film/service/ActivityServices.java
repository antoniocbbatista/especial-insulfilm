package com.example.film.service;

import com.example.film.repository.ActivityRepository;
import com.example.film.dto.ActivityDTO;
import com.example.film.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;


@Service
public class ActivityServices {

    @Autowired
    private ActivityRepository activityRepository;


    @Transactional
    public ActivityDTO save(ActivityDTO activityDTO) {
        Activity entity = new Activity();
        copyDTOToEntity(activityDTO, entity);
        entity = activityRepository.save(entity);
        return new ActivityDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ActivityDTO> findAll(String service, Pageable pageable) {
        Page<Activity> activityDTO = activityRepository.searchByName(service, pageable);
        return activityDTO.map(ActivityDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<ActivityDTO> findByDate(String minDate, String maxDate, Pageable pageable){
        LocalDate now = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate min = "".equals(minDate) ? now.minusYears(1L) : LocalDate.parse(minDate);
        LocalDate max = "".equals(maxDate) ? now : LocalDate.parse(maxDate);
        Page<Activity> entity = activityRepository.searchByDate(min, max, pageable);
        return entity.map(ActivityDTO::new);
    }

    @Transactional
    public ActivityDTO update(Long id, ActivityDTO activityDTO){
        Activity entity = activityRepository.getReferenceById(id);
        copyDTOToEntity(activityDTO, entity);
        entity = activityRepository.save(entity);
        return new ActivityDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        activityRepository.deleteById(id);
    }


    public void copyDTOToEntity(ActivityDTO activityDTO, Activity entity){
        entity.setService(activityDTO.service());
        entity.setPrice(activityDTO.price());
        LocalDate now = LocalDate.now();
        LocalTime time = LocalTime.now();
        entity.setDateService(now);
        entity.setTimeService(time);
    }
}
