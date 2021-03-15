package controller;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IServiceCustomer;
import service.NameException;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    public IServiceCustomer serviceCustomer;

    @GetMapping("")
    public ModelAndView list(){
        ModelAndView modelAndView=new ModelAndView("list","c",serviceCustomer.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formCreate() throws NameException{
        ModelAndView modelAndView=new ModelAndView("create");
        modelAndView.addObject("c",new Customer());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Customer customer){
        ModelAndView modelAndView=new ModelAndView("redirect:/customers");
        serviceCustomer.save(customer);
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public String formEdit(@PathVariable Long id , Model model) throws NameException{
       model.addAttribute("c",serviceCustomer.findById(id));
       return "/edit";
    }
    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute Customer customer,@PathVariable Long id){
        ModelAndView modelAndView=new ModelAndView("redirect:/customers");
        customer.setId(id);
        serviceCustomer.save(customer);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        ModelAndView modelAndView=new ModelAndView("redirect:/customers");
        serviceCustomer.delete(id);
        return modelAndView;
    }
    @PostMapping("")
    public ModelAndView search(@RequestParam String name) throws NameException{
        ModelAndView modelAndView=new ModelAndView("list");
        List<Customer> list=serviceCustomer.findByName(name);
        modelAndView.addObject("c",list);
        return modelAndView;
    }
//    @PostMapping("")
//    public ModelAndView updateCustomer(Customer customer){
//        try {
//            serviceCustomer.save(customer);
//            return new ModelAndView("redirect:/customers");
//        }catch (NameException e){
//            return new  ModelAndView ("error");
//        }
//    }

    @ExceptionHandler(NameException.class)
    public ModelAndView showError(){
        return new ModelAndView("error");
    }



}
