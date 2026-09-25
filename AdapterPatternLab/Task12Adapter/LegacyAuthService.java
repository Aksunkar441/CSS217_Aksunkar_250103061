package Task12Adapter;

public class LegacyAuthService {
    public boolean authenticateUserHex(String username, String hexHash) {
        return username.equals("admin") && hexHash.equalsIgnoreCase("5f4dcc3b5aa765d61d8327deb882cf99");
    }
}
