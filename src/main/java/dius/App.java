package dius;

import dius.pricingrules.BulkDiscount;
import dius.pricingrules.BundleDiscount;
import dius.pricingrules.BuyXGetYFree;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Store store = new Store();
        store.setName("DiUS Computer Store");
        List<Product> productList = new ArrayList<>();
        Product ipd = new Product("ipd", "Super iPad", new BigDecimal("549.99"));
        Product mbp = new Product("mbp", "MacBook Pro", new BigDecimal("1399.99"));
        Product atv = new Product("atv", "Apple TV", new BigDecimal("109.50"));
        Product vga = new Product("vga", "VGA adapter", new BigDecimal("30.00"));
        productList.add(ipd);
        productList.add(mbp);
        productList.add(atv);
        productList.add(vga);
        store.setProductList(productList);
        List<PricingRule> pricingRules = new ArrayList<>();
        pricingRules.add(new BuyXGetYFree(atv, 2, 1));
        pricingRules.add(new BulkDiscount(ipd, 5, new BigDecimal("499.99")));
        pricingRules.add(new BundleDiscount(mbp, vga));
        Checkout checkout = new CheckoutImpl(pricingRules);
        checkout.scan(ipd);
        checkout.scan(mbp);
        checkout.scan(atv);
        checkout.scan(vga);
        System.out.println(checkout.total());
    }
}
