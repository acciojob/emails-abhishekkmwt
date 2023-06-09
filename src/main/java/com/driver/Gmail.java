package com.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    private List<Mails> Inbox=new ArrayList<>() ;
    private List<Mails> Trash=new ArrayList<>();
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message)throws NullPointerException{
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already
        if(!Inbox.isEmpty() && Inbox.size() == inboxCapacity){
               Mails mail = Inbox.get(0);
               Trash.add(mail);
               Inbox.remove(mail);
        }
        Mails mails=new Mails(date,sender,message);
        Inbox.add(mails);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0;i<Inbox.size();i++){
            if(Inbox.get(i).message.equals(message)){
                Trash.add(Inbox.get(i));
                Inbox.remove(i);
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(Inbox.size() == 0){
            return null;
        }
        return Inbox.get(Inbox.size()-1).message;

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(Inbox.size() == 0){
            return null;
        }
        return Inbox.get(0).message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for(int i=0;i<Inbox.size();i++){
            if(Inbox.get(i).date.after(start) && Inbox.get(i).date.before(end)){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.removeAll(Trash);
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }

    public class Mails{
        private Date date;
        private String senderId;
        private String message;

        public Mails(Date date, String senderId, String message) {
            this.date = date;
            this.senderId = senderId;
            this.message = message;
        }

    }
}
