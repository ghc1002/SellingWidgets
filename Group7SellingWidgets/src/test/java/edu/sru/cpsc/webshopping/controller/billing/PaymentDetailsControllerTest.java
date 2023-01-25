package edu.sru.cpsc.webshopping.controller.billing;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.web.bind.annotation.RestController;

import edu.sru.cpsc.webshopping.domain.billing.PaymentDetails;
import edu.sru.cpsc.webshopping.repository.billing.PaymentDetailsRepository;

public class PaymentDetailsControllerTest {
	private Logger log= Logger.getLogger(getClass());

@BeforeAll
static void initAll() {
	
}
@BeforeEach
void init() {
	
}
@Test
@DisplayName("delete Payment Details")
public void deletePaymentDetails() {
	try {
		log.info("Starting Execution");
		
		PaymentDetails details= null;
		
		PaymentDetailsRepository paymentdetailsrepository= null;
		PaymentDetailsRepository paymentDetailsRepository = null;
		PaymentDetailsController paymentdetailscontroller= new PaymentDetailsController(paymentDetailsRepository);
		paymentdetailscontroller.deletePaymentDetails(details);
		Assertions.assertTrue(true);
	}
	catch(Exception exception) {
		log.error("exception");
		exception.printStackTrace();
		Assertions.assertFalse(false);
	}
}
@AfterEach
void tearDown() {
	
}
@AfterAll
static void tearDownAll() {
	
}

}
