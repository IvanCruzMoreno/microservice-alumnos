package com.ivanmoreno.alumnos.services;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivanmoreno.alumnos.clients.CursoClientFeign;
import com.ivanmoreno.alumnos.models.repository.AlumnoRepository;
import com.ivanmoreno.commons.models.entity.Alumno;
import com.ivanmoreno.commons.services.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService{

	private CursoClientFeign cursoFeignClient;
	
	public AlumnoServiceImpl(AlumnoRepository repository, CursoClientFeign cursoFeign) {
		super(repository);
		cursoFeignClient = cursoFeign;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String value) {
		return this.repository.findByNombreOrApellido(value);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findAllById(List<Long> ids) {
		return (List<Alumno>) this.repository.findAllById(ids);
	}

	@Override
	public void removeCursoAlumnoById(Long id) {
		cursoFeignClient.removeCursoAlumnoById(id);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		super.deleteById(id);
		removeCursoAlumnoById(id);
	}

}
