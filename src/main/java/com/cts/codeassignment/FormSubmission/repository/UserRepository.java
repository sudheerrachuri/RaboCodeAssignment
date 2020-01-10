package com.cts.codeassignment.FormSubmission.repository;

import com.cts.codeassignment.FormSubmission.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
