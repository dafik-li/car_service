package com.solvd.carservice;

import com.solvd.carservice.domain.controller.Generator;

public class Main {

    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.selectDBRepository();
    }
}
