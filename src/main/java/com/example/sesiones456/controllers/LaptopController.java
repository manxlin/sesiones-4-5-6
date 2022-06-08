package com.example.sesiones456.controllers;

import com.example.sesiones456.entities.Laptop;
import com.example.sesiones456.repositories.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("api/laptops")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    @GetMapping("api/laptop/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        Optional<Laptop> optLaptop = laptopRepository.findById(id);
        if(optLaptop.isPresent()) {
            return ResponseEntity.ok(optLaptop.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("api/addLaptop")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-agent"));
        if(laptop.getId() != null){
            log.warn("No se puede crear el Laptop porque tiene ID");
            return ResponseEntity.badRequest().build();
        }else{
            log.info("Creado el Laptop");
            Laptop result = laptopRepository.save(laptop);
            return ResponseEntity.ok(result);
        }
    }

    @PutMapping("api/updateLaptop")
    public ResponseEntity<Laptop> updateLaptop(@RequestBody Laptop laptop){
        Optional<Laptop> optLaptop = laptopRepository.findById(laptop.getId());
        if(laptop.getId() == null){
            //400
            log.warn("No se ha pasado un id");
            return ResponseEntity.badRequest().build();
        }else if(optLaptop.isPresent()){
            //200
            log.info("Laptop updated");
            Laptop result = laptopRepository.save(laptop);
            return ResponseEntity.ok(result);
        }else{
            //404
            log.warn("id did not matched");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("api/deleteOne/{id}")
    public ResponseEntity<Laptop> deleteOneById(@PathVariable Long id){
        if(laptopRepository.existsById(id)){
            //No content (OK)
            log.info("Laptop eliminado");
            laptopRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            //404
            log.warn("No se ha encontrado el id");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("api/deleteAll")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("Eliminados todos los registros");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
