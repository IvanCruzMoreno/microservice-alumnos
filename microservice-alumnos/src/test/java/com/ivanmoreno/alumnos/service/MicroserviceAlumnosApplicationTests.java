package com.ivanmoreno.alumnos.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ivanmoreno.alumnos.DatosTest;
import com.ivanmoreno.alumnos.clients.CursoClientFeign;
import com.ivanmoreno.alumnos.models.repository.AlumnoRepository;
import com.ivanmoreno.alumnos.services.AlumnoService;
import com.ivanmoreno.alumnos.services.AlumnoServiceImpl;
import com.ivanmoreno.commons.models.entity.Alumno;

@SpringBootTest
class MicroserviceAlumnosApplicationTests {
	
	private AlumnoRepository alumnoRepo;
	
	private CursoClientFeign cursoFeign;
	
	private AlumnoService alumnoService;
	
	@BeforeEach
	void setUp() {
		alumnoRepo = mock(AlumnoRepository.class);
		cursoFeign = mock(CursoClientFeign.class);
		
		alumnoService = new AlumnoServiceImpl(alumnoRepo, cursoFeign);
	}
	
	@Test
	@DisplayName("Find Alumnos by Nombre or Apellido")
	void findByNombreOrApellidoTest() {
		String value = "U1";
		
		List<Alumno> expectedAlumnos = Arrays.asList(DatosTest.createAlumno001().orElseThrow(null));
		
		when(alumnoRepo.findByNombreOrApellido(value)).thenReturn(expectedAlumnos);
		
		List<Alumno> resultAlumnos = alumnoService.findByNombreOrApellido(value);
		
		verify(alumnoRepo, times(1)).findByNombreOrApellido(value);
		assertSame(expectedAlumnos, resultAlumnos);
	}
	
	@Test
	@DisplayName("Find Alumnos by alumnos ids list")
	void findAllByIdTest() {
	
		List<Alumno> expectedAlumnos = Arrays.asList(
				DatosTest.createAlumno001().orElseThrow(null),
				DatosTest.createAlumno003().orElseThrow(null));
		
		List<Long> ids = Arrays.asList(1L, 3L);
		
		when(alumnoRepo.findAllById(ids)).thenReturn(expectedAlumnos);
		
		List<Alumno> resultAlumnos = alumnoService.findAllById(ids);
		
		assertSame(expectedAlumnos, resultAlumnos);
	}
	
	@Test
	@DisplayName("Delete Alumno by Id")
	void deleteByIdTest() {
		Long value = 1L;
		
		alumnoService.deleteById(value);
		
		verify(alumnoRepo, times(1)).deleteById(value);
		verify(cursoFeign, times(1)).removeCursoAlumnoById(value);
	}
	
	

}
