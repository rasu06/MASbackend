package com.example.appuser;

import com.example.registration.token.ConfirmationToken;
import com.example.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{


    private final static String USER_NOT_FOUND_MSG="user with username %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;




    // this is the method which spring security will invoke during authentication
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public String signUpUser(AppUser appUser)
    {
       boolean emailExist= appUserRepository.findByEmail(appUser.getEmail())      //appUser repository mai is username ko find karo
                              .isPresent();
       /*boolean userEmailExist= appUserRepository.findByEmail(appUser.getEmail())
                              .isPresent();*/
      if(emailExist) {
          throw new IllegalStateException("Username is already present");
      }
      /*if(userEmailExist){
          throw new IllegalStateException("Email is already used");
      }*/

    String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());    //encode the users password
        appUser.setPassword(encodedPassword);                     // Update the password with encoded one

        appUserRepository.save(appUser);                      // Saving the user to our database

        // SEND EMAIL CONFIRMATION STARTS
        //Token Generation
         String token= UUID.randomUUID().toString();
        ConfirmationToken confirmationToken=new ConfirmationToken(
                LocalDateTime.now(),                  //created
                LocalDateTime.now().plusMinutes(15),      // expire
                token,
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);    //saving our token into table confirmation_token
        // Email sending

        return token;                                //Returning the token to RegistrationService
    }
    public int enableAppUser(String email) {                   //Users email is verified so update it on database
        return appUserRepository.enableAppUser(email);
    }
}
