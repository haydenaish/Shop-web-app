/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Customer;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author haydenaish
 */
public interface CustomerJdbiDAO extends CustomerDAO{

    @Override
    @SqlUpdate("insert into Customer(username, firstname, surname, password, emailAddress, shippingAddress) values(:username, :firstName, :surname, :password, :emailAddress, :shippingAddress)")
    public void saveCustomer(@BindBean Customer customer);


    @Override
    @SqlUpdate("delete from Customer where username = :username")
    public void removeCustomer(@BindBean Customer customer);
        

    @Override
    @SqlQuery("select exists (select * from Customer where Username = :username and Password = :password)")
    public boolean authorize(@Bind("username")String username, @Bind("password")String password);
        


    @Override
    @SqlQuery("select * from Customer where username = :username")
    @RegisterBeanMapper(Customer.class)
    public Customer searchByUserName(@Bind("username") String username);
    
}
