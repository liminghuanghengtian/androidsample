// IBookManager.aidl
package com.example.adaministrator.myapplication.aidl;

// Declare any non-default types here with import statements
import com.example.adaministrator.myapplication.aidl.Book;
interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
