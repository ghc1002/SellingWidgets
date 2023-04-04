package edu.sru.cpsc.webshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.sru.cpsc.webshopping.controller.billing.CardTypeController;
import edu.sru.cpsc.webshopping.controller.billing.PaymentDetailsController;
import edu.sru.cpsc.webshopping.controller.billing.SellerRatingController;
import edu.sru.cpsc.webshopping.controller.billing.StateDetailsController;
import edu.sru.cpsc.webshopping.domain.billing.DirectDepositDetails;
import edu.sru.cpsc.webshopping.domain.billing.DirectDepositDetails_Form;
import edu.sru.cpsc.webshopping.domain.billing.PaymentDetails;
import edu.sru.cpsc.webshopping.domain.billing.PaymentDetails_Form;
import edu.sru.cpsc.webshopping.domain.billing.Paypal;
import edu.sru.cpsc.webshopping.domain.billing.Paypal_Form;
import edu.sru.cpsc.webshopping.domain.billing.ShippingAddress;
import edu.sru.cpsc.webshopping.domain.billing.ShippingAddress_Form;
import edu.sru.cpsc.webshopping.domain.billing.StateDetails;
import edu.sru.cpsc.webshopping.domain.market.Shipping;
import edu.sru.cpsc.webshopping.domain.market.Transaction;
import edu.sru.cpsc.webshopping.domain.user.Message;
import edu.sru.cpsc.webshopping.domain.user.User;
import edu.sru.cpsc.webshopping.repository.billing.PaymentDetailsRepository;
import edu.sru.cpsc.webshopping.repository.billing.ShippingAddressRepository;
import edu.sru.cpsc.webshopping.repository.market.ShippingRepository;
import edu.sru.cpsc.webshopping.repository.user.UserRepository;
import edu.sru.cpsc.webshopping.secure.UserDetailsServiceImpl;


/**
 * Controller for the user account page. Allows users to edit their account information.
 * Interacts with user database.
 * @author NICK
 *
 */

// Enum for describing the currently displayed submenu
enum SUB_MENU {
	USER_DETAILS,
	PAYMENT_DETAILS,
	PAYPAL_DETAILS,
	DEPOSIT_DETAILS,
	SHIPPING_DETAILS
}

@Controller
public class UserDetailsController {
	@PersistenceContext
	private EntityManager entityManager;
	private UserRepository userRepository;
	private UserController userController;
	private UserDetailsServiceImpl userDetails;
	private TransactionController transController;
	private CardTypeController cardController;
	private SellerRatingController ratingController;
	private String userName;
	private String displayName;
	private String creationDate;
	private String userDescription;
	private String email;
	private boolean addNew = false;
	private boolean update = false;
	private boolean relogin = false;
	private long id2;
	private long updateId = -1;
	private PaymentDetailsRepository payDetRepo;
	private PaymentDetailsController payDetCont;
	private SUB_MENU selectedMenu;
	private ShippingAddressDomainController shippingController;
	private ShippingAddressRepository shippingRepository;
	private StateDetailsController stateDetailsController;
	



	public UserDetailsController(UserController userController, UserRepository userRepository, 
			TransactionController transController, CardTypeController cardController,
			SellerRatingController ratingController, PaymentDetailsRepository payDetRepo,
			PaymentDetailsController payDetCont, ShippingAddressDomainController shippingController,
			ShippingAddressRepository shippingRepository, StateDetailsController stateDetailsController)
	{
		this.userController = userController;
		this.userRepository = userRepository;
		this.cardController = cardController;
		this.payDetRepo = payDetRepo;
		this.payDetCont = payDetCont;
		this.transController = transController;
		this.shippingController = shippingController;
		this.shippingRepository = shippingRepository;
		this.stateDetailsController = stateDetailsController;
	}

	@RequestMapping("/userDetails")
	public String userDetails(Model model)
	{
		loadUserData(model);
		// Model for updating Paypal details
		model.addAttribute("paypalDetails", new Paypal_Form());
		// Model for updating Payment Details
		model.addAttribute("paymentDetails", new PaymentDetails_Form());
		model.addAttribute("cardTypes", cardController.getAllCardTypes());
		// Model for updating Direct Deposit Details
		DirectDepositDetails_Form details = new DirectDepositDetails_Form();
		model.addAttribute("directDepositDetails", details);
		User user  = new User();
		user = userController.getCurrently_Logged_In();
		model.addAttribute("user", user);
		selectedMenu = SUB_MENU.USER_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		return "userDetails";
	}
	
