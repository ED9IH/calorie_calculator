Убедитесь что порты 8079 и 5432 у вас свободны
Убедитесь что у вас есть Docker Compose.
Приложение для подсчета нормы калорий.
Что преложение умеет:
- отчет за день с суммой всех калорий и приемов пищи;
- проверка, уложился ли пользователь в свою дневную норму калорий;
- история питания по дням.
Чтобы запустить приложение, клонируйте уго к себе в среду разработки.
Запустите файл db.yml Docker создаст базу PostgresSQL со всеми нужными таблицами
Запустите приложение.
Для того что бы добавить пользователя отправьте POST запрос на http://localhost:8079/user/add в формате Json пример:
{
  "name": "Иван Иванов",
  "email": "ivan@example.com",
  "age": 30,
  "weight": 75,
  "height": 180,
  "goal": "WEIGHT_LOSS"

}
Запрос выдаст вам дневную норму каллорий.
Добавьте несколько блюд, отправьте POST запрос на http://localhost:8079/meal/add в формате Json пример:
{
  "user":{
        "id":1
    } ,
  "dishes":[ {
    "name": "Куриная грудка",
    "numberOfCalories": 200,
    "proteins": 30,
    "fats": 5,
    "carbohydrates": 0
  },
  {
    "name": "Куриная грудка",
    "numberOfCalories": 300,
    "proteins": 30,
    "fats": 5,
    "carbohydrates": 0
  }
  ]
}
Запрос выдаст Response Прием пищи добавлен
Посмотреть сумму калорий за день, отправьте GET запрос http://localhost:8079/meal/sumCalories/{id}/{dd-MM-yyyy} не забудьте указать актульное число. И id пользователя. Приложение задает автоматически текущию дату.
Проверка уложились ли вы в дневную норму каллорий отправьте GET запрос http://localhost:8079/meal/checkCalories/{id}/{dd-MM-yyyy} не забудьте указать актульное число. И id пользователя. Приложение задает автоматически текущию дату.
Посмотреть список блюд определенный день отправьте GET запрос http://localhost:8079/meal/getAllMealByDay/{id}/{dd-MM-yyyy}
Файл для Postman
[calorie_calculator.postman_collection.json](https://github.com/user-attachments/files/19455167/calorie_calculator.postman_collection.json)
