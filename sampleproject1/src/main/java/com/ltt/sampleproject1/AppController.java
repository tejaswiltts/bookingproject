package com.ltt.sampleproject1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
 
    @Autowired
    private BookingService service;
    
    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Booking> listBooking = service.listAll();
        model.addAttribute("listBooking", listBooking);
         
        return "index";
    }
        @RequestMapping("/new")
        public String showNewBookingPage(Model model) {
          Booking booking = new Booking();
            model.addAttribute("booking", booking);
             
            return "new_booking";
        }
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public String saveBooking(@ModelAttribute("booking") Booking booking) {
            service.save(booking);
             
            return "redirect:/";
        }

        @RequestMapping("/edit/{Id}")
        public ModelAndView showEditBookingPage(@PathVariable(name = "Id") long Id) {
            ModelAndView mav = new ModelAndView("edit_booking");
           Booking booking = service.get(Id);
            mav.addObject("booking", booking);
             
            return mav;
        }
        
        @RequestMapping("/delete/{Id}")
        public String deleteBooking(@PathVariable(name = "Id") int Id) {
            service.delete(Id);
            return "redirect:/";       
        }
    }