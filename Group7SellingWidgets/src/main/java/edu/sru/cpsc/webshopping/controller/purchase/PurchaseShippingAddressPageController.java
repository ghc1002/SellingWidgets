package edu.sru.cpsc.webshopping.controller.purchase;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smartystreets.api.exceptions.SmartyException;
import com.smartystreets.api.us_street.Candidate;
import com.smartystreets.api.us_street.Client;
import com.smartystreets.api.us_street.ClientBuilder;
import com.smartystreets.api.us_street.Lookup;

import edu.sru.cpsc.webshopping.controller.ShippingAddressDomainController;
import edu.sru.cpsc.webshopping.controller.ShippingDomainController;
import edu.sru.cpsc.webshopping.controller.TransactionController;
import edu.sru.cpsc.webshopping.controller.UserController;
import edu.sru.cpsc.webshopping.controller.billing.StateDetailsController;
import edu.sru.cpsc.webshopping.domain.billing.PaymentDetails;
import edu.sru.cpsc.webshopping.domain.billing.ShippingAddress;
import edu.sru.cpsc.webshopping.domain.billing.ShippingAddress_Form;
import edu.sru.cpsc.webshopping.domain.billing.StateDetails;
import edu.sru.cpsc.webshopping.domain.market.MarketListing;
import edu.sru.cpsc.webshopping.domain.market.Shipping;
import edu.sru.cpsc.webshopping.domain.market.Transaction;
import edu.sru.cpsc.webshopping.domain.user.User;
import edu.sru.cpsc.webshopping.repository.billing.ShippingAddressRepository;

/**
 * Manages the functionality of the confirmShipping page
 * This is used to get the shipping address of the user when they attempt
 * to make a purchase
 */
@Controller
public class PurchaseShippingAddressPageController {
	private StateDetailsController stateDetailsController;
	private ShippingAddress_Form shippingAddress;
	private MarketListing prevListing;
	private Transaction purchaseOrder;
	private Iterable<StateDetails> states;
	private ConfirmPurchasePageController purchasePageController;
	private UserController userController;
	private ShippingAddressDomainController shippingController;
	private boolean relogin = true;
	private boolean loginEr = false;
	private boolean toShipping = true;
	private boolean allSelected = false;
	private PaymentDetails details;
	private boolean modifyPayment = false;
	private ShippingAddressRepository addressRepository;
	
	public PurchaseShippingAddressPageController(StateDetailsController stateDetailsController,
												@Lazy ConfirmPurchasePageController purchasePageController,
												UserController userController,
												ShippingAddressDomainController shippingController,
												ShippingAddressRepository addressRepository) {
		this.stateDetailsController = stateDetailsController;
		this.purchasePageController = purchasePageController;
		this.userController = userController;
		this.shippingController = shippingController;
		this.addressRepository = addressRepository;
	}
	

	/**
	 * Opens the confirmShipping page
	 * @param prevListing the MarketListing the User is attempting to buy from
	 * @param purchaseOrder the partially filled out transaction from the viewMarketListing page
	 * @param model the page Model from the viewMarketListings page
	 * @param transController a TransactionController
	 * @return the confirmShippingAddressPage page
	 */
	@RequestMapping("/confirmShipping")
	public String openConfirmShippingPage(Boolean relogin, MarketListing prevListing, Transaction purchaseOrder, PaymentDetails details, Model model) {
		System.out.println("shipping test");
		if(relogin != null)
			this.relogin = relogin;
		if(details != null)
			this.details = details;
		if(purchaseOrder.getTotalPriceBeforeTaxes() != null)
			this.purchaseOrder = purchaseOrder;
		if(prevListing.getPricePerItem() != null)
			this.prevListing = prevListing;
		model.addAttribute("shippingAddress", new ShippingAddress_Form());
		User user = userController.getCurrently_Logged_In();
		model.addAttribute("user", user);
		model.addAttribute("selectedPayment", details);
		model.addAttribute("allSelected", allSelected);
		model.addAttribute("marketListing", this.prevListing);
		model.addAttribute("widget", this.prevListing.getWidgetSold());
		model.addAttribute("relogin", this.relogin);
		model.addAttribute("purchase", this.purchaseOrder);
		model.addAttribute("loginEr", loginEr);
		model.addAttribute("modifyPayment", modifyPayment);
		model.addAttribute("toShipping", toShipping);
		model.addAttribute("states", stateDetailsController.getAllStates());
		if(user.getDefaultShipping() != null)
			model.addAttribute("defaultShippingDetails", user.getDefaultShipping());
		else
			model.addAttribute("defaultShippingDetails", null);
		if(user.getShippingDetails() != null && user.getShippingDetails().isEmpty())
			model.addAttribute("savedShippingDetails", null);
		else
			model.addAttribute("savedShippingDetails", shippingController.getShippingDetailsByUser(user));
		return "confirmPurchase";
	}
	
