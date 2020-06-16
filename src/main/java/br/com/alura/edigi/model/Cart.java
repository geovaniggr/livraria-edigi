package br.com.alura.edigi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import br.com.alura.edigi.repository.BookRepository;

public class Cart {

    private Map<Book, Integer> items = new HashMap<>();
    private LocalDateTime createdAt;
    private BookRepository bookRepository;

    public Cart(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addItem(Book book) {
        if (bookRepository.findByTitle(book.getTitle()).isEmpty())
            throw new RuntimeException("O livro não está em nossa base de dados");

        this.items.put(book, this.items.getOrDefault(book, 0) + 1);
    }

    public BigDecimal getTotal() {
        return this.items.entrySet().stream()
                .map(item -> item.getKey().getPrice().multiply(new BigDecimal(item.getValue())))
                .reduce(BigDecimal.ZERO, (previousValue, atualValue) -> previousValue.add(atualValue));
    }

    public String checkout() {
        this.createdAt = LocalDateTime.now();

        return
        "Compra Realizada com Sucesso!\n\n".concat(this.items.entrySet().stream().map(item -> {
            String titulo = item.getKey().getTitle();
            BigDecimal total = item.getKey().getPrice().multiply(new BigDecimal(item.getValue()));
            return String.format("Livro: %s  -  Quantidade: %d  -  Preço: %.2f  --  Total: %.2f", titulo, item.getValue(), item.getKey().getPrice(), total);
        })
        .reduce("", (livroAnterior, livro) -> livroAnterior.concat(livro).concat("\n"))
        .concat(String.format("\nTotal: %.2f\n", this.getTotal())));
    }

}