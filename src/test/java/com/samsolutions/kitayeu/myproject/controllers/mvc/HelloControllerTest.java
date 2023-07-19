package com.samsolutions.kitayeu.myproject.controllers.mvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HelloControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
	}

/*	@Test
	@WithMockUser(username = "test_user", password = "test_pwd") // see test application.properties
	public void testHomePage() throws Exception {
		this.mockMvc
				.perform(
						get("/")
								.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(content().string(
						equalTo("<!DOCTYPE html>\r\n<html>\r\n<head>\r\n<meta charset=\"UTF-8\">\r\n<title>My page</title>\r\n</head>\r\n<body>\r\n\t<h1>Hello,\r\n\t\t<span>this is my first spring application</span></h1>\r\n\t<br/>\r\n\t<br/>\r\n\t<a href=\"/departments\">List of departments</a>\r\n\t<br/>\r\n\t<br/>\r\n\t<a href=\"/roles\">List of roles</a>\r\n\t<br/>\r\n\t<br/>\r\n\t<a href=\"/employees\">List of employees</a>\r\n\t<br/>\r\n\t<br/>\r\n\t<a href=\"/users\">List of users</a>\r\n</body>\r\n</html>")));
	}
*/
	@Test
	@WithMockUser(username = "test_user", password = "test_pwd") // see test application.properties
	public void testNotFoundPage() throws Exception {
		this.mockMvc
				.perform(
						get("/nonexisting_page")
								.with(csrf()))
				.andExpect(status().isNotFound());
	}

}

//test
// test1