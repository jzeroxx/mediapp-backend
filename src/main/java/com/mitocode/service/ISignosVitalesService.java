package com.mitocode.service;

import com.mitocode.model.SignosVitales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISignosVitalesService extends ICRUD<SignosVitales, Integer>{
	
	Page<SignosVitales> listarPageable(Pageable pageable);

}
