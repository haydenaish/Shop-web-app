package web;

import dao.CustomerCollectionsDAO;
import dao.CustomerDAO;
import dao.JdbiDaoFactory;
import dao.ProductCollectionsDAO;
import dao.ProductDAO;
import domain.Customer;
import domain.Product;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;
import java.math.BigDecimal;

public class Server extends Jooby {
    
//    private final static CustomerDAO customerDao = new CustomerCollectionsDAO();
//    private final static ProductDAO productDao = new ProductCollectionsDAO();
    private final static ProductDAO productDao = JdbiDaoFactory.getProductDAO();
    private final static CustomerDAO customerDao = JdbiDaoFactory.getCustomerDAO();


	public Server() {
		install(new GsonModule());
                mount(new CustomerModule(customerDao));
                mount(new ProductModule(productDao));
		mount(new StaticAssetModule());
	}


	public static void main(String[] args) {
//            customerDao.saveCustomer(new Customer("aisha876", "Hayden","aish", "password", "123", "aisha876@gmail.com"));
//            customerDao.saveCustomer(new Customer("toma123", "tom","aish", "password", "123", "toma123@gmail.com"));
//            customerDao.saveCustomer(new Customer("ant876", "ant","aish", "password", "123", "ant876@gmail.com"));
            
//            productDao.saveProduct(new Product("1234", "samba", "flat top", "shoes", new BigDecimal("67"), new BigDecimal("91") ));
//            productDao.saveProduct(new Product("4321", "trefiol", "flat top", "Jacket", new BigDecimal("67"), new BigDecimal("91") ));
                  
                    
		System.out.println("\nStarting Server.");
		new Server()
			 .setServerOptions(new ServerOptions().setPort(8087))
			 .start();
	}

}
