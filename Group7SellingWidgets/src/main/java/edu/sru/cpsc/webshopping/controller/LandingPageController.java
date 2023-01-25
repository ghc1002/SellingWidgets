package edu.sru.cpsc.webshopping.controller;

import edu.sru.cpsc.webshopping.domain.market.MarketListing;
import edu.sru.cpsc.webshopping.domain.market.Transaction;
import edu.sru.cpsc.webshopping.domain.user.Message;
import edu.sru.cpsc.webshopping.domain.user.User;
import edu.sru.cpsc.webshopping.domain.user.UserList;
import edu.sru.cpsc.webshopping.domain.widgets.Widget;
import edu.sru.cpsc.webshopping.domain.widgets.appliances.Appliance_Dryers;
import edu.sru.cpsc.webshopping.domain.widgets.appliances.Appliance_Microwave;
import edu.sru.cpsc.webshopping.domain.widgets.appliances.Appliance_Refrigerator;
import edu.sru.cpsc.webshopping.domain.widgets.appliances.Appliance_Washers;
import edu.sru.cpsc.webshopping.domain.widgets.electronics.Electronics_Computers;
import edu.sru.cpsc.webshopping.domain.widgets.electronics.Electronics_VideoGames;
import edu.sru.cpsc.webshopping.domain.widgets.lawncare.LawnCare_LawnMower;
import edu.sru.cpsc.webshopping.domain.widgets.vehicles.Vehicle_Car;
import edu.sru.cpsc.webshopping.repository.user.UserRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for Home page and searching. Interacts with the widget database and its inheriting
 * classes.
 *
 * @author NICK
 */
@Controller
public class LandingPageController {

  UserRepository userRepository;
  WidgetController widgetController;
  MarketListingDomainController marketController;
  UserListDomainController userListController;
  MessageDomainController msgcontrol;
  EmailController emailController;
  private List<User> allUsers = new ArrayList<>();
  private TransactionController transController;
  private UserController userController;
  private String page;
  private String page2;
  private String page3;

  private int count = 0;
  private List<MarketListing> allMarketListings = new ArrayList<>();

  LandingPageController(
      UserRepository userRepository,
      WidgetController widgetController,
      MarketListingDomainController marketController,
      TransactionController transController,
      UserController userController,
      UserListDomainController userListController,
      MessageDomainController msgcontrol,
      EmailController emailController) {
    this.userRepository = userRepository;
    this.widgetController = widgetController;
    this.marketController = marketController;
    this.transController = transController;
    this.userController = userController;
    this.userListController = userListController;
    this.msgcontrol = msgcontrol;
    this.emailController = emailController;
  }

  @GetMapping({"/friendsOff"})
  public String friendsOff(Model model) {
    setPage("friendOff");

    model.addAttribute("widgets", widgetController.getAllWidgets());
    model.addAttribute("listings", marketController.getAllListings());
    Iterable<Transaction> purchases =
        transController.getUserPurchases(userController.getCurrently_Logged_In());
    model.addAttribute("purchases", purchases);
    Iterable<Transaction> soldTrans =
        transController.getUserSoldItems(userController.getCurrently_Logged_In());
    model.addAttribute("soldTrans", soldTrans);
    model.addAttribute("sellerRating", userController.getSellerRating());
    model.addAttribute("page", getPage());
    model.addAttribute("user", userController.getCurrently_Logged_In());

    return "homePage";
  }

  @GetMapping({"/blockList"})
  public String blockList(Model model) {
    setPage("friends");
    setPage3("blockList");
    getAllUsers().clear();
    Iterable<User> allUsersIterator = userController.getAllUsers();
    allUsersIterator.iterator().forEachRemaining(u -> getAllUsers().add(u));
    String[] allusernames = new String[getAllUsers().size()];
    for (int i = 0; i < allusernames.length; i++) {
      allusernames[i] = getAllUsers().get(i).getUsername();
    }

    model.addAttribute("names", allusernames);
    User user = userController.getCurrently_Logged_In();
    List<UserList> blocked = userListController.getBlocked(user);
    model.addAttribute("myBlocked", blocked);
    model.addAttribute("widgets", widgetController.getAllWidgets());
    model.addAttribute("listings", marketController.getAllListings());
    Iterable<Transaction> purchases =
        transController.getUserPurchases(userController.getCurrently_Logged_In());
    model.addAttribute("purchases", purchases);
    Iterable<Transaction> soldTrans =
        transController.getUserSoldItems(userController.getCurrently_Logged_In());
    model.addAttribute("soldTrans", soldTrans);
    model.addAttribute("sellerRating", userController.getSellerRating());
    model.addAttribute("page", getPage());
    model.addAttribute("page3", getPage3());
    model.addAttribute("user", userController.getCurrently_Logged_In());

    return "homePage";
  }

  @GetMapping({"/friends"})
  public String friends(Model model) {
    setPage("friends");
    setPage3("friendList");
    getAllUsers().clear();
    Iterable<User> allUsersIterator = userController.getAllUsers();
    allUsersIterator.iterator().forEachRemaining(u -> getAllUsers().add(u));
    String[] allusernames = new String[getAllUsers().size()];
    for (int i = 0; i < allusernames.length; i++) {
      allusernames[i] = getAllUsers().get(i).getUsername();
    }

    model.addAttribute("names", allusernames);

    User user = userController.getCurrently_Logged_In();
    List<UserList> friends = userListController.getFriends(user);
    model.addAttribute("myFriends", friends);
    model.addAttribute("widgets", widgetController.getAllWidgets());
    model.addAttribute("listings", marketController.getAllListings());
    Iterable<Transaction> purchases =
        transController.getUserPurchases(userController.getCurrently_Logged_In());
    model.addAttribute("purchases", purchases);
    Iterable<Transaction> soldTrans =
        transController.getUserSoldItems(userController.getCurrently_Logged_In());
    model.addAttribute("soldTrans", soldTrans);
    model.addAttribute("sellerRating", userController.getSellerRating());
    model.addAttribute("page", getPage());
    model.addAttribute("page3", getPage3());
    model.addAttribute("user", userController.getCurrently_Logged_In());

    return "homePage";
  }

  @GetMapping({"/suggested"})
  public String suggested(Model model) {
    setPage("friends");
    setPage3("suggestedList");
    getAllUsers().clear();
    Iterable<User> allUsersIterator = userController.getAllUsers();
    allUsersIterator.iterator().forEachRemaining(u -> getAllUsers().add(u));
    String[] allusernames = new String[getAllUsers().size()];
    for (int i = 0; i < allusernames.length; i++) {
      allusernames[i] = getAllUsers().get(i).getUsername();
    }

    model.addAttribute("names", allusernames);

    User user = userController.getCurrently_Logged_In();
    model.addAttribute("widgets", widgetController.getAllWidgets());
    model.addAttribute("listings", marketController.getAllListings());
    Iterable<Transaction> purchases =
        transController.getUserPurchases(userController.getCurrently_Logged_In());
    model.addAttribute("purchases", purchases);
    Iterable<Transaction> soldTrans =
        transController.getUserSoldItems(userController.getCurrently_Logged_In());
    model.addAttribute("soldTrans", soldTrans);
    model.addAttribute("sellerRating", userController.getSellerRating());
    model.addAttribute("page", getPage());
    model.addAttribute("page3", getPage3());
    model.addAttribute("user", userController.getCurrently_Logged_In());

    return "homePage";
  }

