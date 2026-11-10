interface Internet {
    void connectTo(String site);
}

class RealInternet implements Internet {
    public void connectTo(String site) {
        System.out.println("Connected to site: " + site);
    }
}

class SchoolProxyInternet implements Internet {
    private RealInternet realInternet;

    public void connectTo(String site) {
        if (site.equals("gaming.com") || site.equals("socialmedia.com")) {
            System.out.println("[BLOCKED BY FIREWALL] Cannot access: " + site);
        } else {
            if (realInternet == null) {
                realInternet = new RealInternet();
            }
            realInternet.connectTo(site);
        }
    }
}

public class Task3_SchoolFirewall {
    public static void main(String[] args) {
        Internet proxy = new SchoolProxyInternet();
        proxy.connectTo("wikipedia.org");
        proxy.connectTo("gaming.com");
    }
}