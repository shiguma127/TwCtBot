package twctbot;

import twitter4j.*;
import twitter4j.api.HelpResources;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Main {
    public  static String tweet;
    static String ConsumerKey;
    static String ConsumerSecret;
    static String AccessToken;
    static String AccessTokenSecret;
    public static AsyncTwitter asyncTwitter;

    public static void main(String[] args) {

        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream("TwitterTkbot.properties"), "UTF-8"));
            ConsumerKey = properties.getProperty("OAuthConsumerKey");
            ConsumerSecret = properties.getProperty("OAuthConsumerSecret");
            AccessToken = properties.getProperty("OAuthAccessToken");
            AccessTokenSecret = properties.getProperty("OAuthAccessTokenSecret");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration conf = new ConfigurationBuilder()

                .setOAuthConsumerKey(ConsumerKey)
                .setOAuthConsumerSecret(ConsumerSecret)
                .setOAuthAccessToken(AccessToken)
                .setOAuthAccessTokenSecret(AccessTokenSecret)

                .build();

        asyncTwitter = new AsyncTwitterFactory(conf).getInstance();
        asyncTwitter.addListener(new TwitterAdapter() {
            @Override
            public void onException(TwitterException e, TwitterMethod m) {
                e.printStackTrace();
            }
        });
        TwitterStream twitterStream = new TwitterStreamFactory(conf).getInstance();
        StatusListener statusListener=new StatusListener() {

            @Override
            public void onException(Exception ex) {


            }

            @Override
            public void onStatus(Status status) {


            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {

            }

            @Override
            public void onStallWarning(StallWarning warning) {

            }
        };
        twitterStream.addListener(statusListener);
        FilterQuery lang= new FilterQuery();
        lang.track("ja");
        twitterStream.filter(lang);


    }
}
