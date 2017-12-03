package com.jeikode.microserviceauth.services;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jeikode.microserviceauth.dao.UserDao;
import com.jeikode.microserviceauth.model.User;

@RestController
public class AuthService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JwtService JwtService;
	
	@Autowired
	public AuthService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping("/")
	public String sayHello() {
		return "hello from Auth";
	}

	@RequestMapping(path="/singUp",method = RequestMethod.POST, produces=MediaType.TEXT_PLAIN, consumes=MediaType.APPLICATION_JSON)
	public ResponseEntity<?> singup(@RequestBody  User user){
		try {
			userDao.save(user);
			String token = JwtService.createToken(user);
			return new ResponseEntity<String>(token,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(path="/singIn",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON, consumes=MediaType.APPLICATION_JSON)
	public ResponseEntity<?> signIn(@RequestBody  User userLogin){
		try {
			User userFound= userDao.findByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());
			if(userFound == null) {
				return new ResponseEntity<String>("No existe el usuario",HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<User>(userFound,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
