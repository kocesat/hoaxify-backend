package com.hoaxify.backend.host;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/host")
public class HostController {

  @GetMapping
  public Map<String, Object> getHostName() throws UnknownHostException {
    var hostMap = new HashMap<String, Object>();
    hostMap.put("fromEnv", System.getenv("HOSTNAME"));
    System.out.println(InetAddress.getLocalHost());
    var host = InetAddress.getLocalHost();
    hostMap.put("fromInet", host.getHostName() + host.getHostAddress());
    return hostMap;
  }
}
