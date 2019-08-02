package com.previred.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.previred.dto.PeriodoDto;
import com.previred.exception.ValidationException;


@Service
public class PeriodoServiceImpl implements PeriodoService{

	private static final String URL_API_PERIODOS ="http://127.0.0.1:8080/periodos/api";
	
	Logger log = LoggerFactory.getLogger(PeriodoService.class);
	
	@Override
	public PeriodoDto listar() {
		
	    RestTemplate restTemplate = new RestTemplate();
	    PeriodoDto response=new PeriodoDto();
	    try {
	    	//Consuminos el servicio para obtener el periodo y las fechas a validar
	    	response = restTemplate.getForObject(URL_API_PERIODOS, PeriodoDto.class);
	    	List<LocalDate> fechasFaltantes=new ArrayList<LocalDate>();
	    	
	    	//validamos que la respuesta no sea nula
	    	if (response==null){
	    		throw new ValidationException("No se recuperaron resultados del servicio solicitado"+ URL_API_PERIODOS );
	    	}
	    	
	    	//validamos la fecha de inicio
	    	if (response.getFechaCreacion()==null){
	    		throw new ValidationException("La fecha inicio del periodo no puede ser nula");
	    	}
	    	
	       	//validamos la fecha de fin
	    	if (response.getFechaFin()==null){
	    		throw new ValidationException("La fecha fin del periodo no puede ser nula");
	    	}
	    	
	       	//validamos que fecha inicio no sea mayor a la fecha de fin
	    	if (response.getFechaCreacion().isAfter(response.getFechaFin())){
	    		throw new ValidationException("La fecha de inicio no puede ser mayor a la fecha de fin");
	    	}
	    	
	    	LocalDate fechaActual=response.getFechaCreacion();
	    	
	    	//asigna un mes mas a la fecha de finalización para contemplar el ultimo caso
	    	LocalDate fechaFin=response.getFechaFin().plusMonths(1).withDayOfMonth(1);
	    	
	    	while (fechaActual.compareTo(fechaFin)!=0) {
	    		
	    		boolean existe=false;
	    		//recorre la lista de fechas para verificar si existen
	    		for(LocalDate fecha : response.getFechas())
		    	{
	    			if (fechaActual.compareTo(fecha)==0) {
		    	    	existe=true;
		    	    	break;
		    	    }
		    	    
		    	}
	    		//en el caso de no existir la agrega a la lista de periodos faltantes
	    		if (!existe) {
	    			fechasFaltantes.add(fechaActual);
	    		}

	    		//asigna el día 1 del proximo mes
	    		fechaActual=fechaActual.plusMonths(1).withDayOfMonth(1);
	    	}
	    	
	    	//asgina la lista de fechas faltantes
	    	response.setFechasFaltantes(fechasFaltantes);

	    }catch(HttpClientErrorException ex){
	    	log.error("(PREVIRED) No fue posible establecer conexión con el servicio: "+URL_API_PERIODOS, ex);
	    }
	    catch(ValidationException ex) {
	    	log.error("(PREVIRED) Error de Validacion ", ex);
	    }
	    catch(Exception ex) {
	    	log.error("(PREVIRED) Problemas procesando la información", ex);
	    }
		
		
		
		return response;
	}

}
