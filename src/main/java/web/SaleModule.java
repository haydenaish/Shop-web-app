/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;
import dao.ProductDAO;
import domain.Product;
import io.jooby.Jooby;
import io.jooby.StatusCode;
/**
 *
 * @author haydenaish
 */
public class SaleModule extends Jooby{
    public SaleModule(ProductDAO dao) {

        post("/api/sales", ctx -> {
            Product product = ctx.body().to(Product.class);
            dao.saveProduct(product);
            return ctx.send(StatusCode.CREATED);
        });
    }
    
}
