package com.istv.banq.model;

import javax.persistence.*;

@Entity
@Table(name = "HISTORY")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HISTORY_ID")
    private int id;
    @Column(name = "AMOUNT", nullable = false, columnDefinition = "float(10,2) default 0.00")
    private float balance;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID_1")
    private User user1;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID_2")
    private User user2;

    public History() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
