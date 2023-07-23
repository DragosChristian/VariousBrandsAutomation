package Models;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class LoginModel {
    private AccountModel account;
    private String generalError;


    public LoginModel() {
    }

    public AccountModel getAccount() {
        return account;
    }


    public void setAccount(AccountModel account) {
        this.account = account;
    }


    public String getGeneralError() {
        return generalError;
    }


    public void setGeneralError(String generalError) {
        this.generalError = generalError;
    }

    public LoginModel(AccountModel account, String generalError) {
        this.account = account;
        this.generalError = generalError;
    }


    public LoginModel(String username, String password, String generalError) {
        AccountModel ac = new AccountModel();
        ac.setUsername(username);
        ac.setPassword(password);
        this.account = ac;
        this.generalError = generalError;
    }


}
