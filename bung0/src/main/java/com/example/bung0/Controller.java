package com.example.bung0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller
{
    @RequestMapping(value = "/name", method = RequestMethod.POST)
    @ResponseBody
    public String postName()
    {
        return "Jason";
    }

    @RequestMapping(value = "/")
    public String getName()
    {
        return "Jason";
    }
}
