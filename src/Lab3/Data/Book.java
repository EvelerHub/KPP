package Lab3.Data;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Eveler on 07.05.2015.
 */
public class Book implements Serializable, Externalizable {
    private static final long serialVersionUID = 7052235300686348644L;
    private Author[] authors;
    private String name;
    private Date dateOfRelease;
    private SimpleDateFormat dateFormat;


    public Book() {
    }

    public Book(Author[] authors, String name, Date dateOfRelease) {
        this.authors = authors;
        this.name = name;
        this.dateOfRelease = dateOfRelease;
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return "Book{" +
                "authors=" + Arrays.toString(authors) +
                ", name='" + name + '\'' +
                ", dateOfRelease=" + dateFormat.format(dateOfRelease) +
                '}';
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        int size = authors.length;
        out.writeInt(size);

        for (Externalizable author : authors) {
            author.writeExternal(out);
        }

        out.writeObject(name);
        out.writeObject(dateOfRelease);
        out.writeObject(dateFormat);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int size = in.readInt();

        authors = new Author[size];
        for (int i = 0; i < authors.length; i++) {
            Author author = new Author();
            author.readExternal(in);
            authors[i] = author;
        }

        name = (String) in.readObject();
        dateOfRelease = (Date) in.readObject();
        dateFormat = (SimpleDateFormat) in.readObject();
    }

}
