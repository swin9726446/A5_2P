package com.example.a9726446.a5_2p;

/**
 * Created by 9726446 on 5/10/18 @ LB1-MAC-024
 * With references from https://guides.codepath.com/android/using-parcelable (accessed 5/10/18)
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Image implements Parcelable {

    private int resourceID;
    /**
     * 0 - Name
     * 1 - Source
     * 2 - Keywords, comma separated
     * 3 - Date
     * 4 - Finder's Email
     * 5 - Rating, validated to be a number between 0 and 5 inclusive.
     */
    private String [] metadata;
    private boolean shared = false;

    /**
     * Getters
     */
    public int getResourceID(){
        return resourceID;
    }
    public String getName() {
        return metadata[0];
    }
    public String getSrc() {
        return metadata[1];
    }
    public String getKey() {
        return metadata[2];
    }
    public String getDate() {
        return metadata[3];
    }
    public String getEmail() {
        return metadata[4];
    }
    public String getRating() {
        return metadata[5];
    }
    public boolean isShared(){
        return shared;
    }



    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>(){
        //Construct object using parcel
        @Override
        public Image createFromParcel (Parcel p){
              return new Image(p);
        }
        //make a whole bunch of 'em
        @Override
        public Image[] newArray(int size){
            return new Image[size];
        }
    };

    /**
     * Main Constructor
     * @param id The resource id of the image used
     * @param name image name
     * @param src image source
     * @param key key words, comma separated
     * @param date date image was obtained
     * @param email finder's email
     * @param rate between 0 and 5 inclusive
     * @param share if sharing is to be enabled
     */
    public Image (int id, String name, String src, String key, String date, String email, String rate, boolean share){
        if (name == "") {
            Log.e("ImageInit", "Constructor Error: Name cannot be empty!");
            throw new IllegalArgumentException();
        }
        resourceID = id;
        metadata = new String[]{
                name,
                src,
                key,
                date,
                email,
                ""
        };
        try{
            int rating = Integer.parseInt(rate);
            metadata[5] = (rating < 0 ? "0" : (rating > 5 ? "5" : rate));
        } catch (NumberFormatException nfe){
            Log.e("ImageInit", String.format("Non-fatal: Rating (%s) was not a number. \n%s", rate, nfe.toString()));
            metadata[5] = "ERR";
        }
        shared = share;
    }

    /**
     * Parcel Constructor (& packer!)
     * @param p Parcel built by another Image.
     */
    public Image (Parcel p){
        resourceID = p.readInt();
        metadata = p.createStringArray();
        shared = p.readInt() == 1;
    }
    public void writeToParcel (Parcel parcel, int i){
        parcel.writeInt(resourceID);
        parcel.writeStringArray(metadata);
        parcel.writeInt(shared ? 1 : 0);
    }

    /**
     * I'm pretty sure this is just for compliance.
     * @return
     */
    @Override
    public int describeContents(){
        return 0;
    }


}
//    private String name = "";
//    private String grade = "";
//    private boolean complete = false;
//    /**
//     * Main Constructor
//     * @param name
//     * @param grade
//     * @param complete
//     */
//    public Image(String name, String grade, boolean complete) {
//        update(name, grade, complete);
//    }
//
//    /**
//     * For when constructed from Parcels
//     * @param p
//     */
//    private Image (Parcel p){
//        update(p.readString(), p.readString(), p.readInt() == 1);
//    }
//
//    private void update(String name, String grade, boolean complete) {
//        this.name = name;
//        this.grade = grade;
//        this.complete = complete;
//    }
//
//    /**
//     * For when packing everything up.
//     * @param parcel
//     * @param i
//     */
//    public void writeToParcel(Parcel parcel, int i){
//        parcel.writeString(name);
//        parcel.writeString(grade);
//        parcel.writeInt(complete ? 1 : 0 );
//    }