  @RequestMapping({"/friend"})
  public String friend(@RequestParam("friend") String friendName, Model model) {
    setPage2("null");
    getAllUsers().clear();
    Iterable<User> allUsersIterator = userController.getAllUsers();
    allUsersIterator.iterator().forEachRemaining(u -> getAllUsers().add(u));
    String[] allusernames = new String[getAllUsers().size()];
    for (int i = 0; i < allusernames.length; i++) {
      allusernames[i] = getAllUsers().get(i).getUsername();
    }

    model.addAttribute("names", allusernames);
    User user = userController.getCurrently_Logged_In();
    UserList myList = new UserList();
    myList.setOwner(user);
    List<UserList> friends = userListController.getFriends(user);
    if (userController.getUserByUsername(friendName) != null) {
      User userFriend = userController.getUserByUsername(friendName);

      UserList[] friendsArray = friends.toArray(new UserList[friends.size()]);
      for (int i = 0; i < friendsArray.length; i++) {
        if (friendsArray[i].getFriend().getId() == userFriend.getId()) {

          setPage2("alreadyFriends");
        }
      }
      if (!(getPage2().contains("alreadyFriends"))) {
        userListController.addFriend(myList, userFriend);
        setPage2("newFriend");
      }
    } else {
      setPage2("friendNoExist");
    }
    friends = userListController.getFriends(user);
    model.addAttribute("page3", getPage3());
    model.addAttribute("page2", getPage2());
    model.addAttribute("myFriends", friends);
    model.addAttribute("widgets", widgetController.getAllWidgets());
    model.addAttribute("listings", marketController.getAllListings());
    Iterable<Transaction> purchases =
        transController.getUserPurchases(userController.getCurrently_Logged_In());
    model.addAttribute("purchases", purchases);
    Iterable<Transaction> soldTrans =
        transController.getUserSoldItems(userController.getCurrently_Logged_In());
    model.addAttribute("soldTrans", soldTrans);
    model.addAttribute("sellerRating", userController.getSellerRating());
    model.addAttribute("page", getPage());
    model.addAttribute("user", userController.getCurrently_Logged_In());
    return "homePage";
  }

  @RequestMapping({"/sendFriendMessage"})
  public String sendFriendMessage(
      @RequestParam("recipient") String name,
      @RequestParam("message") String content,
      @RequestParam("subject") String subject,
      Model model) {

    User user = userController.getCurrently_Logged_In();
    User receiver = userController.getUserByUsername(name);

    model.addAttribute("page", getPage());
    model.addAttribute("user", user);

    if (userController.getUserByUsername(name) == null) {
      setPage2("fail");
      model.addAttribute("page2", getPage2());

      return "homepage";
    }

    if (subject.length() == 0) {
      subject = "<no subject>";
    }
    if (content.length() == 0) {
      content = "<no content>";
    }
    Message message = new Message();
    message.setOwner(user);
    message.setSender(user.getUsername());
    message.setContent(content);
    message.setSubject(subject);
    message.setMsgDate();
    message.setReceiverName(name);
    message.setReceiver(receiver);
    msgcontrol.addMessage(message);
    user = userController.getCurrently_Logged_In();
    emailController.messageEmail(receiver, user, message);

    setPage2("sent");
    getAllUsers().clear();
    Iterable<User> allUsersIterator = userController.getAllUsers();
    allUsersIterator.iterator().forEachRemaining(u -> getAllUsers().add(u));
    String[] allusernames = new String[getAllUsers().size()];
    for (int i = 0; i < allusernames.length; i++) {
      allusernames[i] = getAllUsers().get(i).getUsername();
    }

    model.addAttribute("names", allusernames);

    List<UserList> friends = userListController.getFriends(user);

    model.addAttribute("page3", getPage3());
    model.addAttribute("page2", getPage2());
    model.addAttribute("myFriends", friends);
    model.addAttribute("widgets", widgetController.getAllWidgets());
    model.addAttribute("listings", marketController.getAllListings());
    Iterable<Transaction> purchases =
        transController.getUserPurchases(userController.getCurrently_Logged_In());
    model.addAttribute("purchases", purchases);
    Iterable<Transaction> soldTrans =
        transController.getUserSoldItems(userController.getCurrently_Logged_In());
    model.addAttribute("soldTrans", soldTrans);
    model.addAttribute("sellerRating", userController.getSellerRating());
    model.addAttribute("page", getPage());
    model.addAttribute("user", userController.getCurrently_Logged_In());
    return "homePage";
  }

  @RequestMapping({"/addFriend/{id}"})
  public String addAFriend(@PathVariable("id") int id, Model model) {
    setPage2("null");
    User friend = userController.getUser(id, model);
    getAllUsers().clear();
    Iterable<User> allUsersIterator = userController.getAllUsers();
    allUsersIterator.iterator().forEachRemaining(u -> getAllUsers().add(u));
    String[] allusernames = new String[getAllUsers().size()];
    for (int i = 0; i < allusernames.length; i++) {
      allusernames[i] = getAllUsers().get(i).getUsername();
    }

    model.addAttribute("names", allusernames);
    User user = userController.getCurrently_Logged_In();
    UserList myList = new UserList();
    myList.setOwner(user);
    List<UserList> friends = userListController.getFriends(user);
    if (userController.getUserByUsername(friend.getUsername()) != null) {

      UserList[] friendsArray = friends.toArray(new UserList[friends.size()]);
      for (int i = 0; i < friendsArray.length; i++) {
        if (friendsArray[i].getFriend().getId() == friend.getId()) {

          setPage2("alreadyFriends");
        }
      }
      if (!(getPage2().contains("alreadyFriends"))) {
        userListController.addFriend(myList, friend);
        setPage2("newFriend");
      }
    } else {
      setPage2("friendNoExist");
    }
    friends = userListController.getFriends(user);
    model.addAttribute("page3", getPage3());
    model.addAttribute("page2", getPage2());
    model.addAttribute("myFriends", friends);
    model.addAttribute("widgets", widgetController.getAllWidgets());
    model.addAttribute("listings", marketController.getAllListings());
    Iterable<Transaction> purchases =
        transController.getUserPurchases(userController.getCurrently_Logged_In());
    model.addAttribute("purchases", purchases);
    Iterable<Transaction> soldTrans =
        transController.getUserSoldItems(userController.getCurrently_Logged_In());
    model.addAttribute("soldTrans", soldTrans);
    model.addAttribute("sellerRating", userController.getSellerRating());
    model.addAttribute("page", getPage());
    model.addAttribute("user", userController.getCurrently_Logged_In());
    return "homePage";
  }

  @RequestMapping({"/sendMessageToFriend/{id}"})
  public String sendMessageToFriend(@PathVariable("id") int id, Model model) {
    if (getPage2() == null || !(getPage2().equals("sendPrep"))) {
      setPage2("sendPrep");
    } else {
      setPage2("null");
    }

    User friend = userController.getUser(id, model);
    getAllUsers().clear();
    Iterable<User> allUsersIterator = userController.getAllUsers();
    allUsersIterator.iterator().forEachRemaining(u -> getAllUsers().add(u));
    String[] allusernames = new String[getAllUsers().size()];
    for (int i = 0; i < allusernames.length; i++) {
      allusernames[i] = getAllUsers().get(i).getUsername();
    }

    model.addAttribute("names", allusernames);
    model.addAttribute("friend", friend);
    User user = userController.getCurrently_Logged_In();

    List<UserList> friends = userListController.getFriends(user);
    List<UserList> blocked = userListController.getBlocked(user);

    model.addAttribute("page3", getPage3());
    model.addAttribute("page2", getPage2());
    if (getPage3().equals("blockList")) {

      model.addAttribute("myBlocked", blocked);
    } else {
      model.addAttribute("myFriends", friends);
    }
    model.addAttribute("widgets", widgetController.getAllWidgets());
    model.addAttribute("listings", marketController.getAllListings());
    Iterable<Transaction> purchases =
        transController.getUserPurchases(userController.getCurrently_Logged_In());
    model.addAttribute("purchases", purchases);
    Iterable<Transaction> soldTrans =
        transController.getUserSoldItems(userController.getCurrently_Logged_In());
    model.addAttribute("soldTrans", soldTrans);
    model.addAttribute("sellerRating", userController.getSellerRating());
    model.addAttribute("page", getPage());
    model.addAttribute("user", userController.getCurrently_Logged_In());
    return "homePage";
  }

