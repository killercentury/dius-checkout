package dius;

import java.util.List;

public class Store {

    private String name;
    private List<Product> productList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Product getProductBySKU(String sku) {
        return productList.stream().filter(p -> p.getSku().equals(sku)).findFirst().get();
    }
}
