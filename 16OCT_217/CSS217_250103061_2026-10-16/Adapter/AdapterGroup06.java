
class Task33_BookCatalog {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface LibraryItem {
        String getCitation();
    }
    static class OldBookEntry {
        private final String author, title;
        private final int year;
        OldBookEntry(String a, String t, int y){
            author = a;
            title = t;
            year = y;
        }
        String getAuthor(){
            return author;
        }
        String getTitle(){
            return title;
        }
        int getPubYear(){
            return year;
        }
    }
    static class BookAdapter implements LibraryItem {
        private final OldBookEntry b;
        BookAdapter(OldBookEntry b){
            this.b = b;
        }
        public String getCitation(){
            return b.getAuthor() + " ("+ b.getPubYear() + ") - "+ b.getTitle();
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(33, "Book catalog adapter", new BookAdapter(new OldBookEntry("Ursula Le Guin", "A Wizard of Earthsea", 1968)).getCitation());
    }
}

class Task36_FileSize {
    private static void show(int n, String title, Object result) {
        System.out.println(n + ". " + title + ": " + result);
    }
    interface MegabyteReader {
        double getSizeInMB();
    }
    static class ByteFileStat {
        private final long bytes;
        ByteFileStat(long b){
            bytes = b;
        }
        long getBytes(){
            return bytes;
        }
    }
    static class FileSizeAdapter implements MegabyteReader {
        private final ByteFileStat f;
        FileSizeAdapter(ByteFileStat f){
            this.f = f;
        }
        public double getSizeInMB(){
            return f.getBytes() / 1048576.0;
        }
    }
    // Demonstrates this exercise with sample data.
    public static void main(String[] args) {
        show(36, "File size adapter", new FileSizeAdapter(new ByteFileStat(2097152)).getSizeInMB() + " MB");
    }
}
