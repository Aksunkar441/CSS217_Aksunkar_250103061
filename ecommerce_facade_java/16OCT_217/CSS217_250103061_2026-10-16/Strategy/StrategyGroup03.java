
class Task13_RpgAttack {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface AttackStrategy {
        int computeDamage(int baseStrength);
    }
    static class MeleeAttack implements AttackStrategy {
        public int computeDamage(int s){
            return s * 2;
        }
    }
    static class RangedAttack implements AttackStrategy {
        public int computeDamage(int s){
            return s + 15;
        }
    }
    static class MagicAttack implements AttackStrategy {
        public int computeDamage(int s){
            return s * 3 - 10;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(13, "RPG attack", new MagicAttack().computeDamage(20));
    }
}

class Task16_StringCompression {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface CompressionStrategy {
        String compress(String rawData);
    }
    static class HyphenPackStrategy implements CompressionStrategy {
        public String compress(String s){
            return s.replace(' ', '-') + ".hpk";
        }
    }
    static class VowelStripStrategy implements CompressionStrategy {
        public String compress(String s){
            return s.replaceAll("(?i)[aeiou]", "") + ".vzp";
        }
    }
    static class ReversePackStrategy implements CompressionStrategy {
        public String compress(String s){
            return new StringBuilder(s).reverse() + ".rev";
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(16, "Compression strategy", new VowelStripStrategy().compress("Design Patterns"));
    }
}
