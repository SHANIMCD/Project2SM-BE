package com.qa.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.domains.Workout;

@Repository
public interface WorkoutRepo extends JpaRepository<Workout, Long> {

	List<Workout> findByTitle(String title);
}