	@RequestMapping("/userDetails/paymentDetails")
	public String openPaymentDetails(Model model) {
		loadUserData(model);
		// Model for updating Paypal details
		model.addAttribute("paypalDetails", new Paypal_Form());
		// Model for updating Payment Details
		model.addAttribute("paymentDetails", new PaymentDetails_Form());
		model.addAttribute("cardTypes", cardController.getAllCardTypes());
		// Model for updating Direct Deposit Details
		DirectDepositDetails_Form details = new DirectDepositDetails_Form();
		model.addAttribute("directDepositDetails", details);
		User user  = new User();
		user = userController.getCurrently_Logged_In();
		model.addAttribute("user", user);
		if(user.getDefaultPaymentDetails() != null)
			model.addAttribute("defaultPaymentDetails", payDetCont.getPaymentDetail(user.getDefaultPaymentDetails().getId(), null));
		else
			model.addAttribute("defaultPaymentDetails", null);
		if(user.getPaymentDetails() != null && user.getPaymentDetails().isEmpty())
			model.addAttribute("savedDetails", null);
		else
			model.addAttribute("savedDetails", payDetCont.getPaymentDetailsByUser(user));
		model.addAttribute("addNew", addNew);
		model.addAttribute("updateId", updateId);
		model.addAttribute("update", update);
		selectedMenu = SUB_MENU.PAYMENT_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		return "userDetails";
	}
	
	@RequestMapping("/userDetails/shippingDetails")
	public String openShippingDetails(Model model) {
		loadUserData(model);
		
		model.addAttribute("shippingDetails", new ShippingAddress_Form());
		User user = userController.getCurrently_Logged_In();
		model.addAttribute("user", user);
		selectedMenu = SUB_MENU.SHIPPING_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		model.addAttribute("states", stateDetailsController.getAllStates());
		if(user.getDefaultShipping() != null)
			model.addAttribute("defaultShippingDetails", user.getDefaultShipping());
		else
			model.addAttribute("defaultShippingDetails", null);
		if(user.getShippingDetails() != null && user.getShippingDetails().isEmpty())
			model.addAttribute("savedShippingDetails", null);
		else
			model.addAttribute("savedShippingDetails", shippingController.getShippingDetailsByUser(user));
		model.addAttribute("addNew", addNew);
		model.addAttribute("updateId", updateId);
		model.addAttribute("update", update);
		model.addAttribute("relogin", relogin);
		return "userDetails";
	}
	
	@RequestMapping("/addShippingDetails")
	public String addShippingDetails(Model model)
	{
		relogin = true;
		update = false;
		addNew = true;
		updateId = -1;
		return "redirect:/userDetails/shippingDetails";
	}
	
	@RequestMapping("/addNewCard")
	public String addNewCard(Model model)
	{
		update = false;
		addNew = true;
		updateId = -1;
		return "redirect:/userDetails/paymentDetails";
	}
	
	@RequestMapping("/goBackToMainPD")
	public String backToMainPD(Model model)
	{
		update = false;
		addNew = false;
		updateId = -1;
		return "redirect:/userDetails/paymentDetails";
	}
	
	@RequestMapping("/goBackToMainSD")
	public String backToMainSD(Model model)
	{
		update = false;
		addNew = false;
		updateId = -1;
		relogin = false;
		return "redirect:/userDetails/shippingDetails";
	}
	
	@RequestMapping("/updatePaymentDetails/{id}")
	public String updateCard(@PathVariable("id") long id, Model model)
	{
		update = true;
		this.id2 = id;
		addNew = false;
		updateId = id;
		return "redirect:/userDetails/paymentDetails";
	}
	
	@RequestMapping("/userDetails/paypalDetails")
	public String openPaypalDetails(Model model) {
		loadUserData(model);
		// Model for updating Paypal details
		model.addAttribute("paypalDetails", new Paypal_Form());
		// Model for updating Payment Details
		model.addAttribute("paymentDetails", new PaymentDetails_Form());
		model.addAttribute("cardTypes", cardController.getAllCardTypes());
		// Model for updating Direct Deposit Details
		DirectDepositDetails_Form details = new DirectDepositDetails_Form();
		model.addAttribute("directDepositDetails", details);
		User user  = new User();
		user = userController.getCurrently_Logged_In();
		model.addAttribute("user", user);
		selectedMenu = SUB_MENU.PAYPAL_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		return "userDetails";
	}
	
