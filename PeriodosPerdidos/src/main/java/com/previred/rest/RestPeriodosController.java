package com.previred.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.previred.dto.PeriodoDto;
import com.previred.service.PeriodoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/periodos")
@Api(value = "periodos")
public class RestPeriodosController {
	
	@Autowired
	private PeriodoService periodo;
  
	@ApiOperation(value = "Lista periodos perdidos", nickname = "listar", notes = "", response = PeriodoDto.class, tags={ "periodos", })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Periodo, lista de fechas y fechas faltantes", response = PeriodoDto.class) })
    @RequestMapping(value = "/listar",produces = { "application/json" }, method = RequestMethod.GET)
	@GetMapping("/listar")
	public PeriodoDto listar() {
		return periodo.listar();
	}
	
}
