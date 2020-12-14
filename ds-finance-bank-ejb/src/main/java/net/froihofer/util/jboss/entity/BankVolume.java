package net.froihofer.util.jboss.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BankVolume")
public class BankVolume {
    @Id
    private String id;
    @Column
    private int max;
    @Column
    private int current;

    public  BankVolume(){
    }

    public BankVolume(String id, int max, int current) {
        this.id = id;
        this.max = max;
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}