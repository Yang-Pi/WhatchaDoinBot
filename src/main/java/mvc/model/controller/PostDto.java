package mvc.model.controller;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class PostDto {
    private long telegramId;
    private Date date;
    private String categoryName;

    public PostDto(long telegramId, Date date, String categoryName) {
        this.telegramId = telegramId;
        this.date = date;
        this.categoryName = categoryName;
    }

    public long getTelegramId() {
        return telegramId;
    }

    public Date getDate() {
        return date;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
