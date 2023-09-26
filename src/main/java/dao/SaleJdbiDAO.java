/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Sale;
import domain.SaleItem;
import java.time.LocalDateTime;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

/**
 *
 * @author haydenaish
 */
public interface SaleJdbiDAO extends SaleDAO {

	@SqlUpdate("insert into Sale(date, customer, status) values (:date, :customer.customerId, :status)")
	@GetGeneratedKeys
	Integer insertSale(@BindBean Sale sale);

	@SqlUpdate(" insert into SaleItem(product, saleId, salePrice, quantityPurchased) values (:product.productId, :saleId, :salePrice, :quantityPurchased)")
	void insertSaleItem(@BindBean SaleItem item, @Bind("saleId") Integer saleId);

	@SqlUpdate("UPDATE Product SET quantityInStock = quantityInStock - :quantityPurchased WHERE productId = :product.productId")
	void updateStockLevel(@BindBean SaleItem item);

	@Override
	@Transaction
	default void save(Sale sale) {
        // save current date
        sale.setDate(LocalDateTime.now());

        // set sale status
        sale.setStatus("NEW ORDER");

        // call the insertSale method, and get the generated sale ID.
		Integer saleId = insertSale(sale);
		sale.setSaleId(saleId);

        // loop through the sale's items.
		for (SaleItem item : sale.getItems()) {
			insertSaleItem(item, saleId);
			updateStockLevel(item);
		}

	}
}
