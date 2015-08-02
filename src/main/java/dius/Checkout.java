package dius;

import java.math.BigDecimal;

public interface Checkout {
    void scan(Product item);
    void clear();
    BigDecimal total();
}
