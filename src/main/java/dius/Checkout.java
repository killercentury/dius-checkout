package dius;

import java.math.BigDecimal;

public interface Checkout {
    void scan(Product item);
    BigDecimal total();
}
