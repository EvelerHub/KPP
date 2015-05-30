package Lab6.Ex2;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jul on 26.04.2015.
 */
public class User{
    private static final DateFormat dateFormatter =
            new SimpleDateFormat("dd.MM.yyyy");
    private static final DateFormat dateParser = dateFormatter;
    private String name;
    private String surName;
    private String sex;
    private Date birthday;

    public User() {
    }

    public User(String name, String surName, String sex, String birthday) {
        this.name = name;
        this.surName = surName;
        this.sex = sex;
        try {
            this.birthday = dateParser.parse(birthday);
        } catch (ParseException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public static DateFormat getDateFormatter() {
        return dateFormatter;
    }

    public static DateFormat getDateParser() {
        return dateParser;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return name + " " + surName + " " + sex +
                " " + dateFormatter.format(birthday);
    }


}
