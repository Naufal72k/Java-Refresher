import java.net.*;

public class IPFinder {
    public static void main(String[] args) {
        String host = "google.com";
        try {
            InetAddress address = InetAddress.getByName(host);
            System.out.println("alamat ip: " + address.toString());

        } catch (Exception e) {

            System.out.println("tidak menemukan " + host);
        }
    }

}
