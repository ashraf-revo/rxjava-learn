package com.packtpub.reactive.chapter01;

import com.packtpub.reactive.common.Program;
import rx.Observable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Demonstrate the differences between Iterators and Observables.
 *
 * @author meddle
 */
public class ObservableVSIterator implements Program {

    private static void usingIteratorExample() {
        List<String> list = Arrays
                .asList("One", "Two", "Three", "Four", "Five");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void usingObservableExample() {
        List<String> list = Arrays
                .asList("One", "Two", "Three", "Four", "Five");
        Observable<String> observable = Observable.from(list);
        observable.subscribe(element -> System.out.println(element), t ->
                        System.err.println(t)
                , () ->
                        System.out.println("We've finnished!")
        );
    }

    @Override
    public String name() {
        return "Iterator vs Observable";
    }

    @Override
    public void run() {
        System.out.println("Running Iterator example:");
        usingIteratorExample();

        System.out.println("Running Observable example:");
        usingObservableExample();
    }

    @Override
    public int chapter() {
        return 1;
    }

    public static void main(String[] args) {
        new ObservableVSIterator().run();
    }
}
