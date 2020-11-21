package com.manoloscorp.coupommachine.company.resource;

import com.manoloscorp.coupommachine.company.entity.User;
import com.manoloscorp.coupommachine.company.repository.UserRepository;
import com.manoloscorp.coupommachine.company.resource.payload.AuthenticateRequest;
import com.manoloscorp.coupommachine.company.shared.RestConstants;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = RestConstants.APPLICATION_API + RestConstants.RESOURCE_AUTH, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthResource {

    private UserRepository repository;

    public AuthResource(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticateRequest request) throws Exception {
        User user = repository.findByEmail(request.getEmail());

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity
                    .badRequest()
                    .body(("Error: Password is incorrect!"));
        }

        return ResponseEntity.ok("User logged");
    }
}
