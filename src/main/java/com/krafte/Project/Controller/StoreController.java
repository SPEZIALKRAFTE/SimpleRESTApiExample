package com.krafte.Project.Controller;

import com.krafte.Project.Model.Address;
import com.krafte.Project.Repository.MySQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class StoreController {

    @Autowired
    MySQLRepository mySQLRepository;

    @GetMapping("/get-all-addresses")
    public List<Address> getAllAddresses() {
        return mySQLRepository.findAll();
    }

    @GetMapping("/get-address/{id}")
    public Address getSingleAddress(@PathVariable("id") Integer id){
        return mySQLRepository.findById(id).get();
    }

    @PostMapping("/add")
    public Address create(@RequestBody Map<String, String> body){
        String street = body.get("street");
        Integer number = Integer.parseInt(body.get("number"));
        String postcode = body.get("postcode");
        Address newAddress = new Address(number,street,postcode);
        return mySQLRepository.save(newAddress);

    }

    @DeleteMapping("/remove/{id}")
    public boolean DeleteAddress(@PathVariable("id") Integer id){
        if(!mySQLRepository.findById(id).equals(Optional.empty())){
            mySQLRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @PutMapping("/update/{id}")
    public Address updateAddress(@PathVariable("id") Integer id,
                                 @RequestBody Map<String, String> body){
        Address current = mySQLRepository.findById(id).get();

        current.setStreet(body.get("street"));
        current.setNumber(Integer.parseInt(body.get("number")));
        current.setPostcode(body.get("postcode"));

        mySQLRepository.save(current);
        return current;
    }

}
