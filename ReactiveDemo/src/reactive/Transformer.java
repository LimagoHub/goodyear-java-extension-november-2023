package reactive;

import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;
import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscription;


public class Transformer<T, R> extends SubmissionPublisher<R> implements Processor<T, R> {

  private Function<T, R> function;
  private Subscription subscription;

  public Transformer(Function<T, R> function) {
      super();
      this.function = function;
  }

  @Override
  public void onSubscribe(Subscription subscription) {
      this.subscription = subscription;
      subscription.request(1);
  }

  @Override
  public void onNext(T item) {
      try {
		submit(function.apply(item));// item.lenght() Interger.valueOf(item)
	} catch (Exception e) {
		
		e.printStackTrace();
	}
      subscription.request(1);
  }

  @Override
  public void onError(Throwable t) {
      t.printStackTrace();
  }

  @Override
  public void onComplete() {
      close();
  }
}