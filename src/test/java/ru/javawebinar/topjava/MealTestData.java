package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID1 = START_SEQ + 3;
    public static final int MEAL_ID_NOT_FOUND = 100000;

    public static final Meal userMeal1 = new Meal(MEAL_ID1, LocalDateTime.of(2022, 10, 29, 7, 0), "breakfast User", 500);
    public static final Meal userMeal2 = new Meal(MEAL_ID1 + 1, LocalDateTime.of(2022, 10, 29, 13, 0), "lunch User", 1500);
    public static final Meal userMeal3 = new Meal(MEAL_ID1 + 2, LocalDateTime.of(2022, 10, 29, 17, 0), "dinner User", 1000);
    public static final Meal userMeal4 = new Meal(MEAL_ID1 + 3, LocalDateTime.of(2022, 10, 30, 12, 0), "lunch User", 1200);

    public static final Meal adminMeal1 = new Meal(MEAL_ID1 + 4, LocalDateTime.of(2022, 10, 25, 8, 0), "breakfast Admin", 300);
    public static final Meal adminMeal2 = new Meal(MEAL_ID1 + 5, LocalDateTime.of(2022, 10, 25, 12, 0), "lunch Admin", 1000);
    public static final Meal adminMeal3 = new Meal(MEAL_ID1 + 6, LocalDateTime.of(2022, 10, 25, 19, 0), "dinner Admin", 800);

    public static final List<Meal> userMeals = Arrays.asList(userMeal4, userMeal3, userMeal2, userMeal1);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2022, 10, 20, 7, 0), "New lunch", 500);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL_ID1, LocalDateTime.of(2022, 10, 31, 14, 30), "Updated lunch", 750);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
