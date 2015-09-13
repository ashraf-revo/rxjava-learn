package com.packtpub.reactive;


import rx.Observable;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Created by ashraf on 9/6/2015.
 */
public class main {

    public static void main(String[] args) {

        long l = System.currentTimeMillis();
        Observable<Path> paths = Observable.<Path>create(s -> {
            try {
                Iterator<Path> src = Files.
                        walk(Paths.get("src"))
                        .filter(Files::isRegularFile)
                        .iterator();
                while (src.hasNext()) {
                    s.onNext(src.next());
                }
            } catch (IOException e) {
                s.onError(e);
            }
            s.onCompleted();
        }).subscribeOn(Schedulers.io());


        Observable<Observable<String>> map = paths.map(path -> generateTask(path.toString()));

        map.subscribe(d -> d.subscribe(s -> {
            print(s);
        }));


        while (true) {
        }
    }

    private static void print(String s) {
        System.out.println(s);
    }


    static Observable<String> generateTask(String i) {
        return Observable.<String>create(s -> {
            sleep(1000);
            s.onNext(i);
            s.onCompleted();
        }).subscribeOn(Schedulers.newThread());
    }

    private static void print(int i) {
        System.out.println(i + "        " + Thread.currentThread().getId());
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
        }
    }
}