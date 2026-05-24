package SS5.course.repository;

import SS5.course.dto.response.CourseResponseV2;
import SS5.course.model.Course;
import SS5.course.model.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.status = :status")
    Page<Course> findAllByStatus(@Param("status") CourseStatus status, Pageable pageable);
    @Query("SELECT new SS5.course.dto.response.CourseResponseV2(c.id, c.title, c.status) " +
            "FROM Course c WHERE c.status = :status")
    Page<CourseResponseV2> findAllByStatusProjection(@Param("status") CourseStatus status, Pageable pageable);
}