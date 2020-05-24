package org.edu.cloud.repository;

import java.util.List;

import org.edu.cloud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query(value = "select s.mobileNo from students s", nativeQuery = true)
	List<String> findMobileNo();
}
