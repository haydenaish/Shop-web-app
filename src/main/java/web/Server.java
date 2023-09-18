package web;

import dao.CustomerCollectionsDAO;
import dao.CustomerDAO;
import dao.ProductCollectionsDAO;
import dao.ProductDAO;
import domain.Customer;
import domain.Product;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;
import java.math.BigDecimal;

public class Server extends Jooby {
    
    private final static CustomerDAO customerDAO = new CustomerCollectionsDAO();
    private final static ProductDAO productDAO = new ProductCollectionsDAO();


	public Server() {
		install(new GsonModule());
                mount(new CustomerModule(customerDAO));
                mount(new ProductModule(productDAO));
		mount(new StaticAssetModule());
	}


	public static void main(String[] args) {
            customerDAO.saveCustomer(new Customer("aisha876", "Hayden","aish", "password", "123", "aisha876@gmail.com"));
            customerDAO.saveCustomer(new Customer("toma123", "tom","aish", "password", "123", "toma123@gmail.com"));
            customerDAO.saveCustomer(new Customer("ant876", "ant","aish", "password", "123", "ant876@gmail.com"));
            
            productDAO.saveProduct(new Product("1234", "samba", "flat top", "shoes", new BigDecimal("67"), new BigDecimal("91") ));
            productDAO.saveProduct(new Product("4321", "trefiol", "flat top", "Jacket", new BigDecimal("67"), new BigDecimal("91") ));
                  
                    
		System.out.println("\nStarting Server.");
		new Server()
			 .setServerOptions(new ServerOptions().setPort(8087))
			 .start();
	}

}
