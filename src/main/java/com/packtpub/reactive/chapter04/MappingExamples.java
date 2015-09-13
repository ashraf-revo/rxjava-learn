package com.packtpub.reactive.chapter04;

import com.packtpub.reactive.common.Program;

/**
 * Demonstration of using map, flatMap, flatMapIterable and switchMap.
 *
 * @author meddle
 */
public class MappingExamples implements Program {

    @Override
    public String name() {
        return "Examples of using Observable transformations";
    }

    @Override
    public int chapter() {
        return 4;
    }

    @Override
    public void run() {
//        Observable<String> mapped = Observable.just(2, 3, 5, 8).map(v -> v * 3)
//                .map(v -> (v % 2 == 0) ? "even" : "odd");
//
//        subscribePrint(mapped, "map");
//
//        System.out.println("-----------------");
//
//        Observable<Integer> flatMapped = Observable.just(3, 5, 8).flatMap(
//                v -> Observable.range(v, v));
//
//        subscribePrint(flatMapped, "flatMap");
//
//        System.out.println("-----------------");
//
//        flatMapped = Observable
//                .<Integer>just(-1, 0, 1)
//                .map(v -> 2 / v)
//                .flatMap(
//                        v -> {
//                            return Observable.<Integer>just(v);
//                        },
//                        e -> {
//                            return Observable.just(0);
//                        },
//                        () -> {
//                            return Observable.just(42);
//                        }
//                );
//
//        subscribePrint(flatMapped, "flatMap");
//        System.out.println("-----------------");
//
//        flatMapped = Observable.just(5, 432).flatMap(v -> Observable.range(v, 2),
//                (x, y) -> x + y);
//
//        subscribePrint(flatMapped, "flatMap");
//        System.out.println("-----------------");
//
//        Observable<String> fsObs = CreateObservable.listFolder(
//                Paths.get("src", "main", "resources"),
//                "{revo.txt,letters.txt}")
//                .flatMap(path -> CreateObservable.from(path), (path, line) -> path.getFileName() + " : " + line);
//
//        subscribePrint(fsObs, "FS");
//
//        Observable<?> fMapped = Observable.just(Arrays.asList(2, 4),
//                Arrays.asList("two", "four"), Arrays.asList('t', 'f'),
//                Arrays.asList(true, false)).flatMap(l -> Observable.from(l));
//
//        subscribePrint(fMapped, "flatMap");
//        Observable<?> fIterableMapped = Observable.just(Arrays.asList(2, 4),
//                Arrays.asList("two", "four"), Arrays.asList('t', 'f'),
//                Arrays.asList(true, false)).flatMapIterable(l -> l);
//
//        subscribePrint(fIterableMapped, "flatMapIterable");
//        System.out.println("-----------------");
//
//        Observable<Object> obs = Observable
//                .interval(40L, TimeUnit.MILLISECONDS).switchMap(
//                        v -> Observable.timer(0L, 10L, TimeUnit.MILLISECONDS)
//                                .map(u -> "Observable <" + (v + 1) + "> : " + (v + u)));
//
//        Subscription sub = subscribePrint(obs, "switchMap");
//
//        try {
//            Thread.sleep(400L);
//        } catch (InterruptedException e) {
//        }
//        sub.unsubscribe();
//
//        System.out.println("-----------------");

            }

    public static void main(String[] args) {
        new MappingExamples().run();
    }
}
