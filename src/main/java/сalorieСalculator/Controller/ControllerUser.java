package сalorieСalculator.Controller;

import сalorieСalculator.Entity.User;
import сalorieСalculator.Service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для User
 */
@RestController
@RequestMapping("/user")
public class ControllerUser {
    private final ServiceUser serviceUser;

    @Autowired
    public ControllerUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    /**
     * Метод добавления Пользователя(User)
     */
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        serviceUser.addUser(user);
        return ResponseEntity.ok("Пользователь добавлен " + "Суточная норма каллорий " + serviceUser.сalorieСalculation(user.getGoal(), user));
    }
}
