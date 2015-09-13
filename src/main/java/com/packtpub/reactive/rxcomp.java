package com.packtpub.reactive;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by ashraf on 9/4/2015.
 */
public class rxcomp {
    static Executor executor = Executors.newFixedThreadPool(100, r -> new Thread(r));

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        Observable<Observable<Integer>> map = Observable.range(0, 100).map(i -> generateTask(i, executor));
        Observable<List<Integer>> m = Observable.merge(map).toList();


        m.subscribe(
                l -> System.out.print(l), e -> {
                },
                () -> System.out.println(System.currentTimeMillis() - start));
        while (true) {
        }

    }

    static Observable<Integer> generateTask(int i, Executor ex) {
        return Observable.<Integer>create(s -> {
            delay(1000);
            s.onNext(i);
            s.onCompleted();
        }).subscribeOn(Schedulers.from(ex));
    }

    static void delay(int d) {
        try {
            Thread.sleep(d);
        } catch (InterruptedException e) {

        }
    }
}
