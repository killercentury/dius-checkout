package dius.pricingrules;

import dius.PricingRule;
import dius.Product;

import java.math.BigDecimal;
import java.util.List;

public class BulkDiscount implements PricingRule {

    private Product product;
    // The quantity for valid discount should be plus 1 on this base quantity
    private int baseQuantity;
    private BigDecimal discountPrice;

    public BulkDiscount(Product product, int baseQuantity, BigDecimal discountPrice) {
        this.product = product;
        this.baseQuantity = baseQuantity;
        this.discountPrice = discountPrice;
    }

    @Override
    public BigDecimal apply(List<Product> items) {
        BigDecimal discount = BigDecimal.ZERO;
        long quantity = items
                .stream()
                .filter(i -> i.getSku().equals(product.getSku()))
                .count();
        if (quantity > baseQuantity) {
            BigDecimal discountPerItem = product.getPrice().subtract(discountPrice);
            discount = discountPerItem.multiply(new BigDecimal(quantity));
        }
        return discount;
    }
}
