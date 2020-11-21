package com.manoloscorp.coupommachine.company.resource;

import com.manoloscorp.coupommachine.company.entity.Benefit;
import com.manoloscorp.coupommachine.company.repository.BenefitRepository;
import com.manoloscorp.coupommachine.company.shared.RestConstants;
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

    public BenefitResource(BenefitRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> getAllBenefits() {
        List<Benefit> benefits = repository.findAll();
        return new ResponseEntity<List>(benefits, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createBenefit(@RequestBody Benefit request) {

        repository.save(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();

        return ResponseEntity.created(uri).body(request);
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
