package br.com.wbs.modules.game.controllers;

import br.com.wbs.modules.game.dto.GameDetailsDTO;
import br.com.wbs.modules.game.dto.GameRegisterDTO;
import br.com.wbs.modules.game.useCases.CreateGameUseCase;
import br.com.wbs.modules.game.useCases.FindAllGamesUseCase;
import br.com.wbs.modules.game.useCases.FindByIdGameUseCase;
import br.com.wbs.modules.studio.dto.StudioDetailsDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/game")
@Tag(name = "Game", description = "Endpoints for use with Game")
public class GameController {

    @Autowired
    private CreateGameUseCase createGameUseCase;
    @Autowired
    private FindAllGamesUseCase findAllGamesUseCase;
    @Autowired
    private FindByIdGameUseCase findByIdGameUseCase;

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

    @GetMapping("/")
    @Operation(summary = "Get all games", description = "Retrieve a list of all games in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Games retrieved successfully",
                    content = @Content(schema = @Schema(implementation = GameDetailsDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Object> getAllGames() {
        try {
            var allGames = findAllGamesUseCase.execute();
            return ResponseEntity.ok().body(allGames);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get game by ID", description = "Retrieve a game's details using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game found",
                    content = @Content(schema = @Schema(implementation = GameDetailsDTO.class))),
            @ApiResponse(responseCode = "404", description = "Game not found",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Object> getOneGame(@PathVariable("id") UUID idGame, UUID idStudio) {
        try {
            var game = findByIdGameUseCase.execute(idGame, idStudio);
            return ResponseEntity.ok().body(game);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
