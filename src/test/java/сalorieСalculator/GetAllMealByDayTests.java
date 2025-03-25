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
import сalorieСalculator.Repository.MealRepository;
import сalorieСalculator.Repository.UserRepository;
import сalorieСalculator.Service.ServiceMeal;
import сalorieСalculator.dto.AllMealByDayDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тест история приемов пиши за день
 */
@ExtendWith(MockitoExtension.class)
public class GetAllMealByDayTests {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ServiceMeal serviceMeal;

    @Test
    void getAllMealByDay_ShouldReturnFilteredDishes() {
        // Arrange
        long userId = 1L;
        LocalDate date = LocalDate.of(2023, 12, 31);
        User user = new User(1L, "Иван Иванов", "test@example.com", 30, 75, 180, Goal.WEIGHT_LOSS);

        Meal userMeal1 = new Meal();
        userMeal1.setUser(user);
        userMeal1.setLocalDate(date);
        userMeal1.setDishes(List.of(
                new Dish(1L, "Dish1", 200, 30, 10, 20, userMeal1),
                new Dish(2L, "Dish1", 200, 30, 10, 20, userMeal1)
        ));

        Meal userMeal2 = new Meal();
        userMeal2.setUser(user);
        userMeal2.setLocalDate(date);
        userMeal2.setDishes(List.of(
                new Dish(3L, "Dish1", 200, 30, 10, 20, userMeal2)
        ));

        // Другие данные, которые не должны попасть в результат
        Meal otherUserMeal = new Meal();
        otherUserMeal.setUser(new User(2L, "Иван Иванов", "test@example.com", 30, 75, 180, Goal.WEIGHT_LOSS));
        otherUserMeal.setLocalDate(date);

        Meal otherDateMeal = new Meal();
        otherDateMeal.setUser(user);
        otherDateMeal.setLocalDate(LocalDate.now());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mealRepository.findAll()).thenReturn(List.of(userMeal1, userMeal2, otherUserMeal, otherDateMeal));

        AllMealByDayDTO result = serviceMeal.getAllMealByDay(userId, date);

        assertEquals(date, result.getDate());
        assertEquals(3, result.getDishes().size());
        verify(userRepository).findById(userId);
        verify(mealRepository).findAll();
    }
}
