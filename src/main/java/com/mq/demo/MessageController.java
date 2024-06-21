package com.mq.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class MessageController {
	
	@Autowired
    private MessageTransferService messageTransferService;

    @PostMapping("/transfer")
    public String transferMessages() {
        messageTransferService.transferMessages();
        return "Message transfer initiated.";
    }
	

}
