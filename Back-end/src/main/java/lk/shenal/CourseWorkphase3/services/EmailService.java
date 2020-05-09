package lk.shenal.CourseWorkphase3.services;

import lk.shenal.CourseWorkphase3.model.Customer;
import lk.shenal.CourseWorkphase3.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Customer customer) throws MailException {
        Vehicle vehicle = vehicleService.getVehicle(customer.getPlateNo());
        String orderDetails =  "YOUR BOOKING DETAILS ARE : " + '\n' + '\n' +
                               "* your customer Id : " + customer.getCustomerId() + '\n' +
                               "* Name : " +  customer.getCustomerName() + '\n' +
                               "* Ordered vehicle : " +  vehicle.getMake() + ' ' + vehicle.getModel() + '\n' +
                               "* plate number of the vehicle : " + vehicle.getPlateNo() + '\n' +
                               "* pick up date : " + customer.getPickUpDate() + '\n' +
                               "* The vehicle should be returned by " + customer.getDropOfDate() + '\n' +
                               "* Your total rent : Rs." + customerService.calculateRent(customer);


        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(customer.getCustomerEmail());
        mail.setFrom("westminstervehiclerental@gmail.com");
        mail.setSubject("Your booking is confirmed!");
        mail.setText(orderDetails);
        javaMailSender.send(mail);
    }
}
