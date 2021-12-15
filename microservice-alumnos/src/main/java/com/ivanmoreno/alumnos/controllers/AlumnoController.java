package com.ivanmoreno.alumnos.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ivanmoreno.alumnos.services.AlumnoService;
import com.ivanmoreno.commons.controllers.CommonController;
import com.ivanmoreno.commons.models.entity.Alumno;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id) {
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
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
	
	@GetMapping("/filtrar/{value}")
	public ResponseEntity<?> filter(@PathVariable String value) {
		List<Alumno> alumnos = this.service.findByNombreOrApellido(value);
		return ResponseEntity.ok(alumnos);
	}

	@PostMapping("/create-with-photo")
	public ResponseEntity<?> createWithPhoto(@Valid Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
		if(!archivo.isEmpty()) {
			alumno.setPhoto(archivo.getBytes());
		}
		return super.create(alumno, result);
	}
	
	@PutMapping("/edit-with-photo/{id}")
	public ResponseEntity<?> editWithPhoto(@Valid Alumno alumno, BindingResult result, @PathVariable Long id, @RequestParam MultipartFile archivo) throws IOException {
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Alumno> alumnoOpt = this.service.findById(id);
		
		if(!alumnoOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDB = alumnoOpt.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		
		if(!archivo.isEmpty()) {
			alumnoDB.setPhoto(archivo.getBytes());
		}
		
		Alumno alumnoSaved = this.service.save(alumnoDB);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoSaved);
	}
	
	@GetMapping("/download/img/{id}")
	public ResponseEntity<?> showImage(@PathVariable Long id) {
		
		Optional<Alumno> entity = service.findById(id);
		
		if(!entity.isPresent() || entity.get().getPhoto() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource image = new ByteArrayResource(entity.get().getPhoto());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}
	
}
