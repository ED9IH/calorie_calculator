package сalorieСalculator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import сalorieСalculator.Entity.Dish;
import сalorieСalculator.Entity.Meal;
import сalorieСalculator.Entity.User;
import сalorieСalculator.Repository.DishRepository;
import сalorieСalculator.Repository.MealRepository;
import сalorieСalculator.Repository.UserRepository;
import сalorieСalculator.dto.AllMealByDayDTO;
import сalorieСalculator.dto.DishDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис приема пищи(Meal)
 */
@Service
public class ServiceMeal {

    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final ServiceUser serviceUser;

    /**
     * Внедрение зависимостей через конструктор
     */
    @Autowired
    public ServiceMeal(MealRepository mealRepository, UserRepository userRepository, DishRepository dishRepository, ServiceUser serviceUser) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
        this.serviceUser = serviceUser;
    }

    /**
     * Метод добавления приема пищи в БД
     */
    @Transactional
    public void addMeal(Meal meal) {
        List<Dish> dishes = meal.getDishes();
        dishes.forEach(e -> e.setMeal(meal));
        dishRepository.saveAll(dishes);
        meal.setLocalDate(LocalDate.now());
        meal.setUser(meal.getUser());
        mealRepository.save(meal);
    }

    /**
     * Метод получение суммы каллорий за день
     */
    public int getAllCaloriesByDay(long id, LocalDate localDate) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found user"));
        List<Meal> meals = mealRepository.findAll();
        return meals.stream().filter(m -> m.getUser().getId().equals(user.getId())
                        && m.getLocalDate().isEqual(localDate)).flatMap(m -> m.getDishes().stream()).
                mapToInt(Dish::getNumberOfCalories).reduce(Integer::sum).orElseThrow();
    }

    /**
     * Метод получение сумма приема пищи за день
     */
    public int getAllMealCountByDay(long id, LocalDate localDate) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found user"));
        List<Meal> meals = mealRepository.findAll();
        return (int) meals.stream().filter(m -> m.getUser().getId().equals(user.getId())
                && m.getLocalDate().isEqual(localDate)).count();
    }

    /**
     * Метод проверки калорий в день
     */
    public String checkingCalorieNorms(long id, LocalDate localDate) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found user"));
        int calorieNorms = serviceUser.сalorieСalculation(user.getGoal(), user);
        int caloriesByDay = getAllCaloriesByDay(id, localDate);
        if (caloriesByDay <= calorieNorms) {
            return "Вы уложились в норму каллорий в день, количество каллорий " + caloriesByDay;
        } else return "Вы привысили норму калорий на " + (caloriesByDay - calorieNorms);
    }

    /**
     * История приемов пиши за день
     */
    public AllMealByDayDTO getAllMealByDay(LocalDate localDate) {
        List<Meal> meals = mealRepository.findAll();
        List<DishDTO> dishDTOS = meals.stream()
                .filter(m -> m.getLocalDate().isEqual(localDate))
                .flatMap(m -> m.getDishes().stream())
                .map(DishDTO::new) // Используем конструктор DishDTO(Dish dish)
                .toList();
        return new AllMealByDayDTO(localDate, dishDTOS);
    }
}
