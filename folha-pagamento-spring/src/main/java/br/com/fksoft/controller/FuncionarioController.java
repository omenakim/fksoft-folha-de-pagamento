package br.com.fksoft.controller;

import br.com.fksoft.controller.view.model.FuncionarioForm;
import br.com.fksoft.controller.view.model.SumarioDeFuncionario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private Set<FuncionarioForm> fakeBancoDeDados;

    public FuncionarioController() {
        fakeBancoDeDados = new HashSet<>();
    }

    @GetMapping
    public String navegarListaFuncionarios(Model model) {
        List<SumarioDeFuncionario> funcionarios = new ArrayList<>();
        for (FuncionarioForm funcionario : fakeBancoDeDados) {
            funcionarios.add(new SumarioDeFuncionario(funcionario.getId(), funcionario.getNomeCompleto()));
        }
        model.addAttribute("tabelaFuncionarios", funcionarios);
        return "funcionario-list";
    }

    @GetMapping("/novo")
    public String navegarNovoFuncionario(Model model) {
        FuncionarioForm funcionarioNovo = new FuncionarioForm();
        model.addAttribute("funcionarioForm", funcionarioNovo);
        return "funcionario-add";
    }

    @GetMapping("/{id}")
    public String navegarEditarFuncionario(@PathVariable("id") Long id, Model model) {
        for (FuncionarioForm funcionario : fakeBancoDeDados) {
            if (funcionario.getId().equals(id)) {
                model.addAttribute("funcionarioForm", funcionario);
                break;
            }
        }
        return "funcionario-edit";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid FuncionarioForm funcionarioForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "funcionario-add";
        }
        funcionarioForm.setId(new Long(fakeBancoDeDados.size() + 1));
        fakeBancoDeDados.add(funcionarioForm);
        model.addAttribute("mensagem", "Usuário Salvo com Sucesso");
        return "funcionario-edit";
    }

    @PostMapping("/editar")
    public String editar(@Valid FuncionarioForm funcionarioForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "funcionario-add";
        } else if (funcionarioForm.getId() == null)
            throw new IllegalArgumentException();
        fakeBancoDeDados.remove(funcionarioForm);
        fakeBancoDeDados.add(funcionarioForm);
        model.addAttribute("mensagem", "Usuário editado com Sucesso");
        return "funcionario-edit";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Long id, Model model) {
        FuncionarioForm funcionarioForm = new FuncionarioForm();
        funcionarioForm.setId(id);
        fakeBancoDeDados.remove(funcionarioForm);
        return navegarListaFuncionarios(model);
    }
}

