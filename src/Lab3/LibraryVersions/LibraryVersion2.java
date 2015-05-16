package Lab3.LibraryVersions;

import Lab3.Data.BookStore;
import Lab3.Data.Reader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Eveler on 07.05.2015.
 */
public class LibraryVersion2 implements Serializable {
    private static final long serialVersionUID = 6937333852801758452L;
    private String name;
    private transient LinkedList<BookStore> bookStores;
    private transient LinkedList<Reader> readers;

    public LibraryVersion2(String name) {
        this.name = name;
    }

    public LibraryVersion2(String name, BookStore[] bookStores, Reader[] readers) {
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

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();

        int bookStoreSize = bookStores.size();
        objectOutputStream.writeInt(bookStoreSize);

        for (BookStore bookStore : bookStores) {
            objectOutputStream.writeObject(bookStore);
        }

        int readersSize = readers.size();
        objectOutputStream.writeInt(readersSize);

        for (Reader reader : readers) {
            objectOutputStream.writeObject(reader);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();

        int bookStoreSize = objectInputStream.readInt();
        bookStores = new LinkedList<BookStore>();

        for (int i = 0; i < bookStoreSize; i++) {
            BookStore bookStore = (BookStore) objectInputStream.readObject();
            bookStores.add(bookStore);
        }

        int readersSize = objectInputStream.readInt();
        readers = new LinkedList<Reader>();

        for (int i = 0; i < readersSize; i++) {
            Reader reader = (Reader) objectInputStream.readObject();
            readers.add(reader);
        }
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
