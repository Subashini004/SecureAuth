package in.sadhur.SecureAuth.service;

import in.sadhur.SecureAuth.io.ProfileRequest;
import in.sadhur.SecureAuth.io.ProfileResponse;

public interface ProfileService {
    ProfileResponse createProfile(ProfileRequest request);
    ProfileResponse getProfile(String email);
    void sendResetOtp(String email);
    void resetPassword(String email, String otp, String newPassword);
    void sendOtp(String email);
    void verifyOtp(String email, String otp);
}
