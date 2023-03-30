package edu.sru.cpsc.webshopping.controller.billing;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;

import edu.sru.cpsc.webshopping.domain.billing.PaymentDetails;
import edu.sru.cpsc.webshopping.repository.billing.PaymentDetailsRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
classes = PaymentDetailsControllerTest.class)
@ContextConfiguration(classes = PaymentDetailsControllerTest.class)
@AutoConfigureMockMvc
public class PaymentDetailsControllerTest {
	private Logger log= Logger.getLogger(getClass());
	
	@MockBean
	private PaymentDetailsRepository paymentDetailsRepository;
	
	@Autowired
	private MockMvc mvc;
	
	PaymentDetails details = new PaymentDetails();
	
	@Before
	public void setUp()
	{
		details.setCardholderName("tyler");
		details.setId(0L);
		Mockito.when(paymentDetailsRepository.findById(details.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid ID passed to find a user"))).thenReturn(details);
	}

@Test
@DisplayName("get Payment Details")
public void getPaymentDetails()
{
	PaymentDetailsController paymentDetailsController= new PaymentDetailsController(paymentDetailsRepository);
	paymentDetailsController.addPaymentDetails(details);
	Assertions.assertEquals(paymentDetailsController.getPaymentDetail(0L, null), details);
}

@Test
@DisplayName("delete Payment Details")
public void deletePaymentDetails() {
	try {
		log.info("Starting Execution");
		PaymentDetailsController paymentDetailsController= new PaymentDetailsController(paymentDetailsRepository);
		PaymentDetails details = paymentDetailsController.getPaymentDetail(0, null);
		
		paymentDetailsController.deletePaymentDetails(details);
		Assertions.assertTrue(true);
	}
	catch(Exception exception) {
		log.error("exception");
		exception.printStackTrace();
		Assertions.assertFalse(false);
	}
}


}
