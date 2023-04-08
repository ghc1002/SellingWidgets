package edu.sru.cpsc.webshopping.controller.purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.sru.cpsc.webshopping.controller.MarketListingDomainController;
import edu.sru.cpsc.webshopping.controller.TransactionController;
import edu.sru.cpsc.webshopping.controller.billing.PaymentDetailsController;
import edu.sru.cpsc.webshopping.controller.UserController;
import edu.sru.cpsc.webshopping.controller.UserDetailsController;
import edu.sru.cpsc.webshopping.controller.billing.CardTypeController;
import edu.sru.cpsc.webshopping.domain.billing.DirectDepositDetails_Form;
import edu.sru.cpsc.webshopping.domain.billing.PaymentDetails;
import edu.sru.cpsc.webshopping.domain.billing.PaymentDetails_Form;
import edu.sru.cpsc.webshopping.domain.billing.Paypal;
import edu.sru.cpsc.webshopping.domain.billing.Paypal_Form;
import edu.sru.cpsc.webshopping.domain.billing.ShippingAddress;
import edu.sru.cpsc.webshopping.domain.market.MarketListing;
import edu.sru.cpsc.webshopping.domain.market.Shipping;
import edu.sru.cpsc.webshopping.domain.market.Transaction;
import edu.sru.cpsc.webshopping.domain.user.User;
import edu.sru.cpsc.webshopping.repository.market.ShippingRepository;
import edu.sru.cpsc.webshopping.repository.market.TransactionRepository;
import edu.sru.cpsc.webshopping.repository.billing.PaymentDetailsRepository;

/**
 * Manages functionality for the confirmPurchase page This page is used to
 * verify payment details, and to submit the purchase/ create the associated
 * transaction
 */
@Controller
public class ConfirmPurchasePageController {
	private Transaction purchase;
	private MarketListing prevListing;
	private PaymentDetails details;
	private PaymentDetailsRepository payDetRepository;
	private ShippingAddress address;
	private Paypal_Form paypal;
	@Lazy
	private PurchaseShippingAddressPageController shippingController;
	// SQL Controllers
	private TransactionController transController;
	private PaymentDetailsController payDetController;
	private MarketListingDomainController marketListingController;
	private UserController userController;
	private CardTypeController cardController;
	private UserDetailsController userDetController;
	private boolean allSelected = false;
	private boolean modifyPayment = false;
	private PaymentDetails validatedDetails;
	private boolean toShipping = false;

	ConfirmPurchasePageController(MarketListingDomainController marketListingController, UserController userController,
			TransactionController transController, UserDetailsController userDetController,
			CardTypeController cardController, PaymentDetailsController payDetController,
			PaymentDetailsRepository payDetRepository, PurchaseShippingAddressPageController shippingController) {
		this.marketListingController = marketListingController;
		this.payDetController = payDetController;
		this.payDetRepository = payDetRepository;
		this.userController = userController;
		this.transController = transController;
		this.cardController = cardController;
		this.userDetController = userDetController;
		this.shippingController = shippingController;
	}
	
