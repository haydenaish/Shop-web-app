/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Customer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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
public class CustomerCollectionsDAOTest {

    private CustomerDAO dao;

    private Customer customer1;
    private Customer customer2;
    private Customer customer3;

    public CustomerCollectionsDAOTest() {
    }

    @BeforeAll
    public static void initialise() {
        JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }

    @BeforeEach
    public void setUp() {
//        dao = new CustomerCollectionsDAO();
        dao = JdbiDaoFactory.getCustomerDAO();
        customer1 = new Customer("aisha876", "Hayden", "Aish", "password", "123 Castle St", "aisha876@student.otago.ac.nz");
        customer2 = new Customer("obema123", "Matt", "Oberdries", "password", "123 Castle St", "obesm123@student.otago.ac.nz");
        customer3 = new Customer("smama984", "Max", "Smail", "password", "123 Castle St", "smama984@student.otago.ac.nz");

        dao.saveCustomer(customer1);
        dao.saveCustomer(customer2);

    }

    @AfterEach
    public void tearDown() {
        dao.removeCustomer(customer1);
        dao.removeCustomer(customer2);
        dao.removeCustomer(customer3);
    }

    @Test
    public void testSaveCustomer() {
        //Check that the dao does not contain username smama
        assertThat(dao.searchByUserName("smama984"), is(nullValue()));

        dao.saveCustomer(customer3);

        assertThat(dao.searchByUserName("smama984"), is(notNullValue()));
    }

    @Test
    public void testRemoveCustomer() {
        assertThat(dao.searchByUserName("aisha876"), is(notNullValue()));
        dao.removeCustomer(customer1);
        assertThat(dao.searchByUserName("aisha876"), is(nullValue()));

    }

    @Test
    public void testAuthorize() {
        Boolean doesExist = dao.authorize(customer1.getUsername().toString(), customer1.getPassword());
        assertThat(doesExist, is(true));

        Boolean d2 = dao.authorize("12456", "password");
        assertThat(d2, is(false));

    }

    @Test
    public void testSearchByUserName() {
        assertThat(dao.searchByUserName("aisha876"), is(notNullValue()));
        assertThat(dao.searchByUserName("obema123"), is(notNullValue()));
        assertThat(dao.searchByUserName("smama984"), is(nullValue()));
    }

}
