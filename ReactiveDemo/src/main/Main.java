package main;

import reactive.EndSubscriber;
import reactive.Transformer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws  Exception{
        var list = List.of("1","2","Drei", "4", "5", "SECHS", "Sieben");

        EndSubscriber<Integer> endSubscriber = new EndSubscriber<>();

        Transformer<String,Integer> transformer = new Transformer<>(item->Integer.valueOf(item));
        transformer.subscribe(endSubscriber);

        SubmissionPublisher<String> publisher= new SubmissionPublisher<>();

        publisher.subscribe(transformer);

        list.forEach(publisher::submit);

        publisher.close();

        ExecutorService service = (ExecutorService) publisher.getExecutor();
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("Hello world!");
    }
}