package spring.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Employee;
import spring.core.entity.Job;
import spring.core.service.EmployeeService;
import spring.core.service.JobService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ModelAndView getAll() throws DaoException {
        ModelAndView model = new ModelAndView("employees");
        List<Employee> employees = employeeService.getAll();
        model.addObject("employees", employees);
        return model;
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable String id) throws DaoException {
        ModelAndView model = new ModelAndView("employee");
        Employee employee = employeeService.getById(Integer.parseInt(id));
        model.addObject("employee", employee);
        return model;
    }

    @RequestMapping(value = "/employees/{id}/jobs", method = RequestMethod.GET)
    public ModelAndView getJobs(@PathVariable String id) throws DaoException {
        ModelAndView model = new ModelAndView("jobs");
        List<Job> jobs = new ArrayList<>(employeeService.getAllJobs(employeeService.getById(Integer.parseInt(id))));
        model.addObject("jobs", jobs);
        model.addObject("aboutEmployee", true);
        return model;
    }

    @RequestMapping(value = "/employees/addEmployee", method = RequestMethod.GET)
    public String viewAddEmployee(ModelMap model) {
        Employee employee = new Employee();
        model.put("employee", employee);
        return "addEmployee";
    }

    @RequestMapping(value = "/employees/addEmployee", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employee") Employee employee) throws DaoException {
        employeeService.create(employee);
        return "redirect:/employees";
    }

    @RequestMapping(value = "/employees/deleteEmployee/{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable(value = "id") int id) throws DaoException {
        employeeService.delete(employeeService.getById(id));
        return "redirect:/employees";
    }
}
