package com.example.film.controller;

import com.example.film.dto.ActivityDTO;
import com.example.film.service.ActivityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/atividades")
public class Controller {

    @Autowired
    private ActivityServices activityServices;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping()
    public ResponseEntity<Page<ActivityDTO>> findAll(@RequestParam(name = "service", defaultValue = "") String service, Pageable pageable){
        Page<ActivityDTO> activityDTO = activityServices.findAll(service, pageable);
        return ResponseEntity.ok(activityDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/bydate")
    public ResponseEntity<Page<ActivityDTO>> findByDate(
            @RequestParam(name = "min") String min,
            @RequestParam(name = "max") String max,
            Pageable pageable) {
        Page<ActivityDTO> activityDTO = activityServices.findByDate(min, max, pageable);
        return ResponseEntity.ok(activityDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<ActivityDTO> save (@RequestBody ActivityDTO activityDTO){
        activityDTO = activityServices.save(activityDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(activityDTO.id()).toUri();
        return ResponseEntity.created(uri).body(activityDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ActivityDTO> update(@PathVariable Long id, @RequestBody ActivityDTO activityDTO){
        activityDTO = activityServices.update(id, activityDTO);
        return ResponseEntity.ok(activityDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        activityServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
