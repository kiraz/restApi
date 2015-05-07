package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.endpoints.dto.CompanyDTO;
import com.renobidz.store.entity.Company;
import com.renobidz.store.entity.User;

public class CompanyConverter {
	private static CompanyConverter singleton = null;

	private CompanyConverter() {
		
	}
	
	/**
	 * @return
	 */
	public static CompanyConverter getInstance() {
		if (singleton == null) {
			singleton = new CompanyConverter();
		}
		return singleton;
	}
	
	/**
	 * @param dto
	 * @return
	 * 
	 * To objectify entity
	 */
	public Company toEntity(CompanyDTO dto){
		Company entity = new Company();
		entity.setName(dto.getName());
		entity.setServicedCategory(dto.getServicedCategory());
		entity.setWebsite(dto.getWebsite());
		entity.setLicenseNumber(dto.getLicenseNumber());
		entity.setDescription(dto.getDescription());
		entity.setServicesProvided(dto.getServicesProvided());
		entity.setAreasServed(dto.getAreasServed());
		entity.setCertificationsAndAwards(dto.getCertificationsAndAwards());
		entity.setAffiliations(dto.getAffiliations());
		entity.setTypicalJobCostCurrency(dto.getTypicalJobCostCurrency());
		entity.setTypicalJobCostFrom(dto.getTypicalJobCostFrom());
		entity.setTypicalJobCostTo(dto.getTypicalJobCostTo());
		entity.setUser(Ref.create(Key.create(User.class, dto.getUserId())));
		return entity;
	}
	
	public List<Company> toEntityList(List<CompanyDTO> dtos){
		List<Company> listDTOs = new ArrayList<Company>(0);
		for(CompanyDTO dto : dtos){
			listDTOs.add(toEntity(dto));
		}
		return listDTOs;
	}
	
	/**
	 * @param dto
	 * @return
	 * 
	 * To dto from entity
	 */
	public CompanyDTO toDTO(Company entity){
		CompanyDTO dto = new CompanyDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setServicedCategory(entity.getServicedCategory());
		dto.setWebsite(entity.getWebsite());
		dto.setLicenseNumber(dto.getLicenseNumber());
		dto.setDescription(entity.getDescription());
		dto.setServicesProvided(entity.getServicesProvided());
		dto.setAreasServed(entity.getAreasServed());
		dto.setCertificationsAndAwards(entity.getCertificationsAndAwards());
		dto.setAffiliations(entity.getAffiliations());
		dto.setTypicalJobCostCurrency(entity.getTypicalJobCostCurrency());
		dto.setTypicalJobCostFrom(entity.getTypicalJobCostFrom());
		dto.setTypicalJobCostTo(entity.getTypicalJobCostTo());
		return dto;
	}
	
	public List<CompanyDTO> toDTOsList(List<Company> list){
		List<CompanyDTO> listDTOs = new ArrayList<CompanyDTO>(0);
		for(Company entity : list){
			listDTOs.add(toDTO(entity));
		}
		return listDTOs;
	}
}
