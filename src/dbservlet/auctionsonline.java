package dbservlet;

import java.util.Date;

public class auctionsonline {

    private Integer id;
    private Date first_day;
    private Date last_day;
    private String subject;
    private Integer PersonId;


    public auctionsonline(Integer id, Date first_day, Date last_day, String subject, Integer personId) {
        this.id = id;
        this.first_day = first_day;
        this.last_day = last_day;
        this.subject = subject;
        PersonId = personId;
    }

    public Integer getId(){return id;}

    public Date getFirst_day() {
        return first_day;
    }

    public void setFirst_day(Date first_day) {
        this.first_day = first_day;
    }

    public Date getLast_day() {
        return last_day;
    }

    public void setLast_day(Date last_day) {
        this.last_day = last_day;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getPersonId() {
        return PersonId;
    }
}
