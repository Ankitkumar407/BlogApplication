package com.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payLoad.UserDto;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private ModelMapper modelMapper;

	
	
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
//		convert userDto To user		
		User user = this.modelMapper.map(userDto, User.class);
		
//		save user in database
	    User saveUser = this.userRepository.save(user);
	    
//	    convert user To userDto
	    UserDto saveUserDto = this.modelMapper.map(saveUser, UserDto.class);
		
		return saveUserDto;
	}

	
	
	
	
	
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
//		search user
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","user Id",userId));
		
//		set property in user
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
//		save user
		User updateUser = this.userRepository.save(user);
		
//		convert user To userDto
		UserDto updateUserDto = this.modelMapper.map(updateUser, UserDto.class);
		
		return updateUserDto;
	}

	
	
	
	
	@Override
	public void deleteUser(Integer userId) {
		
//		search user 
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user Id" , userId));
		
//		delete user
		this.userRepository.delete(user);

	}
	
	
	
	

	
	@Override
	public UserDto getUserById(Integer userId) {
		
//		search user
		User user  = this.userRepository.findById(userId).
				orElseThrow(() -> new ResourceNotFoundException("User", "user Id" ,userId));
		
//		convert user To userDto
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		return userDto;
	}

	
	
	
	
	
	
	@Override
	public List<UserDto> getAllUser() {
		
//		get AllUser
		List<User> users = this.userRepository.findAll();
		
//		convert list of User To list of UserDto
		List<UserDto> userDtos = users.stream().map(user ->this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
		
		return userDtos;
	}

}
