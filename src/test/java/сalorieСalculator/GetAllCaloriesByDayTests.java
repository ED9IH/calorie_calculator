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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class GetAllCaloriesByDayTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private ServiceMeal mealService;
    @Test
    void getAllCaloriesByDay_ShouldReturnCorrectSum() {

        long userId = 1L;
        LocalDate date = LocalDate.of(2023, 12, 31);
        User user = new User(1L, "Иван Иванов", "test@example.com", 30, 75, 180, Goal.WEIGHT_LOSS);

        Meal meal1 = new Meal();
        meal1.setUser(user);
        meal1.setLocalDate(date);
        meal1.setDishes(List.of(
                new Dish(1L,"Dish1",200,30,10,20,meal1),
                new Dish(2L,"Dish2", 300, 30, 10, 20,meal1)
        ));

        Meal meal2 = new Meal();
        meal2.setUser(user);
        meal2.setLocalDate(date);
        meal2.setDishes(List.of(
                new Dish(3L,"Dish3", 150, 15, 3, 25,meal2)
        ));

        Meal mealOtherDate = new Meal();
        mealOtherDate.setUser(user);
        mealOtherDate.setLocalDate(LocalDate.now());
        mealOtherDate.setDishes(List.of(
                new Dish(4L,"Dish4", 500, 50, 20, 30,mealOtherDate)
        ));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mealRepository.findAll()).thenReturn(List.of(meal1, meal2, mealOtherDate));

        int result = mealService.getAllCaloriesByDay(userId, date);

        assertEquals(650, result);
        verify(userRepository).findById(userId);
        verify(mealRepository).findAll();
    }
}
