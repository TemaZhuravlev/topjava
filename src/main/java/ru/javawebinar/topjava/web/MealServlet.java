package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.MealStorage;
import ru.javawebinar.topjava.storage.MemoryMealStorage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private MealStorage storage;
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = new MemoryMealStorage();
        log.debug("Initialised Storage");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            List<MealTo> mealToList = MealsUtil.filteredByStreams(storage.getAll(), LocalTime.MIN,
                    LocalTime.MAX, MemoryMealStorage.CALORIES_PER_DAY);
            request.setAttribute("mealsTo", mealToList);
            request.getRequestDispatcher("meals.jsp").forward(request, response);
            log.debug("Forward to meals.jsp");
            return;
        }
        switch (action) {
            case "delete":
                String id = request.getParameter("id");
                storage.delete(Integer.parseInt(id));
                response.sendRedirect("meals");
                log.debug("Delete meal id=" + id + " and redirect to meals");
                return;
            case "add":
                Meal meal = new Meal();
                request.setAttribute("meal", meal);
                break;
            case "edit":
                id = request.getParameter("id");
                meal = storage.get(Integer.parseInt(id));
                request.setAttribute("meal", meal);
                break;
            default:
                response.sendRedirect("meals");
                log.debug("action=" + action + ", redirect to meals");
                return;

        }
        request.setAttribute("action", action);
        request.getRequestDispatcher("editMeal.jsp").forward(request, response);
        log.debug("Forward to edit.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime ldt = LocalDateTime.parse(request.getParameter("date-time"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal;
        boolean isEmptyId = id == null || id.isEmpty();
        if (isEmptyId) {
            meal = new Meal(ldt, description, calories);
            storage.create(meal);
            log.debug("Create new meal");
        } else {
            meal = new Meal(Integer.parseInt(id), ldt, description, calories);
            storage.update(meal);
            log.debug("Update meal");
        }
        response.sendRedirect("meals");
        log.debug("Redirect to meals");
    }
}
