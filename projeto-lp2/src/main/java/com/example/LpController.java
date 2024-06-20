package com.example;

import io.micronaut.http.annotation.*;

@Controller("/lp")
public class LpController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}