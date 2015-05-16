package Lab3.Data;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Eveler on 07.05.2015.
 */
public class BookStore implements Serializable, Externalizable {
    private static final long serialVersionUID = -4002473508782842647L;
    private HashSet<Book> books;
    private String name;

    public BookStore() {
    }

    ;

    public BookStore(String name) {
        this.name = name;
    }

    public BookStore(String name, Set<Book> books) {
        this.name = name;
        this.books = (HashSet<Book>) books;
    }

    public HashSet<Book> getBooks() {
        return books;
    }

    public void setBooks(HashSet<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return books.size();
    }

    public boolean addBook(Book book) {
        return books.add(book);
    }

    @Override
    public String toString() {
        return "BookCupboard{" +
                "name=" + name +
                "books=" + books +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        int size = books.size();
        out.writeInt(size);

        for (Externalizable book : books) {
            book.writeExternal(out);
        }

        out.writeObject(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int size = in.readInt();

        books = new HashSet<Book>();
        for (int i = 0; i < size; i++) {
            Book book = new Book();
            book.readExternal(in);
            books.add(book);
        }

        name = (String) in.readObject();
    }

}
