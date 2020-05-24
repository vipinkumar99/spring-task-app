package org.edu.cloud.repository;

import java.util.List;

import org.edu.cloud.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	@Query(value = "select t.mobileNo from teachers t", nativeQuery = true)
	List<String> findMobileNo();
}
