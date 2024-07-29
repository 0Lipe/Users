package com.example.Usuarios.Repository;

import com.example.Usuarios.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
