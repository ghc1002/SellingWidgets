package edu.sru.cpsc.webshopping.controller;

import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Set;

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
import edu.sru.cpsc.webshopping.controller.billing.SellerRatingController;
import edu.sru.cpsc.webshopping.domain.billing.DirectDepositDetails;
import edu.sru.cpsc.webshopping.domain.billing.DirectDepositDetails_Form;
import edu.sru.cpsc.webshopping.domain.billing.PaymentDetails;
import edu.sru.cpsc.webshopping.domain.billing.PaymentDetails_Form;
import edu.sru.cpsc.webshopping.domain.billing.Paypal;
import edu.sru.cpsc.webshopping.domain.billing.Paypal_Form;
import edu.sru.cpsc.webshopping.domain.market.Transaction;
import edu.sru.cpsc.webshopping.domain.user.Message;
import edu.sru.cpsc.webshopping.domain.user.User;
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
	DEPOSIT_DETAILS
}

@Controller
public class UserDetailsController {

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
	private SUB_MENU selectedMenu;
	



	public UserDetailsController(UserController userController, UserRepository userRepository, 
			TransactionController transController, CardTypeController cardController,
			SellerRatingController ratingController)
	{
		this.userController = userController;
		this.userRepository = userRepository;
		this.cardController = cardController;
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
		selectedMenu = SUB_MENU.PAYMENT_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		return "userDetails";
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
				String fileLocation = new File("src\\main\\resources\\static\\images\\userImages").getAbsolutePath() + "\\" + tempImageName;
				FileOutputStream output = new FileOutputStream(fileLocation);
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
	public String sendUpdate(
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
	@RequestMapping(value = "/submitPaymentDetailsAction", 
			method = RequestMethod.POST, params="submit")
	public String sendUpdate(@Validated @ModelAttribute("paymentDetails") PaymentDetails_Form details, BindingResult result, Model model) {
		selectedMenu = SUB_MENU.PAYMENT_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		if (result.hasErrors() || paymentDetailsConstraintsFailed(details)) {
			// Add error messages
			if (cardExpired(details))
				model.addAttribute("cardExpiredError", "The credit card is expired.");
			model.addAttribute("errMessage", "Your updated payment details has errors.");
			// Add back page data
			model.addAttribute("cardTypes", cardController.getAllCardTypes());
			model.addAttribute("directDepositDetails", new DirectDepositDetails_Form());
			loadUserData(model);
			return "userDetails";
		}
		PaymentDetails payment = new PaymentDetails();
		payment.buildFromForm(details);
		this.userController.updatePaymentDetails(payment);
		return "redirect:/userDetails";
	}
	
	@RequestMapping(value = "/submitPaymentDetailsAction", 
			method = RequestMethod.POST, params="delete")
	public String deleteExisting(@Validated @ModelAttribute("paymentDetails") PaymentDetails_Form details, BindingResult result, Model model) {
		selectedMenu = SUB_MENU.PAYMENT_DETAILS;
		model.addAttribute("selectedMenu", selectedMenu);
		if (userController.getCurrently_Logged_In().getPaymentDetails() == null) {
			model.addAttribute("errMessage", "Your payment details have already been deleted.");
			// Add back page data
			model.addAttribute("cardTypes", cardController.getAllCardTypes());
			model.addAttribute("directDepositDetails", new DirectDepositDetails_Form());
			loadUserData(model);
			return "userDetails";
		}
		this.userController.deletePaymentDetails();
		return "redirect:/userDetails";
	}
	
	@RequestMapping(value = "/submitPaypalDetailsAction", method = RequestMethod.POST, params="submit")
	public String sendUpdate(@Validated @ModelAttribute("paypalDetails") Paypal_Form details, BindingResult result, Model model) {
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
	
	/**
	 * Checks if the non Spring Validation constraints are met by the passed for
	 * @param form The PaymentDetails_Form to check
	 * @return true if the constraints fail, false otherwise
	 */
	public boolean paymentDetailsConstraintsFailed(PaymentDetails_Form form) {
		return cardExpired(form);
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
}