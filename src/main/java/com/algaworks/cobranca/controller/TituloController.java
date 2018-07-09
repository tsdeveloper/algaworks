package com.algaworks.cobranca.controller;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.service.CadastroTituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/titulos")
public class TituloController {
    private final static  String ROOT_URL = "titulos/";
    private final static  String CADASTRO_VIEW_INDEX = "cobranca/index";
    private final static  String CADASTRO_VIEW_CREATE = "cobranca/create";
    private final static  String CADASTRO_VIEW_DELETE = "cobranca/delete";
    private final static  String CADASTRO_VIEW_TESTE = "cobranca/teste";


    @Autowired
    public CadastroTituloService cadastroTituloService;


    //    @RequestMapping("/listar")
    @RequestMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView(CADASTRO_VIEW_INDEX);
        List<Titulo> tituloListas = cadastroTituloService.findAll();
        mv.addObject("tituloListas", tituloListas);
        return mv;
    }

    @RequestMapping("/teste")
    public ModelAndView teste() {
        ModelAndView mv = new ModelAndView(CADASTRO_VIEW_TESTE);

        return mv;
    }

    @RequestMapping("/novo")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView(CADASTRO_VIEW_CREATE);
        mv.addObject(new Titulo());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {

        if (errors.hasErrors())
            return CADASTRO_VIEW_CREATE;

        try {

            cadastroTituloService.save(titulo);
            attributes.addFlashAttribute("message", "Cadastrado com Sucesso!!!");

        } catch (IllegalArgumentException e) {
            errors.rejectValue("dataVencimento", null, e.getMessage());
        return CADASTRO_VIEW_CREATE;
        }

        return "redirect:" + ROOT_URL + "novo";

    }

    @RequestMapping("/editar/{codigo}")
    public ModelAndView edit(@PathVariable("codigo") Titulo titulo) {

        ModelAndView mv = new ModelAndView(CADASTRO_VIEW_CREATE);
       mv.addObject(titulo);
        return mv;
    }

//    @RequestMapping("/editar/{codigo}")
//    public ModelAndView excluir(@PathVariable("codigo") Titulo titulo) {
//
//        ModelAndView mv = new ModelAndView(CADASTRO_VIEW_CREATE);
//        mv.addObject(titulo);
//        return mv;
//    }

    @RequestMapping(value = {"/delete/{codigo}"},method = RequestMethod.DELETE)
    public String excluir(@PathVariable("codigo") Titulo titulo, RedirectAttributes attributes) {

        System.out.print(">>>> excluir código titulo: " + titulo.getCodigo());
        cadastroTituloService.delete(titulo);

        attributes.addFlashAttribute("message", "Excluído com sucesso!!!");
//        ModelAndView mv = new ModelAndView(CADASTRO_VIEW_DELETE);
//        mv.addObject(titulo);
        return "redirect:/" + ROOT_URL;
    }


    @ModelAttribute("todosStatusTitulo")
    public List<StatusTitulo> todosStatusTitulo() {
        return Arrays.asList(StatusTitulo.values());
    }


}
