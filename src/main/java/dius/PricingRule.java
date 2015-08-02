package dius;

import java.math.BigDecimal;
import java.util.List;

public interface PricingRule {
    BigDecimal apply(List<Product> items);
}
