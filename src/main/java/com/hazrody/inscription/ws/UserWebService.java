package com.hazrody.inscription.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazrody.inscription.dao.entity.User;
import com.hazrody.inscription.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserWebService {

    @Autowired
    UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Operation(
            summary = "Récupèrer un utilisateur par son identifiant",
            description = "Récupèrer un utilisateur par son identifiant",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = User.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Identifiant de l'utilisateur invalide",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }


    @Operation(
            summary = "Créer un utilisateur.",
            description = "Créer un utilisateur.",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = User.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Idenfiant de l'utilisateur invalide",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @PostMapping("/")
    public ResponseEntity<User> createUser(
            @RequestBody User newUser) throws JsonProcessingException {
        User user = userService.createUser(newUser);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        rabbitTemplate.convertAndSend("user-exchange", "user", userJson);
        return ResponseEntity.ok(user);
    }


}
