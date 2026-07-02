interface CustomerRepository {
    String findCustomerById(int id);
}

class CustomerRepositoryImpl implements CustomerRepository {

    public String findCustomerById(int id) {
        return "Customer ID: " + id + ", Name: Rahul";
    }
}

class CustomerService {

    private CustomerRepository repository;

    CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    void displayCustomer(int id) {
        System.out.println(repository.findCustomerById(id));
    }
}

public class ExEleven {

    public static void main(String[] args) {

        CustomerRepository repository = new CustomerRepositoryImpl();

        CustomerService service = new CustomerService(repository);

        service.displayCustomer(101);
    }
}