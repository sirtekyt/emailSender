package com.onwelo.mailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receivers")
public class ReceiverController {

    @Autowired
    ReceiverRepository receiverRepository;

    @GetMapping("/receivers")
    public List<Receiver> getAllReceivers(){
        return receiverRepository.findAll();
    }

    @PostMapping("/receivers")
    public Receiver createReceiver(@RequestBody Receiver receiver){
        return receiverRepository.save(receiver);
    }

    @GetMapping("/receivers/{id}")
    public Receiver getReceiverById(@PathVariable(value= "id") Long id){
        return receiverRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Receiver","id",id));
    }

    @PutMapping("/receivers/{id}")
    public Receiver updateReceiver(@PathVariable(value = "id") Long id, @RequestBody Receiver receiverDetails){
        Receiver receiver=receiverRepository.findById(String.valueOf(id))
                .orElseThrow(()->new ResourceNotFoundException("Receiver","id",id));
        receiver.setEmail(receiverDetails.getEmail());
        Receiver updatedReceiver= receiverRepository.save(receiver);
        return updatedReceiver;
    }

    public ResponseEntity<?> deleteReceiver(@PathVariable(value = "id") Long id){
        Receiver receiver=receiverRepository.findById(String.valueOf(id))
                .orElseThrow(()->new ResourceNotFoundException("Receiver","id",id));

        receiverRepository.delete(receiver);

        return ResponseEntity.ok().build();
    }



}
