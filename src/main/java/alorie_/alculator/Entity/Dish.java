package alorie_.alculator.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    @NotEmpty(message = "Поле не должно быть пустым")
    private String name;
    @Column
    @NotEmpty(message = "Поле не должно быть пустым")
    private int numberOfCalories;
    @Column
    @NotEmpty(message = "Поле не должно быть пустым")
    private int proteins;
    @Column
    @NotEmpty(message = "Поле не должно быть пустым")
    private int fats;
    @Column
    @NotEmpty(message = "Поле не должно быть пустым")
    private int carbohydrates;
}
