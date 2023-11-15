package reactive;

import java.util.concurrent.Flow;

public class EndSubscriber<T> implements Flow.Subscriber<T> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(final Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(final T item) {
        System.out.println(item);
        subscription.request(1);
    }

    @Override
    public void onError(final Throwable throwable) {
        System.out.println("Publisher kaputt. Keine weiteren Nachrichten");
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Ich habe fertig");
    }
}
