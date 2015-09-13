package com.packtpub.reactive.chapter01;

import rx.Observable;
import rx.observables.ConnectableObservable;

import java.util.Scanner;
import java.util.regex.Pattern;


public class ReactiveSumV1 {


    public static Observable<Double> varStream(final String varName,
                                               Observable<String> input) {
        final Pattern pattern = Pattern.compile("^\\s*" + varName
                + "\\s*[:|=]\\s*(-?\\d+\\.?\\d*)$");

        return input.map(str -> pattern.matcher(str)).
                filter(matcher -> matcher.matches() && matcher.group(1) != null).
                map(matcher -> matcher.group(1)).
                filter(str -> str != null).map(Double::parseDouble);
    }


    public static ConnectableObservable<String> from(final Scanner stream) {


        return Observable.<String>create(s -> {
            try {
                String line;
                if (s.isUnsubscribed()) {
                    return;
                }
                while (!s.isUnsubscribed() && stream.hasNextLine() && (line = stream.nextLine()) != null) {
                    if (line.equals("exit")) {
                        break;
                    }
                    s.onNext(line);
                }
            } catch (Exception e) {
                s.onError(e);
            }
            if (!s.isUnsubscribed()) {
                s.onCompleted();
            }
        }).publish();
    }

    public static void main(String[] args) {
        ConnectableObservable<String> input = from(new Scanner(System.in));
        Observable<Double> a = varStream("a", input);
        Observable<Double> b = varStream("b", input);
        Observable.combineLatest(a.startWith(0.0), b.startWith(0.0), Double::sum)
                .subscribe(System.out::println);
        input.connect();
    }
}
