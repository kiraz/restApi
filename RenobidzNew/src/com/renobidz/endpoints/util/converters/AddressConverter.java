package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.endpoints.dto.AddressDTO;
import com.renobidz.store.entity.Address;
import com.renobidz.store.entity.User;

public class AddressConverter {
	private static AddressConverter singleton = null;

	private AddressConverter() {
		
	}
	
	/**
	 * @return
	 */
	public static AddressConverter getInstance() {
		if (singleton == null) {
			singleton = new AddressConverter();
		}
		return singleton;
	}
	
	/**
	 * @param dto
	 * @return
	 * 
	 * To objectify entity
	 */
	public Address toEntity(AddressDTO dto){
		Address entity = new Address();
		entity.setAddressLine1(dto.getAddressLine1());
		entity.setAddressLine2(dto.getAddressLine2());
		entity.setCity(dto.getCity());
		entity.setZipCode(dto.getZipCode());
		entity.setState(dto.getState());
		entity.setCountry(dto.getCountry());
        entity.setIsDefault(dto.getIsDefault());
        entity.setLongitude(dto.getLongitude());
        entity.setLatitude(dto.getLatitude());
		entity.setUser(Ref.create(Key.create(User.class, dto.getUserId())));
		return entity;
	}
	
	public List<Address> toEntityList(List<AddressDTO> dtos){
		List<Address> listDTOs = new ArrayList<Address>(0);
		for(AddressDTO dto : dtos){
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
	public AddressDTO toDTO(Address entity){
		AddressDTO dto = new AddressDTO();
		dto.setId(entity.getId());
		dto.setAddressLine1(entity.getAddressLine1());
		dto.setAddressLine2(entity.getAddressLine2());
		dto.setCity(entity.getCity());
		dto.setZipCode(entity.getZipCode());
		dto.setState(entity.getState());
		dto.setCountry(entity.getCountry());
        dto.setIsDefault(entity.getIsDefault());
        dto.setLongitude(entity.getLongitude());
        dto.setLatitude(entity.getLatitude());
		return dto;
	}
	
	public List<AddressDTO> toDTOsList(List<Address> list){
		List<AddressDTO> listDTOs = new ArrayList<AddressDTO>(0);
		for(Address entity : list){
			listDTOs.add(toDTO(entity));
		}
		return listDTOs;
	}
}
