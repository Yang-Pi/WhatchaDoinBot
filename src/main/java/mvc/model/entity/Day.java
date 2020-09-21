package mvc.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JsonManagedReference
    private User user;

    @Column(nullable = false)
    private Date date;

    @OneToMany
    Map<Date, Category> businesses = new HashMap<>();

    public Day() {
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Date, Category> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Map<Date, Category> businesses) {
        this.businesses = businesses;
    }

    public void addBusiness(Date date, Category category) {
        businesses.put(date, category);
    }

    //UTILS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day = (Day) o;

        if (user != null ? !user.equals(day.user) : day.user != null) return false;
        return date != null ? date.equals(day.date) : day.date == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
