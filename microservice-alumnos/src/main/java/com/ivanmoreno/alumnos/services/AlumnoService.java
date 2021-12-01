package com.ivanmoreno.alumnos.services;

import java.util.Optional;

import com.ivanmoreno.alumnos.models.entity.Alumno;

public interface AlumnoService {

	public Iterable<Alumno> findAll();
	
	public Optional<Alumno> findById(Long id);
	
	public Alumno save(Alumno alumno);
	
	public void deleteById(Long id);
}
