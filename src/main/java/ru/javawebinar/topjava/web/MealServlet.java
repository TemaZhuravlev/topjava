package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.storage.StorageMap;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealServlet extends HttpServlet {
    private Storage storageMap;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storageMap = new StorageMap();
        for (Meal meal : MealTestData.MEALS) {
            storageMap.save(meal);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if (action == null) {
            List<MealTo> mealToList = MealsUtil.filteredByStreams(storageMap.getAll(), MealTestData.MIN_TIME,
                    MealTestData.MAX_TIME, MealTestData.CALORIES_PER_DAY);
            request.setAttribute("mealsTo", mealToList);
            request.getRequestDispatcher("/WEB-INF/jsp/meals.jsp").forward(request, response);
            return;
        }
        Meal meal;
        switch (action) {
            case "delete":
                storageMap.delete(Integer.parseInt(id));
                response.sendRedirect("meals");
                return;
            case "add":
                meal = new Meal();
                break;
            case "edit":
                meal = storageMap.get(Integer.parseInt(id));
                break;
            default:
                throw new IllegalArgumentException("Action " + action + "is illegal");
        }
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Integer id = Integer.parseInt(request.getParameter("id"));
        Meal meal;
        if (storageMap.get(id) == null) {
            meal = new Meal();
        } else {
            meal = storageMap.get(id);
        }
        request.getParameter("date-time");
        LocalDateTime ldt = LocalDateTime.parse(request.getParameter("date-time"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        meal.setDateTime(ldt);
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        if (storageMap.get(id) == null) {
            storageMap.save(meal);
        } else {
            storageMap.update(meal);
        }
        response.sendRedirect("meals");
    }
}
