package com.manoloscorp.coupommachine.company.resource;

import com.manoloscorp.coupommachine.company.entity.User;
import com.manoloscorp.coupommachine.company.repository.UserRepository;
import com.manoloscorp.coupommachine.company.resource.payload.UserRequest;
import com.manoloscorp.coupommachine.company.shared.RestConstants;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = RestConstants.APPLICATION_API + RestConstants.PROFILE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    private UserRepository repository;
    private final ModelMapper mapper;

    public UserResource(UserRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) {

        Optional<User> user = repository.findById(id);

        return ResponseEntity.ok().body(user);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProfile(@RequestBody UserRequest request) {

        User user = mapper.map(request, User.class);

        repository.save(user);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(request);
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
