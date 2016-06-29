package spring.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Ticket;
import spring.core.entity.User;
import spring.core.service.UserService;
import spring.core.util.TimestampPropertyEditor;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void binderDate(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new TimestampPropertyEditor());
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getAllUsers() throws DaoException {
        ModelAndView model = new ModelAndView("users");
        List<User> users = userService.getAllUsers();
        model.addObject("users", users);
        return model;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable String id) throws DaoException {
        ModelAndView model = new ModelAndView("user");
        User user = userService.getById(Integer.parseInt(id));
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value = "/users/{id}/tickets", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@PathVariable String id) throws DaoException {
        ModelAndView model = new ModelAndView("tickets");
        User user = userService.getById(Integer.parseInt(id));
        List<Ticket> tickets = userService.getBookedTickets(user);
        model.addObject("tickets", tickets);
        return model;
    }

    @RequestMapping(value = "/users/addUser", method = RequestMethod.GET)
    public String viewAddUser(ModelMap model) {
        User user = new User();
        model.put("user", user);
        return "addUser";
    }

    @RequestMapping(value = "/users/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) throws DaoException {
        userService.create(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable(value = "id") int id) throws DaoException {
        userService.delete(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "users/byUser.pdf", headers = "Accept=application/pdf")
    public ModelAndView getAllUserPdf() throws DaoException {
        List<User> users = userService.getAllUsers();
        ModelAndView mv = new ModelAndView("userPdfView");
        mv.addObject("users", users);
        return mv;
    }
}
