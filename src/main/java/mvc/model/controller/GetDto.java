package mvc.model.controller;

import mvc.model.entity.Category;
import mvc.model.entity.Day;

import java.util.Set;

public class GetDto {
    private Set<Day> days;
    private Set<Category> categories;

    public GetDto(Set<Day> days, Set<Category> categories) {
        this.days = days;
        this.categories = categories;
    }

    public Set<Day> getDays() {
        return days;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
