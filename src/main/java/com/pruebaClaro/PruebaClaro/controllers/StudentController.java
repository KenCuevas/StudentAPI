package com.pruebaClaro.PruebaClaro.controllers;

import com.pruebaClaro.PruebaClaro.exceptions.StudentNotFoundException;
import com.pruebaClaro.PruebaClaro.models.Student;
import com.pruebaClaro.PruebaClaro.repositories.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/")
//Con esta anotacion solo estoy diciendole al navegador que puede permitir cualquier origen de peticiones desde cualquier url o dominio
@CrossOrigin("*")
public class StudentController {
    @Autowired
    private StudentRepository repository;
    /*
    Escribimos en el buscador http://localhost:8080/api/v2/student/all y nos muestra un json
    con todos los estudiantes registrados dentro de la base de datos.
     */
    @GetMapping(path = "student/all")
    public @ResponseBody Iterable<Student>list(){
        return repository.findAll();
    }
    /*
    Escribimos en el buscador http://localhost:8080/api/v2/student/1 y nos muestra un json
    con el estudiante que corresponda al id 1 (en la parte de {id} se debe sustituir por
    el id del estudiante que queremos ver.
    */
    @GetMapping(path = "student/{id}")
    public Student getStudent(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }
    /*
    Escribimos en el buscador http://localhost:8080/api/v2/student/add y nos permitira agregar un estudiante
    a nuestra base de datos.
     */
    @PostMapping("student/add")
    public Student createStudent(@RequestBody final Student student){
        return repository.save(student);
    }
    /*
    Escribimos en el buscador http://localhost:8080/api/v2/deleteStudent/1 y nos permitira eliminar un estudiante
    de nuestra base de datos.
    */
    @DeleteMapping("deleteStudent/{id}")
    void deleteStudent(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping("replaceStudent/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student){
        Student existingStudent = repository.getById(id);
        BeanUtils.copyProperties(student, existingStudent, "id");
        return repository.saveAndFlush(existingStudent);
    }
}
