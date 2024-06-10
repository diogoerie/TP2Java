package com.example.springboottp2.controller;

import com.example.springboottp2.model.Produto;
import com.example.springboottp2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LojaController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos") // mostrar todos produtos existentes
    public ResponseEntity<List<Produto>> getAllProdutos() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/produtos/{id}") // mostrar produto específico
    public ResponseEntity<Produto> getProdutoById(@PathVariable int id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/produtos") // cadastrar novo produto
    public Produto addProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/produtos/{id}") // editar produto existente
    public ResponseEntity<Produto> updateProduto(@PathVariable int id, @RequestBody Produto produto) {
        return produtoRepository.findById(id)
                .map(existingProduto -> {
                    produto.setId(existingProduto.getId());
                    Produto updatedProduto = produtoRepository.save(produto);
                    return ResponseEntity.ok(updatedProduto);
                })
                .orElseGet(() -> {
                    produto.setId(id);
                    Produto newProduto = produtoRepository.save(produto);
                    return ResponseEntity.status(HttpStatus.CREATED).body(newProduto);
                });
    }

    @DeleteMapping("/produtos/{id}") // deletar produto
    public ResponseEntity<Void> deleteProduto(@PathVariable int id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