	@RequestMapping("/userDetails/depositDetails")
	public String openDepositDetails(Model model) {
		loadUserData(model);
		// Model for updating Paypal details
		model.addAttribute("paypalDetails", new Paypal_Form());
		// Model for updating Payment Details
		model.addAttribute("paymentDetails", new PaymentDetails_Form());
		model.addAttribute("cardTypes", cardController.getAllCardTypes());
		// Model for updating Direct Deposit Details
		DirectDepositDetails_Form details = new DirectDepositDetails_Form();
		model.addAttribute("directDepositDetails", details);
		User user  = new User();
		user = userController.getCurrently_Logged_In();
		model.addAttribute("user", user);
		selectedMenu = SUB_MENU.DEPOSIT_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		return "userDetails";
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(Model model, @RequestParam("imageName") MultipartFile file, RedirectAttributes attributes, @RequestParam("username") String username, @RequestParam("description") String description, @RequestParam("displayName") String displayName, @RequestParam("email") String email)
	{
		/*
		model.addAttribute("user", userRepository.findAll());
		model.addAttribute("users", userController.getAllUsers());
		*/
		User user  = new User();
		user = userController.getCurrently_Logged_In();
		userName = username;
		this.userDescription = description;
		this.displayName = displayName;
		this.email = email;
		
		user.setUsername(userName);
		user.setUserDescription(this.userDescription);
		user.setDisplayName(this.displayName);
		user.setEmail(email);
		
		model.addAttribute("user", user);
		model.addAttribute("userName", userName);
		model.addAttribute("userDescription", userDescription);
		model.addAttribute("displayName", displayName);
		model.addAttribute("email", email);
		if(!file.isEmpty())
		{
			String tempImageName;
			tempImageName = user.getId() + StringUtils.cleanPath(file.getOriginalFilename());
			System.out.println(file.getOriginalFilename());
			System.out.println(tempImageName);
			try {
				String fileLocation = new File("src/main/resources/static/images/userImages").getAbsolutePath() + "/" + tempImageName;
				String fileLocationTemp = new ClassPathResource("static/images/userImages").getFile().getAbsolutePath() + "/" + tempImageName;

				FileOutputStream output = new FileOutputStream(fileLocation);
				output.write(file.getBytes());
				output.close();

				output = new FileOutputStream(fileLocationTemp);
				output.write(file.getBytes());
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("upload failed");
			}
			model.addAttribute("userImage", tempImageName);
			user.setUserImage(tempImageName);
		}
		
		userRepository.save(user);
		
		return "redirect:userDetails";
	}
	
	/**
	 * Function for adding data on the logged in User to the model and controller
	 * @param model The page Model
	 * @return true if successful
	 */
	private boolean loadUserData(Model model) {
		model.addAttribute("user", userRepository.findAll());
		model.addAttribute("users", userController.getAllUsers());
		User user = new User();
		user = userController.getCurrently_Logged_In();
		model.addAttribute("currUser", user);
		System.out.println(user.getUserImage());
		userName = user.getUsername();
		userDescription = user.getUserDescription();
		creationDate = user.getCreationDate();
		displayName = user.getDisplayName();
		email = user.getEmail();
		model.addAttribute("userName", userName);
		model.addAttribute("userDescription", userDescription);
		model.addAttribute("creationDate", creationDate);
		model.addAttribute("displayName", displayName);
		model.addAttribute("currUser", userController.getCurrently_Logged_In());
		model.addAttribute("sellerRating", userController.getSellerRating());
		model.addAttribute("email", email);
		model.addAttribute("displayUserID", user.getId());
		return true;
	}
	
	/**
	 * Creates or updates the DirectDepositDetails associated with the user
	 * @param details the filled out DirectDepositDetails from the page's form
	 * @return 	a redirection string pointing to the userDetails page
	 */
	@RequestMapping(value = "/submitDepositDetailsAction", 
			method = RequestMethod.POST, params="submit")
	public String sendUpdateDD(
			@Validated @ModelAttribute("directDepositDetails") DirectDepositDetails_Form details,
			BindingResult result, Model model) {
		selectedMenu = SUB_MENU.DEPOSIT_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		if (result.hasErrors()) {
			System.out.println("deposit error");
			model.addAttribute("errMessage", "Your updated direct deposit details has errors.");
			model.addAttribute("paymentDetails", new PaymentDetails_Form());
			loadUserData(model);
			return "userDetails";
		}
		DirectDepositDetails deposit = new DirectDepositDetails();
		deposit.buildFromForm(details);
		this.userController.updateDirectDepositDetails(deposit);
		return "redirect:/userDetails";
	}
	
	@RequestMapping(value = "/submitDepositDetailsAction", 
			method = RequestMethod.POST, params="delete")
	public String deleteExisting(
			@Validated @ModelAttribute("directDepositDetails") DirectDepositDetails_Form details,
			BindingResult result, Model model) {
		selectedMenu = SUB_MENU.DEPOSIT_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		if (userController.getCurrently_Logged_In().getDirectDepositDetails() == null) {
			model.addAttribute("errMessage", "Your direct deposit details have already been deleted.");
			// Add back page data
			model.addAttribute("cardTypes", cardController.getAllCardTypes());
			model.addAttribute("paymentDetails", new PaymentDetails_Form());
			loadUserData(model);
			return "userDetails";
		}
		this.userController.deleteDirectDepositDetails();
		return "redirect:/userDetails";
	}
	
	/**
	 * Creates or updates the PaymentDetails associated with the logged in user
	 * @param details the filled out PaymentDetails from the page's form
	 * @return 	a redirection string pointing to the userDetails page
	 */
	@PostMapping(value = "/submitPaymentDetailsAction", params="submit")
	public String createDetails(@Validated @ModelAttribute("paymentDetails") PaymentDetails_Form details, BindingResult result, Model model) {
		selectedMenu = SUB_MENU.PAYMENT_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		System.out.println(details.getExpirationDate());
		if (result.hasErrors() || paymentDetailsConstraintsFailed(details)) {
			// Add error messages
			if (cardExpired(details))
				model.addAttribute("cardError", "The credit card is expired.");
			if(cardFarFuture(details))
				model.addAttribute("cardError", "The expiration date is an impossible number of years in the future");
			model.addAttribute("errMessage", "Your updated payment details has errors.");
			// Add back page data
			model.addAttribute("cardTypes", cardController.getAllCardTypes());
			model.addAttribute("directDepositDetails", new DirectDepositDetails_Form());
			loadUserData(model);
			return "/userDetails";
		}
		PaymentDetails payment = new PaymentDetails();
		payment.buildFromForm(details);
		payment.setUser(userController.getCurrently_Logged_In());
		User user = userController.getCurrently_Logged_In();
		Set<PaymentDetails> PD = user.getPaymentDetails();
		if(PD == null)
			PD = new HashSet<PaymentDetails>();
		PD.add(payment);
		user.setPaymentDetails(PD);
		payDetCont.addPaymentDetails(payment);
		userRepository.save(user);
		addNew = false;
		return "redirect:/userDetails/paymentDetails";
	}
	
	
	/**
	 * Creates or updates the PaymentDetails associated with the logged in user
	 * @param details the filled out PaymentDetails from the page's form
	 * @return 	a redirection string pointing to the userDetails page
	 */
	@PostMapping(value = "/submitPaymentDetailsAction", params="update")
	public String sendUpdatePD(@Validated @ModelAttribute("paymentDetails") PaymentDetails_Form details, BindingResult result, Model model) {
		selectedMenu = SUB_MENU.PAYMENT_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		PaymentDetails currDetails = payDetCont.getPaymentDetail(id2, model);
		System.out.println(details.getExpirationDate());
		if (result.hasErrors() || paymentDetailsConstraintsFailed(details)) {
			// Add error messages
			if (cardExpired(details))
				model.addAttribute("cardError", "The credit card is expired.");
			if(cardFarFuture(details))
				model.addAttribute("cardError", "The expiration date is an impossible number of years in the future");
			model.addAttribute("errMessage", "Your updated payment details has errors.");
			// Add back page data
			model.addAttribute("cardTypes", cardController.getAllCardTypes());
			model.addAttribute("directDepositDetails", new DirectDepositDetails_Form());
			loadUserData(model);
			return "/userDetails";
		}
		PaymentDetails payment = new PaymentDetails();
		payment.buildFromForm(details);
		payDetCont.updatePaymentDetails(payment, currDetails);
		User user = userController.getCurrently_Logged_In();		
		Set<PaymentDetails> pDetails = user.getPaymentDetails();
		List<PaymentDetails> PD = new ArrayList<>(pDetails);
		for(PaymentDetails payDet : PD)
			if(payDet.getId() == id2)
				currDetails = payDet;
		PD.remove(PD.indexOf(currDetails));
		PD.add(payDetCont.getPaymentDetail(id2, model));
		Set<PaymentDetails> PD2 = new HashSet<>(PD);
		user.setPaymentDetails(PD2);
		model.addAttribute("user", user);
		userRepository.save(user);
		addNew = false;
		update = false;
		updateId = -1;
		return "redirect:/userDetails/paymentDetails";
	}

	/**
	 * deletes the existing payment details of a user
	 * @param details The paymentDetails_Form to be deleted
	 * @param result
	 * @param model
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/deleteExistingPaymentDetails/{id}")
	public String deleteExisting(@PathVariable("id") long id) {
		System.out.println("entered udcont");
		User user = userController.getCurrently_Logged_In();
		selectedMenu = SUB_MENU.PAYMENT_DETAILS;
		int index = -1;
		System.out.println(id);
		PaymentDetails currDetails = payDetCont.getPaymentDetail(id, null);
		if(user.getDefaultPaymentDetails() != null && currDetails.getId() == user.getDefaultPaymentDetails().getId())
		{
			System.out.println("detached");
			entityManager.detach(user.getDefaultPaymentDetails());
			user.setDefaultPaymentDetails(null);
			userRepository.save(user);
		}
		List<PaymentDetails> PD = new ArrayList<>(user.getPaymentDetails());
		System.out.println(PD.size());
		if(PD.size()==1)
			PD.remove(0);
		else
			for(PaymentDetails details : PD)
			{
				if(details.getId() == currDetails.getId())
					index = PD.indexOf(details);
			}
		if(index != -1)
			PD.remove(index);
		if(PD.isEmpty())
			user.setPaymentDetails(null);
		else
			user.setPaymentDetails(new HashSet<>(PD));
		if(transController.findByPaymentDetails(currDetails).isEmpty())
		{
			payDetCont.deletePaymentDetails(currDetails);
		}
		currDetails.setUser(null);
		addNew = false;
		update = false;
		updateId = -1;
		return "redirect:/userDetails/paymentDetails";
	}
	
	/**
	 * Changes the current default payment details to another set of payment details
	 * @param id
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/makeDefaultPaymentDetails/{id}")
	public String makeDefaultPaymentDetails(@PathVariable("id") long id) {
		selectedMenu = SUB_MENU.PAYMENT_DETAILS;
		User user = userController.getCurrently_Logged_In();
		PaymentDetails currDetails = payDetCont.getPaymentDetail(id, null);
		user.setDefaultPaymentDetails(currDetails);
		System.out.println(user.getDefaultPaymentDetails().getCardType());
		userRepository.save(user);
		return "redirect:/userDetails/paymentDetails";
	}
	
	/**
	 * Updates the users details and sends the update to the paymentdetails Database location of that users paymentDetails
	 * @param details The updated Payment Details
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/submitPaypalDetailsAction", method = RequestMethod.POST, params="submit")
	public String sendUpdatePPD(@Validated @ModelAttribute("paypalDetails") Paypal_Form details, BindingResult result, Model model) {
		selectedMenu = SUB_MENU.PAYPAL_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		if (result.hasErrors()) {
			model.addAttribute("errMessage", "Your updated payment details has errors.");
			// Add back page data
			model.addAttribute("cardTypes", cardController.getAllCardTypes());
			model.addAttribute("directDepositDetails", new DirectDepositDetails_Form());
			loadUserData(model);
			return "userDetails";
		}
		Paypal payment = new Paypal();
		payment.buildFromForm(details);
		System.out.println("after printing errors");
		this.userController.updatePaypalDetails(payment);
		return "redirect:/userDetails";
	}
	
	/**
	 * send an error if the users paypal Details had already been deleted
	 * @param details
	 * @param result
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value ="/submitPaypalDetailsAction", method = RequestMethod.POST, params="delete")
	public String deleteExisting(@Validated @ModelAttribute("paypalDetails") Paypal_Form details, BindingResult result, Model model) {
		selectedMenu = SUB_MENU.PAYPAL_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		if (userController.getCurrently_Logged_In().getPaypal() == null) {
			model.addAttribute("errMessage", "Your paypal details have already been deleted.");
			model.addAttribute("cardTypes", cardController.getAllCardTypes());
			model.addAttribute("directDepositDetails", new DirectDepositDetails_Form());
			loadUserData(model);
			return "userDetails";
		}
		this.userController.deletePaypal();
		return "redirect:/userDetails";
	}
	
	@RequestMapping("/updateShippingDetails/{id}")
	public String updateShipping(@PathVariable("id") long id, Model model)
	{
		update = true;
		this.id2 = id;
		addNew = false;
		updateId = id;
		relogin = true;
		return "redirect:/userDetails/shippingDetails";
	}
	
	@PostMapping(value = "/submitShippingAddressAction", params="submit")
	public String createShippingDetails(@Validated @ModelAttribute("shippingDetails") ShippingAddress_Form details, BindingResult result, @RequestParam("stateId") String stateId, Model model) {
		selectedMenu = SUB_MENU.SHIPPING_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		if (result.hasErrors() || shippingAddressConstraintsFailed(details)) {
			// Add error messages
			model.addAttribute("shippingError", "Address does not exist");
			model.addAttribute("shippingDetails", new ShippingAddress_Form());
			loadUserData(model);
			return "/userDetails";
		}
		ShippingAddress shipping = new ShippingAddress();
		details.setState(stateDetailsController.getState(stateId));
		shipping.buildFromForm(details);
		shipping.setUser(userController.getCurrently_Logged_In());
		User user = userController.getCurrently_Logged_In();
		Set<ShippingAddress> SA = user.getShippingDetails();
		if(SA == null)
			SA = new HashSet<ShippingAddress>();
		SA.add(shipping);
		user.setShippingDetails(SA);
		shippingController.addShippingAddress(shipping);
		userRepository.save(user);
		addNew = false;
		return "redirect:/userDetails/shippingDetails";
	}
	
	
	/**
	 * Creates or updates the PaymentDetails associated with the logged in user
	 * @param details the filled out PaymentDetails from the page's form
	 * @return 	a redirection string pointing to the userDetails page
	 */
	@PostMapping(value = "/submitShippingAddressAction", params="update")
	public String sendUpdateSA(@Validated @ModelAttribute("shippingDetail") ShippingAddress_Form details, BindingResult result, @RequestParam("stateId") String stateId, Model model) {
		selectedMenu = SUB_MENU.SHIPPING_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		ShippingAddress currDetails = shippingController.getShippingAddressEntry(id2);
		if (result.hasErrors() || shippingAddressConstraintsFailed(details)) {
			// Add error messages
			model.addAttribute("shippingError", "Address does not exist");
			model.addAttribute("shippingDetails", new ShippingAddress_Form());
			loadUserData(model);
			return "/userDetails";
		}
		ShippingAddress shipping = new ShippingAddress();
		details.setState(stateDetailsController.getState(stateId));
		shipping.buildFromForm(details);
		shippingController.updateShippingAddress(shipping, currDetails);
		User user = userController.getCurrently_Logged_In();		
		Set<ShippingAddress> sAddress = user.getShippingDetails();
		List<ShippingAddress> SA = new ArrayList<>(sAddress);
		for(ShippingAddress address : SA)
			if(address.getId() == id2)
				currDetails = address;
		SA.remove(SA.indexOf(currDetails));
		SA.add(shippingController.getShippingAddressEntry(id2));
		Set<ShippingAddress> SA2 = new HashSet<>(SA);
		user.setShippingDetails(SA2);
		model.addAttribute("user", user);
		userRepository.save(user);
		addNew = false;
		update = false;
		updateId = -1;
		return "redirect:/userDetails/shippingDetails";
	}
	
	@Transactional
	@RequestMapping(value = "/deleteExistingShippingDetails/{id}")
	public String deleteExistingShipping(@PathVariable("id") long id) {
		System.out.println("entered udcont");
		User user = userController.getCurrently_Logged_In();
		selectedMenu = SUB_MENU.SHIPPING_DETAILS;
		int index = -1;
		System.out.println(id);
		ShippingAddress currDetails = shippingController.getShippingAddressEntry(id);
		if(user.getDefaultShipping() != null && currDetails.getId() == user.getDefaultShipping().getId())
		{
			System.out.println("detached");
			entityManager.detach(user.getDefaultShipping());
			user.setDefaultShipping(null);
			userRepository.save(user);
		}
		List<ShippingAddress> SD = new ArrayList<>(user.getShippingDetails());
		System.out.println(SD.size());
		if(SD.size()==1)
			SD.remove(0);
		else
			for(ShippingAddress details : SD)
			{
				if(details.getId() == currDetails.getId())
					index = SD.indexOf(details);
			}
		if(index != -1)
			SD.remove(index);
		if(SD.isEmpty())
			user.setShippingDetails(null);
		else
			user.setShippingDetails(new HashSet<>(SD));
		if(transController.findByShippingDetails(currDetails).isEmpty())
		{
			shippingController.deleteShippingAddress(currDetails);
		}
		currDetails.setUser(null);
		addNew = false;
		update = false;
		updateId = -1;
		return "redirect:/userDetails/shippingDetails";
	}
	
	@Transactional
	@RequestMapping(value = "/makeDefaultShippingDetails/{id}")
	public String makeDefaultShippingDetails(@PathVariable("id") long id) {
		selectedMenu = SUB_MENU.SHIPPING_DETAILS;
		User user = userController.getCurrently_Logged_In();
		ShippingAddress currDetails = shippingController.getShippingAddressEntry(id);
		user.setDefaultShipping(currDetails);
		System.out.println(user.getDefaultShipping().getStreetAddress());
		userRepository.save(user);
		return "redirect:/userDetails/shippingDetails";
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping(value = "/submitShippingAddressAction", params="loginInfo")
	public String relogin(@RequestParam("usernameSA") String username, @RequestParam("passwordSA") String password, Model model) {
		if(!validateLoginInfo(username, password))
		{
			model.addAttribute("loginError", "Incorrect Username or Password Entered");
			return "redirect:/userDetails/shippingDetails";
		}
		relogin = false;
		return "redirect:/userDetails/shippingDetails";
	}
	
	/**
	 * Checks if the non Spring Validation constraints are met by the passed for
	 * @param form The PaymentDetails_Form to check
	 * @return true if the constraints fail, false otherwise
	 */
	public boolean paymentDetailsConstraintsFailed(PaymentDetails_Form form) {
		return cardExpired(form);
	}
	
	public boolean shippingAddressConstraintsFailed(ShippingAddress_Form form) {
		return addressExists(form);
	}
	
	public boolean addressExists(ShippingAddress_Form shipping)
	{
		return false;
	}
	
	public boolean validateLoginInfo(String username, String password)
	{
		User user = userController.getCurrently_Logged_In();
		System.out.println(username.equals(user.getUsername()));
		System.out.println(passwordEncoder.matches(password, user.getPassword()));
		if(username.equals(user.getUsername()) && passwordEncoder.matches(password, user.getPassword()))
			return true;
		
		return false;
	}
	
	/**
	 * Checks if the expirationDate of the passed PaymentDetails_Form is expired
	 * @param details the form to validate
	 * @return true if the card is expired, false if the card is not expired, or the expirationDate is invalid
	 */
	public boolean cardExpired(PaymentDetails_Form details) {
		LocalDate expirDate;
		try {
			expirDate = LocalDate.parse(details.getExpirationDate());
			return expirDate.compareTo(LocalDate.now()) < 0;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Checks to see if the card's expiration date is more than 5 years in the future
	 * @param details the PaymentDetails_Form to check
	 * @return true if the card is not in the far future
	 */
	public boolean cardFarFuture(PaymentDetails_Form details) {
		int thisYear = LocalDate.now().getYear();
		try {
			int year = Integer.parseInt(details.getExpirationDate().substring(0, 4));
			if((thisYear + 5) >= year)
				return false;
			return true;
		}
			catch (Exception e) {
			return true;
		}
				
	}
}