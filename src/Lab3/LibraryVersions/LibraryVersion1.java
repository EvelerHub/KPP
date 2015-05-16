package Lab3.LibraryVersions;

import Lab3.Data.BookStore;
import Lab3.Data.Reader;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Eveler on 07.05.2015.
 */
public class LibraryVersion1 implements Serializable {
    private static final long serialVersionUID = 6937333852801758452L;
    private String name;
    private LinkedList<BookStore> bookStores;
    private LinkedList<Reader> readers;

    public LibraryVersion1(String name) {
        this.name = name;
    }

    public LibraryVersion1(String name, BookStore[] bookStores, Reader[] readers) {
        this.name = name;
        this.bookStores = new LinkedList<BookStore>();
        this.bookStores.addAll(Arrays.asList(bookStores));
        this.readers = new LinkedList<Reader>();
        this.readers.addAll(Arrays.asList(readers));
    }

    public boolean addBookStore(BookStore bookStore) {
        return bookStores.add(bookStore);
    }

    public boolean addReader(Reader reader) {
        return readers.add(reader);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<BookStore> getBookStores() {
        return bookStores;
    }

    public void setBookStores(LinkedList<BookStore> bookStores) {
        this.bookStores = bookStores;
    }

    public LinkedList<Reader> getReaders() {
        return readers;
    }

    public void setReaders(LinkedList<Reader> readers) {
        this.readers = readers;
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", bookStores=" + bookStores +
                ", readers=" + readers +
                '}';
    }

}
