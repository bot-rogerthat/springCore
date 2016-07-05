package spring.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Job;
import spring.core.service.EmployeeService;
import spring.core.service.JobService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public ModelAndView getAll() throws DaoException {
        ModelAndView model = new ModelAndView("jobs");
        List<Job> jobs = jobService.getAll();
        model.addObject("jobs", jobs);
        return model;
    }

    @RequestMapping(value = "/jobs/addJob", method = RequestMethod.GET)
    public String viewAddJob(ModelMap model) {
        Job job = new Job();
        model.put("job", job);
        return "addJob";
    }

    @RequestMapping(value = "/jobs/addJob", method = RequestMethod.POST)
    public String addJob(@ModelAttribute("job") Job job) throws DaoException {
        jobService.create(job);
        return "redirect:/jobs";
    }

    @RequestMapping(value = "/jobs/deleteJob/{jobId}", method = RequestMethod.GET)
    public String deleteJob(@PathVariable("jobId") int jobId) throws DaoException {
        jobService.delete(jobService.getById(jobId));
        return "redirect:/jobs";
    }

    @RequestMapping(value = "/jobs/assignJob/{jobId}", method = RequestMethod.GET)
    public ModelAndView viewAssignJob(@PathVariable("jobId") int jobId) throws DaoException {
        ModelAndView model = new ModelAndView("assignJob");
        model.addObject("employees", employeeService.getAll());
        model.addObject("job", jobService.getById(jobId));
        return model;
    }

    @RequestMapping(value = "/jobs/assignJob/{jobId}", method = RequestMethod.POST)
    public String assignJob(@PathVariable("jobId") int jobId,
                            @RequestParam("employee") int employeeId,
                            @RequestParam("date") Date date) throws DaoException {
        jobService.assign(employeeService.getById(employeeId), jobService.getById(jobId), new Timestamp(date.getTime()));
        return "redirect:/jobs";
    }
}
