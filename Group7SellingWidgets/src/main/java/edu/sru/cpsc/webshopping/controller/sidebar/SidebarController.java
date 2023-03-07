package edu.sru.cpsc.webshopping.controller.sidebar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import edu.sru.cpsc.webshopping.domain.sidebar.Sidebar;
import edu.sru.cpsc.webshopping.domain.sidebar.SidebarCSVModel;
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

	/*public Iterable<Sidebar> getAllTabs() {
		Iterable<Sidebar> sidebar = sidebarRepository.findAll();
		return () ->
		StreamSupport.stream(sidebar.spliterator(), true).iterator();
	}*/

	public List<SidebarCSVModel> readAllTabs() {
		String csvFilePath;
		csvFilePath = "Documents\\Program Documents\\Excel-Sheets\\sidebar.csv";
		List<SidebarCSVModel> tabs;
		
		try {
			tabs = new CsvToBeanBuilder(new FileReader(csvFilePath))
			       .withType(SidebarCSVModel.class).build().parse();
			
		}
		catch (FileNotFoundException e) {
			System.out.println("sidebar.csv is missing, please find it");
			tabs = new ArrayList<SidebarCSVModel>(); // make tabs an empty list
		}
		System.out.println(tabs);
		return tabs;
	}
}

