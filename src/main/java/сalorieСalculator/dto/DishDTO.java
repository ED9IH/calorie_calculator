package сalorieСalculator.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import сalorieСalculator.Entity.Dish;

@Getter
@Setter
@Data
public class DishDTO {
    private Long id;
    private String name;
    private int numberOfCalories;
    private int proteins;
    private int fats;
    private int carbohydrates;

    public DishDTO(Dish dish) {
        this.id = dish.getId();
        this.name = dish.getName();
        this.numberOfCalories = dish.getNumberOfCalories();
        this.proteins = dish.getProteins();
        this.fats = dish.getFats();
        this.carbohydrates = dish.getCarbohydrates();
    }
}
