package com.packtpub.reactive.chapter06;

import com.packtpub.reactive.common.Program;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;

import static com.packtpub.reactive.common.Helpers.debug;

/**
 * Demonstrates using subscribeOn and observeOn with {@link Schedulers} and {@link Observable}s.
 *
 * @author meddle
 */
public class SubscribeOnAndObserveOn implements Program {

	@Override
	public String name() {
		return "A few examples of observeOn and subscribeOn";
	}

	@Override
	public int chapter() {
		return 6;
	}

	@Override
	public void run() {
		CountDownLatch latch = new CountDownLatch(1);
//
//		Observable<Integer> range = Observable
//				.range(20, 5)
//				.flatMap(n -> Observable
//						.range(n, 3)
//						.subscribeOn(Schedulers.computation())
//						.doOnEach(debug("Source"))
//						);


		Observable<Character> chars = Observable.range(5,25)
				.observeOn(Schedulers.newThread())
				.map(n -> n + 48)
				.observeOn(Schedulers.computation())
				.map(n -> Character.toChars(n))
				.map(c -> c[0]);
		
		chars.subscribe(i->{
			System.out.println(i.charValue()+"          "+Thread.currentThread().getId());
		});

		System.out.println("Hey!");
		
		try {
			latch.await();
		} catch (InterruptedException e) {}
	}

	public static void main(String[] args) {
		new SubscribeOnAndObserveOn().run();
	}
}
