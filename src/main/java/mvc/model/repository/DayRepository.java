package mvc.model.repository;

import mvc.model.entity.Day;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.Optional;

public interface DayRepository extends CrudRepository<Day, Integer> {
    Optional<Day> findByDate(Date date);
}
