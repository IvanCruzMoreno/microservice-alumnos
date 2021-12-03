package com.ivanmoreno.alumnos.services;


import org.springframework.stereotype.Service;

import com.ivanmoreno.alumnos.models.repository.AlumnoRepository;
import com.ivanmoreno.commons.models.entity.Alumno;
import com.ivanmoreno.commons.services.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService{

	public AlumnoServiceImpl(AlumnoRepository repository) {
		super(repository);
	}

	

}
