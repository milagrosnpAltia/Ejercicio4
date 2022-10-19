package com.curso.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.curso.model.Curso;
import com.curso.model.Formacion;

@Service
public class FormacionServiceImpl implements FormacionService {
	@Autowired
	private RestTemplate template;
	private String url = "http://localhost:8080/cursos/";
	
	@Override
	public List<Formacion> cursos() {
		Curso[] listaCursos = template.getForObject(url+"ver", Curso[].class);
		List<Formacion> listaFormaciones = convertirLCursoALFormacion(Arrays.asList(listaCursos));
		return listaFormaciones;
	}

	@Override
	public void altaCurso(Formacion formacion) {
		boolean existe = false;
		Curso[] listaCursos = template.getForObject(url+"ver", Curso[].class);
		
		for(Curso curso : listaCursos) {
			if(curso.getNombre().equals(formacion.getCurso())) {
				existe = true;
				break;
			}
		}

		if(!existe) {
			int horasCurso = formacion.getAsignaturas()*10;
			String codCurso = calcularCodCurso(formacion.getCurso(), horasCurso);
			Curso nuevoCurso = new Curso(codCurso,formacion.getCurso(),horasCurso,formacion.getPrecio());
			
			listaCursos = template.postForObject(url+"agregar", nuevoCurso, Curso[].class);
		}
	}
	
	private List<Formacion> convertirLCursoALFormacion(List<Curso> listaCursos){
		List<Formacion> listaFormaciones = new ArrayList<>();
		for(Curso curso : listaCursos) {
			Formacion formacion = new Formacion(curso.getNombre(), calcularAsignaturas(curso.getDuracion()), curso.getPrecio());
			listaFormaciones.add(formacion);
		}
		return listaFormaciones;
	}
	
	private int calcularAsignaturas(int horas) {
		int asignaturas;
		if(horas >= 50) {
			asignaturas = 10;
		} else {
			asignaturas = 5;
		}
		return asignaturas;
	}

	private String calcularCodCurso(String nombre, int duracion) {
		String codigo = nombre.substring(0, 3) + String.valueOf(duracion);
		return codigo;
	}
}
