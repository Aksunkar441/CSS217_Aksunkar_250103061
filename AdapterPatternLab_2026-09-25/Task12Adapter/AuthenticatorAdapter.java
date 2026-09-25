package Task12Adapter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthenticatorAdapter implements IModernAuthenticator {
    private final LegacyAuthService service;

    public AuthenticatorAdapter(LegacyAuthService service) { this.service = service; }
    public AuthenticatorAdapter() { this(new LegacyAuthService()); }

    @Override
    public boolean login(String username, String plainTextPassword) {
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(plainTextPassword.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte value : hash) hex.append(String.format("%02x", value & 0xff));
            return service.authenticateUserHex(username, hex.toString());
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("MD5 algorithm is unavailable", exception);
        }
    }
}
