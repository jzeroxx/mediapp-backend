package com.mitocode.controller;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.SignosVitales;
import com.mitocode.model.SignosVitales;
import com.mitocode.service.ISignosVitalesService;
import com.mitocode.service.ISignosVitalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/signos-vitales")
public class SignosVitalesController {

    @Autowired
    private ISignosVitalesService service;

    
        @GetMapping
        public ResponseEntity<List<SignosVitales>> listar() throws Exception {
            List<SignosVitales> lista = service.listar();
            return new ResponseEntity<List<SignosVitales>>(lista, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<SignosVitales> listarPorId(@PathVariable("id") Integer id) throws Exception {
            SignosVitales obj = service.listarPorId(id);

            if(obj.getIdSignosVitales() == null) {
                throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
            }
            return new ResponseEntity<SignosVitales>(obj, HttpStatus.OK);
        }

        @GetMapping("/hateoas/{id}")
        public EntityModel<SignosVitales> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
            SignosVitales obj = service.listarPorId(id);

            if (obj == null) {
                throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
            }

            // localhost:8080/pacientes/{id}
            EntityModel<SignosVitales> recurso = EntityModel.of(obj);

            WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(id));

            recurso.add(link.withRel("paciente-recurso"));

            return recurso;

        }


        @PostMapping
        public ResponseEntity<SignosVitales> registrar(@Valid @RequestBody SignosVitales p) throws Exception{

            System.out.println("Request: "+p);

            SignosVitales obj = service.registrar(p);

            // localhost:8080/pacientes/{2}
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSignosVitales()).toUri();
            return ResponseEntity.created(location).build();
        }

        @PutMapping
        public ResponseEntity<SignosVitales> modificar(@Valid @RequestBody SignosVitales p) throws Exception{
            SignosVitales obj = service.modificar(p);

            return new ResponseEntity<SignosVitales>(obj, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
            SignosVitales obj = service.listarPorId(id);

            if(obj == null) {
                throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
            }
            service.eliminar(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    
    @GetMapping("/pageable")
    public ResponseEntity<Page<SignosVitales>> listarPageable(Pageable pageable) throws Exception {

        Page<SignosVitales> signosVitales = service.listarPageable(pageable);

        return new ResponseEntity<Page<SignosVitales>>(signosVitales, HttpStatus.OK);
    }

}