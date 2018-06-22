package twctbot;

import twitter4j.Status;
import twitter4j.TwitterAdapter;
import twitter4j.UserStreamAdapter;
import twitter4j.UserStreamListener;

public class analysis extends UserStreamAdapter {
    private final Main main;
    public analysis(Main main){
        this.main =main;
    }

    public void onStatus(Status status){
        System.out.println("@"+status.getUser().getScreenName()+":"+status.getText());
    }



}
