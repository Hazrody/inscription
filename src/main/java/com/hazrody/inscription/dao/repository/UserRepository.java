package com.hazrody.inscription.dao.repository;

import com.hazrody.inscription.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
