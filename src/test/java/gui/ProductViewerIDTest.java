/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author haydenaish
 */
public class ProductViewerIDTest {
    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;
    private Product p1;
    private Product p2;
    
    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        robot.settings().delayBetweenEvents(75);
        
        // create a mock for the DAO
	dao = mock(ProductDAO.class);
        p1 = new Product("20948", "Samba", "Flat shoes", "Shoes", new BigDecimal("160"), new BigDecimal("235"));
        p2 = new Product("32412", "Predator", "Soccer Boots", "Boots",  new BigDecimal("240"), new BigDecimal("89"));        
        
        Collection<Product> products = new ArrayList();
        products.add(p1);
        products.add(p2);
        when(dao.getProducts()).thenReturn(products);
        
        when(dao.searchById(p1.getProductId())).thenReturn(p1);
    }
    
    @AfterEach
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testSearch() {
        ProductViewer dialog = new ProductViewer(null, true, dao);
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();
        fixture.click();
        
        fixture.textBox("txtSearchId").enterText("20948");
        fixture.button("btnSearch").click();
        
        verify(dao).searchById("20948");
        fixture.list("lstProducts").requireItemCount(1);
        
        // get the model
        SimpleListModel model = (SimpleListModel) fixture.list("lstProducts").target().getModel();

         // check the contents
        assertThat(model, hasItem(p1));
        assertThat(model, not(hasItem(p2)));
        
        
    }
    
}
