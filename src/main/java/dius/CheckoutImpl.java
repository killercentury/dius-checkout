package dius;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CheckoutImpl implements Checkout {

    List<Product> productList = new ArrayList<>();

    @Override
    public void scan(Product product) {
        productList.add(product);
    }

    public BigDecimal subTotal() {
        return productList.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal total() {
        BigDecimal total = subTotal();
        return total;
    }
}
