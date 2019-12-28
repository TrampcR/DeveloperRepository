// IOnNewBookArrivedListener.aidl
package com.trampcr.developerrepository.ipc.aidl;

// Declare any non-default types here with import statements

import com.trampcr.developerrepository.ipc.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book book);
}
