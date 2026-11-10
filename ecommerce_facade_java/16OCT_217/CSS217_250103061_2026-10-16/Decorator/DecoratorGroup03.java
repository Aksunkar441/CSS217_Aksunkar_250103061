
class Task14_RpgArmor {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface CharacterDefense {
        int getArmorRating();
    }
    static class BasicTunic implements CharacterDefense {
        public int getArmorRating(){
            return 10;
        }
    }
    abstract static class DefenseDecorator implements CharacterDefense {
        final CharacterDefense inner;
        DefenseDecorator(CharacterDefense d){
            inner = d;
        }
    }
    static class IronShieldDecorator extends DefenseDecorator {
        IronShieldDecorator(CharacterDefense d){
            super(d);
        }
        public int getArmorRating(){
            return inner.getArmorRating() + 25;
        }
    }
    static class HelmetDecorator extends DefenseDecorator {
        HelmetDecorator(CharacterDefense d){
            super(d);
        }
        public int getArmorRating(){
            return inner.getArmorRating() + 15;
        }
    }
    static class MagicAuraDecorator extends DefenseDecorator {
        MagicAuraDecorator(CharacterDefense d){
            super(d);
        }
        public int getArmorRating(){
            return inner.getArmorRating() * 2;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        CharacterDefense defenseA = new MagicAuraDecorator(new IronShieldDecorator(new HelmetDecorator(new BasicTunic())));
        CharacterDefense defenseB = new IronShieldDecorator(new HelmetDecorator(new MagicAuraDecorator(new BasicTunic())));
        show(14, "Armor wrapping order", defenseA.getArmorRating() + " vs "+ defenseB.getArmorRating());
    }
}

class Task17_DataStreamSecurity {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface DataSource {
        String readData();
    }
    static class RawStringSource implements DataSource {
        public String readData(){
            return "confidential_payload";
        }
    }
    abstract static class DataDecorator implements DataSource {
        final DataSource inner;
        DataDecorator(DataSource d){
            inner = d;
        }
    }
    static class ReverseCipherDecorator extends DataDecorator {
        ReverseCipherDecorator(DataSource d){
            super(d);
        }
        public String readData(){
            return new StringBuilder(inner.readData()).reverse().toString();
        }
    }
    static class ShiftCipherDecorator extends DataDecorator {
        ShiftCipherDecorator(DataSource d){
            super(d);
        }
        public String readData(){
            return inner.readData().chars().collect(StringBuilder::new, (b, c) -> b.append((char)(c + 1)), StringBuilder::append).toString();
        }
    }
    static class HeaderDecorator extends DataDecorator {
        HeaderDecorator(DataSource d){
            super(d);
        }
        public String readData(){
            return "SEC_HEADER:"+ inner.readData();
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        DataSource secured = new HeaderDecorator(new ShiftCipherDecorator(new ReverseCipherDecorator(new RawStringSource())));
        show(17, "Secure data decorators", secured.readData());
    }
}
