package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.tests.Factory;
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
    private long idInexistente;
    private long countTotalProducts;

    @BeforeEach
    void setUp() throws Exception {
        idExistente = 1L;
        idInexistente = 100000L;
        countTotalProducts = 25L;
    }

    @Test
    public void saveDevePersistirComAutoIncrementoQuandoIdForNulo() {
        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());
    }

    @Test
    public void findByIdDeveRetornarNaoVazioSeIdExistir() {

        Optional<Product> result = repository.findById(idExistente);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdDeveRetornarVazioSeIdNaoExistir() {

        Optional<Product> result = repository.findById(idInexistente);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void deletarDeveDeletarObjetoQuandoIdExistir() {

        repository.deleteById(idExistente);
        Optional<Product> result = repository.findById(idExistente);

        Assertions.assertFalse(result.isPresent());

    }

}