	/**
	 * This function saves the ShippingAddress, and then opens the page for
	 * verifying PaymentDetails for the purchase
	 * @param address address from the page form
	 * @param stateId the state name of the state the user is attempting to ship to
	 * @param model the page model, passed by dependency injection
	 * @return the confirmPurchasePage
	 */
	@RequestMapping(value = "/confirmShipping/submitAddress", method = RequestMethod.POST, params = "submit")
	public String submitAddress(@Validated @ModelAttribute("shippingAddress") ShippingAddress_Form address, BindingResult result, @RequestParam("stateId") String stateId, Model model) {
		// If there are errors, then refresh the page
		address.setState(stateDetailsController.getState(stateId));
		if (result.hasErrors() || shippingAddressConstraintsFailed(address)) {
			model.addAttribute("shippingAddress", new ShippingAddress_Form());
			model.addAttribute("states", states);	
			if(!result.hasErrors() && shippingAddressConstraintsFailed(address))
				model.addAttribute("shippingError", "Address does not exist");
			// Add errors to model
			User user = userController.getCurrently_Logged_In();
			model.addAttribute("user", user);
			model.addAttribute("relogin", relogin);
			model.addAttribute("loginEr", loginEr);
			model.addAttribute("modifyPayment", modifyPayment);
			model.addAttribute("allSelected", allSelected);
			model.addAttribute("purchase", purchaseOrder);
			model.addAttribute("marketListing", this.prevListing);
			model.addAttribute("toShipping", toShipping);
			model.addAttribute("widget", this.prevListing.getWidgetSold());
			model.addAttribute("selectedPayment", details);
			model.addAttribute("states", stateDetailsController.getAllStates());
			if(user.getDefaultShipping() != null)
				model.addAttribute("defaultShippingDetails", user.getDefaultShipping());
			else
				model.addAttribute("defaultShippingDetails", null);
			if(user.getShippingDetails() != null && user.getShippingDetails().isEmpty())
				model.addAttribute("savedShippingDetails", null);
			else
				model.addAttribute("savedShippingDetails", shippingController.getShippingDetailsByUser(user));
			for (FieldError error : result.getFieldErrors()) {
				model.addAttribute(error.getField() + "Err", error.getDefaultMessage());
			}
			return "confirmPurchase";
		}
		StateDetails selectedState = stateDetailsController.getState(stateId);
		address.setState(selectedState);
		System.out.println(address.getState().getStateName());
		ShippingAddress validatedAddress = new ShippingAddress();
		validatedAddress.buildFromForm(address);
		System.out.println(validatedAddress.getId());
		shippingController.addShippingAddress(validatedAddress);
		relogin = false;
		this.persistAddress(validatedAddress);
		return this.purchasePageController.initializePurchasePage(validatedAddress, prevListing, purchaseOrder, model);
	}
	
