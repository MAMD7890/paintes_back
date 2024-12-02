package inventario.back.service;

import inventario.back.Dto.SignupRequest;

public interface AuthService {
    boolean createdUser(SignupRequest signupRequest);
}
