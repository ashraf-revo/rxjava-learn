package com.packtpub.reactive.chapter04;

import com.packtpub.reactive.common.CreateObservable;
import com.packtpub.reactive.common.Program;
import rx.Observable;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.packtpub.reactive.common.Helpers.subscribePrint;

/**
 * Demonstration of using flatMap with an Observable created by directory stream,
 * reading all the files from it, using Observables.
 * 
 * @author meddle
 */
public class FlatMapAndFiles implements Program {

	@Override
	public String name() {
		return "Working with files using flatMap";
	}

	@Override
	public int chapter() {
		return 4;
	}

	@Override
	public void run() {
		Observable<String> fsObs = CreateObservable.listFolder(
				Paths.get("src", "main", "resources", "f"),
				"*")
				.flatMap(path -> CreateObservable.from(path));

		subscribePrint(fsObs, "FS");
	}
	
	public static void main(String[] args) {
		new FlatMapAndFiles().run();
	}

}
