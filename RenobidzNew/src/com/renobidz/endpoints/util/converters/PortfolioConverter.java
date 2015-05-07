package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.endpoints.dto.PortfolioDTO;
import com.renobidz.store.entity.Portfolio;
import com.renobidz.store.entity.User;

public class PortfolioConverter {
	private static PortfolioConverter singleton = null;

	private PortfolioConverter() {
		
	}
	
	/**
	 * @return
	 */
	public static PortfolioConverter getInstance() {
		if (singleton == null) {
			singleton = new PortfolioConverter();
		}
		return singleton;
	}
	
	/**
	 * @param dto
	 * @return
	 * 
	 * To objectify entity
	 */
	public Portfolio toEntity(PortfolioDTO dto){
		Portfolio entity = new Portfolio();
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setPortfolioImages(dto.getPortfolioImages());
		entity.setUser(Ref.create(Key.create(User.class, dto.getUserId())));
		return entity;
	}
	
	public List<Portfolio> toEntityList(List<PortfolioDTO> dtos){
		List<Portfolio> listDTOs = new ArrayList<Portfolio>(0);
		for(PortfolioDTO dto : dtos){
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
	public PortfolioDTO toDTO(Portfolio entity){
		PortfolioDTO dto = new PortfolioDTO();
		dto.setTitle(entity.getTitle());
		dto.setDescription(entity.getDescription());
		dto.setPortfolioImages(entity.getPortfolioImages());
		return dto;
	}
	
	public List<PortfolioDTO> toDTOsList(List<Portfolio> list){
		List<PortfolioDTO> listDTOs = new ArrayList<PortfolioDTO>(0);
		for(Portfolio entity : list){
			listDTOs.add(toDTO(entity));
		}
		return listDTOs;
	}
}
