package com.Alura.repository;

import com.Alura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    List<Autor> findByNascimentoLessThanEqualAndFalecimentoGreaterThanEqual(Integer ano1, Integer ano2);
}