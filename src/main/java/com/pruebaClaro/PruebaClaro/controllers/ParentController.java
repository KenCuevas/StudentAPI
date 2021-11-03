package com.pruebaClaro.PruebaClaro.controllers;

import com.pruebaClaro.PruebaClaro.exceptions.ParentNotFoundException;
import com.pruebaClaro.PruebaClaro.models.Parent;
import com.pruebaClaro.PruebaClaro.repositories.ParentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin("*")
public class ParentController {
    @Autowired
    private ParentRepository repository;

    @GetMapping(path = "parent/all")
    public @ResponseBody Iterable<Parent>list(){
        return repository.findAll();
    }
    @GetMapping(path = "parent/{id}")
    public Parent getParent(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ParentNotFoundException(id));
    }
    @PostMapping("parent/add")
    public Parent createParent(@RequestBody final Parent parent){
        return repository.save(parent);
    }
    @PutMapping("replaceParent/{id}")
    public Parent updateParent(@PathVariable Long id, @RequestBody Parent parent){
        Parent existingParent = repository.getById(id);
        BeanUtils.copyProperties(parent, existingParent, "id");
        return repository.saveAndFlush(existingParent);
    }
    @DeleteMapping("deleteParent/{id}")
    void deleteParent(@PathVariable Long id){
        repository.deleteById(id);
    }



}
