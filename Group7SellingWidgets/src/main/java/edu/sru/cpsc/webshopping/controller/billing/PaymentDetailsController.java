package edu.sru.cpsc.webshopping.controller.billing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sru.cpsc.webshopping.domain.billing.PaymentDetails;
import edu.sru.cpsc.webshopping.domain.user.Statistics;
import edu.sru.cpsc.webshopping.domain.user.User;
import edu.sru.cpsc.webshopping.domain.user.Statistics.Category;
import edu.sru.cpsc.webshopping.repository.billing.PaymentDetailsRepository;
import edu.sru.cpsc.webshopping.domain.billing.PaymentDetails;

/**
 * Controller for handling PaymentDetails in database
 */
@RestController
public class PaymentDetailsController {
	@PersistenceContext
	private EntityManager entityManager;
	private PaymentDetailsRepository paymentDetailsRepository;
	
	public PaymentDetailsController(PaymentDetailsRepository paymentDetailsRepository) {
		this.paymentDetailsRepository = paymentDetailsRepository;
	}
	
	/**
	 * Deletes the passed PaymentDetails from the database
	 * @param details details to delete
	 */
	public void deletePaymentDetails(PaymentDetails details) {
		PaymentDetails dbDetails = entityManager.find(PaymentDetails.class, details.getId());
		if (dbDetails != null)
			paymentDetailsRepository.deleteById(dbDetails.getId());
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void addPaymentDetails(@Validated PaymentDetails details) {
		System.out.println("update payment details database function called");
		
		// Encode fields
		details.setCardholderName(passwordEncoder.encode(details.getCardholderName()));
		details.setCardNumber(passwordEncoder.encode(details.getCardNumber()));
		details.setLast4Digits(details.getLast4Digits());
		details.setCardType(details.getCardType());
		details.setExpirationDate(passwordEncoder.encode(details.getExpirationDate()));
		details.setPostalCode(passwordEncoder.encode(details.getPostalCode()));
		details.setSecurityCode(passwordEncoder.encode(details.getSecurityCode()));
		// No assigned details - add to user
			entityManager.persist(details);
		
	}
	
	@RequestMapping("/get-all-payment-details") 
	public Iterable<PaymentDetails> getAllPaymentDetails() {
		Iterable<PaymentDetails> paymentDetails = paymentDetailsRepository.findAll();
		return paymentDetails;
	}
	
	/**
	 * checks to see if the paymentDetailsRepository already has the passed PaymentDetails
	 * @param details
	 * @return
	 */
	@RequestMapping("/check-for-same")
	public boolean checkDuplicateCard(PaymentDetails details){
		int check = 1;
		for(PaymentDetails PD : paymentDetailsRepository.findAll())
		{
			if( passwordEncoder.matches(details.getCardholderName(), PD.getCardholderName())
			&& passwordEncoder.matches(details.getCardNumber(), PD.getCardNumber())
			&& passwordEncoder.matches(details.getExpirationDate(), PD.getExpirationDate())
			&& passwordEncoder.matches(details.getPostalCode(), PD.getPostalCode())
			&& passwordEncoder.matches(details.getSecurityCode(), PD.getSecurityCode()))
				check = 0;
		}
		if(check == 1)
			return false;
		else
			return true;
	}
	
	@RequestMapping("/get-payment-detail") 
	public PaymentDetails getPaymentDetail(@PathVariable("id") long id, Model model) {
		PaymentDetails paymentDetails = paymentDetailsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID passed to find a user"));
		return paymentDetails;
	}
	
	/**
	 * finds if there are payment details in the paymentDetailsRepository that match the passed PaymentDetails 
	 * @param details
	 * @return
	 */
	public PaymentDetails getPaymentDetailsByCardNumberAndExpirationDate(PaymentDetails details) {
		for(PaymentDetails PD : paymentDetailsRepository.findAll())
		{
			if( passwordEncoder.matches(details.getCardholderName(), PD.getCardholderName())
			&& passwordEncoder.matches(details.getCardNumber(), PD.getCardNumber())
			&& passwordEncoder.matches(details.getExpirationDate(), PD.getExpirationDate())
			&& passwordEncoder.matches(details.getPostalCode(), PD.getPostalCode())
			&& passwordEncoder.matches(details.getSecurityCode(), PD.getSecurityCode()))
				return PD;
		}
		return details;
	}
}
