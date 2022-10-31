package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_ID1, USER_ID);
        assertThat(meal).usingRecursiveComparison().isEqualTo(MealTestData.mealUser1);
    }

    @Test
    public void getNotFoundMealFromUser() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID_NOT_FOUND, USER_ID));
    }

    @Test
    public void getNotFoundMealFromAlienUser() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID1, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID1, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID1, USER_ID));
    }

    @Test
    public void deletedNotFoundMealFromUser() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_ID_NOT_FOUND, USER_ID));
    }

    @Test
    public void deletedNotFoundMealFromAlienUser() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_ID1, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> meals = service.getBetweenInclusive(LocalDate.of(2022, 10, 29),
                LocalDate.of(2022, 10, 29), USER_ID);
        assertThat(meals).usingRecursiveComparison().isEqualTo(Arrays.asList(mealUser3, mealUser2, mealUser1));
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        assertThat(meals).usingRecursiveComparison().isEqualTo(MealTestData.meals);
    }

    @Test
    public void update() {
        Meal updated = MealTestData.getUpdated();
        service.update(updated, USER_ID);
        assertThat(updated).usingRecursiveComparison().isEqualTo(service.get(MEAL_ID1, USER_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(MealTestData.getNew(), USER_ID);
        Meal newMeal = MealTestData.getNew();
        newMeal.setId(created.getId());
        assertThat(created).usingRecursiveComparison().isEqualTo(newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, mealUser1.getDateTime(), "Duplicate DateTime", 500), USER_ID));
    }
}