package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.renobidz.endpoints.dto.UserDTO;
import com.renobidz.store.entity.User;

public class UserConverter {
	private static UserConverter singleton = null;

	private UserConverter() {
		
	}
	
	/**
	 * @return
	 */
	public static UserConverter getInstance() {
		if (singleton == null) {
			singleton = new UserConverter();
		}
		return singleton;
	}
	
	/**
	 * @param dto
	 * @return
	 * 
	 * To objectify entity
	 */
	public User toEntity(UserDTO dto){
		User entity = new User();
		entity.setFirstName(dto.getFirstName());
		entity.setFirstName(dto.getLastName());
		entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setPrefLanguage(dto.getPrefLanguage());
		entity.setProfilePicture(dto.getProfilePicture());
		entity.setIsLocked(dto.getIsLocked());
		entity.setIsSocialUser(dto.getIsSocialUser());
		entity.setSocialwebsite(dto.getSocialwebsite());
        entity.setIsCompany(dto.getIsCompany());
		return entity;
	}
	
	public List<User> toEntityList(List<UserDTO> dtos){
		List<User> listDTOs = new ArrayList<User>(0);
		for(UserDTO dto : dtos){
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
	public UserDTO toDTO(User entity){
		UserDTO dto = new UserDTO();
		dto.setFirstName(entity.getFirstName());
		dto.setFirstName(entity.getLastName());
		dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setPrefLanguage(entity.getPrefLanguage());
		dto.setProfilePicture(entity.getProfilePicture());
		dto.setIsLocked(entity.getIsLocked());
		dto.setIsSocialUser(entity.getIsSocialUser());
		dto.setSocialwebsite(entity.getSocialwebsite());
        dto.setIsCompany(entity.getIsCompany());
		return dto;
	}
	
	public List<UserDTO> toDTOsList(List<User> list){
		List<UserDTO> listDTOs = new ArrayList<UserDTO>(0);
		for(User entity : list){
			listDTOs.add(toDTO(entity));
		}
		return listDTOs;
	}
}
