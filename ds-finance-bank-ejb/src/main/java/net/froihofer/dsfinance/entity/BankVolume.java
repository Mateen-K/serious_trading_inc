package net.froihofer.dsfinance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="BANKVOLUME")
public class BankVolume implements Serializable {
    @Id
    private int id;
    @NotNull
    @Column
    private BigDecimal current;

    public  BankVolume(){
    }

    public BankVolume(int id, BigDecimal current) {
        this.id = id;
        this.current = current;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getCurrent() {
        return current;
    }

    public void setCurrent(BigDecimal current) {
        this.current = current;
    }
}
