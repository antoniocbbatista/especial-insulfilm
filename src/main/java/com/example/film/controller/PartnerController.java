package com.example.film.controller;


import com.example.film.dto.PartnerDTO;
import com.example.film.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/parceiros")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping()
    public ResponseEntity<List<PartnerDTO>> findAll(){
        List<PartnerDTO> partnerDTO = partnerService.findAll();
        return ResponseEntity.ok(partnerDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<PartnerDTO> save(@RequestBody PartnerDTO partnerDTO){
        partnerDTO = partnerService.save(partnerDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(partnerDTO.id()).toUri();
        return ResponseEntity.created(uri).body(partnerDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(value = "/{id}")
    public ResponseEntity<PartnerDTO> update(@PathVariable Long id, @RequestBody PartnerDTO partnerDTO){
        partnerDTO = partnerService.update(id, partnerDTO);
        return ResponseEntity.ok(partnerDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        partnerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
