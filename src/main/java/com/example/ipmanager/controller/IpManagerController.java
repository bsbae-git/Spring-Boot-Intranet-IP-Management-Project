package com.example.ipmanager.controller;

import com.example.ipmanager.model.IpAddress;
import com.example.ipmanager.service.IpScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class IpManagerController {

    private final IpScanService ipScanService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hosts", ipScanService.getAllIpAddresses());
        return "index";
    }

    @GetMapping("/scan")
    public String scanForm() {
        return "scan";
    }

    @PostMapping("/scan")
    public String scan(@RequestParam String networkPrefix,
                       @RequestParam int startRange,
                       @RequestParam int endRange) {
        ipScanService.scanNetwork(networkPrefix, startRange, endRange);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        IpAddress ipAddress = ipScanService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid IP Address Id:" + id));
        model.addAttribute("ipAddress", ipAddress);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(IpAddress ipAddress) {
        ipScanService.saveIpAddress(ipAddress);
        return "redirect:/";
    }
}