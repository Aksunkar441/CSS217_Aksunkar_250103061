
class Task44_CloudStorage {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface StoragePlan {
        int getStorageGB();
        double getCost();
    }
    static class StarterPlan implements StoragePlan {
        public int getStorageGB(){
            return 50;
        }
        public double getCost(){
            return 5;
        }
    }
    abstract static class StorageDecorator implements StoragePlan {
        final StoragePlan inner;
        StorageDecorator(StoragePlan s){
            inner = s;
        }
    }
    static class Extra100GBDecorator extends StorageDecorator {
        Extra100GBDecorator(StoragePlan s){
            super(s);
        }
        public int getStorageGB(){
            return inner.getStorageGB() + 100;
        }
        public double getCost(){
            return inner.getCost() + 8;
        }
    }
    static class AutoBackupDecorator extends StorageDecorator {
        AutoBackupDecorator(StoragePlan s){
            super(s);
        }
        public int getStorageGB(){
            return inner.getStorageGB();
        }
        public double getCost(){
            return inner.getCost() + 3.5;
        }
    }
    static class FamilyShareDecorator extends StorageDecorator {
        FamilyShareDecorator(StoragePlan s){
            super(s);
        }
        public int getStorageGB(){
            return inner.getStorageGB() + 50;
        }
        public double getCost(){
            return inner.getCost() + 4;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        StoragePlan plan = new FamilyShareDecorator(new AutoBackupDecorator(new Extra100GBDecorator(new StarterPlan())));
        show(44, "Cloud storage plan", plan.getStorageGB() + " GB / "+ plan.getCost());
    }
}
