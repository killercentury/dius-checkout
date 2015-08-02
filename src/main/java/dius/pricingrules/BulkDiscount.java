package dius.pricingrules;

import dius.PricingRule;
import dius.Product;

import java.math.BigDecimal;
import java.util.List;

public class BulkDiscount implements PricingRule {

    private Product product;
    private int minQuantity;
    private BigDecimal discountPrice;

    public BulkDiscount(Product product, int minQuantity, BigDecimal discountPrice) {
        this.product = product;
        this.minQuantity = minQuantity;
        this.discountPrice = discountPrice;
    }

    @Override
    public BigDecimal apply(List<Product> items) {
        BigDecimal discount = BigDecimal.ZERO;
        long quantity = items
                .stream()
                .filter(i -> i.getSku().equals(product.getSku()))
                .count();
        if (quantity >= minQuantity) {
            BigDecimal discountPerItem = product.getPrice().subtract(discountPrice);
            discount = discountPerItem.multiply(new BigDecimal(quantity));
        }
        return discount;
    }
}
