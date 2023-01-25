
package edu.sru.cpsc.webshopping.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.sru.cpsc.webshopping.controller.misc.CustomErrorControllerSpringTest;
import edu.sru.cpsc.webshopping.domain.user.Applicant;
import edu.sru.cpsc.webshopping.domain.widgets.Category;
import edu.sru.cpsc.webshopping.repository.widgets.CategoryRepository;


@SpringBootTest(classes = {CategoryControllerTest.class})
@ConditionalOnBean(CategoryControllerTest.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "CategoryController/.xml",
"/CategoryControllerTest.xml"})
@AutoConfigureMockMvc

public class CategoryControllerTest {

	private CategoryRepository categoryRepo;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void categoryLoadTest() throws Exception {
		Category cat = new Category();
		mvc.perform(post("/get-all-categories").content("/get-all-categories"));
		assertThat(cat.equals("/get-all-categories"));
	}

}
