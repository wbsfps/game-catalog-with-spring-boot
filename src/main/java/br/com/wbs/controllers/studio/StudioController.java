package br.com.wbs.controllers.studio;

import br.com.wbs.domain.studio.StudioDTODetails;
import br.com.wbs.domain.studio.StudioDTORegister;
import br.com.wbs.services.studio.StudioServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/studio")
public class StudioController {

    @Autowired
    private StudioServices services;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudioDTODetails> create(@Valid @RequestBody StudioDTORegister dto) {
        var newStudio = services.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudio);
    }

    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudioDTODetails> findById(@PathVariable("uuid") UUID uuid) {
        var studio = services.findById(uuid);
        return ResponseEntity.ok().body(studio);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudioDTODetails>> findAll() {
        var studios = services.findAll();
        return ResponseEntity.ok(studios);
    }
}
