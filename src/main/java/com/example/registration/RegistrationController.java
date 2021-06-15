package com.example.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path="api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;


    //We generally don't write the logic directly in controller, we create a separate class for logic,and pass our request body to it

    @PostMapping
     public ResponseEntity<Void> register( RegistrationRequest request){
        System.out.println("fantaaaaa");
        System.out.println(request.getPassword());
         return registrationService.register(request);              //Registration starts==>RegistrationService will execute the logic for registration
     }

     @GetMapping(path = "confirm")
     public ResponseEntity<Void> confirm(@RequestParam("token")String token){
        return registrationService.confirmToken(token);
     }



}
