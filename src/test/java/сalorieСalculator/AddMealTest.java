package сalorieСalculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import сalorieСalculator.Entity.Dish;
import сalorieСalculator.Entity.Meal;
import сalorieСalculator.Entity.User;
import сalorieСalculator.Enum.Goal;
import сalorieСalculator.Repository.DishRepository;
import сalorieСalculator.Repository.MealRepository;
import сalorieСalculator.Service.ServiceMeal;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Тест на успешное добавление Meal
 */
@ExtendWith(MockitoExtension.class)
public class AddMealTest {
    @Mock
    private MealRepository mealRepository;
    @Mock
    private DishRepository dishRepository;
    @InjectMocks
    private ServiceMeal mealService;

    @Test
    void addMeal_ShouldSaveMealAndDishes() {

        User user = new User(1L, "Иван Иванов", "test@example.com", 30, 75, 180, Goal.WEIGHT_LOSS);
        Meal meal = new Meal();
        meal.setUser(user);
        Dish dish1 = new Dish();
        Dish dish2 = new Dish();

        meal.setDishes(List.of(dish1, dish2));

        when(mealRepository.save(any(Meal.class))).thenReturn(meal);
        when(dishRepository.saveAll(anyList())).thenReturn(List.of(dish1, dish2));

        mealService.addMeal(meal);

        assertEquals(meal, dish1.getMeal());
        assertEquals(meal, dish2.getMeal());

        assertEquals(LocalDate.now(), meal.getLocalDate());
        verify(dishRepository, times(1)).saveAll(List.of(dish1, dish2));
        verify(mealRepository, times(1)).save(meal);
    }
}
