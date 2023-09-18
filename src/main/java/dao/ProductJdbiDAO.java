/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Product;
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
public interface ProductJdbiDAO extends ProductDAO {
    
        @Override
        @SqlQuery("select * from product where category = :category")
        @RegisterBeanMapper(Product.class)
    	public Collection<Product> filterByCategory(@Bind("category")String category);

        @Override
        @SqlQuery("select distinct category from product order by category")
	public Collection<String> getCategories();

        @Override
        @SqlQuery("select * from Product order by productId")
        @RegisterBeanMapper(Product.class)
	public Collection<Product> getProducts();

        @Override
        @SqlUpdate("delete from Product where productId = :productId ")
	void removeProduct(@BindBean Product product);

        @Override
        @SqlUpdate("insert into Product(productId, name, description, category, listPrice, quantityInStock) values (:productId, :name, :description, :category, :listPrice, :quantityInStock)")
	void saveProduct(@BindBean Product product);
        
        @Override
        @SqlQuery("select * from Product where PRODUCTID = :productId")
        @RegisterBeanMapper(Product.class)
	public Product searchById(@Bind("productId")String id);

    
}
