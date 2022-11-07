package com.mypisubproject.PiSub.Project.husmodataapi.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.mypisubproject.PiSub.Project.husmodataapi.DataTopUpRequest;
import com.mypisubproject.PiSub.Project.husmodataapi.DataTopUpResponse;
import com.mypisubproject.PiSub.Project.husmodataapi.DataTopUpService;
import com.mypisubproject.PiSub.Project.husmodataapi.GeAllDataPlans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataTopUpController {

    private static final Logger logger = LoggerFactory.getLogger(DataTopUpController.class);

    private final GeAllDataPlans getDataPlans;
    private final DataTopUpService rechargeDataService;

    public DataTopUpController(GeAllDataPlans getDataPlans, DataTopUpService rechargeDataService) {
        this.getDataPlans = getDataPlans;
        this.rechargeDataService = rechargeDataService;
    }


    @PostMapping(value = "/getAllDataPlans")
    public ResponseEntity<?> getDataPlans() throws UnirestException {
        return new ResponseEntity<>(getDataPlans.getAllDataPlans(), HttpStatus.OK);
    }

    @PostMapping(value = "/recharge_data")
    public DataTopUpResponse rechargeData(@RequestBody DataTopUpRequest request) throws Exception {
        return rechargeDataService.dataTopUp(request);
    }

}
