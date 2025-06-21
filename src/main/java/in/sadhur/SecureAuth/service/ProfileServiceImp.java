package in.sadhur.SecureAuth.service;

import in.sadhur.SecureAuth.entity.UserEntity;
import in.sadhur.SecureAuth.io.ProfileRequest;
import in.sadhur.SecureAuth.io.ProfileResponse;
import in.sadhur.SecureAuth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ProfileServiceImp implements ProfileService{
    private final UserRepository userRepositoryObj;
    private final PasswordEncoder passwordEncoderObj;
    private final EmailService emailServiceObj;

    @Override
    public ProfileResponse createProfile(ProfileRequest request) {
        UserEntity newProfile = convertToUserEntity(request);
        if (!userRepositoryObj.existsByEmail(request.getEmail())) {
            newProfile = userRepositoryObj.save(newProfile);
            return convertToProfileResponse(newProfile);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
    }

    @Override
    public ProfileResponse getProfile(String email) {
        UserEntity existingUser = userRepositoryObj.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return convertToProfileResponse(existingUser);
    }

    @Override
    public void sendResetOtp(String email) {
        UserEntity existingEntity = userRepositoryObj.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        //Otp Generation
        String otp = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));

        //Expire Time
        long expireTime = System.currentTimeMillis() + (15 * 60 * 1000);

        //Update the Profile
        existingEntity.setResetOtp(otp);
        existingEntity.setResetOtpExpireAt(expireTime);

        //Save it in Database
        userRepositoryObj.save(existingEntity);

        try {
            emailServiceObj.sendResetOtpEmail(existingEntity.getEmail(), otp);
        } catch (Exception ex) {
            throw new RuntimeException("Unable to send email.");
        }
    }

    private UserEntity convertToUserEntity(ProfileRequest request) {
        return UserEntity.builder()
                .email(request.getEmail())
                .userId(UUID.randomUUID().toString())
                .name(request.getName())
                .password(passwordEncoderObj.encode(request.getPassword()))
                .isAccountVerified(false)
                .resetOtpExpireAt(0L)
                .verifyOtp(null)
                .verifyOtpExpireAt(0L)
                .resetOtp(null)
                .build();
    }
    private ProfileResponse convertToProfileResponse(UserEntity newProfile) {
        return ProfileResponse.builder()
                .name(newProfile.getName())
                .email(newProfile.getEmail())
                .userId(newProfile.getUserId())
                .isAccountVerified(newProfile.getIsAccountVerified())
                .build();
    }

}
