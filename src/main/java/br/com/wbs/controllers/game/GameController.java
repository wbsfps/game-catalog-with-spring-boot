package br.com.wbs.controllers.game;

import br.com.wbs.domain.game.GameDTODetails;
import br.com.wbs.domain.game.GameDTORegister;
import br.com.wbs.domain.game.GameDTOUpdateData;
import br.com.wbs.services.game.GameServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    @Autowired
    private GameServices services;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GameDTODetails>> findAll() {
        return ResponseEntity.ok().body(services.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameDTODetails> findById(@PathVariable("id") UUID uuid) {
        var game = services.findById(uuid);
        return ResponseEntity.ok().body(game);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameDTODetails> create(@RequestBody GameDTORegister gameRegister) {
        GameDTODetails game = services.create(gameRegister);
        return ResponseEntity.ok(game);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<GameDTODetails> update(@Valid @RequestBody GameDTOUpdateData dto) {
        GameDTODetails updateGame = services.update(dto);
        return ResponseEntity.ok().body(updateGame);
    }
}
