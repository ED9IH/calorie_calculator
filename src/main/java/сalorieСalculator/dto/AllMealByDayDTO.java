package сalorieСalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data // Аннотация Lombok
@AllArgsConstructor
public class AllMealByDayDTO {
    private LocalDate date;
    private List<DishDTO> dishes;
}
