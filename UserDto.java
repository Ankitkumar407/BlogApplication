package com.blog.payLoad;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
	
	private Integer Id;
	
	@NotEmpty
	@Size(min =4,message = "Username must be min of 4 characters !!")
	private String name;
	
	@Email(message = "Email address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min =3 ,max = 10, message = "Password must be min of 3 character and max of 10 character !!")
	private String password;
	
	@NotEmpty
	private String about;

}
