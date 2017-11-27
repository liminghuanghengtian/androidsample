package com.example.adaministrator.myapplication.aidl;

import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adaministrator on 2017/11/27.
 */

class AidlBinder extends IBookManager.Stub {
    private IRespCallBack callback;

    @Override
    public void registerCallback(IRespCallBack cb) throws RemoteException {
        callback = cb;
    }

    @Override
    public void unregisterCallback(IRespCallBack cb) throws RemoteException {
        callback = null;
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "android开发艺术探索"));
        books.add(new Book(2, "java编程思想"));
        if (callback != null) {
            callback.onResp(0, books.toString());
        }
        return books;
        //        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        if (callback != null) {
            callback.onResp(1, "添加成功！");
        }
    }
}
