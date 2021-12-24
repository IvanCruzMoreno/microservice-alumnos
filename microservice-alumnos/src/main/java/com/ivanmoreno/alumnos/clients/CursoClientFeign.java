package com.ivanmoreno.alumnos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-cursos")
public interface CursoClientFeign {

	@DeleteMapping("/eliminar-alumno/{id}")
	public void removeCursoAlumnoById(@PathVariable Long id);
}
