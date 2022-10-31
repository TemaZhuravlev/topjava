package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MealTestData {
    public static final int MEAL_ID1 = 100003;
    public static final int MEAL_ID_NOT_FOUND = 100000;

    public static final Meal mealUser1 = new Meal(MEAL_ID1, LocalDateTime.of(2022, 10, 29, 7, 0), "breakfast User", 500);
    public static final Meal mealUser2 = new Meal(MEAL_ID1+1, LocalDateTime.of(2022, 10, 29, 13, 0), "lunch User", 1500);
    public static final Meal mealUser3 = new Meal(MEAL_ID1+2, LocalDateTime.of(2022, 10, 29, 17, 0), "dinner User", 1000);
    public static final Meal mealUser4 = new Meal(MEAL_ID1+3, LocalDateTime.of(2022, 10, 30, 12, 0), "lunch User", 1200);

    public static final Meal mealAdmin1 = new Meal(MEAL_ID1+4, LocalDateTime.of(2022, 10, 25, 8, 0), "breakfast Admin", 300);
    public static final Meal mealAdmin2 = new Meal(MEAL_ID1+5, LocalDateTime.of(2022, 10, 25, 12, 0), "lunch Admin", 1000);
    public static final Meal mealAdmin3 = new Meal(MEAL_ID1+6, LocalDateTime.of(2022, 10, 25, 19, 0), "dinner Admin", 800);

    public static final List<Meal> meals = Arrays.asList(mealUser4, mealUser3, mealUser2, mealUser1);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2022, 10, 20, 7, 0), "New lunch", 500);
    }

    public static Meal getUpdated(){
        return new Meal(MEAL_ID1, mealUser1.getDateTime(), "Updated lunch", 750);
    }
}
