package dius;

import dius.pricingrules.BulkDiscount;
import dius.pricingrules.BundleDiscount;
import dius.pricingrules.BuyXGetYFree;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        // initialize store
        Store store = new Store();
        store.setName("DiUS Computer Store");
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("ipd", "Super iPad", new BigDecimal("549.99")));
        productList.add(new Product("mbp", "MacBook Pro", new BigDecimal("1399.99")));
        productList.add(new Product("atv", "Apple TV", new BigDecimal("109.50")));
        productList.add(new Product("vga", "VGA adapter", new BigDecimal("30.00")));
        store.setProductList(productList);

        // load products from store
        Product atv = store.getProductBySKU("atv");
        Product ipd = store.getProductBySKU("ipd");
        Product mbp = store.getProductBySKU("mbp");
        Product vga = store.getProductBySKU("vga");

        // initialize pricing rules
        List<PricingRule> pricingRules = new ArrayList<>();
        // rule for Apple TV - "buy 2 get 1 free" or "have 3 for 2"
        pricingRules.add(new BuyXGetYFree(atv, 2, 1));
        // rule for Super iPad - price will drop to $499.99 each when buying equal or more than 5
        pricingRules.add(new BulkDiscount(ipd, 5, new BigDecimal("499.99")));
        // rule for MacBook Pro bundle - free VGA adapter for each
        pricingRules.add(new BundleDiscount(mbp, vga));

        // initialize checkout service with pricing rules
        Checkout checkout = new CheckoutImpl(pricingRules);

        System.out.println("Scenario 1");
        checkout.scan(atv);
        checkout.scan(atv);
        checkout.scan(atv);
        checkout.scan(vga);
        System.out.println("SKUs Scanned: atv, atv, atv, vga");
        System.out.println("Total:" + checkout.total());
        checkout.clear();

        System.out.println("Scenario 2");
        checkout.scan(atv);
        checkout.scan(ipd);
        checkout.scan(ipd);
        checkout.scan(atv);
        checkout.scan(ipd);
        checkout.scan(ipd);
        checkout.scan(ipd);
        System.out.println("SKUs Scanned: atv, ipd, ipd, atv, ipd, ipd, ipd");
        System.out.println("Total: " + checkout.total());
        checkout.clear();

        System.out.println("Scenario 3");
        checkout.scan(mbp);
        checkout.scan(vga);
        checkout.scan(ipd);
        System.out.println("SKUs Scanned: mbp, vga, ipd");
        System.out.println("Total:" + checkout.total());
        checkout.clear();
    }
}
