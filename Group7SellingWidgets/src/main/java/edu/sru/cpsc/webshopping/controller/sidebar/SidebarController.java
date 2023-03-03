package edu.sru.cpsc.webshopping.controller.sidebar;

import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.sru.cpsc.webshopping.domain.sidebar.Sidebar;
import edu.sru.cpsc.webshopping.repository.sidebar.SidebarRepository;

@Controller
public class SidebarController {
	@Autowired
	private SidebarRepository sidebarRepository;
	
	
	public SidebarController(SidebarRepository sidebarRepository) {
		this.sidebarRepository = sidebarRepository;
	}
	public SidebarRepository getSidebarRepository() {
		return sidebarRepository;
	}
	public void setSidebarRepository(SidebarRepository sidebarRepository) {
		this.sidebarRepository = sidebarRepository;
	}
	
	public Iterable<Sidebar> getAllTabs() {
		Iterable<Sidebar> sidebar = sidebarRepository.findAll();
		return () ->
        StreamSupport.stream(sidebar.spliterator(), true).iterator();
	}
}
