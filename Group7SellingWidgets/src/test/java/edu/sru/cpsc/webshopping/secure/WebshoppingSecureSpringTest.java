package edu.sru.cpsc.webshopping.secure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.core.userdetails.UserDetails;

import edu.sru.cpsc.webshopping.domain.billing.DomainBillingSpringTest;
import edu.sru.cpsc.webshopping.domain.user.User;
import edu.sru.cpsc.webshopping.repository.user.UserRepository;

@SpringBootTest(classes = {WebshoppingSecureSpringTest.class})
@AutoConfigureMockMvc
public class WebshoppingSecureSpringTest {
	
	@MockBean
	private UserRepository userRepo;
	
	@Test
	void UserDetailsServiceImplTest() {
		
		UserDetailsServiceImpl impl = new UserDetailsServiceImpl(null, null, null);
		UserDetails user = impl.loadUserByUsername("userName");
		
		assertEquals("userName", user.getUsername());
	}
	
	@Test
	void UserDetailsServiceImplExceptionTest() {
		UserDetailsServiceImpl impl = new UserDetailsServiceImpl(null, null, null);
		
		try {
		UserDetails user = impl.loadUserByUsername("thisUserDoesntExist");
		
		assertEquals("thisUserDoesntExist", user.getUsername());
		}
		catch(Exception e)
		{
			assertTrue(true);
		}
	}

}
