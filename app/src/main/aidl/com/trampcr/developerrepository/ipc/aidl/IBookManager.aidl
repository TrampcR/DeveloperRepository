// IBookManager.aidl
package com.trampcr.developerrepository.ipc.aidl;

// Declare any non-default types here with import statements

import com.trampcr.developerrepository.ipc.aidl.Book;
import com.trampcr.developerrepository.ipc.aidl.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
