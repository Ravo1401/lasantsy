package model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductTemplate {
    private int id;
    private String name;
    private double price;
}
