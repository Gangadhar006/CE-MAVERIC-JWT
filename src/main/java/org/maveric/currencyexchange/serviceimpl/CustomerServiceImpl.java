package org.maveric.currencyexchange.serviceimpl;

import org.maveric.currencyexchange.entity.Credentials;
import org.maveric.currencyexchange.entity.Customer;
import org.maveric.currencyexchange.enums.RoleType;
import org.maveric.currencyexchange.exception.CustomerNotFoundException;
import org.maveric.currencyexchange.payload.request.CustomerRequest;
import org.maveric.currencyexchange.payload.response.CustomerResponse;
import org.maveric.currencyexchange.repository.ICredentialsRepository;
import org.maveric.currencyexchange.repository.ICustomerRepository;
import org.maveric.currencyexchange.repository.IRolesRepository;
import org.maveric.currencyexchange.service.ICustomerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    public static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private ModelMapper mapper;
    private ICustomerRepository customerRepo;
    private ICredentialsRepository credentialsRepo;
    private IRolesRepository rolesRepo;
    private PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(ModelMapper mapper,
                               ICustomerRepository customerRepo,
                               ICredentialsRepository credentialsRepo,
                               IRolesRepository rolesRepo,
                               PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.customerRepo = customerRepo;
        this.credentialsRepo = credentialsRepo;
        this.rolesRepo = rolesRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = mapper.map(customerRequest, Customer.class);
        customer = customerRepo.save(customer);
        Credentials credentials = Credentials.builder()
                .email(customerRequest.getEmail())
                .role(rolesRepo.findByRole(RoleType.USER))
                .password(passwordEncoder.encode(customerRequest.getPassword()))
                .customer(customer)
                .build();
        credentialsRepo.save(credentials);
        return mapper.map(customer, CustomerResponse.class);
    }

    @Transactional
    @Override
    public CustomerResponse updateCustomer(long customerId, CustomerRequest customerRequest) {
        Customer customer = verifyCustomer(customerId);
        mapper.map(customerRequest, customer);
        updateDobAndAge(customer, customerRequest.getDob());
        return mapper.map(customer, CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return customers.stream()
                .map(customer -> mapper.map(customer, CustomerResponse.class))
                .toList();
    }

    @Override
    @Transactional
    public String deleteCustomer(long customerId) {
        Customer customer = verifyCustomer(customerId);
        customerRepo.delete(customer);
        return "Customer Deleted Successfully";
    }

    @Override
    public CustomerResponse findCustomer(long customerId) {
        Customer customer = verifyCustomer(customerId);
        return mapper.map(customer, CustomerResponse.class);
    }

    public Customer verifyCustomer(long customerId) {
        return customerRepo.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }

    private void updateDobAndAge(Customer customer, LocalDate dob) {
        customer.setDob(dob);
        LocalDate currentDate = LocalDate.now();
        customer.setAge(Period.between(dob, currentDate).getYears());
    }
}