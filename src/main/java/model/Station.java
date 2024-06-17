package model;

import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Station {
    private UUID stationID;
    private String name;
    private String longitude;
    private String latitude;
    private int number_of_employees;
}
