package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {

    int size();

    void clear();

    void update(Meal meal);

    void save(Meal meal);

    void delete(Integer id);

    Meal get(Integer id);

    List<Meal> getAll();
}
