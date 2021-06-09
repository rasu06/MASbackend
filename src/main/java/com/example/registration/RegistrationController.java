package com.example.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController

@RequestMapping(path="api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;


    //We generally don't write the logic directly in controller, we create a separate class for logic,and pass our request body to it

    @PostMapping
     public String register(@RequestBody RegistrationRequest request){
        System.out.println("fantaaaaa");
        System.out.println(request.getPassword());
         return registrationService.register(request);              //Registration starts==>RegistrationService will execute the logic for registration
     }

     @GetMapping(path = "confirm")
     public String confirm(@RequestParam("token")String token){
        return registrationService.confirmToken(token);
     }

}
