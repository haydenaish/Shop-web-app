/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author haydenaish
 */
public class ProductEditorSaveTest {
    
    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;
        
    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();
		 
        robot.settings().delayBetweenEvents(75);

        Collection<String> categorys = new ArrayList<>();
	categorys.add("Shoes");
	categorys.add("Jackets");

	
	dao = mock(ProductDAO.class);

		
	when(dao.getCategories()).thenReturn(categorys);
    }
    
    @AfterEach
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testSave() {
       
	ProductEditor dialog = new ProductEditor(null, true, dao);


	fixture = new DialogFixture(robot, dialog);

	fixture.show().requireVisible();

	fixture.click();

	
	fixture.textBox("txtId").enterText("11111");
	fixture.textBox("txtName").enterText("Samba");
	fixture.textBox("txtDescription").enterText("Flat Shoes");
	fixture.textBox("txtPrice").enterText("164");
	fixture.textBox("txtQuantity").enterText("234");
	fixture.comboBox("cmbCategory").selectItem("Shoes");
       
	
	fixture.button("btnSave").click();
        
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
        
        verify(dao).saveProduct(argument.capture());
        
        Product savedProduct = argument.getValue();

        
	assertThat("Ensure the ID was saved", savedProduct, hasProperty("productId", equalTo("11111")));
	assertThat("Ensure the Name was saved", savedProduct, hasProperty("name", equalTo("Samba")));
	assertThat("Ensure the Description was saved", savedProduct, hasProperty("description", equalTo("Flat Shoes")));
	assertThat("Ensure the Price was saved", savedProduct, hasProperty("listPrice", equalTo(new BigDecimal("164"))));
	assertThat("Ensure the Quantity was saved", savedProduct, hasProperty("quantityInStock", equalTo(new BigDecimal("234"))));
	assertThat("Ensure the Category was saved", savedProduct, hasProperty("category", equalTo("Shoes")));
    }
    
}
