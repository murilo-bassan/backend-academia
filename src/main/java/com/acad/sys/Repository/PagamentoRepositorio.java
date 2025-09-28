package com.acad.sys.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.acad.sys.Model.Aluno;
import com.acad.sys.Model.Pagamento;

public interface PagamentoRepositorio extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByAluno(Aluno aluno);
    
    // Buscar os últimos 5 pagamentos, ordenados por data em ordem decrescente
@Query("SELECT p FROM Pagamento p ORDER BY p.data DESC LIMIT 5")
    List<Pagamento> findTop5ByOrderByDataDesc();

}
