package in.sadhur.SecureAuth.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ProfileRequest {
    private String name;
    private String email;
    private String password;
}
