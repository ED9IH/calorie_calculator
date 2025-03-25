package сalorieСalculator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import сalorieСalculator.Entity.Meal;
import сalorieСalculator.Service.ServiceMeal;
import сalorieСalculator.dto.AllMealByDayDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Конроллер приема пищи
 */
@RestController
@RequestMapping("/meal")
public class ControllerMeal {
    private final ServiceMeal serviceMeal;
    @Autowired
    public ControllerMeal(ServiceMeal serviceMeal) {
        this.serviceMeal = serviceMeal;
    }
    /**
     * Метод добавления приема пищи в БД
     */
    @PostMapping("/add")
    public ResponseEntity<String> addMeal(@RequestBody Meal meal) {
        serviceMeal.addMeal(meal);
        return ResponseEntity.ok("Прием пищи добавлен");
    }
    /**
     * Метод получение суммы каллорий за день
     * Количество приемов пищи
     */
    @GetMapping("/sumCalories/{userId}/{date}")
    public ResponseEntity<String> getAllCaloriesByDay(
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,@PathVariable long userId) {
        return ResponseEntity.ok("Сумма калорий за число " + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " составляет " + serviceMeal.getAllCaloriesByDay(userId,date)
        + ", количество приемов пищи " + serviceMeal.getAllMealCountByDay(userId,date));
    }

    /**
     * Метод проверки калорий в день
     */
    @GetMapping("/checkCalories/{userId}/{date}")
    public ResponseEntity<String> checkingCalorieNorms(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,@PathVariable long userId){
        return ResponseEntity.ok(serviceMeal.checkingCalorieNorms(userId,date));
    }
    /**
     * История приемов пиши за день
     */
    @GetMapping("/getAllMealByDay/{date}")
    public ResponseEntity<AllMealByDayDTO>getAllMealByDay(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return ResponseEntity.ok(serviceMeal.getAllMealByDay(date));
    }
}
