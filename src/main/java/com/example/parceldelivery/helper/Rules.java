package com.example.parceldelivery.helper;

import java.util.function.Supplier;

public class Rules<T> {

    public Supplier<Boolean> condition;
    public Supplier<T> process;

    public Rules(Supplier<Boolean> condition, Supplier<T> process) {
        this.condition = condition;
        this.process = process;
    }


}
