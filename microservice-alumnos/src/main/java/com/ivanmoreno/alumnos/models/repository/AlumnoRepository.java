package com.ivanmoreno.alumnos.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.ivanmoreno.alumnos.models.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long>{

	
}
