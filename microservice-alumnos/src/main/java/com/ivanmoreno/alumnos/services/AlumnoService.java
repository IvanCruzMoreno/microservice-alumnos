package com.ivanmoreno.alumnos.services;

import java.util.List;


import com.ivanmoreno.commons.models.entity.Alumno;
import com.ivanmoreno.commons.services.CommonService;

public interface AlumnoService extends CommonService<Alumno>{

	public List<Alumno> findByNombreOrApellido(String value);
	
	public List<Alumno> findAllById(List<Long> ids);
	
	public void removeCursoAlumnoById(Long id);
}
