package dius;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Store store = new Store();
        store.setName("DiUS Computer Store");
        List<Product> productList = new ArrayList<>();
        Product ipd = new Product("ipd", "Super iPad", new BigDecimal("549.99"));
        Product mbp = new Product("mbp", "MacBook Pro", new BigDecimal("1399.99"));
        Product atv = new Product("atv", "Apple TV", new BigDecimal("109.50"));
        Product vga = new Product("vga", "VGA adapter", new BigDecimal("30.00"));
        productList.add(ipd);
        productList.add(mbp);
        productList.add(atv);
        productList.add(vga);
        store.setProductList(productList);
    }
}
