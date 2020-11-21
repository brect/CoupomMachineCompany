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
@RequestMapping(value = RestConstants.APPLICATION_API + RestConstants.CREDIT, produces = MediaType.APPLICATION_JSON_VALUE)
public class CreditResource {

    private UserRepository repository;

    public CreditResource(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> getCredits() {
        return new ResponseEntity("", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCredit(@PathVariable Long id, @RequestBody Long request) {

        Optional<User> user = repository.findById(id);

        double currentCredit = user.get().getCredit();

        currentCredit += request;

        user.get().setCredit(currentCredit);

        return ResponseEntity.ok().body(user.get().getCredit());
    }

}
