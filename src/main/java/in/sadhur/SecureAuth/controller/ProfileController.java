package in.sadhur.SecureAuth.controller;

import in.sadhur.SecureAuth.io.ProfileRequest;
import in.sadhur.SecureAuth.io.ProfileResponse;
import in.sadhur.SecureAuth.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0")
public class ProfileController {
    private final ProfileService profileServiceObj;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse register(@Valid @RequestBody ProfileRequest request) {
        ProfileResponse response = profileServiceObj.createProfile(request);
        // Welcome Email
        return response;
    }
}
