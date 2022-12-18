package orca.application.wordsortgame;

public class UserModel {
    private int Id;
    private String FullName;
    private String Email;
    private String PhoneNumber;
    private String Password;
    private int Score;
    private String Username;
    @Override
    public String toString() {
        return "UserModel{" +
                "Id=" + Id +
                ", Username='" + Username + '\'' +
                ", FullName='" + FullName + '\'' +
                ", Email='" + Email + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Password='" + Password + '\'' +
                ", Score=" + Score +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername(){
        return Username;
    }

    public void setUsername(String username){
        Username = username;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public UserModel(){}

    public UserModel(int id, String username, String fullName, String email, String phoneNumber, String password, int score) {
        Id = id;
        Username = username;
        FullName = fullName;
        Email = email;
        PhoneNumber = phoneNumber;
        Password = password;
        Score = score;
    }
}
