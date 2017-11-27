// IBookManager.aidl
package com.example.adaministrator.myapplication.aidl;

// Declare any non-default types here with import statements
import com.example.adaministrator.myapplication.aidl.Book;
import com.example.adaministrator.myapplication.aidl.IRespCallBack;
interface IBookManager {
    void registerCallback(IRespCallBack cb);
    void unregisterCallback(IRespCallBack cb);
    List<Book> getBookList();
    void addBook(in Book book);
}
