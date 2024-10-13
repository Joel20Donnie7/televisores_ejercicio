package com.gestiontelevisor.controller;

import com.gestiontelevisor.entity.Televisor;
import com.gestiontelevisor.repository.TelevisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
@Controller
public class TelevisorController {

    @Autowired
    private TelevisorRepository televisorRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/televisor";
    }

    @GetMapping("/televisor")
    public String listarTelevisor(Model model) {
        List<Televisor> televisors = televisorRepository.findAll();
        model.addAttribute("televisores", televisors); // Cambiado a "televisores"
        return "televisor"; // Asegúrate de que la vista se llame "televisor.html"
    }

    @GetMapping("/televisor/nuevo")
    public String agregarTelevisor(Model model) {
        Televisor televisor = new Televisor();
        model.addAttribute("televisor", televisor);
        model.addAttribute("pageTitle", "Nueva Televisor");
        return "televisor_form"; // Vista para agregar nueva televisor
    }

    @PostMapping("/televisor/save")
    public String guardarTelevisor(Televisor televisor, RedirectAttributes redirectAttributes) {
        try {
            televisorRepository.save(televisor);
            redirectAttributes.addFlashAttribute("message", "El computador ha sido guardado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/televisor";
    }

    @GetMapping("/televisor/editar/{id}")
    public String editarTelevisor(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Televisor> optionalTelevisor = televisorRepository.findById(id);
        if (optionalTelevisor.isPresent()) {
            model.addAttribute("televisor", optionalTelevisor.get());
            model.addAttribute("pageTitle", "Editar Televisor");
            return "televisor_form"; // Vista para editar
        } else {
            redirectAttributes.addFlashAttribute("message", "Televisor no encontrado");
            return "redirect:/televisor";
        }
    }

    @GetMapping("/televisor/eliminar/{id}")
    public String eliminarTelevisor(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            televisorRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Televisor eliminado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error al eliminar el televisor: " + e.getMessage());
        }
        return "redirect:/televisor";
    }
}
