package com.example.film.repository;

import com.example.film.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;


public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT obj FROM Activity obj WHERE UPPER(obj.service) LIKE UPPER(CONCAT('%', :service, '%'))")
    Page<Activity> searchByName(String service, Pageable pageable);

    @Query("SELECT obj FROM Activity obj WHERE obj.dateService BETWEEN :min AND :max")
    Page<Activity> searchByDate(@Param("min") LocalDate min, @Param("max") LocalDate max, Pageable pageable);
}
