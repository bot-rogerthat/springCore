package spring.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import spring.core.entity.Auditorium;
import spring.core.entity.Event;
import spring.core.entity.User;
import spring.core.service.AuditoriumService;
import spring.core.service.EventService;
import spring.core.service.UserService;
import spring.core.util.JsonUtil;

import java.io.IOException;

@Controller
public class UploadController {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    AuditoriumService auditoriumService;

    @RequestMapping(value = "upload/users", method = RequestMethod.POST)
    public String uploadUsers(@RequestParam(value = "file", required = false) MultipartFile multipart) throws IOException {
        User[] users = JsonUtil.getEntities(JsonUtil.parseFile(multipart), User[].class);
        for (User user : users) {
            userService.create(user);
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "upload/events", method = RequestMethod.POST)
    public String uploadEvents(@RequestParam(value = "file", required = false) MultipartFile multipart) throws IOException {
        Event[] events = JsonUtil.getEntities(JsonUtil.parseFile(multipart), Event[].class);
        for (Event event : events) {
            eventService.create(event);
        }
        return "redirect:/events";
    }

    @RequestMapping(value = "upload/auditoriums", method = RequestMethod.POST)
    public String uploadAuditoriums(@RequestParam(value = "file", required = false) MultipartFile multipart) throws IOException {
        Auditorium[] auditoriums = JsonUtil.getEntities(JsonUtil.parseFile(multipart), Auditorium[].class);
        for (Auditorium auditorium : auditoriums) {
            auditoriumService.create(auditorium);
        }
        return "redirect:/auditoriums";
    }
}
