package com.ivanmoreno.alumnos;

import java.util.Optional;

import com.ivanmoreno.commons.models.entity.Alumno;

public class DatosTest {

	public static Optional<Alumno> createAlumno001() {
		return Optional.of(new Alumno(1L, "Usuario 1", "UNO", "u1@gmail.com", null, null));
	}
	
	public static Optional<Alumno> createAlumno002() {
		return Optional.of(new Alumno(2L, "Usuario 2", "DOS", "u2@gmail.com", null, null));
	}
	
	public static Optional<Alumno> createAlumno003() {
		return Optional.of(new Alumno(3L, "Usuario 3", "TRES", "u3@gmail.com", null, null));
	}
}
