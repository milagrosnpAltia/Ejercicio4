package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Curso;
import com.curso.service.CursosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="Microservicio de cursos.")
@RestController
public class CursosController {
	@Autowired
	CursosService service;
	
	@ApiOperation(value = "Operación para ver todos los cursos.")
	@GetMapping(value="ver", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> verCursos(){
		return service.obtenerCursos();
	}
	
	@ApiOperation(value = "Operación para buscar un curso por su código.")
	@GetMapping(value="ver/{codigo}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso verCurso(@ApiParam(value = "Código del curso a consultar.") @PathVariable("codigo") String codigo) {
		return service.infoCurso(codigo);
	}
	
	@ApiOperation(value = "Operación para ver los cursos que están en un rango de precios.")
	@GetMapping(value="ver/{pMin}/{pMax}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> verCursosPorPrecio(@ApiParam(value = "Precio mínimo del rango de precios.") @PathVariable("pMin") double pMin, @ApiParam(value = "Precio máximo del rango de precios.") @PathVariable("pMax") double pMax){
		return service.obtenerCursosPorPrecio(pMin, pMax);
	}
	
	@ApiOperation(value = "Operación para añadir un nuevo curso a la lista de cursos.")
	@PostMapping(value="agregar", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> agregarCurso(@ApiParam(value = "Datos del nuevo curso en formato JSON.") @RequestBody Curso curso){
		return service.altaCurso(curso);
	}
	
	@ApiOperation(value = "Operación para actualizar la duración (en horas) de un curso por su código.")
	@PutMapping(value="actualizar/{codigo}/{horas}")
	public void actualizarDuracionCurso(@ApiParam(value = "Código del curso a actualizar.") @PathVariable("codigo") String codigo, @ApiParam(value = "Horas que durará el curso tras la actualización.") @PathVariable("horas") int horas) {
		service.modificarCurso(codigo, horas);
	}
	
	@ApiOperation(value = "Operación para eliminar un curso por su código.")
	@DeleteMapping(value="eliminar/{codigo}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> eliminarCurso(@ApiParam(value = "Código del curso a eliminar.") @PathVariable("codigo") String codigo){
		return service.borrarCurso(codigo);
	}
	
}
