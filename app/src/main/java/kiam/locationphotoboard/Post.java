package kiam.locationphotoboard;

<<<<<<< HEAD
//import java.awt.Image;
=======
//import java.awt.Image; --what's the image thing?
>>>>>>> master
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

public class Post {
<<<<<<< HEAD
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
    private ArrayList < String > Comments;
    private String textContent;
    private Date theDate;

    // Text post route and Initialization of Content
    public Post() {
        // Initialization of (1)
        Rating = 0; //Initialization of Rating
        Comments = new ArrayList < String > (); //Initialization of the post's comments.

    }

    //TODO: Initialize image route -- will contain check for size (phone standard)
    // if someone upload a camera res photo it will be dumbed down to phone standard resolution
    //    public Post(Image imagePost)
    //    {
    //        Date theDate = new Date();
    //        Rating = 0;
    //        Comments = new ArrayList<String>();
    //    }
=======
	/*
	 * 	Upon initialization of the Post (which happens as soon as the user presses
	 * 	a button triggering the instantiation withtin the phone app) the following
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
    private ArrayList<String> Comments;

    // Text post route and Initialization of Content
    public Post(String textPost) {
        Date theDate = new Date(); //the date of the moment of uploading
        testString(textPost); //Limit message to 100 characters, if greater, return error on app.

        // Initialization of (1)
        Rating = 0; //Initialization of Rating
        Comments = new ArrayList<String>(); //Initialization of the post's comments.

    }

    //TODO: Initialize image route -- will contain check for size (phone standard), if someone uploads
    // a camera res photo it will be dumbed down to phone standard resolution
    /*public Post(Image imagePost) {
        Date theDate = new Date();
        Rating = 0;
        Comments = new ArrayList<String>();
    }*/

>>>>>>> master

    //adding comments
    public void addToComments(String comment) {
        Comments.add(comment);
    }

    //returns the comment array
<<<<<<< HEAD
    public ArrayList < String > getComments() {
        return Comments;
    } //TODO: figure out if AndStd can use ArrayLists easily
=======
    public ArrayList<String> getComments() {
        return Comments;
    } //TODO: figure out if Android Studio can use ArrayLists easily
>>>>>>> master

    // add/sub to the rating
    public void addRating() {
        Rating++;
    }
<<<<<<< HEAD
=======

>>>>>>> master
    public void subRating() {
        Rating--;
    }

    //returns the rating
    public int getRating() {
        return Rating;
    }

<<<<<<< HEAD
    //returns the Text for posting
    public String getTextContent() {
        return textContent;
    }

    //returns the date
    public Date getDate() {
        return theDate;
    }

    /*
     * The Great Limiters
     * */
    public void testString(String s) {
        if (s.length() < 100) {
            textContent = s; //if < 100 chars, allow setting of content.
            theDate = new Date();
        } else {
            textContent = "MESSAGE TOO LONG";
        }
    }

}
=======
    /*
     * The Great Limiter
     * */
    public static boolean testString(String s) {
        return s.length() > 100;
    }

}
>>>>>>> master
