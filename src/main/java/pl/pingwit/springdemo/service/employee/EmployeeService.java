package pl.pingwit.springdemo.service.employee;

import org.springframework.stereotype.Service;
import pl.pingwit.springdemo.controller.employee.CreateEmployeeInputDto;
import pl.pingwit.springdemo.controller.employee.EmployeeDto;
import pl.pingwit.springdemo.converter.EmployeeConverter;
import pl.pingwit.springdemo.exception.PingwitNotFoundException;
import pl.pingwit.springdemo.repository.employee.Employee;
import pl.pingwit.springdemo.repository.employee.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeConverter converter;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeConverter converter, EmployeeRepository employeeRepository) {
        this.converter = converter;
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto getEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new PingwitNotFoundException("Employee not found"));
        return converter.toDto(employee);
    }

    public Integer createEmployee(CreateEmployeeInputDto inputDto) {
        Employee entity = converter.toEntity(inputDto);

        return employeeRepository.save(entity).getId();
    }
}
