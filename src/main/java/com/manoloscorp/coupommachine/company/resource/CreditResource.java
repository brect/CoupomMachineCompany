package com.manoloscorp.coupommachine.company.resource;

import com.manoloscorp.coupommachine.company.entity.User;
import com.manoloscorp.coupommachine.company.repository.UserRepository;
import com.manoloscorp.coupommachine.company.shared.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        User user = repository.findById(id).orElseThrow();

        double currentCredit = user.getCredit();

        currentCredit += request;

        user.setCredit(currentCredit);

        repository.save(user);

        return ResponseEntity.ok().body(user.getCredit());
    }

}
