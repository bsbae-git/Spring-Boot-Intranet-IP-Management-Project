package com.example.ipam.controller;

import com.example.ipam.service.IpAddressService;
import com.example.ipam.vo.IpAddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IpAddressController {

    private final IpAddressService ipAddressService;

    @Autowired
    public IpAddressController(IpAddressService ipAddressService) {
        this.ipAddressService = ipAddressService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<IpAddressVO> ipList = ipAddressService.getAllIpAddresses();
        model.addAttribute("ipList", ipList);
        return "index";
    }

    @PostMapping("/scan")
    public String scan(@RequestParam("subnet") String subnet) {
        ipAddressService.updateScanResults(subnet);
        return "redirect:/";
    }
}
