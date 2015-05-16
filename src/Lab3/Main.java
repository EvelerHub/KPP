package Lab3;

import Lab3.Data.Author;
import Lab3.Data.Book;
import Lab3.Data.BookStore;
import Lab3.Data.Reader;
import Lab3.LibraryVersions.LibraryVersion1;
import Lab3.LibraryVersions.LibraryVersion2;
import Lab3.LibraryVersions.LibraryVersion3;

import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Eveler on 07.05.2015.
 */

public class Main {

    public static void main(String[] args) {
        Set<Book> books = new HashSet<Book>();

        books.add(new Book(new Author[]{new Author("Ali", "Baba", new Date())}, "War", new Date()));
        LibraryVersion1 libraryVersion1 = new LibraryVersion1("Lib", new BookStore[]{new BookStore("bookStor", books)}, new Reader[]{new Reader("Fafa", "Mustam", new Date())});

        Main main = new Main();

        System.out.println("\n~~~~~~~~~~~~~~~~~~Version2~~~~~~~~~~~~~~~~~\n");

        main.doSerialize("Version1.txt", libraryVersion1);

        System.out.println("\n~~~~~~~~~~~~~~~~~~Version2~~~~~~~~~~~~~~~~~\n");

        Reader[] readers = new Reader[0];
        BookStore[] bookStores = new BookStore[0];
        LibraryVersion2 libraryVersion2 = new LibraryVersion2(
                libraryVersion1.getName(),
                libraryVersion1.getBookStores().toArray(bookStores),
                libraryVersion1.getReaders().toArray(readers)
        );

        main.doSerialize("Version2.txt", libraryVersion2);

        System.out.println("\n~~~~~~~~~~~~~~~~~~Version3~~~~~~~~~~~~~~~~~\n");
        LibraryVersion3 libraryVersion3 = new LibraryVersion3(
                libraryVersion1.getName(),
                libraryVersion1.getBookStores().toArray(bookStores),
                libraryVersion1.getReaders().toArray(readers)
        );

        main.doSerialize("Version3.txt", libraryVersion3);

    }

    private Object deSerealize(String fileName) throws IOException, ClassNotFoundException {
        Object object = null;
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        object = objectInputStream.readObject();
        objectInputStream.close();

        return object;
    }

    private void serealize(String fileName, Object object) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }

    private void doSerialize(String fielName, Object libraryVersion) {
        System.out.println("До сиреализации:\n" + libraryVersion);

        try {
            serealize(fielName, libraryVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }

        libraryVersion = null;

        System.out.println("Присваиваем null:\n" + libraryVersion);

        try {
            libraryVersion = deSerealize(fielName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("После сиреализации:\n" + libraryVersion);
    }

}
