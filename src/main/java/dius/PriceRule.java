package dius;

import java.math.BigDecimal;
import java.util.List;

public interface PriceRule {
    BigDecimal apply(List<Product> items);
}
