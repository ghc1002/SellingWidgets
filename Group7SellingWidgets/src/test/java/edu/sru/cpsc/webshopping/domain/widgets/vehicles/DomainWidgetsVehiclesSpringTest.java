package edu.sru.cpsc.webshopping.domain.widgets.vehicles;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.cpsc.webshopping.domain.widgets.appliances.DomainWidgetsAppliancesSpringTest;

/*
 * Tests the entire vehicle package. It ensure that the car 
 * file inherits the Widget file. The tests ensure that both are
 * working and correspond to each other. 
 * 
 * Gets confused between Condition and ItemCondition
 */

@SpringBootTest(classes = {DomainWidgetsVehiclesSpringTest.class})
public class DomainWidgetsVehiclesSpringTest {

	/*
	 * Tests that the attributes are added correctly for cars
	 */
	@Test
	void vehicle_CarTest() {
		Vehicle_Car car = new Vehicle_Car();
		car.setCategory("vehicle");
		car.setCondition("good");
		car.setDescription("red");
		car.setId(45);
		car.setImageName("car");
		//car.setItemCondition("mint");
		car.setMake("Kia");
		car.setModel("Soul");
		car.setName("Heidi");
		car.setRoadSafe("yes");
		car.setSubCategory("car");
		car.setTerrain("rough");
		car.setTransmissionType("Automatic");
		car.setWheelDrive("yes");
		car.setYear(2022);
		
		assertEquals("vehicle", car.getCategory());
		assertEquals("good", car.getCondition());
		assertEquals("red", car.getDescription());
		assertEquals(45, car.getId());
		assertEquals("car", car.getImageName());
		//assertEquals("mint", car.getItemCondition());
		assertEquals("Kia", car.getMake());
		assertEquals("Soul", car.getModel());
		assertEquals("Heidi", car.getName());
		assertEquals("yes", car.getRoadSafe());
		assertEquals("car", car.getSubCategory());
		assertEquals("rough", car.getTerrain());
		assertEquals("Automatic", car.getTransmissionType());
		assertEquals("yes", car.getWheelDrive());
		assertEquals(2022, car.getYear());
	}
	
	/*
	 * Tests that the attributes are added correctly for the widget
	 */
	@Test
	void widget_VehiclesTest() {
		Widget_Vehicles widget = new Widget_Vehicles();
		widget.setCategory("widget");
		widget.setCondition("fair");
		widget.setDescription("pink");
		widget.setId(12);
		widget.setImageName("widget");
		//widget.setItemCondition("poor");
		widget.setName("Beth");
		widget.setRoadSafe("no");
		widget.setSubCategory("vehicle");
		widget.setTerrain("bumpy");
		
		assertEquals("widget", widget.getCategory());
		assertEquals("fair", widget.getCondition());
		assertEquals("pink", widget.getDescription());
		assertEquals(12, widget.getId());
		assertEquals("widget", widget.getImageName());
		//assertEquals("poor", widget.getItemCondition());
		assertEquals("Beth", widget.getName());
		assertEquals("no", widget.getRoadSafe());
		assertEquals("vehicle", widget.getSubCategory());
		assertEquals("bumpy", widget.getTerrain());
	}

}