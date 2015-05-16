package Lab3.Data;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Eveler on 07.05.2015.
 */
public class Person implements Serializable, Externalizable {
    private static final long serialVersionUID = 7542513603435505756L;
    private String first_name;
    private String last_name;
    private Date bDay;
    private SimpleDateFormat dateFormat;

    public Person(String first_name, String last_name, Date bDay) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.bDay = bDay;
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getbDay() {
        return bDay;
    }

    public void setbDay(Date bDay) {
        this.bDay = bDay;
    }

    @Override
    public String toString() {
        return first_name + " " +
                last_name + " " +
                dateFormat.format(bDay);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person peson = (Person) obj;
            return first_name.equals(peson.getFirst_name()) &&
                    last_name.equals(peson.getLast_name()) &&
                    bDay.equals(peson.getbDay());
        }else {
            return false;
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(first_name);
        out.writeObject(last_name);
        out.writeObject(bDay);
        out.writeObject(dateFormat);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        first_name = (String) in.readObject();
        last_name = (String) in.readObject();
        bDay = (Date) in.readObject();
        dateFormat = (SimpleDateFormat) in.readObject();
    }

}
