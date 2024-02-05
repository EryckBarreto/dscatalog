package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long idExistente;

    @BeforeEach
    void setUp() throws Exception {
        idExistente = 1L;
    }

    @Test
    public void deletarDeveDeletarObjetoQuandoIdExistir() {

        repository.deleteById(idExistente);
        Optional<Product> result = repository.findById(idExistente);

        Assertions.assertFalse(result.isPresent());

    }

}
