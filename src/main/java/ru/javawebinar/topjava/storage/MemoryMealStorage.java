package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryMealStorage implements MealStorage {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<Integer, Meal> storage = new ConcurrentHashMap<>();

    public static final int CALORIES_PER_DAY = 2000;

    private final List<Meal> meals = Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );

    public MemoryMealStorage() {
        for (Meal meal : meals) {
            create(meal);
        }
    }

    @Override
    public Meal update(Meal meal) {
        if (storage.get(meal.getId()) == null) {
            throw new IllegalStateException("Meal " + meal.getId() + " not exist");
        }
        storage.put(meal.getId(), meal);
        return storage.get(meal.getId());
    }

    @Override
    public Meal create(Meal meal) {
        int id = counter.getAndIncrement();
        meal.setId(id);
        storage.put(id, meal);
        return storage.get(id);
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
    }

    @Override
    public Meal get(int id) {
        return storage.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }
}
