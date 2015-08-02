package dius.pricingrules;

import dius.PricingRule;
import dius.Product;

import java.math.BigDecimal;
import java.util.List;

public class BundleDiscount implements PricingRule {

    private Product mainProduct;
    private Product bundledProduct;

    public BundleDiscount(Product mainProduct, Product bundledProduct) {
        this.mainProduct = mainProduct;
        this.bundledProduct = bundledProduct;
    }

    @Override
    public BigDecimal apply(List<Product> items) {
        BigDecimal discount = BigDecimal.ZERO;
        long mainItemQuantity = items.stream().filter(i -> i.getSku().equals(mainProduct.getSku())).count();
        long bundledItemQuantity = items.stream().filter(i -> i.getSku().equals(bundledProduct.getSku())).count();
        if (mainItemQuantity >= bundledItemQuantity) {
            discount = bundledProduct.getPrice().multiply(new BigDecimal(bundledItemQuantity));
        } else {
            discount = bundledProduct.getPrice().multiply(new BigDecimal(mainItemQuantity));
        }
        return discount;
    }
}
