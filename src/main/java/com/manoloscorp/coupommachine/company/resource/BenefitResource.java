package com.manoloscorp.coupommachine.company.resource;

import com.manoloscorp.coupommachine.company.entity.Benefit;
import com.manoloscorp.coupommachine.company.entity.User;
import com.manoloscorp.coupommachine.company.repository.BenefitRepository;
import com.manoloscorp.coupommachine.company.repository.UserRepository;
import com.manoloscorp.coupommachine.company.resource.payload.BenefitRequest;
import com.manoloscorp.coupommachine.company.resource.payload.BenefitResponse;
import com.manoloscorp.coupommachine.company.shared.RestConstants;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = RestConstants.APPLICATION_API + RestConstants.BENEFIT, produces = MediaType.APPLICATION_JSON_VALUE)
public class BenefitResource {

    private BenefitRepository repository;
    private UserRepository userRepository;
    private final ModelMapper mapper;

    public BenefitResource(BenefitRepository repository, UserRepository userRepository, ModelMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getBenefitByUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        List<Benefit> benefits = user.getBenefits();
        return ResponseEntity.ok().body(benefits);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createBenefit(@RequestBody BenefitRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow();

        Benefit benefit = mapper.map(request, Benefit.class);

        benefit.setUser(user);

        user.getBenefits().add(benefit);

        userRepository.save(user);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(benefit.getId())
                .toUri();

        return ResponseEntity.created(uri).body(benefit);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateBenefit(@PathVariable Long id, @RequestBody Benefit request) {

        Optional<Benefit> benefit = repository.findById(id);

        if (request != null) {
            request.setId(benefit.get().getId());
            repository.save(request);
            return ResponseEntity.ok().body(request);
        }
        return ResponseEntity.ok().body("Unable to update benefit");
    }
}
