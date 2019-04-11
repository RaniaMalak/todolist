package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/")
    public String listToDo(Model model) {
        model.addAttribute("todolist", todoRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String todoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todoform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todoform";
        }
        todoRepository.save(todo);
        return "redirect:/";

    }


    @RequestMapping("/details/{id}")
    public String showToDo(@PathVariable("id") long id,Model model)
    {
        model.addAttribute("todo",todoRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateToDo(@PathVariable("id") long id,Model model){
        model.addAttribute("todo" , todoRepository.findById(id).get());
        return "todoform";

    }

    @RequestMapping("/delete/{id}")
    public String delToDo(@PathVariable("id") long id){
        todoRepository.deleteById(id);
        return "redirect:/";
    }
}

