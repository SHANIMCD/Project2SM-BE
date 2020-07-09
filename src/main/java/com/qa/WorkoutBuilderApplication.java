package com.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.qa.domains.Exercise;
import com.qa.repos.ExerciseRepo;
import com.qa.repos.WorkoutRepo;

@SpringBootApplication
public class WorkoutBuilderApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(WorkoutBuilderApplication.class, args);

		ExerciseRepo exDao = beanBag.getBean(ExerciseRepo.class);
		exDao.save(new Exercise(0, "Star jumps", "heartrate",
				"https://thumbs.dreamstime.com/b/jumping-jack-sport-exersice-silhouettes-woman-doing-exercise-workout-training-vector-illustration-jumping-jack-sport-exersice-119786219.jpg",
				null));
		System.out.println(exDao.findByName("Star jumps"));

		WorkoutRepo wDao = beanBag.getBean(WorkoutRepo.class);
		System.out.println(wDao.findByTitle("workout One"));
	}

}
