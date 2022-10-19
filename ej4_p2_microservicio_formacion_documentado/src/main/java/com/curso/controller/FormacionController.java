package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Formacion;
import com.curso.service.FormacionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "Microservicio de formaciones cliente del microservicio de cursos.")
@RestController
public class FormacionController {

	@Autowired
	FormacionService service;
	
	@ApiOperation(value = "Operación para obtener la lista de formaciones a partir de la lista de cursos.")
	@GetMapping(value="lista",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Formacion> listar(){
		return service.cursos();
	}
	
	@ApiOperation(value = "Operación para dar de alta un nuevo curso a partir de los datos de una formación.")
	@PostMapping(value="alta",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void agregar(@ApiParam(value = "Datos de la formacion en formato JSON a partir de los que se dará de alta el nuevo curso.") @RequestBody Formacion formacion) {
		service.altaCurso(formacion);
	}
}
