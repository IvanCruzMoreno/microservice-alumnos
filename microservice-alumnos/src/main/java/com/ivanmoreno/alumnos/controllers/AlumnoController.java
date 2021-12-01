package com.ivanmoreno.alumnos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ivanmoreno.alumnos.models.entity.Alumno;
import com.ivanmoreno.alumnos.services.AlumnoService;

@RestController
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping
	public ResponseEntity<?> showAll() {
		List<Alumno> alumnos = (List<Alumno>) alumnoService.findAll();
		return ResponseEntity.ok().body(alumnos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> showAlumno(@PathVariable Long id) {
		Optional<Alumno> alumno = alumnoService.findById(id);
		
		if(!alumno.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(alumno.get());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Alumno alumno) {
		Alumno alumnoDB = alumnoService.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDB);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Alumno> alumnoOpt = alumnoService.findById(id);
		
		if(!alumnoOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDB = alumnoOpt.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		
		Alumno alumnoSaved = alumnoService.save(alumnoDB);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoSaved);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		alumnoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
