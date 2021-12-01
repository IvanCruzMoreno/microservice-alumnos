package com.ivanmoreno.alumnos.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivanmoreno.alumnos.models.entity.Alumno;
import com.ivanmoreno.alumnos.models.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService{

	private AlumnoRepository repository;
	
	public AlumnoServiceImpl(AlumnoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		return repository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
