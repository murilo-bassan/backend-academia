package com.acad.sys.Controller;

import com.acad.sys.Model.Aluno;
import com.acad.sys.Repository.AlunoRepositorio;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/alunos")
public class AlunoControle {

    private final AlunoRepositorio alunoRepositorio;

    public AlunoControle(AlunoRepositorio alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }

    @GetMapping("/listar")
    public List<Aluno> listar() {
        return alunoRepositorio.findAll();
    }

    @PostMapping("/criar")
    public Aluno criar(@RequestBody Aluno aluno) {
        return alunoRepositorio.save(aluno);
    }

    @GetMapping("buscar/{id}")
    public Optional<Aluno> buscar(@PathVariable Long id) {
        return alunoRepositorio.findById(id);
    }

    @PutMapping("atualizar/{id}")
    public Aluno atualizar(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        Aluno aluno = alunoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        aluno.setNome(alunoAtualizado.getNome());
        aluno.setEmail(alunoAtualizado.getEmail());
        aluno.setTelefone(alunoAtualizado.getTelefone());
        return alunoRepositorio.save(aluno);
    }

    @DeleteMapping("deletar/{id}")
    public void deletar(@PathVariable Long id) {
        alunoRepositorio.deleteById(id);
    }
}
