package com.example.ipam.service;

import org.apache.commons.net.util.SubnetUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Service
public class NetworkScanService {

    public List<String> scanNetwork(String subnet) {
        final List<String> activeIps = Collections.synchronizedList(new ArrayList<>());
        final SubnetUtils utils = new SubnetUtils(subnet);
        final String[] allIps = utils.getInfo().getAllAddresses();

        int corePoolSize = 50;
        int maxPoolSize = 100;
        long keepAliveTime = 5000;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );

        List<Future<?>> futures = new ArrayList<>();

        for (final String ip : allIps) {
            Future<?> future = executor.submit(() -> {
                try {
                    InetAddress address = InetAddress.getByName(ip);
                    if (address.isReachable(1000)) {
                        activeIps.add(ip);
                    }
                } catch (IOException e) {
                    // Ignore exceptions for unreachable hosts
                }
            });
            futures.add(future);
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                // Handle exceptions if necessary
            }
        }

        executor.shutdown();
        return new ArrayList<>(activeIps);
    }
}
