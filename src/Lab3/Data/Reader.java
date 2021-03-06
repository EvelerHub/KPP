package Lab3.Data;

import java.io.*;
import java.util.Date;

/**
 * Created by Eveler on 07.05.2015.
 */
public class Reader extends Person implements Serializable, Externalizable {
    private static final long serialVersionUID = 9020690254073650690L;

    public Reader() {
        super("", "", new Date());
    }

    public Reader(String first_name, String last_name, Date bDay) {
        super(first_name, last_name, bDay);
    }

    @Override
    public String toString() {
        return "Author{" + super.toString() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
    }

}
