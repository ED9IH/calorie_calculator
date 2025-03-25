package сalorieСalculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
/**
 * Тест получение суммы каллорий за день
 */
@ExtendWith(MockitoExtension.class)
public class GetAllMealCountByDayTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private ServiceMeal mealService;
    @Test
    void getAllMealCountByDay_ShouldReturnCorrectCount() {
        long userId = 1L;
        LocalDate date = LocalDate.of(2023, 12, 31);
        User user = new User(1L, "Иван Иванов", "test@example.com", 30, 75, 180, Goal.WEIGHT_LOSS);


        Meal meal1 = new Meal();
        meal1.setUser(user);
        meal1.setLocalDate(date);

        Meal meal2 = new Meal();
        meal2.setUser(user);
        meal2.setLocalDate(date);

        Meal mealOtherUser = new Meal();
        mealOtherUser.setUser(new User(2L, "Иван Иванов", "test@example.com", 30, 75, 180, Goal.WEIGHT_LOSS));
        mealOtherUser.setLocalDate(date);

        Meal mealOtherDate = new Meal();
        mealOtherDate.setUser(user);
        mealOtherDate.setLocalDate(LocalDate.now());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mealRepository.findAll()).thenReturn(List.of(meal1, meal2, mealOtherUser, mealOtherDate));

        int result = mealService.getAllMealCountByDay(userId, date);

        assertEquals(2, result);
        verify(userRepository).findById(userId);
        verify(mealRepository).findAll();
    }

}
