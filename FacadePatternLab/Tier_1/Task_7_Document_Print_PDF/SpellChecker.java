public class SpellChecker {
    public String check(String text) {
        String corrected = text.replace("teh ", "the ").replace("recieve", "receive");
        System.out.println(corrected.equals(text) ? "Spell check completed." : "A common typo was corrected.");
        return corrected;
    }
}
