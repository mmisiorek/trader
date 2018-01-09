package transformers;

import java.util.concurrent.CompletableFuture;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.Subject;

public class ObservableToCompletableFutureTransformer<T> implements ITransformer<Observable<T>, CompletableFuture<T> > {

	public CompletableFuture<T> transform(Observable<T> observable) throws TransformNotPossibleException {
		CompletableFuture<T> stage = new CompletableFuture<T>();
		
		observable.subscribe(new Subscriber<T>() {
			
			public void onNext(T arg) {
				stage.complete(arg);
			}
			
			public void onCompleted() {
				
			}
			
			public void onError(Throwable exception) {
				stage.completeExceptionally(exception); 
			}
			
		});
		
		return stage;
	}
	
	public Observable<T> reverseTransform(CompletableFuture<T> future) throws TransformNotPossibleException {
		return Observable.create(subscriber -> {
			future.whenComplete((T arg, Throwable exception) -> {
				if(exception != null) {
					subscriber.onError(exception);
				} else {
					subscriber.onNext(arg);
					subscriber.onCompleted(); 
				}
			});
		});
	}
	
}
