package com.qa.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.domains.Exercise;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, Long> {
	
	List<Exercise> findByName(String name);
}