	@RequestMapping("/initializePurchasePage")
	public String initializePurchasePage(ShippingAddress address, MarketListing prevListing, Transaction purchaseOrder,
			Model model) {
		// Setup purchase with total price and profit
		if(purchaseOrder != null)
			this.purchase = purchaseOrder;
		if (address != null && address.getState() != null)
			this.address = address;
		if(prevListing != null)
			this.prevListing = prevListing;
		toShipping = false;
		modifyPayment = false;
		allSelected = false;
		
		if(this.address != null)
		{
			BigDecimal salesTaxPercentage = this.address.getState().getSalesTaxRate().divide(new BigDecimal(100));
			BigDecimal afterSalesTax = purchase.getTotalPriceBeforeTaxes()
					.add(salesTaxPercentage.multiply(purchase.getTotalPriceBeforeTaxes())).setScale(2, RoundingMode.UP);
			purchase.setTotalPriceAfterTaxes(afterSalesTax);
			BigDecimal finalSellerProfit = afterSalesTax
					.subtract(afterSalesTax.multiply(Transaction.WEBSITE_CUT_PERCENTAGE));
			purchase.setSellerProfit(finalSellerProfit);
		}
		
		details = new PaymentDetails();
		paypal = new Paypal_Form();
		User user = userController.getCurrently_Logged_In();
		if(validatedDetails == null)
			validatedDetails = user.getDefaultPaymentDetails();
		if (address == null)
			model.addAttribute("selectedAddress", null);
		else
			model.addAttribute("selectedAddress", this.address);
		model.addAttribute("purchase", purchase);
		model.addAttribute("marketListing", this.prevListing);
		model.addAttribute("widget", this.prevListing.getWidgetSold());
		model.addAttribute("paymentDetails", details);
		model.addAttribute("cardTypes", cardController.getAllCardTypes());
		model.addAttribute("paypal", paypal);
		model.addAttribute("modifyPayment", modifyPayment);
		model.addAttribute("selectedPayment", validatedDetails);
		model.addAttribute("toShipping", toShipping);
		model.addAttribute("allSelected", allSelected);
		model.addAttribute("user", user);
		model.addAttribute("defaultDetails", user.getDefaultPaymentDetails());
		if (user.getPaymentDetails() != null && user.getPaymentDetails().isEmpty())
			model.addAttribute("allDetails", null);
		else
			model.addAttribute("allDetails", payDetController.getPaymentDetailsByUser(user));
		model.addAttribute("existingSecurityCode", new String());
		return "confirmPurchase";
	}

	/**
	 * Initializes the confirmPurchase page
	 * @param address
	 * @param prevListing
	 * @param purchaseOrder
	 * @param model
	 * @param persisted
	 * @return
	 */
	@RequestMapping("/confirmPurchase")
	public String openConfirmPurchasePage(ShippingAddress address, MarketListing prevListing, Transaction purchaseOrder,
			Model model) {
		// Setup purchase with total price and profit
		toShipping = false;
		
		if(address != null && address.getState() != null)
			this.address = address;
		
		//set the amount before and after tax for the user to see (must have a location to know the tax)
		if(this.address != null)
		{
			BigDecimal salesTaxPercentage = this.address.getState().getSalesTaxRate().divide(new BigDecimal(100));
			BigDecimal afterSalesTax = purchase.getTotalPriceBeforeTaxes()
					.add(salesTaxPercentage.multiply(purchase.getTotalPriceBeforeTaxes())).setScale(2, RoundingMode.UP);
			purchase.setTotalPriceAfterTaxes(afterSalesTax);
			BigDecimal finalSellerProfit = afterSalesTax
					.subtract(afterSalesTax.multiply(Transaction.WEBSITE_CUT_PERCENTAGE));
			purchase.setSellerProfit(finalSellerProfit);
		}
		
		// Prepare a form for verifying the user's payment details
		details = new PaymentDetails();
		paypal = new Paypal_Form();
		User user = userController.getCurrently_Logged_In();
		if(validatedDetails == null)
			validatedDetails = user.getDefaultPaymentDetails();
		if(user.getDefaultShipping() == null && address == null)
			this.address = null;
		if (address == null)
			model.addAttribute("selectedAddress", null);
		else
			model.addAttribute("selectedAddress", this.address);
		if(this.address != null && validatedDetails != null)
			allSelected = true;
		model.addAttribute("purchase", purchase);
		model.addAttribute("marketListing", this.prevListing);
		model.addAttribute("widget", this.prevListing.getWidgetSold());
		model.addAttribute("paymentDetails", details);
		model.addAttribute("cardTypes", cardController.getAllCardTypes());
		model.addAttribute("paypal", paypal);
		model.addAttribute("modifyPayment", modifyPayment);
		model.addAttribute("selectedPayment", validatedDetails);
		model.addAttribute("toShipping", toShipping);
		model.addAttribute("allSelected", allSelected);
		model.addAttribute("user", user);
		model.addAttribute("defaultDetails", user.getDefaultPaymentDetails());
		if (user.getPaymentDetails() != null && user.getPaymentDetails().isEmpty())
			model.addAttribute("allDetails", null);
		else
			model.addAttribute("allDetails", payDetController.getPaymentDetailsByUser(user));
		model.addAttribute("existingSecurityCode", new String());
		return "confirmPurchase";
	}

