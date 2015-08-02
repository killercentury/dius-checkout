package dius;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void checkoutShouldReturnTotalOfAllItems() {
        Product ipd = new Product("ipd", "Super iPad", new BigDecimal("549.99"));
        Product mbp = new Product("mbp", "MacBook Pro", new BigDecimal("1399.99"));
        Product atv = new Product("atv", "Apple TV", new BigDecimal("109.50"));
        Product vga = new Product("vga", "VGA adapter", new BigDecimal("30.00"));
        Checkout checkout = new CheckoutImpl();
        checkout.scan(ipd);
        checkout.scan(mbp);
        checkout.scan(atv);
        checkout.scan(vga);
        checkout.total();
        BigDecimal expectedTotal = new BigDecimal("2089.48");
        assertEquals(expectedTotal, checkout.total());
    }
}
