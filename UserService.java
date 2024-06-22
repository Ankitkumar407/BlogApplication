package com.blog.service;

import java.util.List;

import com.blog.payLoad.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto ,Integer userId );
	
	void deleteUser(Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUser();

}
