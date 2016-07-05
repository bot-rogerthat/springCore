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
import spring.core.entity.Auditorium;
import spring.core.service.AuditoriumService;

import java.util.List;

@Controller
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;

    @RequestMapping(value = "/auditoriums", method = RequestMethod.GET)
    public ModelAndView getAllAuditorium() throws DaoException {
        ModelAndView model = new ModelAndView("auditoriums");
        List<Auditorium> auditoriums = auditoriumService.getAllAuditoriums();
        model.addObject("auditoriums", auditoriums);
        return model;
    }

    @RequestMapping(value = "/auditoriums/addAuditorium", method = RequestMethod.GET)
    public String viewAddAuditorium(ModelMap model) throws DaoException {
        Auditorium auditorium = new Auditorium();
        model.put("auditorium", auditorium);
        return "addAuditorium";
    }

    @RequestMapping(value = "/auditoriums/addAuditorium", method = RequestMethod.POST)
    public String addAuditorium(@ModelAttribute("auditorium") Auditorium auditorium) throws DaoException {
        auditoriumService.create(auditorium);
        return "redirect:/auditoriums";
    }

    @RequestMapping(value = "/auditoriums/deleteAuditorium/{id}", method = RequestMethod.GET)
    public String deleteAuditorium(@PathVariable(value = "id") int id) throws DaoException {
        auditoriumService.delete(auditoriumService.getById(id));
        return "redirect:/auditoriums";
    }

    @RequestMapping(value = "auditoriums/byAuditorium.pdf", headers = "Accept=application/pdf")
    public ModelAndView getAllAuditoriumPdf() throws DaoException {
        List<Auditorium> auditoriums = auditoriumService.getAllAuditoriums();
        ModelAndView mv = new ModelAndView("auditoriumPdfView");
        mv.addObject("auditoriums", auditoriums);
        return mv;
    }
}
