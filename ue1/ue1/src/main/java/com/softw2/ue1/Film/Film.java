package com.softw2.ue1.Film;

import java.util.concurrent.atomic.AtomicInteger;

public class Film
{
    private static final AtomicInteger count = new AtomicInteger(-1);
    private int id;
    private String name;

    public Film(String name)
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