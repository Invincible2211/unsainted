package com.softw2.ue2.Film;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table
public class Film
{
    private static final AtomicInteger count = new AtomicInteger(-1);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Film(String name)
    {
        this.name = name;
    }
    public Film(int id, String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId()
    {
        this.id = count.incrementAndGet();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