	/**
	 * Allow the user to use their currently saved Card details by validating using
	 * the security code If successful, the associated Transaction is saved to the
	 * database, and the number of available items for the MarketListing is
	 * decreased by the number of purchased items The variables are passed by
	 * dependency injection
	 * 
	 * @param existingSecurityCode the existing security code
	 * @param result               BindingResult associated with the form
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/confirmPurchase/existingCard", method = RequestMethod.POST, params = "submit")
	public String submitPurchaseExistingCard(@Validated @ModelAttribute("selected_payment_details") Long id,
			@Validated @ModelAttribute("existingSecurityCode") String existingSecurityCode, BindingResult result,
			Model model) {
		if (this.userController.getCurrently_Logged_In() == null) {
			throw new IllegalStateException("Cannot purchase an item when not logged in.");
		}
		allSelected = false;
		// Test that payment details are valid
		System.out.println(id);
		if (id != null && payDetController.matchExistingCard(existingSecurityCode, payDetRepository.findById(id).get())) {
			validatedDetails = payDetRepository.findById(id).get();

				allSelected = true;
			modifyPayment = false;
			return "redirect:/confirmPurchase";
		}
		
		// Transaction failed - post error
		else {
			if (address == null)
				model.addAttribute("selectedAddress", null);
			else
				model.addAttribute("selectedAddress", address);
			details = new PaymentDetails();
			// Build credit card error message
			User user = userController.getCurrently_Logged_In();
			model.addAttribute("exSecurityCodeErr", "Security code doesn't match current user's saved card");
			model.addAttribute("purchase", purchase);
			if (address == null)
				model.addAttribute("noAddress", "Please enter a shipping address");
			model.addAttribute("marketListing", prevListing);
			model.addAttribute("widget", prevListing.getWidgetSold());
			model.addAttribute("selectedPayment", validatedDetails);
			model.addAttribute("errMessage", "Payment Details Invalid");
			model.addAttribute("paypal", paypal);
			model.addAttribute("paymentDetails", details);
			model.addAttribute("cardTypes", cardController.getAllCardTypes());
			model.addAttribute("user", user);
			model.addAttribute("modifyPayment", modifyPayment);
			model.addAttribute("toShipping", toShipping);
			model.addAttribute("allSelected", allSelected);
			model.addAttribute("defaultDetails", userController.getCurrently_Logged_In().getDefaultPaymentDetails());
			if (user.getPaymentDetails() != null && user.getPaymentDetails().isEmpty())
				model.addAttribute("allDetails", null);
			else
				model.addAttribute("allDetails", payDetController.getPaymentDetailsByUser(user));
			model.addAttribute("existingSecurityCode", new String());
			return "confirmPurchase";
		}
	}


	/**
	 * Attempts to confirm the purchase If successful, the associated Transaction is
	 * saved to the database, and the number of available items for the
	 * MarketListing is decreased by the number of purchased items The variables are
	 * passed by dependency injection
	 * 
	 * @param paymentDetails the PaymentDetails from the form
	 * @param result         BindingResult associated with the form
	 * @param model          the page model
	 * @exception IllegalStateException if the user is not logged in
	 * @return a redirection to index, if purchase is successful, or the same page,
	 *         if verification fails
	 */
	@RequestMapping(value = "/confirmPurchase/submitPurchase", method = RequestMethod.POST, params = "submit")
	public String submitPurchase(@Validated @ModelAttribute("paymentDetails") PaymentDetails_Form paymentDetails,
			BindingResult result, Model model) {
		PaymentDetails currDetails = new PaymentDetails();
		allSelected = false;
		currDetails.buildFromForm(paymentDetails);
		if (this.userController.getCurrently_Logged_In() == null) {
			throw new IllegalStateException("Cannot purchase an item when not logged in.");
		}
		// Test that payment details are valid
		if (!paymentDetailsInvalid(paymentDetails) && !result.hasErrors()) {
			// add the card to the database if it's new
			if (!payDetController.checkDuplicateCard(currDetails)) {
				userDetController.createDetails(paymentDetails, result, model);
				System.out.println("option 1");
			} else {
				currDetails = payDetController.getPaymentDetailsByCardNumberAndExpirationDate(currDetails);
				System.out.println(currDetails.getId());
			}
			if (address != null)
				allSelected = true;
			modifyPayment = false;
			validatedDetails = currDetails;
			return "redirect:/confirmPurchase";
		}
		// Transaction failed - post error
		else {
			if (address == null)
				model.addAttribute("selectedAddress", null);
			else
				model.addAttribute("selectedAddress", address);
			details = new PaymentDetails();
			// Build credit card error message
			for (FieldError item : result.getFieldErrors()) {
				model.addAttribute(item.getField() + "Err", item.getDefaultMessage());
			}
			if (paymentDetails.getExpirationDate() != null && paymentDetailsExpired(paymentDetails)) {
				model.addAttribute("cardError", "The Credit Card has expired.");
			}
			if (userDetController.cardFarFuture(paymentDetails) && paymentDetails.getExpirationDate() != "")
				model.addAttribute("cardError", "The expiration date is an impossible number of years in the future");

			User user = userController.getCurrently_Logged_In();
			if (address == null)
				model.addAttribute("noAddress", "Please enter a shipping address");
			model.addAttribute("purchase", purchase);
			model.addAttribute("marketListing", prevListing);
			model.addAttribute("widget", prevListing.getWidgetSold());
			model.addAttribute("paymentDetails", details);
			model.addAttribute("errMessage", "Payment Details Invalid");
			model.addAttribute("paypal", paypal);
			model.addAttribute("modifyPayment", modifyPayment);
			model.addAttribute("selectedPayment", validatedDetails);
			model.addAttribute("toShipping", toShipping);
			model.addAttribute("useThis", true);
			model.addAttribute("allSelected", allSelected);
			model.addAttribute("cardTypes", cardController.getAllCardTypes());
			model.addAttribute("user", user);
			model.addAttribute("defaultDetails", userController.getCurrently_Logged_In().getDefaultPaymentDetails());
			if (user.getPaymentDetails() != null && user.getPaymentDetails().isEmpty())
				model.addAttribute("allDetails", null);
			else
				model.addAttribute("allDetails", payDetController.getPaymentDetailsByUser(user));
			model.addAttribute("existingSecurityCode", new String());
			return "confirmPurchase";
		}
	}

