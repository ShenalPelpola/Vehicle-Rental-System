package lk.shenal.CourseWorkphase3.controller;

import lk.shenal.CourseWorkphase3.model.Customer;
import lk.shenal.CourseWorkphase3.model.Schedule;
import lk.shenal.CourseWorkphase3.services.CustomerService;
import lk.shenal.CourseWorkphase3.services.CustomerServiceInterface;
import lk.shenal.CourseWorkphase3.services.EmailService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(method=RequestMethod.POST,value = "/api/customers")
    public void addCustomer(@RequestBody Customer customer){
        customer.setCustomerId(customerService.generateCustomerId(customer));
        customerService.addCustomer(customer);
        customerService.calculateRent(customer);
        try {
            emailService.sendEmail(customer);
        }catch(MailException e){
            e.printStackTrace();
        }

    }
}
