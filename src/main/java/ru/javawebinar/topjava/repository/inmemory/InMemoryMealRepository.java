package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 20, 10, 0), "Breakfast", 500), 2);
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 20, 10, 0), "Lunch", 1500), 2);
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 20, 10, 0), "Dinner", 800), 2);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {} for userId {}", meal, userId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> {
            if (oldMeal.getUserId() == userId) {
                meal.setUserId(userId);
                return meal;
            }
            return oldMeal;
        });
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {} for userId {}", id, userId);
        Meal meal = repository.get(id);
        if (meal != null) {
            return meal.getUserId() == userId && repository.remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {} for userId {}", id, userId);
        Meal meal = repository.get(id);
        if (meal != null) {
            return meal.getUserId() == userId ? meal : null;
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll");
        return getAllFiltered(userId, meal -> true);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return getAllFiltered(userId, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDateTime(), startDate, endDate));
    }

    private List<Meal> getAllFiltered(int userId, Predicate<Meal> filter) {
        log.info("getAll");
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

