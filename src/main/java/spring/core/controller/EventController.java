package spring.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.core.entity.Auditorium;
import spring.core.entity.Event;
import spring.core.entity.Rating;
import spring.core.entity.Ticket;
import spring.core.service.AuditoriumService;
import spring.core.service.BookingService;
import spring.core.service.EventService;
import spring.core.util.TimePropertyEditor;
import spring.core.util.TimestampPropertyEditor;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private AuditoriumService auditoriumService;

    @InitBinder
    public void binderDate(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new TimestampPropertyEditor());
    }

    @InitBinder
    public void binderTime(WebDataBinder binder) {
        binder.registerCustomEditor(Time.class, new TimePropertyEditor("hh:mm"));
    }


    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView getAllEvents() {
        ModelAndView model = new ModelAndView("events");
        List<Event> events = eventService.getAllEvents();
        model.addObject("events", events);
        return model;
    }

    @RequestMapping(value = "/events/{id}/bookedTickets", method = RequestMethod.GET)
    public ModelAndView getBookedTicketsForEvent(@PathVariable String id) {
        ModelAndView model = new ModelAndView("bookedTickets");
        List<Ticket> bookedTickets = bookingService.getTicketsForEvent(eventService.getById(Integer.parseInt(id)));
        model.addObject("bookedTickets", bookedTickets);
        return model;
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public ModelAndView selectAuditorium(@PathVariable String id) {
        ModelAndView model = new ModelAndView("assign");
        model.addObject("id", id);
        model.addObject("auditoriums", auditoriumService.getAllAuditoriums());
        return model;
    }

    @RequestMapping(value = "/events/{id}/assign", method = RequestMethod.POST)
    public String assignAuditorium(@PathVariable String id,
                                   @ModelAttribute("auditorium") Auditorium auditorium,
                                   @RequestParam("date") Timestamp date,
                                   @RequestParam("time") Time time) {
        Event event = eventService.getById(Integer.parseInt(id));
        eventService.assignAuditorium(event, auditorium, date, time);
        return "redirect:/events";
    }

    @RequestMapping(value = "/events/addEvent", method = RequestMethod.GET)
    public String viewAddEvent(ModelMap model) {
        Event event = new Event();
        model.put("ratings", Rating.values());
        model.put("event", event);
        return "addEvent";
    }

    @RequestMapping(value = "/events/addEvent", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute("event") Event event) {
        eventService.create(event);
        return "redirect:/events";
    }

    @RequestMapping(value = "/events/deleteEvent/{id}", method = RequestMethod.GET)
    public String deleteEvent(@PathVariable(value = "id") int id) {
        eventService.delete(id);
        return "redirect:/events";
    }

    @RequestMapping(value = "events/byEvent.pdf", headers = "Accept=application/pdf")
    public ModelAndView getAllEventPdf() {
        List<Event> events = eventService.getAllEvents();
        ModelAndView mv = new ModelAndView("eventPdfView");
        mv.addObject("events", events);
        return mv;
    }
}
