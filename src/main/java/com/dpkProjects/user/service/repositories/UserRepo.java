package com.dpkProjects.user.service.repositories;

import com.dpkProjects.user.service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <User,String>{


}
