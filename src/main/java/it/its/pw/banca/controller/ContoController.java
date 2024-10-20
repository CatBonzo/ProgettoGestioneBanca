package it.its.pw.banca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.its.pw.banca.model.Conto;
import it.its.pw.banca.service.ContoService;

@Controller
@RequestMapping("/conti")
public class ContoController {

    @Autowired
    private ContoService contoService;

    // Visualizza la lista dei conti
    @GetMapping
    public String listaConti(Model model) {
        List<Conto> conti = contoService.getAllConti();
        model.addAttribute("conti", conti);
        return "listaConti"; 
    }

    // Mostra il form per creare un nuovo conto
    @GetMapping("/crea")
    public String mostraFormCreaConto(Model model) {
        model.addAttribute("conto", new Conto());
        return "creaConto"; 
    }

    // Crea un nuovo conto
    @PostMapping("/crea")
    public String creaConto(@RequestParam String intestatario) {
        contoService.creaConto(intestatario);
        return "redirect:/conti"; 
    }

    @PostMapping("/{id}/deposita")
    public String deposita(@PathVariable Long id, @RequestParam double importo, RedirectAttributes redirectAttributes) {
        try {
            contoService.deposita(id, importo);
            redirectAttributes.addFlashAttribute("successMessage", "Deposito effettuato con successo!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/conti";
    }

    @PostMapping("/{id}/prelievo")
    public String prelievo(@PathVariable Long id, @RequestParam double importo, RedirectAttributes redirectAttributes) {
        try {
            contoService.prelievo(id, importo);
            redirectAttributes.addFlashAttribute("successMessage", "Prelievo effettuato con successo!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/conti";
    }
}