package kiam.locationphotoboard;

/**
 * Created by moeg on 2017-02-08.
 */

public class User {

    private String username;
    private String emailAddress;
    private String password;

    public User(String username, String emailAddress, String password) { //Creating constructor for initializing each

        setUsername(username);
        setEmailAddress(emailAddress);
        setPassword(password);
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }



    public String getUsername() {
        return username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
