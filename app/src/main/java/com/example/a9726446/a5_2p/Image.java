package com.example.a9726446.a5_2p;

/**
 * Created by 9726446 on 5/10/18 @ LB1-MAC-024
 * With references from https://guides.codepath.com/android/using-parcelable (accessed 5/10/18)
 */

// TO DO #2 turn Task into a parcelable object

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {

    private String name = "";
    private String grade = "";
    private boolean complete = false;

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
     * @param name
     * @param grade
     * @param complete
     */
    public Image(String name, String grade, boolean complete) {
        update(name, grade, complete);
    }

    /**
     * For when constructed from Parcels
     * @param p
     */
    private Image (Parcel p){
        update(p.readString(), p.readString(), p.readInt() == 1);
    }

    private void update(String name, String grade, boolean complete) {
        this.name = name;
        this.grade = grade;
        this.complete = complete;
    }

    /**
     * I'm pretty sure this is just for compliance.
     * @return
     */
    @Override
    public int describeContents(){
        return 0;
    }

    /**
     * For when packing everything up.
     * @param parcel
     * @param i
     */
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(name);
        parcel.writeString(grade);
        parcel.writeInt(complete ? 1 : 0 );
    }

    @Override
    public String toString() {
        if (complete) {
            return "Task " + name + " complete for " + grade + " grade";
        } else {
            return "Task " + name + " not yet complete for " + grade + " grade";
        }
    }

}