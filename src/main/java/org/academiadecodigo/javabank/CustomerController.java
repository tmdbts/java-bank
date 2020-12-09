package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET, value = "customer-list")
    public String customerList(Model model) {

        List<Customer> customers = customerService.listCustomers();
        model.addAttribute("customers", customers);

        return "CustomerList";
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
