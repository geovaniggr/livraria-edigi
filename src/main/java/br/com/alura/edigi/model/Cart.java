package br.com.alura.edigi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Book, Integer> items = new HashMap<>();
    private LocalDateTime createdAt;

    public void addItem(Book book) {
        this.items.put(book, this.items.getOrDefault(book, 0) + 1);
    }

    public BigDecimal getTotal(){
     return this.items.entrySet()
            .stream()
            .map(item -> item.getKey().getPrice().multiply(new BigDecimal(item.getValue())))
            .reduce( BigDecimal.ZERO, (previousValue, atualValue) -> previousValue.add(atualValue));
    }

    public String checkout(){
        this.createdAt = LocalDateTime.now();

        return 
        "Compra Realizada com Sucesso!\n\n".concat(
            this.items.entrySet()
            .stream()
            .map( item -> {
                String titulo = item.getKey().getTitle();
                BigDecimal total = item.getKey().getPrice().multiply(new BigDecimal(item.getValue()));
                return String.format("Livro: %s  -  Quantidade: %d  -  Preço: %.2f  --  Total: %.2f", titulo, item.getValue(), item.getKey().getPrice(), total);
            })
            .reduce("", (livroAnterior, livro) -> livroAnterior.concat(livro).concat("\n"))
            .concat(String.format("\nTotal: %.2f\n", this.getTotal())));
    }

}