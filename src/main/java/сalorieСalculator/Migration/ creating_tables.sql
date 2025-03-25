create table "user"
(
    id     bigint generated always as identity
        constraint "User_pk"
            primary key,
    name   varchar not null,
    email  varchar not null,
    age    integer not null,
    weight integer not null,
    height integer not null,
    goal   varchar not null
);

comment on column "user".id is 'Id пользователя';

comment on column "user".name is 'Имя пользователя';

comment on column "user".email is 'Email пользователя';

comment on column "user".age is 'Возраст пользователя';

comment on column "user".weight is 'Вес пользователя';

comment on column "user".height is 'Рост пользователя';

comment on column "user".goal is 'Цель пользователя';

create table meal
(
    id      bigint generated always as identity
        constraint "Meal_pk"
            primary key,
    user_id bigint
        constraint meal_user_id_fk
            references "user",
    data    date not null
);

comment on column meal.id is 'Id приема пищи';

create table dish
(
    id            bigint generated always as identity
        constraint "Dish_pk"
            primary key,
    name          varchar not null,
    calories      integer not null,
    proteins      integer not null,
    fats          integer not null,
    carbohydrates integer not null,
    id_meal       bigint
        constraint fk8f5sv5b7w0tjamtpu3na80pyg
            references meal
);

comment on column dish.id is 'Id Блюда';

comment on column dish.calories is 'Калорийность блюда';

comment on column dish.proteins is 'Белки';

comment on column dish.fats is 'Жиры';

comment on column dish.carbohydrates is 'Углеводы';

