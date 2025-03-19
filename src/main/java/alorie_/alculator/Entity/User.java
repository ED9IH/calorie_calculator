package alorie_.alculator.Entity;

import alorie_.alculator.Enum.Goal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Имя не должно быть пустым")
    private String name;
    @Email
    @NotEmpty(message = "Email не должен быть пустым")
    private String email;
    @NotEmpty(message = "Возраст не должен быть пустым")
    private int age;
    @NotEmpty(message = "Поле не должно быть пустым")
    private int weight;
    @NotEmpty(message = "Поле не должно быть пустым")
    private int height;
    @NotEmpty(message = "Укажите пожалуйста цель")
    @Enumerated(value = EnumType.STRING)
    private Goal goal;


}
