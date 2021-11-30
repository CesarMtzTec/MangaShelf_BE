package mx.tec.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.tec.web.entity.ToRead;

@Repository
public interface ToReadListRepository extends JpaRepository<ToRead, Long> {
    @Query("FROM ToRead WHERE userId = userId")
    List<ToRead> getToReadList(@Param("userId") long userId);
}
