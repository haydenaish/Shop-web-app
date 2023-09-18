/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author haydenaish
 */
public class ProductCollectionsDAOTest {

    private ProductDAO dao;

    private Product product1;
    private Product product2;
    private Product product3;

    public ProductCollectionsDAOTest() {
    }

    @BeforeAll
    public static void initialise() {
        JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }

    @BeforeEach
    public void setUp() {
        dao = JdbiDaoFactory.getProductDAO();
//        dao = new ProductCollectionsDAO();
        product1 = new Product("20948", "Samba", "Flat shoes", "Shoes", new BigDecimal("160"), new BigDecimal("235"));
        product2 = new Product("32412", "Predator", "Soccer Boots", "Boots", new BigDecimal("240"), new BigDecimal("89"));
        product3 = new Product("51231", "Carpenter pants", "Workwear pants", "Pants", new BigDecimal("80"), new BigDecimal("500"));

        dao.saveProduct(product1);
        dao.saveProduct(product2);
    }

    @AfterEach
    public void tearDown() {
        dao.removeProduct(product1);
        dao.removeProduct(product2);
        dao.removeProduct(product3);
    }

    @Test
    public void testSaveProduct() {
        assertThat(dao.getProducts(), not(hasItem(product3)));
//        assertThat(dao.getProducts(), hasSize(2));

        dao.saveProduct(product3);

        assertThat(dao.getProducts(), hasItem(product3));
//	assertThat(dao.getProducts(), hasSize(3));
    }

    @Test
    public void testRemoveProduct() {

        assertThat(dao.getProducts(), hasItem(product1));
//	assertThat(dao.getProducts(), hasSize(2));

        dao.removeProduct(product1);

        assertThat(dao.getProducts(), not(hasItem(product1)));
//	assertThat(dao.getProducts(), hasSize(1));
    }

    @Test
    public void testGetProducts() {
        Collection<Product> products = dao.getProducts();
        assertThat(products, hasItem(product1));
        assertThat(products, hasItem(product2));
//        assertThat(products, containsInAnyOrder(product1, product2));
//        assertThat(products, contains(product1, product2));
        assertThat(products, not(hasItem(product3)));
    }

    @Test
    public void testGetCategories() {
        Collection<String> categories = dao.getCategories();
        assertThat(categories, hasItem("Shoes"));
        assertThat(categories, hasItem("Boots"));
        assertThat(categories, not(hasItem("Pants")));
    }

    @Test
    public void testSearchById() {
        assertThat(dao.searchById("20948"), is(notNullValue()));
        assertThat(dao.searchById("32412"), is(notNullValue()));
        assertThat(dao.searchById("51231"), is(nullValue()));
    }

    @Test
    public void testFilterByCategory() {
        Collection<Product> products = dao.filterByCategory("Shoes");
        assertThat(products, hasItem(product1));
        assertThat(products, not(hasItem(product2)));
        assertThat(products, not(hasItem(product3)));
    }

}
