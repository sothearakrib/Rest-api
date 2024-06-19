package rest_api;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Rest_Api {

    public static void main(String[] args) {
        SpringApplication.run(Rest_Api.class, args);
    }

    @RestController
    @RequestMapping("/products")
    public static class ProductController {

        private Map<Long, Product> products = new HashMap<>();
        private Long nextId = 1L;

        @PostMapping
        public Product addProduct(@RequestBody Product product) {
            product.setId(nextId++);
            products.put(product.getId(), product);
            return product;
        }

        @GetMapping("/{id}")
        public Product getProductById(@PathVariable Long id) {
            return products.get(id);
        }

        @GetMapping
        public List<Product> getAllProducts() {
            return new ArrayList<>(products.values());
        }

        @PutMapping("/{id}")
        public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
            if (products.containsKey(id)) {
                product.setId(id);
                products.put(id, product);
                return product;
            } else {
                return null; 
            }
        }
    }

    public static class Product {
        private Long id;
        private String name;
        private double price;

        public Product() {
        }

        public Product(Long id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
