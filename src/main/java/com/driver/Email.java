package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(this.password == oldPassword){
            for (int i=0;i<newPassword.length();i++){
                if(atLeastEightChar(newPassword)&&atLeastOneDigit(newPassword)&& atLeastOneLowerCase(newPassword) && atLeastOneUpperCase(newPassword) &&atLeastOneSpecialChar(newPassword)){
                    this.password=newPassword;
                }
            }
        }
    }

    public boolean atLeastEightChar(String newPassword){
        if(newPassword.length() >= 8){
            return true;
        }
        return false;
    }

    public boolean atLeastOneUpperCase(String newPassword){
        for(int i=0;i<newPassword.length();i++){
            char c = newPassword.charAt(i);
            if(c-'A' >= 0 && c-'A'<=25) {
                return true;
            }
        }
        return false;
    }

    public boolean atLeastOneLowerCase(String newPassword){
        for(int i=0;i<newPassword.length();i++){
            char c = newPassword.charAt(i);
            if(c-'a' >= 0 && c-'a'<=25) {
                return true;
            }
        }
        return false;
    }

    public boolean atLeastOneDigit(String newPassword){
        for(int i=0;i<newPassword.length();i++){
            char c = newPassword.charAt(i);
            if(c-'0' >= 0 && c-'0'<=9) {
                return true;
            }
        }
        return false;
    }

    public boolean atLeastOneSpecialChar(String newPassword){
        for(int i=0;i<newPassword.length();i++){
            char c = newPassword.charAt(i);
            if(c-'0' < 0 || (c-'0'>9 && c-'A'<0) ||(c-'A'>25 && c-'a'<0) ||(c-'z'>25)) {
                return true;
            }
        }
        return false;
    }
}
