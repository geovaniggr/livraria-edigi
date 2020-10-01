package br.com.alura.edigi.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.alura.edigi.model.Author;
import br.com.alura.edigi.model.Book;
import br.com.alura.edigi.model.Cart;
import br.com.alura.edigi.model.Category;
import br.com.alura.edigi.repository.BookRepository;
import org.mockito.Mockito;

public class CartTest {

    private String descriptionGreatherThan500Characters = "LoLorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla venenatis, ante sed pulvinar consectetur, risus est feugiat ipsum, in facilisis tortor velit rhoncus erat. Integer ligula nibh, rutrum efficitur quam at, interdum commodo sem. Phasellus ligula ligula, dapibus id sollicitudin nec, cursus et nisl. Nam rhoncus aliquet nibh, in tempus ipsum lacinia non. Duis eget eros tincidunt, consequat sem nec, faucibus arcu. In gravida finibus velit, ac venenatis mi ultricies ac. Morbi eros nisl, sodales vitae risus sit amet, egestas consectetur odio. Nullam ut nisi lacus. Aenean vel dapibus sapien. Aliquam tempus ornare lacus, porttitor aliquam sapien ornare vitae. Nam justo sapien, commodo vel elit mattis, mollis tempor lorem. Aenean mi nibh, gravida eget arcu at, faucibus finibus nunc. Quisque facilisis ligula in lacinia pharetra.duis ac auctor leo. Integer efficitur, lectus non laoreet lacinia, tellus nulla elementum odio, malesuada gravida nunc massa nec dolor. Suspendisse tempus tortor quis magna mattis, ac efficitur ipsum dapibus. Etiam molestie, nisl ut consectetur varius, justo purus volutpat ligula, et hendrerit arcu justo eget nisi. Ut placerat diam eget quam faucibus tincidunt. Nulla scelerisque mauris eu ante convallis porta. Nullam elementum nec odio rutrum placerat. Nam pellentesque tortor pulvinar eros placerat, eget tincidunt tortor semper. Nullam sodales sit amet massa ac porttitor. Sed luctus sed ante vel dictum. In lacinia nibh non tincidunt facilisis. In hac habitasse platea dictumst. In hac habitasse platea dictumst. Vestibulum in volutpat nibh, at pharetra lectus. Praesent ultricies neque vel sem pharetra tempus. Curabitur rhoncus urna sit amet sem consectetur, a interdum neque iaculis.Vsivassamus ut faucibus lorem. Nulla ut felis eu sem malesuada congue ut et metus. Vivamus eu lacus a enim commodo viverra. Fusce aliquam at nibh vitae suscipit. Quisque rutrum nibh eu blandit rutrum. Mauris magna nisi, efficitur in faucibus a, aliquet condimentum urna. Vivamus mollis, massa scelerisque cursus venenatis, erat erat porta quam, ut cursus justo quam placerat est. Aenean sed massa in libero facilisis sollicitudin. Suspendisse aliquam molestie risus, et iaculis nisi tempus tincidunt. Nunc bibendum nibh eu elit faucibus, in eleifend neque auctor. Aenean nec mauris blandit, laoreet dolor ut, rutrum dui. Aliquam vitae pharetra mi, in lacinia nunc. Proin et massa in purus imperdiet lobortis.Aenean viverra tempor sem non mattis. Quisque in condimentum quam. Integer at malesuada eros. Morbi augue tellus, gravida eu rutrum sit amet, euismod id sem. Sed mauris nisi, posuere eget enim id, molestie rutrum velit. Fusce rutrum interdum libero, sed vestibulum dui vestibulum eget. Sed metus orci, ultricies ac neque ut, semper feugiat metus. Pellentesque varius tincidunt lorem eget eleifend. Etiam dictum dui in nibh euismod vehicula. Ut ac ligula vitae risus vehicula accumsan et nec purus. Vivamus eget justo turpis. Vivamus nisl urna, aliquam vel leo eu, tincidunt congue diam. Donec elementum hendrerit faucibus. Sed elementum molestie tortor, et dignissim mauris cursus sit amet. Pellentesque sed finibus enim. Vestibulum ac sapien eget metus sollicitudin interdum ac sit amet lorem. Praesent at porta nibh, vel iaculis tortor. Praesent maximus, libero id bibendum vestibulum, tellus urna condimentum leo.";
    private  BookRepository bookRepository = Mockito.mock(BookRepository.class);
    private  Cart carrinho = new Cart(bookRepository);

    @Test
    @DisplayName("Adicionar livro não existente no banco deve lançar exceção")
    public void addNotSavedBook() {
        var author = new Author("Geovani", "geovani@alura.com.br");
        var category = new Category("Programação");
        Book notSavedBook = new Book("Java15", descriptionGreatherThan500Characters, "1. O que é Java", "978-12-12134-12-8", new BigDecimal(40), 50, author, category);

        when(bookRepository.findByTitle(notSavedBook.getTitle())).thenReturn((Optional.ofNullable((List<Book>) null)));

        assertThrows(RuntimeException.class, () -> carrinho.addItem(notSavedBook));
    }

    @Test
    @DisplayName("Livros devem ser adicionados no carrinho e ter o preço correto")
    public void correctPriceForCart() {
        BigDecimal CART_REAL_PRICE = new BigDecimal(180);
        var author = new Author("Geovani", "geovani@alura.com.br");
        var category = new Category("Programação");


        Book book = new Book("Java8", descriptionGreatherThan500Characters, "1. O que é Java", "978-12-12134-12-1", new BigDecimal(50), 50, author, category);
        Book book2 = new Book("Java9", descriptionGreatherThan500Characters, "1. O que é Java", "978-12-12134-12-2", new BigDecimal(40), 50, author, category);
        Book book3 = new Book("Java10", descriptionGreatherThan500Characters, "1. O que é Java", "978-12-12134-12-4", new BigDecimal(40), 50, author, category);

        when(bookRepository.existsByTitle(book.getTitle())).thenReturn(true);
        when(bookRepository.existsByTitle(book2.getTitle())).thenReturn(true);
        when(bookRepository.existsByTitle(book3.getTitle())).thenReturn(true);

        carrinho.addItem(book);
        carrinho.addItem(book);
        carrinho.addItem(book2);
        carrinho.addItem(book3);

        assertEquals(carrinho.getTotal(), CART_REAL_PRICE);
    }
}