package com.trampcr.developerrepository.ipc.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by trampcr on 2019/7/17.
 */

public class Book implements Parcelable{
    private int mBookId;
    private String mBookName;

    public Book(int bookId, String bookName) {
        mBookId = bookId;
        mBookName = bookName;
    }

    private Book(Parcel in) {
        mBookId = in.readInt();
        mBookName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mBookId);
        dest.writeString(mBookName);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public String toString() {
        return "bookId = " + mBookId + ", bookName = " + mBookName;
    }
}
