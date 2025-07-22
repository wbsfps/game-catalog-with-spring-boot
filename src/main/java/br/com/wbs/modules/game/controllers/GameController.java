package br.com.wbs.modules.game.controllers;

import br.com.wbs.modules.game.dto.GameDetailsDTO;
import br.com.wbs.modules.game.dto.GameRegisterDTO;
import br.com.wbs.modules.game.useCases.CreateGameUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/game")
@Tag(name = "Game", description = "Endpoints for use with Game")
public class GameController {

    @Autowired
    private CreateGameUseCase createGameUseCase;

    @PostMapping("/")
    @Operation(summary = "Create a new game", description = "Add a new game in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game created successfully",
            content = @Content(schema = @Schema(implementation = GameDetailsDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Object> create(@Valid @RequestBody GameRegisterDTO gameRegisterDTO) {
        try {
            var newGame = createGameUseCase.execute(gameRegisterDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
