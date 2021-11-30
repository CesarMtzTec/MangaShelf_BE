package mx.tec.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.tec.web.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("FROM Review WHERE mangaId = :mangaId")
    List<Review> findByMangaId(@Param("mangaId") long mangaId);
}
