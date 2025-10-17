package com.example.ipmanager.service;

import com.example.ipmanager.model.IpAddress;
import com.example.ipmanager.repository.IpAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IpScanService {

    private final IpAddressRepository ipAddressRepository;

    public List<IpAddress> scanNetwork(String networkPrefix, int startRange, int endRange) {
        List<IpAddress> activeHosts = new ArrayList<>();
        for (int i = startRange; i <= endRange; i++) {
            String ip = networkPrefix + "." + i;
            if (isReachable(ip)) {
                try {
                    InetAddress addr = InetAddress.getByName(ip);
                    String hostname = addr.getHostName();

                    IpAddress ipAddress = ipAddressRepository.findByIpAddress(ip)
                        .orElse(new IpAddress(null, ip, hostname, ""));

                    ipAddress.setHostname(hostname); // Update hostname in case it changed
                    ipAddressRepository.save(ipAddress);
                    activeHosts.add(ipAddress);

                } catch (Exception e) {
                    // Could not get hostname, but IP is reachable
                    IpAddress ipAddress = ipAddressRepository.findByIpAddress(ip)
                        .orElse(new IpAddress(null, ip, "N/A", ""));

                    ipAddressRepository.save(ipAddress);
                    activeHosts.add(ipAddress);
                }
            }
        }
        return activeHosts;
    }

    private boolean isReachable(String ip) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder processBuilder;

            if (os.contains("win")) {
                processBuilder = new ProcessBuilder("ping", "-n", "1", "-w", "1000", ip);
            } else {
                processBuilder = new ProcessBuilder("ping", "-c", "1", "-W", "1", ip);
            }

            Process process = processBuilder.start();
            return process.waitFor() == 0;
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return false;
        }
    }

    public List<IpAddress> getAllIpAddresses() {
        return ipAddressRepository.findAll();
    }

    public IpAddress saveIpAddress(IpAddress ipAddress) {
        return ipAddressRepository.save(ipAddress);
    }

    public Optional<IpAddress> findById(Long id) {
        return ipAddressRepository.findById(id);
    }

    // Schedule to run every hour
    @org.springframework.scheduling.annotation.Scheduled(fixedRate = 3600000)
    public void scheduledScan() {
        System.out.println("Running scheduled network scan...");
        // In a real application, these values should come from a configuration file
        scanNetwork("192.168.1", 1, 254);
        System.out.println("Scheduled scan finished.");
    }
}