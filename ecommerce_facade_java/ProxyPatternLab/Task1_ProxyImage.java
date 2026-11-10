interface Image {
    void show();
}

class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("--> [SLOW] Reading " + fileName + " from hard drive...");
    }

    public void show() {
        System.out.println("Displaying " + fileName + " on screen!");
    }
}

class ProxyImage implements Image {
    private String fileName;
    private String userRole;
    private RealImage realImage;

    public ProxyImage(String fileName, String userRole) {
        this.fileName = fileName;
        this.userRole = userRole;
    }

    public void show() {
        if (!"ADMIN".equals(userRole)) {
            System.out.println("[ACCESS DENIED] You lack permission to view: " + fileName);
            return;
        }

        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.show();
    }
}

public class Task1_ProxyImage {
    public static void main(String[] args) {
        Image guestView = new ProxyImage("salary_sheet.png", "GUEST");
        Image adminView = new ProxyImage("salary_sheet.png", "ADMIN");

        guestView.show();
        adminView.show();
    }
}