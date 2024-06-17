package model;

import lombok.*;

import java.time.Instant;
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MovementStock {
    private int id;
    private int quantity;
    private Instant stockDatetime;
    private MovementType type;
    private int productId;
}