	/**
	 * Cancels the purchase The user is returned to the viewMarketListing page they
	 * attempted to purchase from No changes are made
	 * 
	 * @param paymentDetails passed by the form
	 * @return a redirection to the viewMarketListing page associated with the
	 *         listing they attempted to purchase from
	 */
	@RequestMapping(value = "/confirmPurchase/submitPurchase", method = RequestMethod.POST, params = "cancel")
	public String cancelPurchase(@Validated @ModelAttribute("paymentDetails") PaymentDetails paymentDetails) {
		allSelected = false;
		modifyPayment = false;
		toShipping = false;
		return "redirect:/confirmPurchase";
	}

	/**
	 * Attempts to submit the purchase via Paypal
	 * 
	 * @param paypal Validated Paypal form
	 * @param result BindingResult associated with Paypal form
	 * @param model  page model
	 * @return Returns to the index if successful, reloads the page if failed
	 */
	@RequestMapping(value = "/confirmPurchase/submitPurchasePaypal", method = RequestMethod.POST, params = "submit")
	public String submitPurchasePaypal(@Validated @ModelAttribute("paypal") Paypal_Form paypal, BindingResult result,
			Model model) {
		Paypal currPaypal = new Paypal();
		currPaypal.buildFromForm(paypal);
		if (this.userController.getCurrently_Logged_In() == null) {
			throw new IllegalStateException("Cannot purchase an item when not logged in.");
		}
		if (this.userController.verifyPaypalDetails(currPaypal)) {
			// Update market listing to reflect purchase
			marketListingController.marketListingPurchaseUpdate(prevListing, purchase.getQtyBought());
			// Creates an unfinished shipping label, to be filled out later by the seller
			// Preparing the transaction for posting to the database
			Shipping shipping = new Shipping();
			shipping.setTransaction(purchase);
			shipping.setAddress(address);
			purchase.setShippingEntry(shipping);
			transController.addTransaction(purchase);
			return "redirect:/homePage";
		} else {
			if (result.hasErrors()) { // Transaction failed - show errors
				// Build credit card error message
				for (FieldError item : result.getFieldErrors()) {
					model.addAttribute(item.getField() + "Err", item.getDefaultMessage());
				}
			}
			model.addAttribute("purchase", purchase);
			model.addAttribute("marketListing", prevListing);
			model.addAttribute("widget", prevListing.getWidgetSold());
			model.addAttribute("paymentDetails", details);
			model.addAttribute("errMessage", "Paypal Details Invalid");
			model.addAttribute("paypal", paypal);
			model.addAttribute("cardTypes", cardController.getAllCardTypes());
			model.addAttribute("user", userController.getCurrently_Logged_In());
			return "confirmPurchase";
		}
	}