  @RequestMapping({"/removeFriend/{id}"})
  public String removeFriend(@PathVariable("id") int id, Model model) {
    setPage2("removeFriend");
    User friend = userController.getUser(id, model);
    getAllUsers().clear();
    Iterable<User> allUsersIterator = userController.getAllUsers();
    allUsersIterator.iterator().forEachRemaining(u -> getAllUsers().add(u));
    String[] allusernames = new String[getAllUsers().size()];
    for (int i = 0; i < allusernames.length; i++) {
      allusernames[i] = getAllUsers().get(i).getUsername();
    }
    User user = userController.getCurrently_Logged_In();
    if (getPage3().equals("blockList")) {
      setPage2("removeBlock");
      userListController.removeBlock(user, friend);
      List<UserList> blocked = userListController.getBlocked(user);
      model.addAttribute("myBlocked", blocked);
    } else {
      userListController.removeFriend(user, friend);
      List<UserList> friends = userListController.getFriends(user);
      model.addAttribute("myFriends", friends);
    }
    model.addAttribute("names", allusernames);

    model.addAttribute("page3", getPage3());
    model.addAttribute("page2", getPage2());

    model.addAttribute("widgets", widgetController.getAllWidgets());
    model.addAttribute("listings", marketController.getAllListings());
    Iterable<Transaction> purchases =
        transController.getUserPurchases(userController.getCurrently_Logged_In());
    model.addAttribute("purchases", purchases);
    Iterable<Transaction> soldTrans =
        transController.getUserSoldItems(userController.getCurrently_Logged_In());
    model.addAttribute("soldTrans", soldTrans);
    model.addAttribute("sellerRating", userController.getSellerRating());
    model.addAttribute("page", getPage());
    model.addAttribute("user", userController.getCurrently_Logged_In());
    return "homePage";
  }

  @RequestMapping({"/block"})
  public String block(@RequestParam("block") String blockName, Model model) {
    setPage2("null");
    getAllUsers().clear();
    Iterable<User> allUsersIterator = userController.getAllUsers();
    allUsersIterator.iterator().forEachRemaining(u -> getAllUsers().add(u));
    String[] allusernames = new String[getAllUsers().size()];
    for (int i = 0; i < allusernames.length; i++) {
      allusernames[i] = getAllUsers().get(i).getUsername();
    }

    model.addAttribute("names", allusernames);
    UserList myList = new UserList();
    User user = userController.getCurrently_Logged_In();
    myList.setOwner(user);
    List<UserList> blocked = userListController.getBlocked(user);
    if (userController.getUserByUsername(blockName) != null) {
      User blockUser = userController.getUserByUsername(blockName);

      UserList[] blockArray = blocked.toArray(new UserList[blocked.size()]);
      for (int i = 0; i < blockArray.length; i++) {
        if (blockArray[i].getBlock().getId() == blockUser.getId()) {
          setPage2("alreadyBlocked");
        }
      }
      if (!(getPage2().contains("alreadyBlocked"))) {
        userListController.addBlock(myList, blockUser);
        setPage2("newBlocked");
      }
    } else {
      setPage2("blockedNoExist");
    }
    blocked = userListController.getBlocked(user);
    model.addAttribute("page3", getPage3());
    model.addAttribute("page2", getPage2());
    model.addAttribute("myBlocked", blocked);
    model.addAttribute("widgets", widgetController.getAllWidgets());
    model.addAttribute("listings", marketController.getAllListings());
    Iterable<Transaction> purchases =
        transController.getUserPurchases(userController.getCurrently_Logged_In());
    model.addAttribute("purchases", purchases);
    Iterable<Transaction> soldTrans =
        transController.getUserSoldItems(userController.getCurrently_Logged_In());
    model.addAttribute("soldTrans", soldTrans);
    model.addAttribute("sellerRating", userController.getSellerRating());
    model.addAttribute("page", getPage());
    model.addAttribute("user", userController.getCurrently_Logged_In());
    return "homePage";
  }

  @RequestMapping("homePage")
  public String homePage(Model widgetModel, Model listingModel, String tempSearch) {
    User user = userController.getCurrently_Logged_In();
    if (user.getRole().equals("ROLE_ADMIN")
        || user.getRole().equals("ROLE_TECHNICALSERVICE")
        || user.getRole().equals("ROLE_CUSTOMERSERVICE")
        || user.getRole().equals("ROLE_SALES")
        || user.getRole().equals("ROLE_HIRINGMANAGER")
        || user.getRole().equals("ROLE_SECURITY")
        || user.getRole().equals("ROLE_ADMIN_SHADOW")
        || user.getRole().equals("ROLE_HELPDESK_ADMIN")
        || user.getRole().equals("ROLE_HELPDESK_REGULAR")) {

      return "redirect:employee";
    }

    widgetModel.addAttribute("widgets", widgetController.getAllWidgets());
    listingModel.addAttribute("listings", marketController.getAllListings());
    Iterable<Transaction> purchases =
        transController.getUserPurchases(userController.getCurrently_Logged_In());
    listingModel.addAttribute("purchases", purchases);
    Iterable<Transaction> soldTrans =
        transController.getUserSoldItems(userController.getCurrently_Logged_In());
    listingModel.addAttribute("soldTrans", soldTrans);
    listingModel.addAttribute("sellerRating", userController.getSellerRating());
    setPage("home");
    listingModel.addAttribute("page", getPage());
    widgetModel.addAttribute("page", getPage());
    listingModel.addAttribute("user", user);
    // Add filter categories
    Iterable<Appliance_Dryers> dryer = widgetController.getAllDryers();
    for (Map.Entry<String, HashSet<String>> entry :
        Appliance_Dryers.getAttributes(dryer).entrySet()) {
      widgetModel.addAttribute(entry.getKey(), entry.getValue());
    }
    widgetModel.addAttribute("dryer", dryer);

    Iterable<Appliance_Washers> washer = widgetController.getAllWashers();
    for (Map.Entry<String, HashSet<String>> entry :
        Appliance_Washers.getAttributes(washer).entrySet()) {
      widgetModel.addAttribute(entry.getKey(), entry.getValue());
    }
    widgetModel.addAttribute("washer", washer);

    Iterable<Appliance_Microwave> microwave = widgetController.getAllMicrowaves();
    for (Map.Entry<String, HashSet<String>> entry :
        Appliance_Microwave.getAttributes(microwave).entrySet()) {
      widgetModel.addAttribute(entry.getKey(), entry.getValue());
    }
    widgetModel.addAttribute("microwave", microwave);

    Iterable<Appliance_Refrigerator> fridge = widgetController.getAllRefrigerators();
    for (Map.Entry<String, HashSet<String>> entry :
        Appliance_Refrigerator.getAttributes(fridge).entrySet()) {
      widgetModel.addAttribute(entry.getKey(), entry.getValue());
    }
    widgetModel.addAttribute("fridge", fridge);

    Iterable<Electronics_Computers> computer = widgetController.getAllComputers();
    for (Map.Entry<String, HashSet<String>> entry :
        Electronics_Computers.getAttributes(computer).entrySet()) {
      widgetModel.addAttribute(entry.getKey(), entry.getValue());
    }
    widgetModel.addAttribute("computer", computer);

    Iterable<Electronics_VideoGames> videoGame = widgetController.getAllVideoGames();
    for (Map.Entry<String, HashSet<String>> entry :
        Electronics_VideoGames.getAttributes(videoGame).entrySet()) {
      widgetModel.addAttribute(entry.getKey(), entry.getValue());
    }
    widgetModel.addAttribute("videoGame", videoGame);

    Iterable<Vehicle_Car> car = widgetController.getAllCars();
    for (Map.Entry<String, HashSet<String>> entry : Vehicle_Car.getAttributes(car).entrySet()) {
      widgetModel.addAttribute(entry.getKey(), entry.getValue());
    }
    widgetModel.addAttribute("car", car);

    Iterable<LawnCare_LawnMower> lawnMower = widgetController.getAllMowers();
    for (Map.Entry<String, HashSet<String>> entry :
        LawnCare_LawnMower.getAttributes(lawnMower).entrySet()) {
      widgetModel.addAttribute(entry.getKey(), entry.getValue());
    }
    widgetModel.addAttribute("lawnMower", lawnMower);

    return "homePage";
  }

