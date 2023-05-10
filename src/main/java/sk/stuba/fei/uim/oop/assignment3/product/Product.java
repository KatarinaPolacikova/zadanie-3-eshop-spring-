package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private int amount;
    private String unit;
    private float price;

    public Product(ProductRequest pr){
        this.name = pr.getName();
        this.description = pr.getDescription();
        this.amount = pr.getAmount();
        this.unit = pr.getUnit();
        this.price = pr.getPrice();
    }
}
