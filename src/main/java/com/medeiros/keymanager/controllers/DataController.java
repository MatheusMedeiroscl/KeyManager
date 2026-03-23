package com.medeiros.keymanager.controllers;


import com.medeiros.keymanager.entities.Data.DataRequestDTO;
import com.medeiros.keymanager.entities.Data.DataResponseDTO;
import com.medeiros.keymanager.services.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService service;
    public DataController(DataService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseDTO> findById(@PathVariable Long id){
        DataResponseDTO data = this.service.findById(id);
        return  ResponseEntity.ok().body(data);

    }

    @PostMapping
    public ResponseEntity<DataResponseDTO> create(@RequestBody DataRequestDTO dto){
        DataResponseDTO data = this.service.create(dto);
        return  ResponseEntity.ok().body(data);
    }

    @PostMapping("/{id}")
    public ResponseEntity<DataResponseDTO> update(@PathVariable Long id, @RequestBody DataRequestDTO dto){
        DataResponseDTO data = this.service.update(dto,id);
        return  ResponseEntity.ok().body(data);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }


}