  @RequestMapping("search")
  public String search(
      @RequestParam("searchString") String searchString,
      @RequestParam("operator") String operator,
      @RequestParam("price") String price,
      @RequestParam("category") String category,
      @RequestParam("subCategory") String subCategory,
      @RequestParam("length") String length,
      @RequestParam("width") String width,
      @RequestParam("height") String height,
      @RequestParam("color") String color,
      @RequestParam("condition") String condition,
      @RequestParam("model") String tempModel,
      @RequestParam("make") String make,
      @RequestParam("memory") String memory,
      @RequestParam("storage") String storage,
      @RequestParam("processor") String processor,
      @RequestParam("GPU") String gpu,
      @RequestParam("developer") String developer,
      @RequestParam("gameTitle") String gameTitle,
      @RequestParam("console") String console,
      @RequestParam("yardSize") String yardSize,
      @RequestParam("powerSource") String powerSource,
      @RequestParam("year") String year,
      @RequestParam("brand") String brand,
      Model model,
      Model listingModel,
      Widget tempWidget,
      Model searchModel,
      MarketListing tempListing) {
    User user = userController.getCurrently_Logged_In();
    listingModel.addAttribute("sellerRating", userController.getSellerRating());
    List<Widget> widgets = new ArrayList<Widget>();
    List<MarketListing> listings = new ArrayList<MarketListing>();
    List<Widget> allWidgets = new ArrayList<Widget>();
    List<MarketListing> allListings = new ArrayList<MarketListing>();

    allWidgets =
        StreamSupport.stream(widgetController.getAllWidgets().spliterator(), true)
            .collect(Collectors.toList());
    allListings =
        StreamSupport.stream(marketController.getAllListings().spliterator(), true)
            .collect(Collectors.toList());
    // Add filter categories
    Iterable<Appliance_Dryers> dryer = widgetController.getAllDryers();
    for (Map.Entry<String, HashSet<String>> entry :
        Appliance_Dryers.getAttributes(dryer).entrySet()) {
      model.addAttribute(entry.getKey(), entry.getValue());
    }
    model.addAttribute("dryer", dryer);

    Iterable<Appliance_Washers> washer = widgetController.getAllWashers();
    for (Map.Entry<String, HashSet<String>> entry :
        Appliance_Washers.getAttributes(washer).entrySet()) {
      model.addAttribute(entry.getKey(), entry.getValue());
    }
    model.addAttribute("washer", washer);

    Iterable<Appliance_Microwave> microwave = widgetController.getAllMicrowaves();
    for (Map.Entry<String, HashSet<String>> entry :
        Appliance_Microwave.getAttributes(microwave).entrySet()) {
      model.addAttribute(entry.getKey(), entry.getValue());
    }
    model.addAttribute("microwave", microwave);

    Iterable<Appliance_Refrigerator> fridge = widgetController.getAllRefrigerators();
    for (Map.Entry<String, HashSet<String>> entry :
        Appliance_Refrigerator.getAttributes(fridge).entrySet()) {
      model.addAttribute(entry.getKey(), entry.getValue());
    }
    model.addAttribute("fridge", fridge);

    Iterable<Electronics_Computers> computer = widgetController.getAllComputers();
    for (Map.Entry<String, HashSet<String>> entry :
        Electronics_Computers.getAttributes(computer).entrySet()) {
      model.addAttribute(entry.getKey(), entry.getValue());
    }
    model.addAttribute("computer", computer);

    Iterable<Electronics_VideoGames> videoGame = widgetController.getAllVideoGames();
    for (Map.Entry<String, HashSet<String>> entry :
        Electronics_VideoGames.getAttributes(videoGame).entrySet()) {
      model.addAttribute(entry.getKey(), entry.getValue());
    }
    model.addAttribute("videoGame", videoGame);

    Iterable<Vehicle_Car> car = widgetController.getAllCars();
    for (Map.Entry<String, HashSet<String>> entry : Vehicle_Car.getAttributes(car).entrySet()) {
      model.addAttribute(entry.getKey(), entry.getValue());
    }
    model.addAttribute("car", car);

    Iterable<LawnCare_LawnMower> lawnMower = widgetController.getAllMowers();
    for (Map.Entry<String, HashSet<String>> entry :
        LawnCare_LawnMower.getAttributes(lawnMower).entrySet()) {
      model.addAttribute(entry.getKey(), entry.getValue());
    }
    model.addAttribute("lawnMower", lawnMower);

    model.addAttribute("car", widgetController.getAllCars());
    model.addAttribute("lawnMower", widgetController.getAllMowers());
    model.addAttribute("computer", widgetController.getAllComputers());
    model.addAttribute("videoGame", widgetController.getAllVideoGames());
    model.addAttribute("widgets", widgetController.getAllWidgets());
    listingModel.addAttribute("listings", marketController.getAllListings());
    BigDecimal bigPrice = new BigDecimal("0.00");
    try {
      System.out.println(searchString);
    } catch (Exception e) {

    }
    try {
      System.out.println(operator);
    } catch (Exception e) {

    }
    try {
      bigPrice = BigDecimal.valueOf(Double.valueOf(price));
    } catch (Exception e) {

    }

    if (category.contentEquals("all")) {
      if (searchString.isBlank() && price.isBlank()) {

        widgets =
            this.onlyCategory(tempWidget, tempListing, category, widgets, allWidgets, allListings);

        try {
          searchModel.addAttribute("searchWidgets", widgets);
        } catch (Exception e) {
          System.out.println("No widgets");
        }
      }
      if (!searchString.isBlank() && price.isBlank()) {

        widgets =
            this.stringOnly(
                tempWidget, widgets, tempListing, searchString, category, allWidgets, allListings);

        try {
          searchModel.addAttribute("searchWidgets", widgets);
        } catch (Exception e) {
          System.out.println("No widgets");
        }

      } else if (searchString.isBlank() && !price.isBlank()) {
        System.out.println("only price");
        model.addAttribute("operator", operator);
        model.addAttribute("price", price);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());

        widgets =
            this.priceOnly(
                tempWidget,
                widgets,
                tempListing,
                searchString,
                operator,
                bigPrice,
                category,
                allWidgets,
                allListings);
        searchModel.addAttribute("searchWidgets", widgets);
      } else if (!searchString.isBlank() && !price.isBlank()) {
        System.out.println("both");
        model.addAttribute("searchString", searchString);
        model.addAttribute("operator", operator);
        model.addAttribute("price", price);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());

        widgets =
            this.stringAndPrice(
                tempWidget,
                widgets,
                tempListing,
                searchString,
                operator,
                bigPrice,
                category,
                allWidgets,
                allListings);
        searchModel.addAttribute("searchWidgets", widgets);
      }
    }

    if (category.contentEquals("appliance")) {
      if (subCategory.contentEquals("washer")) {
        width = width.replace(",", "");
        length = length.replace(",", "");
        height = height.replace(",", "");
        color = color.replace(",", "");
        condition = condition.replace(",", "");
        brand = brand.replace(",", "");
        tempModel = tempModel.replace(",", "");
        if (!length.contentEquals("")
            || !width.contentEquals("")
            || !height.contentEquals("")
            || !color.contentEquals("")
            || !condition.contentEquals("")
            || !brand.contentEquals("")
            || !tempModel.contentEquals("")) {
          allListings.clear();
          allWidgets.clear();
        }
        List<Appliance_Washers> washers = new ArrayList<Appliance_Washers>();
        washers = (List<Appliance_Washers>) widgetController.getAllWashers();
        System.out.println(washers.size());

        if (!length.contentEquals("")) {
          int tempLength = Integer.parseInt(length);
          washers =
              washers.stream()
                  .filter(wash_widget -> wash_widget.getLength() == tempLength)
                  .collect(Collectors.toList());
          System.out.println(washers.size());
          for (int i = 0; i < washers.size(); i++) {
            long tempLong = washers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!width.contentEquals("")) {
          System.out.println(width);
          int tempWidth = Integer.parseInt(width);
          washers =
              washers.stream()
                  .filter(wash_widget -> wash_widget.getWidth() == tempWidth)
                  .collect(Collectors.toList());
          System.out.println(washers.size());
          for (int i = 0; i < washers.size(); i++) {
            long tempLong = washers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!height.contentEquals("")) {
          int tempHeight = Integer.parseInt(height);
          washers =
              washers.stream()
                  .filter(wash_widget -> wash_widget.getHeight() == tempHeight)
                  .collect(Collectors.toList());
          System.out.println(washers.size());
          for (int i = 0; i < washers.size(); i++) {
            long tempLong = washers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!color.contentEquals("")) {
          String tempColor = color;
          washers =
              washers.stream()
                  .filter(wash_widget -> wash_widget.getColor().contentEquals(tempColor))
                  .collect(Collectors.toList());
          System.out.println(washers.size());
          for (int i = 0; i < washers.size(); i++) {
            long tempLong = washers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!condition.contentEquals("")) {
          String tempCondition = condition;
          washers =
              washers.stream()
                  .filter(wash_widget -> wash_widget.getCondition().contentEquals(tempCondition))
                  .collect(Collectors.toList());
          System.out.println(washers.size());
          for (int i = 0; i < washers.size(); i++) {
            long tempLong = washers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!brand.contentEquals("")) {
          String tempBrand = brand;
          washers =
              washers.stream()
                  .filter(wash_widget -> wash_widget.getBrand().contentEquals(tempBrand))
                  .collect(Collectors.toList());
          System.out.println(washers.size());
          for (int i = 0; i < washers.size(); i++) {
            long tempLong = washers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!tempModel.contentEquals("")) {
          String tModel = tempModel;
          washers =
              washers.stream()
                  .filter(wash_widget -> wash_widget.getModel().contentEquals(tModel))
                  .collect(Collectors.toList());
          System.out.println(washers.size());
          for (int i = 0; i < washers.size(); i++) {
            long tempLong = washers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }
      }
      if (subCategory.contentEquals("dryer")) {
        width = width.replace(",", "");
        length = length.replace(",", "");
        height = height.replace(",", "");
        color = color.replace(",", "");
        condition = condition.replace(",", "");
        brand = brand.replace(",", "");
        tempModel = tempModel.replace(",", "");
        if (!length.contentEquals("")
            || !width.contentEquals("")
            || !height.contentEquals("")
            || !color.contentEquals("")
            || !condition.contentEquals("")
            || !brand.contentEquals("")
            || !tempModel.contentEquals("")) {
          allListings.clear();
          allWidgets.clear();
        }
        List<Appliance_Dryers> dryers = new ArrayList<Appliance_Dryers>();
        dryers = (List<Appliance_Dryers>) widgetController.getAllDryers();

        if (!length.contentEquals("")) {
          int tempLength = Integer.parseInt(length);
          dryers =
              dryers.stream()
                  .filter(dry_widget -> dry_widget.getLength() == tempLength)
                  .collect(Collectors.toList());
          System.out.println(dryers.size());
          for (int i = 0; i < dryers.size(); i++) {
            long tempLong = dryers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!width.contentEquals("")) {
          System.out.println(width);
          int tempWidth = Integer.parseInt(width);
          dryers =
              dryers.stream()
                  .filter(dry_widget -> dry_widget.getWidth() == tempWidth)
                  .collect(Collectors.toList());
          System.out.println(dryers.size());
          for (int i = 0; i < dryers.size(); i++) {
            long tempLong = dryers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!height.contentEquals("")) {
          int tempHeight = Integer.parseInt(height);
          dryers =
              dryers.stream()
                  .filter(dry_widget -> dry_widget.getHeight() == tempHeight)
                  .collect(Collectors.toList());
          System.out.println(dryers.size());
          for (int i = 0; i < dryers.size(); i++) {
            long tempLong = dryers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!color.contentEquals("")) {
          String tempColor = color;
          dryers =
              dryers.stream()
                  .filter(dry_widget -> dry_widget.getColor().contentEquals(tempColor))
                  .collect(Collectors.toList());
          System.out.println(dryers.size());
          for (int i = 0; i < dryers.size(); i++) {
            long tempLong = dryers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!condition.contentEquals("")) {
          String tempCondition = condition;
          dryers =
              dryers.stream()
                  .filter(dry_widget -> dry_widget.getCondition().contentEquals(tempCondition))
                  .collect(Collectors.toList());
          System.out.println(dryers.size());
          for (int i = 0; i < dryers.size(); i++) {
            long tempLong = dryers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!brand.contentEquals("")) {
          String tempBrand = brand;
          dryers =
              dryers.stream()
                  .filter(dry_widget -> dry_widget.getBrand().contentEquals(tempBrand))
                  .collect(Collectors.toList());
          System.out.println(dryers.size());
          for (int i = 0; i < dryers.size(); i++) {
            long tempLong = dryers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!tempModel.contentEquals("")) {
          String tModel = tempModel;
          dryers =
              dryers.stream()
                  .filter(dry_widget -> dry_widget.getModel().contentEquals(tModel))
                  .collect(Collectors.toList());
          System.out.println(dryers.size());
          for (int i = 0; i < dryers.size(); i++) {
            long tempLong = dryers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }
      }
      if (subCategory.contentEquals("microwave")) {
        width = width.replace(",", "");
        length = length.replace(",", "");
        height = height.replace(",", "");
        color = color.replace(",", "");
        condition = condition.replace(",", "");
        brand = brand.replace(",", "");
        tempModel = tempModel.replace(",", "");
        if (!length.contentEquals("")
            || !width.contentEquals("")
            || !height.contentEquals("")
            || !color.contentEquals("")
            || !condition.contentEquals("")
            || !brand.contentEquals("")
            || !tempModel.contentEquals("")) {
          allListings.clear();
          allWidgets.clear();
        }
        List<Appliance_Microwave> microwaves = new ArrayList<Appliance_Microwave>();
        microwaves = (List<Appliance_Microwave>) widgetController.getAllMicrowaves();

        if (!length.contentEquals("")) {
          int tempLength = Integer.parseInt(length);
          microwaves =
              microwaves.stream()
                  .filter(micro_widget -> micro_widget.getLength() == tempLength)
                  .collect(Collectors.toList());
          System.out.println(microwaves.size());
          for (int i = 0; i < microwaves.size(); i++) {
            long tempLong = microwaves.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!width.contentEquals("")) {
          System.out.println(width);
          int tempWidth = Integer.parseInt(width);
          microwaves =
              microwaves.stream()
                  .filter(micro_widget -> micro_widget.getWidth() == tempWidth)
                  .collect(Collectors.toList());
          System.out.println(microwaves.size());
          for (int i = 0; i < microwaves.size(); i++) {
            long tempLong = microwaves.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!height.contentEquals("")) {
          int tempHeight = Integer.parseInt(height);
          microwaves =
              microwaves.stream()
                  .filter(micro_widget -> micro_widget.getHeight() == tempHeight)
                  .collect(Collectors.toList());
          System.out.println(microwaves.size());
          for (int i = 0; i < microwaves.size(); i++) {
            long tempLong = microwaves.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!color.contentEquals("")) {
          String tempColor = color;
          microwaves =
              microwaves.stream()
                  .filter(micro_widget -> micro_widget.getColor().contentEquals(tempColor))
                  .collect(Collectors.toList());
          System.out.println(microwaves.size());
          for (int i = 0; i < microwaves.size(); i++) {
            long tempLong = microwaves.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!condition.contentEquals("")) {
          String tempCondition = condition;
          microwaves =
              microwaves.stream()
                  .filter(micro_widget -> micro_widget.getCondition().contentEquals(tempCondition))
                  .collect(Collectors.toList());
          System.out.println(microwaves.size());
          for (int i = 0; i < microwaves.size(); i++) {
            long tempLong = microwaves.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!brand.contentEquals("")) {
          String tempBrand = brand;
          microwaves =
              microwaves.stream()
                  .filter(micro_widget -> micro_widget.getBrand().contentEquals(tempBrand))
                  .collect(Collectors.toList());
          System.out.println(microwaves.size());
          for (int i = 0; i < microwaves.size(); i++) {
            long tempLong = microwaves.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!tempModel.contentEquals("")) {
          String tModel = tempModel;
          microwaves =
              microwaves.stream()
                  .filter(micro_widget -> micro_widget.getModel().contentEquals(tModel))
                  .collect(Collectors.toList());
          System.out.println(microwaves.size());
          for (int i = 0; i < microwaves.size(); i++) {
            long tempLong = microwaves.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }
      }
      if (subCategory.contentEquals("fridge")) {
        width = width.replace(",", "");
        length = length.replace(",", "");
        height = height.replace(",", "");
        color = color.replace(",", "");
        condition = condition.replace(",", "");
        brand = brand.replace(",", "");
        tempModel = tempModel.replace(",", "");
        if (!length.contentEquals("")
            || !width.contentEquals("")
            || !height.contentEquals("")
            || !color.contentEquals("")
            || !condition.contentEquals("")
            || !brand.contentEquals("")
            || !tempModel.contentEquals("")) {
          allListings.clear();
          allWidgets.clear();
        }

        List<Appliance_Refrigerator> fridges = new ArrayList<Appliance_Refrigerator>();
        fridges = (List<Appliance_Refrigerator>) widgetController.getAllRefrigerators();

        if (!length.contentEquals("")) {
          int tempLength = Integer.parseInt(length);
          fridges =
              fridges.stream()
                  .filter(fridge_widget -> fridge_widget.getLength() == tempLength)
                  .collect(Collectors.toList());
          System.out.println(fridges.size());
          for (int i = 0; i < fridges.size(); i++) {
            long tempLong = fridges.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!width.contentEquals("")) {
          System.out.println(width);
          int tempWidth = Integer.parseInt(width);
          fridges =
              fridges.stream()
                  .filter(fridge_widget -> fridge_widget.getWidth() == tempWidth)
                  .collect(Collectors.toList());
          System.out.println(fridges.size());
          for (int i = 0; i < fridges.size(); i++) {
            long tempLong = fridges.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!height.contentEquals("")) {
          int tempHeight = Integer.parseInt(height);
          fridges =
              fridges.stream()
                  .filter(fridge_widget -> fridge_widget.getHeight() == tempHeight)
                  .collect(Collectors.toList());
          System.out.println(fridges.size());
          for (int i = 0; i < fridges.size(); i++) {
            long tempLong = fridges.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!color.contentEquals("")) {
          String tempColor = color;
          fridges =
              fridges.stream()
                  .filter(fridge_widget -> fridge_widget.getColor().contentEquals(tempColor))
                  .collect(Collectors.toList());
          System.out.println(fridges.size());
          for (int i = 0; i < fridges.size(); i++) {
            long tempLong = fridges.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!condition.contentEquals("")) {
          String tempCondition = condition;
          fridges =
              fridges.stream()
                  .filter(
                      fridge_widget -> fridge_widget.getCondition().contentEquals(tempCondition))
                  .collect(Collectors.toList());
          System.out.println(fridges.size());
          for (int i = 0; i < fridges.size(); i++) {
            long tempLong = fridges.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!brand.contentEquals("")) {
          String tempBrand = brand;
          fridges =
              fridges.stream()
                  .filter(fridge_widget -> fridge_widget.getBrand().contentEquals(tempBrand))
                  .collect(Collectors.toList());
          System.out.println(fridges.size());
          for (int i = 0; i < fridges.size(); i++) {
            long tempLong = fridges.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!tempModel.contentEquals("")) {
          String tModel = tempModel;
          fridges =
              fridges.stream()
                  .filter(fridge_widget -> fridge_widget.getModel().contentEquals(tModel))
                  .collect(Collectors.toList());
          System.out.println(fridges.size());
          for (int i = 0; i < fridges.size(); i++) {
            long tempLong = fridges.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }
      }
      if (searchString.isBlank() && price.isBlank()) {
        System.out.println("only category");
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.onlyCategory(tempWidget, tempListing, category, widgets, allWidgets, allListings);
        try {
          searchModel.addAttribute("searchWidgets", widgets);
        } catch (Exception e) {
          System.out.println("No widgets");
        }
      }
      if (!searchString.isBlank() && price.isBlank()) {
        System.out.println("only search string");
        model.addAttribute("searchString", searchString);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.stringOnly(
                tempWidget, widgets, tempListing, searchString, category, allWidgets, allListings);
        searchModel.addAttribute("searchWidgets", widgets);
      } else if (searchString.isBlank() && !price.isBlank()) {
        System.out.println("only price");
        model.addAttribute("operator", operator);
        model.addAttribute("price", price);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.priceOnly(
                tempWidget,
                widgets,
                tempListing,
                searchString,
                operator,
                bigPrice,
                category,
                allWidgets,
                allListings);
        searchModel.addAttribute("searchWidgets", widgets);
      } else if (!searchString.isBlank() && !price.isBlank()) {
        System.out.println("both");
        model.addAttribute("searchString", searchString);
        model.addAttribute("operator", operator);
        model.addAttribute("price", price);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.stringAndPrice(
                tempWidget,
                widgets,
                tempListing,
                searchString,
                operator,
                bigPrice,
                category,
                allWidgets,
                allListings);
        searchModel.addAttribute("searchWidgets", widgets);
      }
    }

    if (category.contentEquals("electronic")) {
      if (subCategory.contentEquals("videoGame")) {
        developer = developer.replace(",", "");
        condition = condition.replace(",", "");
        console = console.replace(",", "");
        gameTitle = gameTitle.replace(",", "");
        if (!console.contentEquals("")
            || !developer.contentEquals("")
            || !condition.contentEquals("")
            || !gameTitle.contentEquals("")) {
          allListings.clear();
          allWidgets.clear();
        }

        List<Electronics_VideoGames> videoGames = new ArrayList<Electronics_VideoGames>();
        videoGames = (List<Electronics_VideoGames>) widgetController.getAllVideoGames();

        if (!console.contentEquals("")) {
          String tempConsole = console;
          videoGames =
              videoGames.stream()
                  .filter(game_widget -> game_widget.getConsole().contentEquals(tempConsole))
                  .collect(Collectors.toList());
          System.out.println(videoGames.size());
          for (int i = 0; i < videoGames.size(); i++) {
            long tempLong = videoGames.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!developer.contentEquals("")) {
          String tempDev = developer;
          videoGames =
              videoGames.stream()
                  .filter(game_widget -> game_widget.getDeveloper().contentEquals(tempDev))
                  .collect(Collectors.toList());
          System.out.println(videoGames.size());
          for (int i = 0; i < videoGames.size(); i++) {
            long tempLong = videoGames.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!developer.contentEquals("")) {
          String tempCondition = condition;
          videoGames =
              videoGames.stream()
                  .filter(game_widget -> game_widget.getCondition().contentEquals(tempCondition))
                  .collect(Collectors.toList());
          System.out.println(videoGames.size());
          for (int i = 0; i < videoGames.size(); i++) {
            long tempLong = videoGames.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!developer.contentEquals("")) {
          String tempTitle = gameTitle;
          videoGames =
              videoGames.stream()
                  .filter(game_widget -> game_widget.getTitle().contentEquals(tempTitle))
                  .collect(Collectors.toList());
          System.out.println(videoGames.size());
          for (int i = 0; i < videoGames.size(); i++) {
            long tempLong = videoGames.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }
      }
      if (subCategory.contentEquals("computer")) {
        memory = memory.replace(",", "");
        gpu = gpu.replace(",", "");
        processor = processor.replace(",", "");
        storage = storage.replace(",", "");
        if (!memory.contentEquals("")
            || !gpu.contentEquals("")
            || !processor.contentEquals("")
            || !storage.contentEquals("")) {
          allListings.clear();
          allWidgets.clear();
        }

        List<Electronics_Computers> computers = new ArrayList<Electronics_Computers>();
        computers = (List<Electronics_Computers>) widgetController.getAllComputers();

        if (!memory.contentEquals("")) {
          String tempMem = memory;
          computers =
              computers.stream()
                  .filter(comp_widget -> comp_widget.getMemory().contentEquals(tempMem))
                  .collect(Collectors.toList());
          System.out.println(computers.size());
          for (int i = 0; i < computers.size(); i++) {
            long tempLong = computers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!gpu.contentEquals("")) {
          String tempGpu = gpu;
          computers =
              computers.stream()
                  .filter(comp_widget -> comp_widget.getGpu().contentEquals(tempGpu))
                  .collect(Collectors.toList());
          System.out.println(computers.size());
          for (int i = 0; i < computers.size(); i++) {
            long tempLong = computers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!processor.contentEquals("")) {
          String tempProcessor = processor;
          computers =
              computers.stream()
                  .filter(comp_widget -> comp_widget.getProcessor().contentEquals(tempProcessor))
                  .collect(Collectors.toList());
          System.out.println(computers.size());
          for (int i = 0; i < computers.size(); i++) {
            long tempLong = computers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!storage.contentEquals("")) {
          String tempStorage = storage;
          computers =
              computers.stream()
                  .filter(comp_widget -> comp_widget.getStorage().contentEquals(tempStorage))
                  .collect(Collectors.toList());
          System.out.println(computers.size());
          for (int i = 0; i < computers.size(); i++) {
            long tempLong = computers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }
      }
      if (searchString.isBlank() && price.isBlank()) {
        System.out.println("only category");
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.onlyCategory(tempWidget, tempListing, category, widgets, allWidgets, allListings);
        try {
          searchModel.addAttribute("searchWidgets", widgets);
        } catch (Exception e) {
          System.out.println("No widgets");
        }
      }
      if (!searchString.isBlank() && price.isBlank()) {
        System.out.println("only search string");
        model.addAttribute("searchString", searchString);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.stringOnly(
                tempWidget, widgets, tempListing, searchString, category, allWidgets, allListings);

        searchModel.addAttribute("searchWidgets", widgets);
      } else if (searchString.isBlank() && !price.isBlank()) {
        System.out.println("only price");
        model.addAttribute("operator", operator);
        model.addAttribute("price", price);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.priceOnly(
                tempWidget,
                widgets,
                tempListing,
                searchString,
                operator,
                bigPrice,
                category,
                allWidgets,
                allListings);
        searchModel.addAttribute("searchWidgets", widgets);
      } else if (!searchString.isBlank() && !price.isBlank()) {
        System.out.println("both");
        model.addAttribute("searchString", searchString);
        model.addAttribute("operator", operator);
        model.addAttribute("price", price);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.stringAndPrice(
                tempWidget,
                widgets,
                tempListing,
                searchString,
                operator,
                bigPrice,
                category,
                allWidgets,
                allListings);
        searchModel.addAttribute("searchWidgets", widgets);
      }
    }

    if (category.contentEquals("lawnCare")) {
      if (subCategory.contentEquals("lawnMower")) {
        yardSize = yardSize.replace(",", "");
        brand = brand.replace(",", "");
        powerSource = powerSource.replace(",", "");
        if (!brand.contentEquals("")
            || !yardSize.contentEquals("")
            || !powerSource.contentEquals("")) {
          allListings.clear();
          allWidgets.clear();
        }
        List<LawnCare_LawnMower> mowers = new ArrayList<LawnCare_LawnMower>();
        mowers = (List<LawnCare_LawnMower>) widgetController.getAllMowers();

        if (!yardSize.contentEquals("")) {
          String tempYardSize = yardSize;
          mowers =
              mowers.stream()
                  .filter(mower_widget -> mower_widget.getYardSize().contentEquals(tempYardSize))
                  .collect(Collectors.toList());
          System.out.println(mowers.size());
          for (int i = 0; i < mowers.size(); i++) {
            long tempLong = mowers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!brand.contentEquals("")) {
          String tempBrand = brand;
          mowers =
              mowers.stream()
                  .filter(mower_widget -> mower_widget.getBrand().contentEquals(tempBrand))
                  .collect(Collectors.toList());
          System.out.println(mowers.size());
          for (int i = 0; i < mowers.size(); i++) {
            long tempLong = mowers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }

        if (!powerSource.contentEquals("")) {
          String tempPowerSource = powerSource;
          mowers =
              mowers.stream()
                  .filter(
                      mower_widget -> mower_widget.getPowerSource().contentEquals(tempPowerSource))
                  .collect(Collectors.toList());
          System.out.println(mowers.size());
          for (int i = 0; i < mowers.size(); i++) {
            long tempLong = mowers.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }
      }
      if (searchString.isBlank() && price.isBlank()) {
        System.out.println("only category");
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        int id = 0;
        tempListing = allListings.get(id);
        tempWidget = allWidgets.get(id);
        widgets =
            this.onlyCategory(tempWidget, tempListing, category, widgets, allWidgets, allListings);
        try {
          searchModel.addAttribute("searchWidgets", widgets);
        } catch (Exception e) {
          System.out.println("No widgets");
        }
      }
      if (!searchString.isBlank() && price.isBlank()) {
        System.out.println("only search string");
        model.addAttribute("searchString", searchString);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.stringOnly(
                tempWidget, widgets, tempListing, searchString, category, allWidgets, allListings);

        searchModel.addAttribute("searchWidgets", widgets);
      } else if (searchString.isBlank() && !price.isBlank()) {
        System.out.println("only price");
        model.addAttribute("operator", operator);
        model.addAttribute("price", price);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.priceOnly(
                tempWidget,
                widgets,
                tempListing,
                searchString,
                operator,
                bigPrice,
                category,
                allWidgets,
                allListings);
        searchModel.addAttribute("searchWidgets", widgets);
      } else if (!searchString.isBlank() && !price.isBlank()) {
        System.out.println("both");
        model.addAttribute("searchString", searchString);
        model.addAttribute("operator", operator);
        model.addAttribute("price", price);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.stringAndPrice(
                tempWidget,
                widgets,
                tempListing,
                searchString,
                operator,
                bigPrice,
                category,
                allWidgets,
                allListings);
        searchModel.addAttribute("searchWidgets", widgets);
      }
    }

    if (category.contentEquals("vehicle")) {
      if (subCategory.contentEquals("car")) {
        make = make.replace(",", "");
        tempModel = tempModel.replace(",", "");
        year = year.replace(",", "");
        if (!make.contentEquals("") || !tempModel.contentEquals("") || !year.contentEquals("")) {
          allListings.clear();
          allWidgets.clear();
        }

        List<Vehicle_Car> cars = new ArrayList<Vehicle_Car>();
        cars = (List<Vehicle_Car>) widgetController.getAllCars();
        if (!make.contentEquals("")) {
          String tempPowerSource = make;
          cars =
              cars.stream()
                  .filter(car_widget -> car_widget.getMake().contentEquals(tempPowerSource))
                  .collect(Collectors.toList());
          System.out.println(cars.size());
          for (int i = 0; i < cars.size(); i++) {
            long tempLong = cars.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }
        if (!tempModel.contentEquals("")) {
          String tempCarModel = tempModel;
          cars =
              cars.stream()
                  .filter(car_widget -> car_widget.getModel().contentEquals(tempCarModel))
                  .collect(Collectors.toList());
          System.out.println(cars.size());
          for (int i = 0; i < cars.size(); i++) {
            long tempLong = cars.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }
        if (!year.contentEquals("")) {
          int tempYear = Integer.parseInt(year);
          cars =
              cars.stream()
                  .filter(car_widget -> car_widget.getYear() == tempYear)
                  .collect(Collectors.toList());
          System.out.println(cars.size());
          for (int i = 0; i < cars.size(); i++) {
            long tempLong = cars.get(i).getId();
            allWidgets.add(widgetController.getWidget(tempLong));
            allListings.add(
                marketController.getListingByWidget(widgetController.getWidget(tempLong)));
          }
        }
      }
      if (searchString.isBlank() && price.isBlank()) {
        System.out.println("only category");
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.onlyCategory(tempWidget, tempListing, category, widgets, allWidgets, allListings);
        try {
          searchModel.addAttribute("searchWidgets", widgets);
        } catch (Exception e) {
          System.out.println("No widgets");
        }
      }
      if (!searchString.isBlank() && price.isBlank()) {
        System.out.println("only search string");
        model.addAttribute("searchString", searchString);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.stringOnly(
                tempWidget, widgets, tempListing, searchString, category, allWidgets, allListings);

        searchModel.addAttribute("searchWidgets", widgets);
      } else if (searchString.isBlank() && !price.isBlank()) {
        System.out.println("only price");
        model.addAttribute("operator", operator);
        model.addAttribute("price", price);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.priceOnly(
                tempWidget,
                widgets,
                tempListing,
                searchString,
                operator,
                bigPrice,
                category,
                allWidgets,
                allListings);
        searchModel.addAttribute("searchWidgets", widgets);
      } else if (!searchString.isBlank() && !price.isBlank()) {
        System.out.println("both");
        model.addAttribute("searchString", searchString);
        model.addAttribute("operator", operator);
        model.addAttribute("price", price);
        model.addAttribute("widgets", widgetController.getAllWidgets());
        listingModel.addAttribute("listings", marketController.getAllListings());
        widgets =
            this.stringAndPrice(
                tempWidget,
                widgets,
                tempListing,
                searchString,
                operator,
                bigPrice,
                category,
                allWidgets,
                allListings);
        searchModel.addAttribute("searchWidgets", widgets);
      }
    }

    return "searchResult";
  }

  public List<Widget> onlyCategory(
      Widget tempWidget,
      MarketListing tempListing,
      String category,
      List<Widget> widgets,
      List<Widget> allWidgets,
      List<MarketListing> allListings) {
    int id = 0;
    if (!allWidgets.isEmpty()) {
      tempListing = allListings.get(id);
      tempWidget = tempListing.getWidgetSold();
    }
    while (tempWidget != null) {
      if ((tempListing != null
              && !tempListing.isDeleted()
              && tempWidget.getCategory().contentEquals(category))
          || (tempListing != null && !tempListing.isDeleted() && category.contentEquals("all"))) {
        System.out.println(tempWidget.getName());
        widgets.add(tempWidget);
      }

      id++;
      try {
        tempListing = allListings.get(id);
        tempWidget = tempListing.getWidgetSold();
      } catch (Exception e) {
        break;
      }
    }
    return widgets;
  }

  public List<Widget> stringOnly(
      Widget tempWidget,
      List<Widget> widgets,
      MarketListing tempListing,
      String searchString,
      String category,
      List<Widget> allWidgets,
      List<MarketListing> allListings) {
    int id = 0;
    if (!allWidgets.isEmpty()) {
      tempListing = allListings.get(id);
      tempWidget = tempListing.getWidgetSold();
    }

    while (tempWidget != null) {
      tempWidget = allWidgets.get(id);
      if (((tempWidget.getName().contains(searchString)
              || tempWidget.getName().toUpperCase().contains(searchString)
              || tempWidget.getName().toLowerCase().contains(searchString)
              || tempWidget.getDescription().contains(searchString)
              || tempWidget.getDescription().toUpperCase().contains(searchString)
              || tempWidget.getDescription().toLowerCase().contains(searchString)))
          && (tempWidget.getCategory().contentEquals(category) || category.contentEquals("all"))) {
        if (tempListing != null && !tempListing.isDeleted()) {
          widgets.add(tempWidget);
        }
      }
      id++;
      try {
        tempListing = allListings.get(id);
        tempWidget = tempListing.getWidgetSold();
      } catch (Exception e) {
        break;
      }
    }
    return widgets;
  }

  public List<Widget> priceOnly(
      Widget tempWidget,
      List<Widget> widgets,
      MarketListing tempListing,
      String searchString,
      String operator,
      BigDecimal bigPrice,
      String category,
      List<Widget> allWidgets,
      List<MarketListing> allListings) {
    int id = 0;
    if (!allWidgets.isEmpty()) {
      tempListing = allListings.get(id);
      tempWidget = tempListing.getWidgetSold();
    }

    while (tempWidget != null) {
      tempListing = allListings.get(id);
      tempWidget = tempListing.getWidgetSold();
      int res = tempListing.getPricePerItem().compareTo(bigPrice);
      if (operator.contentEquals("greater")
          && res == 1
          && (tempWidget.getCategory().contentEquals(category) || category.contentEquals("all"))) {
        if (!tempListing.isDeleted()) {
          widgets.add(tempWidget);
        }
      } else if (operator.contentEquals("lesser")
          && res == -1
          && (tempWidget.getCategory().contentEquals(category) || category.contentEquals("all"))) {
        if (!tempListing.isDeleted()) {
          widgets.add(tempWidget);
        }
      }
      id++;
      try {
        tempListing = allListings.get(id);
        tempWidget = tempListing.getWidgetSold();
      } catch (Exception e) {
        break;
      }
    }
    return widgets;
  }

  public List<Widget> stringAndPrice(
      Widget tempWidget,
      List<Widget> widgets,
      MarketListing tempListing,
      String searchString,
      String operator,
      BigDecimal bigPrice,
      String category,
      List<Widget> allWidgets,
      List<MarketListing> allListings) {
    int id = 0;
    if (!allWidgets.isEmpty()) {
      tempListing = allListings.get(id);
      tempWidget = tempListing.getWidgetSold();
    }

    while (tempWidget != null) {
      tempListing = allListings.get(id);
      tempWidget = tempListing.getWidgetSold();
      if ((tempWidget.getName().contains(searchString)
              || tempWidget.getName().toUpperCase().contains(searchString)
              || tempWidget.getName().toLowerCase().contains(searchString)
              || tempWidget.getDescription().contains(searchString)
              || tempWidget.getDescription().toUpperCase().contains(searchString)
              || tempWidget.getDescription().toLowerCase().contains(searchString))
          && (tempWidget.getCategory().contentEquals(category) || category.contentEquals("all"))) {
        int res = tempListing.getPricePerItem().compareTo(bigPrice);
        if (operator.contentEquals("greater") && (res == 1)) {
          if (!tempListing.isDeleted()) {
            widgets.add(tempWidget);
          }
        } else if (operator.contentEquals("lesser") && (res == -1)) {
          if (!tempListing.isDeleted()) {
            widgets.add(tempWidget);
          }
        }
      }
      id++;
      try {
        tempListing = allListings.get(id);
        tempWidget = tempListing.getWidgetSold();
      } catch (Exception e) {
        break;
      }
    }
    return widgets;
  }

  public String getPage() {
    return page;
  }

  public void setPage(String page) {
    this.page = page;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public List<MarketListing> getAllMarketListings() {
    return allMarketListings;
  }

  public void setAllMarketListings(List<MarketListing> allMarketListings) {
    this.allMarketListings = allMarketListings;
  }

  public String getPage2() {
    return page2;
  }

  public void setPage2(String page2) {
    this.page2 = page2;
  }

  public String getPage3() {
    return page3;
  }

  public void setPage3(String page3) {
    this.page3 = page3;
  }

  public List<User> getAllUsers() {
    return allUsers;
  }

  public void setAllUsers(List<User> allUsers) {
    this.allUsers = allUsers;
  }
}
