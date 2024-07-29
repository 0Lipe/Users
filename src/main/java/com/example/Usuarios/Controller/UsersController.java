package com.example.Usuarios.Controller;

import com.example.Usuarios.DTO.UsersDTO;
import com.example.Usuarios.Model.Users;
import com.example.Usuarios.Repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersRepository repository;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UsersDTO data){
        Users users = new Users();
        users.setEmail(data.email());
        users.setNome(data.nome());
        Users saveUsers = repository.save(users);
        return ResponseEntity.status(HttpStatus.OK).body(saveUsers);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@RequestBody UsersDTO data, @PathVariable long id) {
        Optional<Users> users = repository.findById(id);
        var usersModel = users.get();
        usersModel.setNome(data.nome());
        usersModel.setEmail(data.email());
        repository.save(usersModel);
        return ResponseEntity.status(HttpStatus.OK).body(usersModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("successfully deleted");
    }
}

