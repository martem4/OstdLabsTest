package com.ostdlabs.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Yoghurt_92 on 21.11.2015.
 */

@Entity
@Table(name = "bank_account")
public class BankAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_ACCOUNT_SEQ")
    @SequenceGenerator(name = "BANK_ACCOUNT_SEQ", sequenceName = "bank_account_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "i_ban")
    private String iBan;

    @Column(name = "bik")
    private String bik;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getiBan() {
        return iBan;
    }

    public void setiBan(String iBan) {
        this.iBan = iBan;
    }

    public String getBik() {
        return bik;
    }

    public void setBik(String bik) {
        this.bik = bik;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BankAccount) {
            BankAccount other = (BankAccount) o;
            return new EqualsBuilder()
                    .append(getId(), other.getId())
                    .append(getiBan(), other.getiBan())
                    .append(getBik(), other.getBik())
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .append(getiBan())
                .append(getBik())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", iBan='" + iBan + '\'' +
                ", bik='" + bik + '\'' +
                '}';
    }
}
