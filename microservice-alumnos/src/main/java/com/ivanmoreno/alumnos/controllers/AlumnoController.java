package com.ivanmoreno.alumnos.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ivanmoreno.alumnos.models.entity.Alumno;
import com.ivanmoreno.alumnos.services.AlumnoService;
import com.ivanmoreno.commons.controllers.CommonController;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Alumno> alumnoOpt = this.service.findById(id);
		
		if(!alumnoOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDB = alumnoOpt.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		
		Alumno alumnoSaved = this.service.save(alumnoDB);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoSaved);
	}
	
}
