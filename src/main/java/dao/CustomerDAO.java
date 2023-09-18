package dao;

import domain.Customer;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author haydenaish
 */
public interface CustomerDAO {
    
    /* method for saving a new customer which will be used when creating an account.*/
    void saveCustomer(Customer customer);
    
    /*A method for removing a customer. This is primarily going to be used in the
    tearDown method of automated tests.
    */
    void removeCustomer(Customer customer);
    
    /*A method for checking if a given username and password matches an existing customer 
    which will be used to check the username and password when signing in.*/
    boolean authorize(String username, String password);
    
    
    /*A method for retrieving a customer via their username.
    This method will be used to retreive the customer object during the sign in process.
    */
    Customer searchByUserName(String username);

    
}
