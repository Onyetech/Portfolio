package com.example.akpaego.reloadly;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reload")
public class ReloadlyController {
    private final AccessToken accessToken;
    private final AirtimeTopUpService airtimeDataTopUp;

    public ReloadlyController(AccessToken accessToken, AirtimeTopUpService airtimeDataTopUp) {
        this.accessToken = accessToken;
        this.airtimeDataTopUp = airtimeDataTopUp;
    }

    @GetMapping(value = "/getBearerToken")
    public ResponseEntity<?> getToken() throws Exception {
        return new ResponseEntity<>(accessToken.getAccessToken(), HttpStatus.OK);
    }

    @PostMapping(value = "/top-up")
    public AirtimeTopUpResponse topUp(@RequestBody AirtimeTopUpRequest request) throws Exception {
        return airtimeDataTopUp.topUp(request);
    }
}
