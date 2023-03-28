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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

import edu.sru.cpsc.webshopping.domain.billing.PaymentDetails;
import edu.sru.cpsc.webshopping.repository.billing.PaymentDetailsRepository;

@SpringBootTest
public class PaymentDetailsControllerTest {
	private Logger log= Logger.getLogger(getClass());
	
	@Mock
	private PaymentDetailsRepository repository;
	
	

@Test
@DisplayName("add Payment Details")
public void addPaymentDetails()
{
	PaymentDetails details = new PaymentDetails();
	details.setCardholderName("tyler");
	details.setId(0);
	PaymentDetailsController paymentDetailsController= new PaymentDetailsController(repository);
	
	Assertions.assertEquals(paymentDetailsController.getPaymentDetail(0, null), details);
}

@Test
@DisplayName("delete Payment Details")
public void deletePaymentDetails() {
	try {
		log.info("Starting Execution");
		PaymentDetailsController paymentDetailsController= new PaymentDetailsController(repository);
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
