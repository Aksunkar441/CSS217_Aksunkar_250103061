import java.util.Locale;

class Task20_GymMembership {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface Membership {
        String getPerks();
        double getMonthlyFee();
    }
    static class BasicGymAccess implements Membership {
        public String getPerks(){
            return "Gym Floor";
        }
        public double getMonthlyFee(){
            return 1000;
        }
    }
    abstract static class MembershipDecorator implements Membership {
        final Membership inner;
        MembershipDecorator(Membership m){
            inner = m;
        }
    }
    static class PoolAccessDecorator extends MembershipDecorator {
        PoolAccessDecorator(Membership m){
            super(m);
        }
        public String getPerks(){
            return inner.getPerks() + ", Pool Access";
        }
        public double getMonthlyFee(){
            return inner.getMonthlyFee() + 400;
        }
    }
    static class SaunaDecorator extends MembershipDecorator {
        SaunaDecorator(Membership m){
            super(m);
        }
        public String getPerks(){
            return inner.getPerks() + ", Sauna";
        }
        public double getMonthlyFee(){
            return inner.getMonthlyFee() + 250;
        }
    }
    static class PersonalTrainerDecorator extends MembershipDecorator {
        PersonalTrainerDecorator(Membership m){
            super(m);
        }
        public String getPerks(){
            return inner.getPerks() + ", Personal Trainer";
        }
        public double getMonthlyFee(){
            return inner.getMonthlyFee() + 1500;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        Membership membership = new PersonalTrainerDecorator(new SaunaDecorator(new PoolAccessDecorator(new BasicGymAccess())));
        show(20, "Gym membership", membership.getPerks() + " / "+ membership.getMonthlyFee());
    }
}

class Task23_BbcodeTags {
    private static void show(int n, String title, Object result) {
        System.out.printf(Locale.ROOT, "%02d. %-38s %s%n", n, title, result);
    }
    interface FormattedText {
        String render();
    }
    static class SimpleText implements FormattedText {
        private final String text;
        SimpleText(String s){
            text = s;
        }
        public String render(){
            return text;
        }
    }
    abstract static class TagDecorator implements FormattedText {
        final FormattedText inner;
        TagDecorator(FormattedText f){
            inner = f;
        }
    }
    static class BoldTagDecorator extends TagDecorator {
        BoldTagDecorator(FormattedText f){
            super(f);
        }
        public String render(){
            return "[B]"+ inner.render() + "[/B]";
        }
    }
    static class ItalicTagDecorator extends TagDecorator {
        ItalicTagDecorator(FormattedText f){
            super(f);
        }
        public String render(){
            return "[I]"+ inner.render() + "[/I]";
        }
    }
    static class CodeTagDecorator extends TagDecorator {
        CodeTagDecorator(FormattedText f){
            super(f);
        }
        public String render(){
            return "[CODE]"+ inner.render() + "[/CODE]";
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        FormattedText bb = new BoldTagDecorator(new ItalicTagDecorator(new CodeTagDecorator(new SimpleText("hello"))));
        show(23, "BBCode decorators", bb.render());
    }
}
