/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;
import dao.CustomerDAO;
import domain.Customer;
import io.jooby.Jooby;
import io.jooby.StatusCode;

/**
 *
 * @author haydenaish
 */
public class CustomerModule extends Jooby{
    public CustomerModule(CustomerDAO dao) {
        get("/api/customers/{username}", ctx -> {
            String username = ctx.path("username").value();
            System.out.println(username);

            Customer customer = dao.searchByUserName(username);

            if (customer == null) {
                // no student with that ID found, so return a 404/Not Found error
                return ctx.send(StatusCode.NOT_FOUND);
            } else {
                return customer;
            }
        });
        
        post("/api/register", ctx -> {
            Customer customer = ctx.body().to(Customer.class);
            dao.saveCustomer(customer);
//            System.out.println("Success");
            return ctx.send(StatusCode.CREATED);
  
        });
    }
}
