package сalorieСalculator.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import сalorieСalculator.Enum.Goal;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"user\"")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    @NotEmpty(message = "Имя не должно быть пустым")
    private String name;
    @Column(name = "email")
    @Email
    @NotEmpty(message = "Email не должен быть пустым")
    private String email;
    @Column(name = "age")
    @NotNull(message = "Возраст не должен быть пустым")
    @Min(value = 0, message = "Возраст не может быть отрицательным")
    @Max(value = 100, message = "Возраст не может быть больше 100")
    private int age;
    @Column(name = "weight")
    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 0, message = "Вес не может быть отрицательным")
    @Max(value = 300, message = "Вес не может быть больше 300")
    private int weight;
    @Column(name = "height")
    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 120, message = "Рост не может быть меньше 120")
    @Max(value = 251, message = "Вес не может быть больше 251")
    private int height;
    @Column(name = "goal")
    @NotNull(message = "Укажите пожалуйста цель")
    @Enumerated(value = EnumType.STRING)
    private Goal goal;
}
