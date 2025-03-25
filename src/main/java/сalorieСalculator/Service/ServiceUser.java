package сalorieСalculator.Service;
/**
 * Сервис пользователя
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import сalorieСalculator.Entity.User;
import сalorieСalculator.Enum.Goal;
import сalorieСalculator.Repository.UserRepository;

@Service
public class ServiceUser {

    private final UserRepository userRepository;
    /**
     * Внедрение зависимости через конструктор
     */
    @Autowired
    public ServiceUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Метод добавления Пользователя(User)
     */
    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }
    /**
     * Метод получение user по id
     */
    public User getUserid(long id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("Пользователь не найден"));
    }
    /**
     * Формула расчета нормы каллорий для определеной цели
     */
    public int сalorieСalculation(Goal goal,User user){
        double result=655.1+(9.56*user.getWeight())+(1.85*user.getHeight())-(4.67*user.getAge());
        System.out.println(result);
        if(goal.equals(Goal.WEIGHT_LOSS)){
            return (int)Math.floor(result*0.8);
        }
        if(goal.equals(Goal.WEIGHT_GAIN)){
            return (int)Math.floor(result*1.2);
        }
        return (int)result;
    }
}
