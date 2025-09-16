package com.acad.sys.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.acad.sys.Model.Pagamento;

public interface PagamentoRepositorio extends JpaRepository<Pagamento, Long> {

}
