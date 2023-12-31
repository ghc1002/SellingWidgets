package edu.sru.cpsc.webshopping.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import edu.sru.cpsc.webshopping.controller.StatisticsDomainController;
import edu.sru.cpsc.webshopping.controller.UserController;
import edu.sru.cpsc.webshopping.domain.user.Statistics;
import edu.sru.cpsc.webshopping.domain.user.Statistics.Category;
//import edu.sru.cpsc.webshopping.domain.user.User;
import edu.sru.cpsc.webshopping.repository.user.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
	private UserRepository userRepository;
	private UserController userController;
	private StatisticsDomainController statControl;
	public UserDetailsServiceImpl(UserRepository userRepository, UserController userController,StatisticsDomainController statControl) {
		this.userRepository = userRepository;
		this.userController = userController;
		this.statControl = statControl;
	}

    @Override
    public UserDetails loadUserByUsername(String username) {
    	
    	edu.sru.cpsc.webshopping.domain.user.User user1 = userController.getUserByUsername(username);
        if (user1 == null || user1.getEnabled() == false) {
            throw new UsernameNotFoundException(username);
        }
    
        userController.setCurrently_Logged_In(user1);
        UserDetails user = User.withUsername(user1.getUsername())
        					.password(user1.getPassword())
        					.authorities(user1.getRole())
        					.build();
        Statistics stats = new Statistics(Category.USERLOGIN,1);
        stats.setDescription(user.getUsername() + " logged in");
		statControl.addStatistics(stats);
        return user;
    }
 
}