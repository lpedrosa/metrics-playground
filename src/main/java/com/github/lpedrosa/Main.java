package com.github.lpedrosa;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    public static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        MetricRegistry metrics = new MetricRegistry();

        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();
        reporter.start(1, TimeUnit.SECONDS);

        Meter requests = metrics.meter("requests");

        while (true) {
            requests.mark();
            sleep(5, TimeUnit.SECONDS);
        }
    }

    private static void sleep(long amount, TimeUnit unit) {
        try {
            unit.sleep(amount);
        } catch (InterruptedException ex) {
        }
    }


}
