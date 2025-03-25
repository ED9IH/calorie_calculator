package сalorieСalculator.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    @NotEmpty(message = "Поле не должно быть пустым")
    private String name;
    @Column(name = "calories")
    @NotNull(message = "Поле не должно быть пустым")
    private int numberOfCalories;
    @Column(name = "proteins")
    @NotNull(message = "Поле не должно быть пустым")
    private int proteins;
    @Column(name = "fats")
    @NotNull(message = "Поле не должно быть пустым")
    private int fats;
    @Column(name = "carbohydrates")
    @NotNull(message = "Поле не должно быть пустым")
    private int carbohydrates;
    @ManyToOne
    @JoinColumn(name = "id_meal", referencedColumnName = "id")
    private Meal meal;
}
