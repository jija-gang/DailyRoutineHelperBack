package com.DailyRoutineHelper.storage.repository;

import com.DailyRoutineHelper.data.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
