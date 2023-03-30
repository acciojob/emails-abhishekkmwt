package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    public ArrayList<Meeting> calendar=new ArrayList<>();; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
         super(emailId,Integer.MAX_VALUE);
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar

        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int count = 1;
        int i=0;
        int j=1;
        Comparator<Meeting> comparatorDes = (a,b)->a.getStartTime().compareTo(b.getStartTime());
        Collections.sort(calendar,comparatorDes);
        while(i<calendar.size() && j<calendar.size() && i<=j){
            if(j<calendar.size() && calendar.get(i).getEndTime().compareTo(calendar.get(j).getStartTime()) < 0){
                count++;
                i++;
                j++;
            }
            else if(j<calendar.size() && calendar.get(i).getEndTime().compareTo(calendar.get(j).getStartTime()) > 0){
                j++;
                if(j<calendar.size() && calendar.get(i).getEndTime().compareTo(calendar.get(j).getStartTime()) < 0){
                     count++;
                     i=j;
                }
                else{
                    j++;
                }
            }
        }
//        for(int i=0;i<calendar.size()-1;i++){
//            int compareValue = calendar.get(i).getEndTime().compareTo(calendar.get(i+1).getStartTime());
//            if(compareValue < 0){
//                  count++;
//            }
//            else if(compareValue > 0){
//                if(calendar.get(i+2)!=null && calendar.get(i).getEndTime().compareTo(calendar.get(i+2).getStartTime()) <0){
//                    count++;
//                    i++;
//                }
//                else{
//                    return count;
//                }
//            }
//        }
        return count;
    }
}
