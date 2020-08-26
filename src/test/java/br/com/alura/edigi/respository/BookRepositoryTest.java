package br.com.alura.edigi.respository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.alura.edigi.model.Author;
import br.com.alura.edigi.model.Book;
import br.com.alura.edigi.model.Category;
import br.com.alura.edigi.repository.BookRepository;

public class BookRepositoryTest {

    private final static String descriptionGreatherThan500Characters = "LoLorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla venenatis, ante sed pulvinar consectetur, risus est feugiat ipsum, in facilisis tortor velit rhoncus erat. Integer ligula nibh, rutrum efficitur quam at, interdum commodo sem. Phasellus ligula ligula, dapibus id sollicitudin nec, cursus et nisl. Nam rhoncus aliquet nibh, in tempus ipsum lacinia non. Duis eget eros tincidunt, consequat sem nec, faucibus arcu. In gravida finibus velit, ac venenatis mi ultricies ac. Morbi eros nisl, sodales vitae risus sit amet, egestas consectetur odio. Nullam ut nisi lacus. Aenean vel dapibus sapien. Aliquam tempus ornare lacus, porttitor aliquam sapien ornare vitae. Nam justo sapien, commodo vel elit mattis, mollis tempor lorem. Aenean mi nibh, gravida eget arcu at, faucibus finibus nunc. Quisque facilisis ligula in lacinia pharetra.duis ac auctor leo. Integer efficitur, lectus non laoreet lacinia, tellus nulla elementum odio, malesuada gravida nunc massa nec dolor. Suspendisse tempus tortor quis magna mattis, ac efficitur ipsum dapibus. Etiam molestie, nisl ut consectetur varius, justo purus volutpat ligula, et hendrerit arcu justo eget nisi. Ut placerat diam eget quam faucibus tincidunt. Nulla scelerisque mauris eu ante convallis porta. Nullam elementum nec odio rutrum placerat. Nam pellentesque tortor pulvinar eros placerat, eget tincidunt tortor semper. Nullam sodales sit amet massa ac porttitor. Sed luctus sed ante vel dictum. In lacinia nibh non tincidunt facilisis. In hac habitasse platea dictumst. In hac habitasse platea dictumst. Vestibulum in volutpat nibh, at pharetra lectus. Praesent ultricies neque vel sem pharetra tempus. Curabitur rhoncus urna sit amet sem consectetur, a interdum neque iaculis.Vsivassamus ut faucibus lorem. Nulla ut felis eu sem malesuada congue ut et metus. Vivamus eu lacus a enim commodo viverra. Fusce aliquam at nibh vitae suscipit. Quisque rutrum nibh eu blandit rutrum. Mauris magna nisi, efficitur in faucibus a, aliquet condimentum urna. Vivamus mollis, massa scelerisque cursus venenatis, erat erat porta quam, ut cursus justo quam placerat est. Aenean sed massa in libero facilisis sollicitudin. Suspendisse aliquam molestie risus, et iaculis nisi tempus tincidunt. Nunc bibendum nibh eu elit faucibus, in eleifend neque auctor. Aenean nec mauris blandit, laoreet dolor ut, rutrum dui. Aliquam vitae pharetra mi, in lacinia nunc. Proin et massa in purus imperdiet lobortis.Aenean viverra tempor sem non mattis. Quisque in condimentum quam. Integer at malesuada eros. Morbi augue tellus, gravida eu rutrum sit amet, euismod id sem. Sed mauris nisi, posuere eget enim id, molestie rutrum velit. Fusce rutrum interdum libero, sed vestibulum dui vestibulum eget. Sed metus orci, ultricies ac neque ut, semper feugiat metus. Pellentesque varius tincidunt lorem eget eleifend. Etiam dictum dui in nibh euismod vehicula. Ut ac ligula vitae risus vehicula accumsan et nec purus. Vivamus eget justo turpis. Vivamus nisl urna, aliquam vel leo eu, tincidunt congue diam. Donec elementum hendrerit faucibus. Sed elementum molestie tortor, et dignissim mauris cursus sit amet. Pellentesque sed finibus enim. Vestibulum ac sapien eget metus sollicitudin interdum ac sit amet lorem. Praesent at porta nibh, vel iaculis tortor. Praesent maximus, libero id bibendum vestibulum, tellus urna condimentum leo.";
    private BookRepository bookRepository = new BookRepository();
    private static Author author;
    private static Category category;

    @BeforeAll
    public static void initializeData() {
        author = new Author("Geovani", "geovani@alura.com.br");
        category = new Category("Programação");
    }

    @Test
    @DisplayName("Livro não existente deve ser cadastrado com sucesso")
    public void addBookToDatabase() {
        Book book = new Book("Java8", descriptionGreatherThan500Characters, "1. O que é Java", "978-12-32334-12-1", new BigDecimal(50), 50, author, category);
        bookRepository.save(book);
    }

    @Test
    @DisplayName("Livro com título e ISBN repetido devem lançar exceção")
    public void addExistingsBooks(){
        Book book = new Book("NodeJS", descriptionGreatherThan500Characters, "1. O que é Java", "978-12-32334-12-7", new BigDecimal(50), 50, author, category);
        Book bookWithSameISBN = new Book("Javascript para Backend", descriptionGreatherThan500Characters, "1. O que é Java", "978-12-32334-12-7", new BigDecimal(50), 50, author, category);
        Book bookWithSameTitle = new Book("NodeJS", descriptionGreatherThan500Characters, "1. O que é Java", "978-12-32334-12-3", new BigDecimal(50), 50, author, category);

        bookRepository.save(book);

        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> bookRepository.save(bookWithSameISBN), "Livro com ISBN repetido foi cadastrado"),
            () -> assertThrows(IllegalArgumentException.class, () -> bookRepository.save(bookWithSameTitle), "Livro com titulo repetido foi cadastrado")
        );
    }


}