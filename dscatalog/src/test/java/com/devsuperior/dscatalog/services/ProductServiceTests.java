package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ControllerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    private long idExistente;
    private long idInexistente;
    private long idDependente;

    @BeforeEach
    void setUp() throws Exception {
        idExistente = 1L;
        idInexistente = 100000L;
        idDependente = 25L;

        Mockito.doNothing().when(repository).deleteById(idExistente);
        Mockito.when(repository.existsById(idExistente)).thenReturn(true);
        Mockito.when(repository.existsById(idInexistente)).thenReturn(false);
        Mockito.when(repository.existsById(idDependente)).thenReturn(true);
    }


    @Test
    public void deleteShouldDoNothingWhenIdExists() {

        Assertions.assertDoesNotThrow(() -> {
            service.delete(idExistente);
        });

    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            service.delete(idInexistente);
        });
    }

}
