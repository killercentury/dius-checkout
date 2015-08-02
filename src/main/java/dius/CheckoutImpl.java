package dius;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CheckoutImpl implements Checkout {

    private List<Product> productList = new ArrayList<>();
    private List<PricingRule> pricingRules = new ArrayList<>();

    CheckoutImpl(List<PricingRule> pricingRules) {
        this.pricingRules = pricingRules;
    }

    @Override
    public void scan(Product product) {
        productList.add(product);
    }

    @Override
    public void clear() {
        productList = new ArrayList<>();
    }

    public BigDecimal subTotal() {
        return productList
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal discount() {
        return pricingRules
                .stream()
                .map(p -> p.apply(productList))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal total() {
        //System.out.println(subTotal());
        //System.out.println(discount());
        return subTotal().subtract(discount());
    }
}
