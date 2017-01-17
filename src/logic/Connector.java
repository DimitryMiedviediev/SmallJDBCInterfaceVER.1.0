package logic;

/**
 * Created by Dimitry on 11.01.17.
 */
public class Connector {
    private String url = null;
    private String name = null;
    private String password = null;

    public Connector(String url, String name, String password) {
        this.url = url;
        this.name = name;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
