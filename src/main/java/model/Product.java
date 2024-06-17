package model;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    private UUID ProductId;
    private String name;
}
