public class Contact {
    private String name, phoneNumber, email;

    public String toCsvString() {
        return name + "," + phoneNumber + "," + email;
    }
}