import java.util.LinkedHashMap;
import java.util.Map;

public class ProfileRegistry {
    private final Map<String, String> members = new LinkedHashMap<>();
    private int nextMemberNumber = 1001;

    public String createProfile(String name, int age) {
        String memberId = "MEM-" + nextMemberNumber++;
        members.put(memberId, name + " (age " + age + ")");
        return memberId;
    }
}
