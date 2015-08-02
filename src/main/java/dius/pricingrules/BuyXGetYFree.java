package dius.pricingrules;

import dius.PricingRule;
import dius.Product;

import java.math.BigDecimal;
import java.util.List;

public class BuyXGetYFree implements PricingRule {

    private Product product;
    private int paidQuantity;
    private int freeQuantity;

    public BuyXGetYFree(Product product, int paidQuantity, int freeQuantity) {
        this.product = product;
        this.paidQuantity = paidQuantity;
        this.freeQuantity = freeQuantity;
    }

    @Override
    public BigDecimal apply(List<Product> items) {
        long quantity = items
                .stream()
                .filter(i -> i.getSku().equals(product.getSku()))
                .count();
        return product
                .getPrice()
                .multiply(new BigDecimal((quantity / (paidQuantity + freeQuantity)) * freeQuantity));
    }
}
