package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.FormResponse;

@Repository
public interface FormResponseRepo extends JpaRepository<FormResponse, Integer> {

}
