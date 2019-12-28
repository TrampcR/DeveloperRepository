package com.trampcr.developerrepository.ipc.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by trampcr on 2019/7/19.
 */

public class BookProvider extends ContentProvider {
    public static final String TAG = BookProvider.class.getSimpleName();
    public static final String BOOK = "book";
    public static final String USER = "user";

    public static final String AUTHORITY = "com.trampcr.developerrepository.book.provider";
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BOOK);
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + USER);
    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;

    static {
        mUriMatcher.addURI(AUTHORITY, BOOK, BOOK_URI_CODE);
        mUriMatcher.addURI(AUTHORITY, USER, USER_URI_CODE);
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: thread name = " + Thread.currentThread().getName());
        mContext = getContext();
        initProviderData();

        return true;
    }

    private void initProviderData() {
        mSqLiteDatabase = new BookDbOpenHelper(mContext).getWritableDatabase();
        mSqLiteDatabase.execSQL("delete from " + BookDbOpenHelper.BOOK_TABLE_NAME);
        mSqLiteDatabase.execSQL("delete from " + BookDbOpenHelper.USER_TABLE_NAME);
        mSqLiteDatabase.execSQL("insert into book values(3, 'Android');");
        mSqLiteDatabase.execSQL("insert into book values(4, 'iOS');");
        mSqLiteDatabase.execSQL("insert into book values(5, 'Java');");
        mSqLiteDatabase.execSQL("insert into user values(1, 'zxm', 1);");
        mSqLiteDatabase.execSQL("insert into user values(2, 'lfy', 0);");
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable
            String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query: thread name = " + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (!TextUtils.isEmpty(table)) {
            return mSqLiteDatabase.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, "getType: ");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "insert: ");
        String table = getTableName(uri);
        if (!TextUtils.isEmpty(table)) {
            mSqLiteDatabase.insert(table, null, values);
            mContext.getContentResolver().notifyChange(uri, null);
        }

        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete: ");

        String table = getTableName(uri);
        int count = 0;
        if (!TextUtils.isEmpty(table)) {
            count = mSqLiteDatabase.delete(table, selection, selectionArgs);
            if (count > 0) {
                mContext.getContentResolver().notifyChange(uri, null);
            }
        }

        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update: ");

        String table = getTableName(uri);
        int row = 0;
        if (!TextUtils.isEmpty(table)) {
            row = mSqLiteDatabase.update(table, values, selection, selectionArgs);
            if (row > 0) {
                mContext.getContentResolver().notifyChange(uri, null);
            }
        }

        return row;
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (mUriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = BookDbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = BookDbOpenHelper.USER_TABLE_NAME;
                break;
            default:
                break;
        }

        return tableName;
    }
}
