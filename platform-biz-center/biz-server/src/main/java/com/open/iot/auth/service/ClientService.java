package com.open.iot.auth.service;

import java.util.List;
import java.util.Map;

import com.open.iot.auth.model.Client;

public interface ClientService {

	
	Client getById(Long id) ;
	 

    void deleteClient(Long id);
    
    List<Client> findList(Map<String, Object> params) ;
    
    
}
