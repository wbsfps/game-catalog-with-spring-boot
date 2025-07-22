package br.com.wbs.modules.studio.controllers;

import br.com.wbs.modules.studio.dto.StudioDetailsDTO;
import br.com.wbs.modules.studio.dto.StudioRegisterDTO;
import br.com.wbs.modules.studio.useCases.CreateStudioUseCase;
import br.com.wbs.modules.studio.useCases.FindAllStudiosUseCase;
import br.com.wbs.modules.studio.useCases.FindByIdStudioUseCase;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/studio")
@Tag(name = "Studio", description = "Endpoints for use with Studio")
public class StudioController {

    @Autowired
    private CreateStudioUseCase createStudioUseCase;
    @Autowired
    private FindAllStudiosUseCase findAllStudiosUseCase;
    @Autowired
    private FindByIdStudioUseCase findByIdStudioUseCase;

    @PostMapping("/")
    @Operation(summary = "Create a new Studio", description = "Add new Studio to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Studio created successfully",
            content = @Content(schema = @Schema(implementation = StudioDetailsDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
            content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Object> create(@Valid @RequestBody StudioRegisterDTO dto) {
        try {
            var newStudio = createStudioUseCase.execute(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newStudio);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @Operation(summary = "Get all studios", description = "Retrieve a list of all studios in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Studios retrieved successfully",
                    content = @Content(schema = @Schema(implementation = StudioDetailsDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getAllStudios() {
        try {
            var allStudios = findAllStudiosUseCase.execute();
            return ResponseEntity.ok().body(allStudios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get studio by ID", description = "Retrieve a studio's details using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Studio found",
                    content = @Content(schema = @Schema(implementation = StudioDetailsDTO.class))),
            @ApiResponse(responseCode = "404", description = "Studio not found",
                    content = @Content(schema = @Schema()))
    })
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getStudio(@PathVariable("id") UUID id) {
        try {
            var studio = findByIdStudioUseCase.execute(id);
            return ResponseEntity.ok().body(studio);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
