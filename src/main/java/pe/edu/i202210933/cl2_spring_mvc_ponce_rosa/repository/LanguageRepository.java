package pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}