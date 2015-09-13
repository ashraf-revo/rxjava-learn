package org.revo;

import org.junit.Test;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.function.Supplier;

import static java.lang.System.out;

/**
 * Created by revo on 11/09/15.
 */
public class SchedulingExamples {
    long threadId() {
        return Thread.currentThread().getId();
    }


    public void testFun(Supplier<Observable<Integer>> s) {
        out.println("firstLine   " + threadId());
        s.get().subscribe(this::print);
        out.println("nextLine    " + threadId());
    }

    @Test
    public void defaultApplication() {
        testFun(() -> Observable.from(Arrays.asList(1, 2, 3)));
        sleep(1000);
    }

    @Test
    public void subscribeOnNewThread() {
        testFun(() -> Observable.<Integer>create(
                s -> {
                    s.onNext(1);
                    s.onNext(2);
                    print("inner", 2);
                    s.onNext(3);
                    s.onCompleted();
                }
        ).subscribeOn(Schedulers.newThread()));
        sleep(1000);
    }

    @Test
    public void subscribeOnSchedulerWorkerNewThread() {
        testFun(() -> {
            Scheduler scheduler = Schedulers.newThread();
            Scheduler.Worker worker = scheduler.createWorker();
            return Observable.<Integer>create(
                    s -> {
                        worker.schedule(() -> print("Scheduler.Worker"));
                        s.onNext(1);
                        s.onNext(2);
                        print("inner", 2);
                        s.onNext(3);
                        s.onCompleted();
                    }
            ).subscribeOn(scheduler);
        });
        sleep(1000);
    }

    @Test
    public void subscribeOnSchedulerWorkerImmediate() {
        testFun(() -> {
            Scheduler scheduler = Schedulers.immediate();
            Scheduler.Worker worker = scheduler.createWorker();
            return Observable.<Integer>create(
                    s -> {
                        worker.schedule(() -> print("Scheduler.Worker"));
                        s.onNext(1);
                        s.onNext(2);
                        print("inner", 2);
                        s.onNext(3);
                        s.onCompleted();
                    }
            ).subscribeOn(scheduler);
        });
        sleep(1000);
    }

    @Test
    public void observeOnNewThread() {
        testFun(() -> Observable.<Integer>create(
                s -> {

                    s.onNext(1);
                    s.onNext(2);
                    print("inner", 2);
                    s.onNext(3);
                    s.onCompleted();
                }
        ).observeOn(Schedulers.newThread()));
        sleep(1000);
    }

    private void print(String s, int i) {
        out.println(i + "   " + s + "   " + threadId());

    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {

        }
    }

    private void print(Integer i) {
        out.println(i + "           " + threadId());
    }

    private void print(String s) {
        out.println("    " + s + "   " + threadId());
    }

}
