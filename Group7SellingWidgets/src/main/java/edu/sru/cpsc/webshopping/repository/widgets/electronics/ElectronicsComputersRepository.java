package edu.sru.cpsc.webshopping.repository.widgets.electronics;

import org.springframework.data.repository.CrudRepository;

import edu.sru.cpsc.webshopping.domain.widgets.electronics.Electronics_Computers;

// Generic WidgetRepository that is to be used by Widget subtypes
// Work in progress, using as reference: https://blog.netgloo.com/2014/12/18/handling-entities-inheritance-with-spring-data-jpa/
public interface ElectronicsComputersRepository<T extends Electronics_Computers> extends CrudRepository<T, Long> {

}