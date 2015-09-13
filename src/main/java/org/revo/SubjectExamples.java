package org.revo;


import org.junit.Test;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 * Created by revo on 08/09/15.
 */
public class SubjectExamples {
    private void delay(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {

        }
    }

    @Test
    public void testPublishSubject() {
        PublishSubject<Integer> p = PublishSubject.create();
        p.onNext(1);
        p.subscribe(out::print);
        p.onNext(2);
        p.onNext(3);
        p.onNext(4);

    }

    @Test
    public void testReplaySubject() {
        ReplaySubject<Integer> r = ReplaySubject.create();
        r.onNext(1);
        r.subscribe(out::println);
        r.onNext(2);
        r.onNext(3);
        r.onNext(4);
    }

    @Test
    public void testReplaySubjectWithSize() {
        ReplaySubject<Integer> r = ReplaySubject.createWithSize(2);
        r.onNext(1);
        r.onNext(2);
        r.onNext(3);
        r.subscribe(out::println);
        r.onNext(4);
    }

    @Test
    public void testReplaySubjectWithTime() {
        ReplaySubject<Integer> r = ReplaySubject.createWithTime(1, TimeUnit.SECONDS, Schedulers.immediate());
        r.onNext(1);
        delay(100);
        r.onNext(2);
        delay(900);
        r.onNext(3);
        r.subscribe(out::println);
        r.onNext(4);
    }

    @Test
    public void testBehaviorSubject() {
        BehaviorSubject<Integer> b = BehaviorSubject.create(1);
        b.onNext(2);
        b.subscribe(out::println);
        b.onNext(3);
    }

    @Test
    public void testAsyncSubject() {
        AsyncSubject<Integer> a = AsyncSubject.create();
        a.onNext(1);
        a.subscribe(out::println);
        a.onNext(2);
        a.onNext(3);
        a.onNext(4);
        a.onCompleted();
    }

    @Test
    public void ObservableCreate() {
        Observable.<Integer>create(s -> {

            while (!s.isUnsubscribed()) {
                s.onNext(1);
                delay(3000);
                s.onNext(2);
            }
            s.onCompleted();
        }).subscribe(out::println);

    }

    @Test
    public void main() {
        out.println("kjl");
        Observable.range(0, 10).doOnEach(notification -> {
            out.print(notification.getValue());
        })
        .subscribe(k->{});
        out.println("kjl");
        delay(10000);
    }
}
