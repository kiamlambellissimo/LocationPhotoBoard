package kiam.locationphotoboard;

//import java.awt.Image;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/*	The idea behind this object is to contain everything a certain post will have.
 * 	For example, if one posts a picture at location X; a Post object will be created
 * 	to contain the following:
 *
 * 	- the Date and Time of post
 * 	- the Content(1) of the post
 *	- the Rating(2) of the post
 *  - TODO: Location stuff
 *
 * */

public class Post
{
	/*
	 * 	Upon initialization of the post, (which happens as soon as the user presses
	 * 	triggers the instantiation withtin the phone app) the following
	 * 	happens:
	 *
	 * 	i) 'theDate' Object is instantiated with the current date
	 * 	ii) everything within (1) and (2) are initialized to empty; except the Content
	 * 		itself
	 *
	 * 	There exists 2 constructors for the initialization, one for an image post and
	 * 	another for 'text posts' --put in for testing before images, maybe keep??
	 *
	 * */

    private int Rating;
    private ArrayList<Post> Comments;
    private String textContent;
    private Date theDate;
    private Drawable imageContent;

    // Text post route and Initialization of Content
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Post(String i, String s)
    {
        // Initialization of (1)
        Rating = 0;     //Initialization of Rating
        Comments = new ArrayList<Post>();     //Initialization of the post's comments.
        testString(s);
        testImage(i);

    }

    //TODO: Initialize image route -- will contain check for size (phone standard)
    // if someone upload a camera res photo it will be dumbed down to phone standard resolution
//    public Post(Image imagePost)
//    {
//        Date theDate = new Date();
//        Rating = 0;
//        Comments = new ArrayList<String>();
//    }

    //adding comments
    public void addToComments(Post comment)
    {
        Comments.add(comment);
    }

    //returns the comment array
    public ArrayList<Post> getComments() {return Comments;} //TODO: figure out if AndStd can use ArrayLists easily

    // add/sub to the rating
    public void addRating(){Rating++;}
    public void subRating(){Rating--;}

    //returns the rating
    public int getRating(){return Rating;}

    //returns the Text for posting
    public String getTextContent()
    {
        return textContent;
    }

    //returns the date
    public Date getDate()
    {
        return theDate;
    }

    /*
     * The Great Limiters
     * */
    public void testString(String s)
    {
        if (s.length() < 100)
        {
            textContent = s; //if < 100 chars, allow setting of content.
            theDate = new Date();
        }
        else
        {
            textContent = "MESSAGE TOO LONG";
        }
    }

    public void testImage(String i)
    {
        //TODO: Check if the image is the proper file format
        InputStream is = null;
        try {
            is = (InputStream) new URL(i).getContent();
            imageContent = Drawable.createFromStream(is, "src name");
        } catch (Exception e) {
        }

    }

    public Drawable getImage()
    {
        return imageContent;
    }

}
