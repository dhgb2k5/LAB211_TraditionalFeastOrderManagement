package tool;

public interface Acceptable {
    public final String NAME_VALID = "^.{2,25}$";
    public final String PHONE_VALID = "0\\d{9}$";
    public final String EMAIL_VALID = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
}
