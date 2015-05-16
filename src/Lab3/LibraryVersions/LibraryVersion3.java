package Lab3.LibraryVersions;

import Lab3.Data.BookStore;
import Lab3.Data.Reader;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Eveler on 15.05.2015.
 */
public class LibraryVersion3 implements Externalizable {
    private static final long serialVersionUID = 6937333852801758452L;
    private String name;
    private LinkedList<BookStore> bookStores;
    private LinkedList<Reader> readers;

    public LibraryVersion3() {
    }

    public LibraryVersion3(String name) {
        this.name = name;
    }

    public LibraryVersion3(String name, BookStore[] bookStores, Reader[] readers) {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);

        int bookStoreSize = bookStores.size();
        out.writeInt(bookStoreSize);

        for (Externalizable bookStore : bookStores) {
            bookStore.writeExternal(out);
        }

        int readersSize = readers.size();
        out.writeInt(readersSize);

        for (Externalizable reader : readers) {
            reader.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();

        int bookStoreSize = in.readInt();
        bookStores = new LinkedList<BookStore>();

        for (int i = 0; i < bookStoreSize; i++) {
            BookStore bookStore = new BookStore();
            bookStore.readExternal(in);
            bookStores.add(bookStore);
        }

        int readersSize = in.readInt();
        readers = new LinkedList<Reader>();

        for (int i = 0; i < readersSize; i++) {
            Reader reader = new Reader();
            reader.readExternal(in);
            readers.add(reader);
        }
    }

}