	/**
	 * If the user selects a saved address then set that as the shipping address
	 * and send it to the purchasing page
	 * @param id
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/confirmShipping/existingAddress", method = RequestMethod.POST, params = "submit")
	public String submitExistingAddress(
			@Validated @ModelAttribute("selected_shipping_details") Long id, BindingResult result, Model model) {
		// If there are errors, then refresh the page
		if (result.hasErrors()) {
			model.addAttribute("shippingAddress", new ShippingAddress_Form());
			model.addAttribute("states", states);	
			User user = userController.getCurrently_Logged_In();
			model.addAttribute("user", user);
			model.addAttribute("allSelected", allSelected);
			model.addAttribute("relogin", relogin);
			model.addAttribute("loginEr", loginEr);
			model.addAttribute("modifyPayment", modifyPayment);
			model.addAttribute("purchase", purchaseOrder);
			model.addAttribute("marketListing", this.prevListing);
			model.addAttribute("toShipping", toShipping);
			model.addAttribute("widget", this.prevListing.getWidgetSold());
			model.addAttribute("selectedPayment", details);
			model.addAttribute("states", stateDetailsController.getAllStates());
			if(user.getDefaultShipping() != null)
				model.addAttribute("defaultShippingDetails", user.getDefaultShipping());
			else
				model.addAttribute("defaultShippingDetails", null);
			if(user.getShippingDetails() != null && user.getShippingDetails().isEmpty())
				model.addAttribute("savedShippingDetails", null);
			else
				model.addAttribute("savedShippingDetails", shippingController.getShippingDetailsByUser(user));
			// Add errors to model
			for (FieldError error : result.getFieldErrors()) {
				model.addAttribute(error.getField() + "Err", error.getDefaultMessage());
			}
			return "confirmPurchase";
		}
		relogin = true;
		ShippingAddress validatedAddress = shippingController.getShippingAddressEntry(id);
		return this.purchasePageController.initializePurchasePage(validatedAddress, prevListing, purchaseOrder, model);
	}
	
	/**
	 * Cancels the purchase, returns user to the viewMarketListing page
	 * that they came from
	 * @param address address from the page form
	 * @param stateId the state name of the state the user has selected on the form
	 * @return viewMarketListing page
	 */
	@RequestMapping(value = "/confirmShipping/submitAddress", method = RequestMethod.POST, params = "cancel")
	public String cancelAddress(
			@Validated @ModelAttribute("shippingAddress") ShippingAddress_Form address,
			BindingResult result,
			@RequestParam("stateId") String statId,
			Model model) {
		relogin = true;
		loginEr = false;
		return this.purchasePageController.initializePurchasePage(userController.getCurrently_Logged_In().getDefaultShipping(), prevListing, purchaseOrder, model);
	}
	
	/**
	 * if the user selects back this will bring them back to the confirm shipping main menu
	 * @return
	 */
	@RequestMapping(value = "/confirmShipping/submitAddress/back")
	public String backAddress() {
		relogin = true;
		loginEr = false;
		return "redirect:/confirmShipping";
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/**
	 * If a user tries to add a new address this will need to verify their identity using their login credentials
	 * this will take them and send them to be verified. Works as the logic
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/confirmShipping/submitAddress", params="loginInfo")
	public String relogin(@RequestParam("usernameSA") String username, @RequestParam("passwordSA") String password, Model model) {
		if(!validateLoginInfo(username, password))
		{
			loginEr = true;
			model.addAttribute("loginError", "Incorrect Username or Password Entered");
			return "redirect:/confirmShipping";
		}
		relogin = false;
		loginEr = false;
		return "redirect:/confirmShipping";
	}
	
	public void persistAddress(ShippingAddress address)
	{
		address.setUser(userController.getCurrently_Logged_In());
		addressRepository.save(address);
	}
	
	/**
	 * verifies a users login credentials when they try to add a new address for shipping
	 * @param username
	 * @param password
	 * @return
	 */
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
	 * pass the shipping address to the address validation method
	 * @param form
	 * @return
	 */
	public boolean shippingAddressConstraintsFailed(ShippingAddress_Form form) {
		return addressExists(form);
	}
	
	/**
	 * use the smartystreets api to verify if the passed address exists
	 * @param shipping
	 * @return
	 */
	public boolean addressExists(ShippingAddress_Form shipping)
	{
		Client client = new ClientBuilder("15c052fe-6a81-8841-3359-59658192ff8e", "9d48LSyfCFhlZolc0gi6").build();
		
		Lookup lookup = new Lookup();
		lookup.setAddressee(shipping.getRecipient());
		lookup.setStreet(shipping.getStreetAddress());
		lookup.setCity(shipping.getCity());
		lookup.setState(shipping.getState().getStateName());
		lookup.setZipCode(shipping.getPostalCode());
		System.out.println(lookup.getAddressee());
		System.out.println(lookup.getStreet());
		System.out.println(lookup.getCity());
		System.out.println(lookup.getState());
		System.out.println(lookup.getZipCode());
		
		try {
			client.send(lookup);
		}
		catch(SmartyException | IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		
		List<Candidate> results = lookup.getResult();
		
		if(results.isEmpty())
		{
			System.out.println("cannot find address");
			return true;
		}
		
		return false;
	}
}
