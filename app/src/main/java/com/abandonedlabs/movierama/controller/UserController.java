package com.abandonedlabs.movierama.controller;

import com.abandonedlabs.movierama.dto.AuthUserDTO;
import com.abandonedlabs.movierama.dto.UserDTO;
import com.abandonedlabs.movierama.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type User controller.
 */
@RestController
@Api(value = "Users API", tags = "Users")
@RequestMapping(value = "/api/v1/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * The Service.
	 */
	@Autowired
	private UserService service;

	/**
	 * Login auth user dto.
	 *
	 * @param username the username
	 * @param pwd      the pwd
	 * @return the auth user dto
	 * @throws NotFoundException the not found exception
	 */
	@PostMapping("/login")
	public AuthUserDTO login(@RequestParam("user") String username, @RequestParam("password") String pwd) throws ChangeSetPersister.NotFoundException {
		logger.debug("login({}, {})", username, pwd);
		UserDTO userDTO = service.find(username, pwd);
		String token = getJWTToken(username);
		AuthUserDTO user = new AuthUserDTO();
		user.setUsername(userDTO.getUsername());
		user.setToken(token);
		user.setId(userDTO.getId());
		return user;

	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	/**
	 * Create user response entity.
	 *
	 * @param uriBuilder the uri builder
	 * @param userDTO    the user dto
	 * @return the response entity
	 */
	@RequestMapping(value = "register", produces = {"application/json"}, consumes = {"application/json"},	method = RequestMethod.POST)
	@ApiOperation(value = "Creates a new user", response = UserDTO.class)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "User created successfully", response = UserDTO.class),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<UserDTO> createUser(UriComponentsBuilder uriBuilder, @Valid @RequestBody UserDTO userDTO) {
		logger.debug("createUser({})", userDTO);
		ResponseEntity<UserDTO> response;
		UserDTO data = service.createUser(userDTO);
		UriComponents uriComponents =	uriBuilder.path("/users/{id}").buildAndExpand(data.getId());
		response = ResponseEntity.created(uriComponents.toUri()).body(data);
		return response;
	}

	/**
	 * Gets user.
	 *
	 * @param uriBuilder the uri builder
	 * @param username   the username
	 * @param password   the password
	 * @return the user
	 * @throws NotFoundException the not found exception
	 */
	@ApiOperation(value = "Authenticates the user", response = UserDTO.class)
	@RequestMapping(value = "/id", produces = {"application/json"},	method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User is authenticated", response = UserDTO.class),
			@ApiResponse(code = 401, message = "The credentials are invalid"),
			@ApiResponse(code = 404, message = "No user found!"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<UserDTO> getUser(UriComponentsBuilder uriBuilder, @RequestParam String username, @RequestParam String password) throws ChangeSetPersister.NotFoundException {
		logger.debug("getUser({}, {})", username, password);
		ResponseEntity<UserDTO> response;
		UserDTO account = service.find(username, password);

		if (account == null) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			UriComponents uriComponents = uriBuilder.path("/users/{id}").buildAndExpand(account.getId());
			response = ResponseEntity.status(HttpStatus.OK).location(uriComponents.toUri()).body(account);
		}

		return response;
	}

}
