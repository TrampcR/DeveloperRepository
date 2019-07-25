package com.trampcr.developerrepository.ipc.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.ipc.aidl.Book;

/**
 * Created by trampcr on 2019/7/19.
 */

public class ContentProviderActivity extends AppCompatActivity {
    public static final String TAG = ContentProviderActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content_provider_view);

        Log.d(TAG, "onCreate: thread name = " + Thread.currentThread().getName());

        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "python");
        getContentResolver().insert(BookProvider.BOOK_CONTENT_URI, values);

        Cursor bookCursor = getContentResolver().query(BookProvider.BOOK_CONTENT_URI, new String[] {"_id", "name"},
                null, null, null);
        while (bookCursor.moveToNext()) {
            Book2 book = new Book2();
            book.mBookId = bookCursor.getInt(0);
            book.mBookName = bookCursor.getString(1);

            Log.d(TAG, "onCreate: query bookId = " + book.mBookId + ", bookName = " + book.mBookName);
        }

        bookCursor.close();

        Cursor userCursor = getContentResolver().query(BookProvider.USER_CONTENT_URI, new String[] {"_id", "name", "sex"},
                null, null, null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.mUserId = userCursor.getInt(0);
            user.mUserName = userCursor.getString(1);
            user.mUserSex = userCursor.getInt(2);

            Log.d(TAG, "onCreate: query bookId = " + user.mUserId + ", bookName = " + user.mUserName + ", userSex = "
            + user.mUserSex);
        }

        userCursor.close();
    }
}
