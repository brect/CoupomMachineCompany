package com.manoloscorp.coupommachine.company.resource;

import com.manoloscorp.coupommachine.company.entity.User;
import com.manoloscorp.coupommachine.company.repository.UserRepository;
import com.manoloscorp.coupommachine.company.shared.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = RestConstants.APPLICATION_API + RestConstants.PROFILE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    private UserRepository repository;

    public UserResource(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) {

        Optional<User> user = repository.findById(id);

        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody User request) {

        Optional<User> user = repository.findById(id);

        if (request != null) {
            request.setId(user.get().getId());
            repository.save(request);
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.ok().body("Unable to update user");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
