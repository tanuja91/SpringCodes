package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.accessingdatamysql.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByname(@Param("Vendor_Name") String title);
}