	/**
	 * Attempts to cancel the purchase
	 * 
	 * @param paypal Validated Paypal form
	 * @return the MarketListing that the user attempted to purchase from
	 */
	@RequestMapping(value = "/confirmPurchase/submitPurchasePaypal", method = RequestMethod.POST, params = "cancel")
	public String cancelPurchasePaypal(@Validated @ModelAttribute("paypal") Paypal_Form paypal, BindingResult result,
			Model model) {
		System.out.println("cancel purchase paypal");
		return "redirect:/viewMarketListing/" + prevListing.getId();
	}

	/**
	 * Attempts to cancel the purchase
	 * 
	 * @param paypal Validated Paypal form
	 * @return the MarketListing that the user attempted to purchase from
	 */
	@RequestMapping(value = "/cancel-purchase")
	public String cancelPurchase() {
		System.out.println("cancel purchase paypal");
		return "redirect:/viewMarketListing/" + prevListing.getId();
	}
	
	/**
	 * Allows for the shipping information to be changed
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toShipping")
	public String toShipping(Model model) {
		PaymentDetails details = null;
		allSelected = false;
		if (userController.getCurrently_Logged_In().getDefaultPaymentDetails() != null)
			details = userController.getCurrently_Logged_In().getDefaultPaymentDetails();
		return this.shippingController.openConfirmShippingPage(true, prevListing, purchase, details, model);
	}
	
	/**
	 * prepares the page for a user modifying their payment details
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/modifyPayment")
	public String modifyPayment(Model model) {
		modifyPayment = true;
		User user = userController.getCurrently_Logged_In();
		if(validatedDetails == null)
			validatedDetails = user.getDefaultPaymentDetails();
		if (address == null)
			model.addAttribute("selectedAddress", null);
		else
			model.addAttribute("selectedAddress", this.address);
		if(this.address != null && validatedDetails != null)
			allSelected = true;
		model.addAttribute("purchase", purchase);
		model.addAttribute("marketListing", this.prevListing);
		model.addAttribute("widget", this.prevListing.getWidgetSold());
		model.addAttribute("paymentDetails", details);
		model.addAttribute("cardTypes", cardController.getAllCardTypes());
		model.addAttribute("paypal", paypal);
		model.addAttribute("selectedPayment", validatedDetails);
		model.addAttribute("toShipping", toShipping);
		model.addAttribute("allSelected", allSelected);
		model.addAttribute("user", user);
		model.addAttribute("defaultDetails", user.getDefaultPaymentDetails());
		if (user.getPaymentDetails() != null && user.getPaymentDetails().isEmpty())
			model.addAttribute("allDetails", null);
		else
			model.addAttribute("allDetails", payDetController.getPaymentDetailsByUser(user));
		model.addAttribute("existingSecurityCode", new String());
		model.addAttribute("modifyPayment", modifyPayment);
		return "confirmPurchase";
	}
	
	
	/**
	 * attempts to purchase the widget first checking if a shipping address and payment details are given
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/attemptPurchase")
	public String attemptPurchase(Model model) {
		if (address != null && validatedDetails != null) {
			BigDecimal salesTaxPercentage = this.address.getState().getSalesTaxRate().divide(new BigDecimal(100));
			BigDecimal afterSalesTax = purchase.getTotalPriceBeforeTaxes()
					.add(salesTaxPercentage.multiply(purchase.getTotalPriceBeforeTaxes())).setScale(2, RoundingMode.UP);
			purchase.setTotalPriceAfterTaxes(afterSalesTax);
			BigDecimal finalSellerProfit = afterSalesTax
					.subtract(afterSalesTax.multiply(Transaction.WEBSITE_CUT_PERCENTAGE));
			purchase.setSellerProfit(finalSellerProfit);
			// Update market listing to reflect purchase
			marketListingController.marketListingPurchaseUpdate(prevListing, purchase.getQtyBought());
			// Creates an unfinished shipping label, to be filled out later by the seller
			// Preparing the transaction for posting to the database
			Shipping shipping = new Shipping();
			shipping.setTransaction(purchase);
			shipping.setAddress(address);
			purchase.setShippingEntry(shipping);
			transController.addTransaction(purchase);
			return "redirect:/homePage";
		} else {
			model.addAttribute("errMessage", "A Shipping Address and Payment Details Must Be Selected");
			if (address == null)
				model.addAttribute("selectedAddress", null);
			else
				model.addAttribute("selectedAddress", address);
			details = new PaymentDetails();
			// Build credit card error message

			User user = userController.getCurrently_Logged_In();
			if (address == null)
				model.addAttribute("noAddress", "Please enter a shipping address");
			model.addAttribute("purchase", purchase);
			model.addAttribute("marketListing", prevListing);
			model.addAttribute("widget", prevListing.getWidgetSold());
			model.addAttribute("paymentDetails", details);
			model.addAttribute("paypal", paypal);
			model.addAttribute("selectedPayment", validatedDetails);
			model.addAttribute("toShipping", toShipping);
			model.addAttribute("useThis", true);
			model.addAttribute("allSelected", allSelected);
			model.addAttribute("cardTypes", cardController.getAllCardTypes());
			model.addAttribute("user", user);
			model.addAttribute("defaultDetails", userController.getCurrently_Logged_In().getDefaultPaymentDetails());
			if (user.getPaymentDetails() != null && user.getPaymentDetails().isEmpty())
				model.addAttribute("allDetails", null);
			else
				model.addAttribute("allDetails", payDetController.getPaymentDetailsByUser(user));
			model.addAttribute("existingSecurityCode", new String());
			return "confirmPurchase";
		}
	}

	/**
	 * Returns true if the PaymentDetails fails constraints that are not represented
	 * by the Spring Validation annotations These constraints are: expiration date
	 * must be before the current date
	 * 
	 * @param form A completed PaymentDetails_Form
	 */
	public boolean paymentDetailsInvalid(PaymentDetails_Form form) {
		return paymentDetailsExpired(form);
	}

	/**
	 * Returns true if the passed PaymentDetails are expired
	 * 
	 * @param form The PaymentDetails_Form to check is expired
	 * @return true if the form is expired, false otherwise, and false if the
	 *         expiration date String is null or empty
	 */
	public boolean paymentDetailsExpired(PaymentDetails_Form form) {
		if (form.getExpirationDate() == null || form.getExpirationDate().length() == 0)
			return false;
		// Check if current date is on or past the expiration date
		System.out.println("before parse");
		System.out.println(form.getExpirationDate());
		LocalDate expirDate = LocalDate.parse(form.getExpirationDate()+"-01");
		System.out.println("after parse");
		if (expirDate.compareTo(LocalDate.now()) < 0)
			return true;

		// No errors found
		return false;
	}

}
