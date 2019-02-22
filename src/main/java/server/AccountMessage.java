package server;

public class AccountMessage {
    private String fusername;
    private String fpassword;

    public AccountMessage(String username, String password){
        this.fpassword = password;
        this.fusername = username;
    }

    public String getUsername(){
        return this.fusername;
    }

    public String getPassword(){
        return this.fpassword;
    }

    public void setPassword(String password) {
        this.fpassword = password;
    }

    public void setUsername(String username) {
        this.fusername = username;
    }

    public boolean equals(Object obj) {
        if(this == obj){return true;}

        if(obj instanceof AccountMessage){
            AccountMessage that = (AccountMessage) obj;
            return that.getPassword().equals(this.fpassword)&&that.getUsername().equals(this.fusername);
        }
        return true;
    }

}
