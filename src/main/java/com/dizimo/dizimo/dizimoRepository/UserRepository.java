package com.dizimo.dizimo.dizimoRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dizimo.dizimo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}