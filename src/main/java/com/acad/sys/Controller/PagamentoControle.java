package com.acad.sys.Controller;

import com.acad.sys.Model.Pagamento;
import com.acad.sys.Repository.PagamentoRepositorio;
import com.acad.sys.Repository.AlunoRepositorio;
import com.acad.sys.Model.Aluno;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pagamentos")
public class PagamentoControle {

    private final PagamentoRepositorio pagamentoRepositorio;
    private final AlunoRepositorio alunoRepositorio;

    public PagamentoControle(PagamentoRepositorio pagamentoRepositorio, AlunoRepositorio alunoRepositorio) {
        this.pagamentoRepositorio = pagamentoRepositorio;
        this.alunoRepositorio = alunoRepositorio;
    }

    @GetMapping("/listar")
    public List<Pagamento> listar() {
        return pagamentoRepositorio.findAll();
    }

    @GetMapping("/buscar/{id}")
    public Optional<Pagamento> buscar(@PathVariable Long id) {
        return pagamentoRepositorio.findById(id);
    }

    @PostMapping("/criar/{idAluno}")
    public Pagamento criar(@PathVariable Long idAluno, @RequestBody Pagamento pagamento) {
        Aluno aluno = alunoRepositorio.findById(idAluno)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        pagamento.setAluno(aluno);
        return pagamentoRepositorio.save(pagamento);
    }

    @PutMapping("/atualizar/{id}")
    public Pagamento atualizar(@PathVariable Long id, @RequestBody Pagamento pagamentoAtualizado) {
        Pagamento pagamento = pagamentoRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        pagamento.setValor(pagamentoAtualizado.getValor());
        pagamento.setData(pagamentoAtualizado.getData());
        return pagamentoRepositorio.save(pagamento);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        pagamentoRepositorio.deleteById(id);
    }

    @GetMapping("/ultimos")
    public List<Pagamento> getUltimosPagamentos() {
        return pagamentoRepositorio.findTop5ByOrderByDataDesc();
    }

    @GetMapping("/aluno/{idAluno}")
    public List<Pagamento> listarPorAluno(@PathVariable Long idAluno) {
        Aluno aluno = alunoRepositorio.findById(idAluno)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        return pagamentoRepositorio.findByAluno(aluno);
    }

}
