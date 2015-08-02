package dius;

import dius.pricingrules.BulkDiscount;
import dius.pricingrules.BundleDiscount;
import dius.pricingrules.BuyXGetYFree;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class AppTest {

    private Product ipd;
    private Product mbp;
    private Product atv;
    private Product vga;
    private Checkout checkout;

    @Before
    public void setup() {
        ipd = new Product("ipd", "Super iPad", new BigDecimal("549.99"));
        mbp = new Product("mbp", "MacBook Pro", new BigDecimal("1399.99"));
        atv = new Product("atv", "Apple TV", new BigDecimal("109.50"));
        vga = new Product("vga", "VGA adapter", new BigDecimal("30.00"));
        List<PricingRule> pricingRules = new ArrayList<>();
        pricingRules.add(new BuyXGetYFree(atv, 2, 1));
        pricingRules.add(new BulkDiscount(ipd, 5, new BigDecimal("499.99")));
        pricingRules.add(new BundleDiscount(mbp, vga));
        checkout = new CheckoutImpl(pricingRules);
    }

    // simplest case to test checkout service without valid pricing rule
    @Test
    public void checkoutShouldReturnTotalOfAllItems() {
        checkout.scan(ipd);
        checkout.scan(mbp);
        checkout.scan(atv);
        BigDecimal expectedTotal = new BigDecimal("2059.48");
        assertEquals(expectedTotal, checkout.total());
    }

    // following are unit tests for BuyXGetYFree pricing rule
    @Test
    public void singleAppleTVShouldReturnFullPrice() {
        checkout.scan(atv);
        BigDecimal expectedTotal = new BigDecimal("109.50");
        assertEquals(expectedTotal, checkout.total());
    }

    @Test
    public void twoAppleTVShouldReturnFullPrice() {
        IntStream.rangeClosed(1, 2).forEach(i -> checkout.scan(atv));
        BigDecimal expectedTotal = new BigDecimal("219.00");
        assertEquals(expectedTotal, checkout.total());
    }

    @Test
    public void threeAppleTVShouldReturnDiscountPrice() {
        IntStream.rangeClosed(1, 3).forEach(i -> checkout.scan(atv));
        BigDecimal expectedTotal = new BigDecimal("219.00");
        assertEquals(expectedTotal, checkout.total());
    }

    @Test
    public void fourAppleTVShouldReturnDiscountPrice() {
        IntStream.rangeClosed(1, 4).forEach(i -> checkout.scan(atv));
        BigDecimal expectedTotal = new BigDecimal("328.50");
        assertEquals(expectedTotal, checkout.total());
    }

    @Test
    public void sixAppleTVShouldReturnDiscountPrice() {
        IntStream.rangeClosed(1, 6).forEach(i -> checkout.scan(atv));
        BigDecimal expectedTotal = new BigDecimal("438.00");
        assertEquals(expectedTotal, checkout.total());
    }

    // following are unit tests for BulkDiscount pricing rule
    @Test
    public void fourSuperIpadShouldReturnDiscountPrice() {
        IntStream.rangeClosed(1, 4).forEach(i -> checkout.scan(ipd));
        BigDecimal expectedTotal = new BigDecimal("2199.96");
        assertEquals(expectedTotal, checkout.total());
    }

    @Test
    public void fiveSuperIpadShouldReturnDiscountPrice() {
        IntStream.rangeClosed(1, 5).forEach(i -> checkout.scan(ipd));
        BigDecimal expectedTotal = new BigDecimal("2499.95");
        assertEquals(expectedTotal, checkout.total());
    }

    // following are unit tests for BundleDiscount pricing rule
    @Test
    public void oneMacBookProShouldReturnFullPrice() {
        checkout.scan(mbp);
        BigDecimal expectedTotal = new BigDecimal("1399.99");
        assertEquals(expectedTotal, checkout.total());
    }

    @Test
    public void oneMacBookProWithOneVGAAdapterShouldReturnBundledPrice() {
        checkout.scan(mbp);
        checkout.scan(vga);
        BigDecimal expectedTotal = new BigDecimal("1399.99");
        assertEquals(expectedTotal, checkout.total());
    }

    @Test
    public void oneMacBookProWithTwoVGAAdapterShouldReturnBundledPriceWithOneVGAAdapter() {
        checkout.scan(mbp);
        IntStream.rangeClosed(1, 2).forEach(i -> checkout.scan(vga));
        BigDecimal expectedTotal = new BigDecimal("1429.99");
        assertEquals(expectedTotal, checkout.total());
    }

    // following are tests for scenarios from the instructions
    @Test
    public void scenario1() {
        checkout.scan(atv);
        checkout.scan(atv);
        checkout.scan(atv);
        checkout.scan(vga);
        BigDecimal expectedTotal = new BigDecimal("249.00");
        assertEquals(expectedTotal, checkout.total());
    }

    @Test
    public void scenario2() {
        checkout.scan(atv);
        checkout.scan(ipd);
        checkout.scan(ipd);
        checkout.scan(atv);
        checkout.scan(ipd);
        checkout.scan(ipd);
        checkout.scan(ipd);
        BigDecimal expectedTotal = new BigDecimal("2718.95");
        assertEquals(expectedTotal, checkout.total());
    }

    @Test
    public void scenario3() {
        checkout.scan(mbp);
        checkout.scan(vga);
        checkout.scan(ipd);
        BigDecimal expectedTotal = new BigDecimal("1949.98");
        assertEquals(expectedTotal, checkout.total());
    }
}
