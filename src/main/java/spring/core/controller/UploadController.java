package spring.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import spring.core.dao.AuditoriumDao;
import spring.core.dao.EventDao;
import spring.core.dao.UserDao;
import spring.core.dao.impl.jdbc.DaoException;
import spring.core.entity.Auditorium;
import spring.core.entity.Event;
import spring.core.entity.User;
import spring.core.util.JsonUtil;

import java.io.IOException;

@Controller
public class UploadController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private AuditoriumDao auditoriumDao;

    @RequestMapping(value = "upload/users", method = RequestMethod.POST)
    public String uploadUsers(@RequestParam(value = "file", required = false) MultipartFile multipart) throws IOException, DaoException {
        User[] users = JsonUtil.getEntities(JsonUtil.parseFile(multipart), User[].class);
        for (User user : users) {
            userDao.create(user);
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "upload/events", method = RequestMethod.POST)
    public String uploadEvents(@RequestParam(value = "file", required = false) MultipartFile multipart) throws IOException, DaoException {
        Event[] events = JsonUtil.getEntities(JsonUtil.parseFile(multipart), Event[].class);
        for (Event event : events) {
            eventDao.create(event);
        }
        return "redirect:/events";
    }

    @RequestMapping(value = "upload/auditoriums", method = RequestMethod.POST)
    public String uploadAuditoriums(@RequestParam(value = "file", required = false) MultipartFile multipart) throws IOException, DaoException {
        Auditorium[] auditoriums = JsonUtil.getEntities(JsonUtil.parseFile(multipart), Auditorium[].class);
        for (Auditorium auditorium : auditoriums) {
            auditoriumDao.create(auditorium);
        }
        return "redirect:/auditoriums";
    }
}
