package mvc.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Time;
import java.util.*;

@Entity
@Table(name = "telegram_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany (cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Day> days = new HashSet<>();
    @OneToMany (cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Category> categories = new HashSet<>();

    @Column(unique = true)
    private long telegramId;

    private long checkingInterval; //milliseconds
    private Date sleepTimeBegin;
    private Date sleepTimeEnd;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public Set<Day> getDays() {
        return days;
    }

    public void setDays(Set<Day> days) {
        this.days = days;
    }

    public void addDay(Day day) {
        days.add(day);
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void deleteCategory(Category category) {
        categories.remove(category);
    }

    public long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(long telegramId) {
        this.telegramId = telegramId;
    }

    public long getCheckingInterval() {
        return checkingInterval;
    }

    public void setCheckingInterval(long checkingInterval) {
        this.checkingInterval = checkingInterval;
    }

    public Date getSleepTimeBegin() {
        return sleepTimeBegin;
    }

    public void setSleepTimeBegin(Date sleepTimeBegin) {
        this.sleepTimeBegin = sleepTimeBegin;
    }

    public Date getSleepTimeEnd() {
        return sleepTimeEnd;
    }

    public void setSleepTimeEnd(Date sleepTimeEnd) {
        this.sleepTimeEnd = sleepTimeEnd;
    }
}
