package com.marceloHsousa.LibraryManagementSystem;

import com.marceloHsousa.LibraryManagementSystem.entities.user.User;
import com.marceloHsousa.LibraryManagementSystem.services.BookService;
import com.marceloHsousa.LibraryManagementSystem.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("/application.properties")
@SpringBootTest(classes = LibraryManagementSystemApplication.class)
class LibraryManagementSystemApplicationTests {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@BeforeAll
	public static void beforeAll(){
	}


	@BeforeEach
	public void beforeEach(){
		jdbc.execute("SET foreign_key_checks = 0;");
		jdbc.execute("DELETE FROM books");
		jdbc.execute("DELETE FROM loans");
		jdbc.execute("DELETE FROM users");
		jdbc.execute("DELETE FROM authors");
		jdbc.execute("DELETE FROM book_category");
		jdbc.execute("SET foreign_key_checks = 1;");
	}

	@AfterEach
	public void afterEach(){
	}

	//User tests

	@Test
	public void crateUserAndFindUserByEmail(){
		userService.save(new User(null, "Marcelim", "marcelo@gmail.com", passwordEncoder.encode("gabe123"), "ROLE_ADMIN", null));
		User user=userService.findByEmail("marcelo@gmail.com");
		assertEquals("marcelo@gmail.com", user.getEmail());
	}

	@Test
	public void updateUser(){
		User user=new User(null, "Marcelim", "marcelo@gmail.com", passwordEncoder.encode("gabe123"), "ROLE_ADMIN", null);
		userService.save(user);
		user=userService.findByEmail("marcelo@gmail.com");
		assertEquals("Marcelim", user.getName());
		user.setEmail("marcelobolsonaro@gmail.com");
		userService.update(user.getId(), user);
		user=userService.findByEmail("marcelobolsonaro@gmail.com");
		assertEquals("marcelobolsonaro@gmail.com", user.getEmail());
	}
}
