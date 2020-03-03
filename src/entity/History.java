/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
public class History implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Book book;
    @OneToOne
    private Reader reader;
    @Temporal(TemporalType.TIMESTAMP)
    private Date giveOutBook;
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnBook;

    public History() {
    }
    public History(Book book, Reader reader, Date giveOutBook, Date returnBook) {
        this.book = book;
        this.reader = reader;
        this.giveOutBook = giveOutBook;
        this.returnBook = returnBook;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Date getGiveOutBook() {
        return giveOutBook;
    }

    public void setGiveOutBook(Date giveOutBook) {
        this.giveOutBook = giveOutBook;
    }

    public Date getReturnBook() {
        return returnBook;
    }

    public void setReturnBook(Date returnBook) {
        this.returnBook = returnBook;
    }

    @Override
    public String toString() {
        return "History{" 
                + "book=" + book.getTitle()
                + ", reader=" + reader.getFirstname()
                + " " + reader.getLastname()
                + ", giveOutBook=" + giveOutBook + ", returnBook=" + returnBook + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.book);
        hash = 67 * hash + Objects.hashCode(this.reader);
        hash = 67 * hash + Objects.hashCode(this.giveOutBook);
        hash = 67 * hash + Objects.hashCode(this.returnBook);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final History other = (History) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.book, other.book)) {
            return false;
        }
        if (!Objects.equals(this.reader, other.reader)) {
            return false;
        }
        if (!Objects.equals(this.giveOutBook, other.giveOutBook)) {
            return false;
        }
        if (!Objects.equals(this.returnBook, other.returnBook)) {
            return false;
        }
        return true;
    }

    
    
    
}
