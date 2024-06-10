package com.example.springboottp2.repository;

import com.example.springboottp2.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProdutoRepository {
    private List<Produto> produtos = new ArrayList<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public ProdutoRepository() {
        produtos.add(new Produto(counter.incrementAndGet(), "Arroz", "001", 5.00f));
        produtos.add(new Produto(counter.incrementAndGet(), "Feijão", "002", 7.50f));
        produtos.add(new Produto(counter.incrementAndGet(), "Macarrão", "003", 3.00f));
        produtos.add(new Produto(counter.incrementAndGet(), "Óleo", "004", 4.00f));
        produtos.add(new Produto(counter.incrementAndGet(), "Sal", "005", 1.00f));
    }

    public List<Produto> findAll() {
        return produtos;
    }

    public Optional<Produto> findById(int id) {
        return produtos.stream().filter(produto -> produto.getId() == id).findFirst();
    }

    public Produto save(Produto produto) {
        if (produto.getId() == 0) {
            produto.setId(counter.incrementAndGet());
            produtos.add(produto);
        } else {
            deleteById(produto.getId());
            produtos.add(produto);
        }
        return produto;
    }

    public boolean existsById(int id) {
        return produtos.stream().anyMatch(produto -> produto.getId() == id);
    }

    public void deleteById(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }
}
