package br.com.alura.edigi.models;

import java.math.BigDecimal;

import br.com.alura.edigi.model.Author;
import br.com.alura.edigi.model.Book;
import br.com.alura.edigi.model.Cart;
import br.com.alura.edigi.model.Category;
import br.com.alura.edigi.repository.AuthorRepository;
import br.com.alura.edigi.repository.BookRepository;
import br.com.alura.edigi.repository.CategoryRepository;

public class CartTest {
    public static void main(String[] args) {
        final BigDecimal CART_REAL_PRICE = new BigDecimal(180);
        String descriptonGreatherThan500 = "LoLorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla venenatis, ante sed pulvinar consectetur, risus est feugiat ipsum, in facilisis tortor velit rhoncus erat. Integer ligula nibh, rutrum efficitur quam at, interdum commodo sem. Phasellus ligula ligula, dapibus id sollicitudin nec, cursus et nisl. Nam rhoncus aliquet nibh, in tempus ipsum lacinia non. Duis eget eros tincidunt, consequat sem nec, faucibus arcu. In gravida finibus velit, ac venenatis mi ultricies ac. Morbi eros nisl, sodales vitae risus sit amet, egestas consectetur odio. Nullam ut nisi lacus. Aenean vel dapibus sapien. Aliquam tempus ornare lacus, porttitor aliquam sapien ornare vitae. Nam justo sapien, commodo vel elit mattis, mollis tempor lorem. Aenean mi nibh, gravida eget arcu at, faucibus finibus nunc. Quisque facilisis ligula in lacinia pharetra.duis ac auctor leo. Integer efficitur, lectus non laoreet lacinia, tellus nulla elementum odio, malesuada gravida nunc massa nec dolor. Suspendisse tempus tortor quis magna mattis, ac efficitur ipsum dapibus. Etiam molestie, nisl ut consectetur varius, justo purus volutpat ligula, et hendrerit arcu justo eget nisi. Ut placerat diam eget quam faucibus tincidunt. Nulla scelerisque mauris eu ante convallis porta. Nullam elementum nec odio rutrum placerat. Nam pellentesque tortor pulvinar eros placerat, eget tincidunt tortor semper. Nullam sodales sit amet massa ac porttitor. Sed luctus sed ante vel dictum. In lacinia nibh non tincidunt facilisis. In hac habitasse platea dictumst. In hac habitasse platea dictumst. Vestibulum in volutpat nibh, at pharetra lectus. Praesent ultricies neque vel sem pharetra tempus. Curabitur rhoncus urna sit amet sem consectetur, a interdum neque iaculis.Vsivassamus ut faucibus lorem. Nulla ut felis eu sem malesuada congue ut et metus. Vivamus eu lacus a enim commodo viverra. Fusce aliquam at nibh vitae suscipit. Quisque rutrum nibh eu blandit rutrum. Mauris magna nisi, efficitur in faucibus a, aliquet condimentum urna. Vivamus mollis, massa scelerisque cursus venenatis, erat erat porta quam, ut cursus justo quam placerat est. Aenean sed massa in libero facilisis sollicitudin. Suspendisse aliquam molestie risus, et iaculis nisi tempus tincidunt. Nunc bibendum nibh eu elit faucibus, in eleifend neque auctor. Aenean nec mauris blandit, laoreet dolor ut, rutrum dui. Aliquam vitae pharetra mi, in lacinia nunc. Proin et massa in purus imperdiet lobortis.Aenean viverra tempor sem non mattis. Quisque in condimentum quam. Integer at malesuada eros. Morbi augue tellus, gravida eu rutrum sit amet, euismod id sem. Sed mauris nisi, posuere eget enim id, molestie rutrum velit. Fusce rutrum interdum libero, sed vestibulum dui vestibulum eget. Sed metus orci, ultricies ac neque ut, semper feugiat metus. Pellentesque varius tincidunt lorem eget eleifend. Etiam dictum dui in nibh euismod vehicula. Ut ac ligula vitae risus vehicula accumsan et nec purus. Vivamus eget justo turpis. Vivamus nisl urna, aliquam vel leo eu, tincidunt congue diam. Donec elementum hendrerit faucibus. Sed elementum molestie tortor, et dignissim mauris cursus sit amet. Pellentesque sed finibus enim. Vestibulum ac sapien eget metus sollicitudin interdum ac sit amet lorem. Praesent at porta nibh, vel iaculis tortor. Praesent maximus, libero id bibendum vestibulum, tellus urna condimentum leo.";

        BookRepository bookRepository = new BookRepository();
        AuthorRepository authorRepository = new AuthorRepository();
        CategoryRepository categoryRepository = new CategoryRepository();

        Author author = new Author("Geovani", "geovani@alura.com.br");
        Category category = new Category("Programação");

        Book notSavedBook = new Book("Java15", descriptonGreatherThan500, "1. O que é Java", "978-12-12134-12-8", new BigDecimal(40), 50, author, category);

        categoryRepository.save(category);
        authorRepository.save(author);

        Book book = new Book("Java8", descriptonGreatherThan500, "1. O que é Java", "978-12-12134-12-1", new BigDecimal(50), 50, author, category);
        Book book2 = new Book("Java9", descriptonGreatherThan500, "1. O que é Java", "978-12-12134-12-2", new BigDecimal(40), 50, author, category);
        Book book3 = new Book("Java10", descriptonGreatherThan500, "1. O que é Java", "978-12-12134-12-4", new BigDecimal(40), 50, author, category);

        bookRepository.save(book);
        bookRepository.save(book2);
        bookRepository.save(book3);

        Cart carrinho = new Cart(bookRepository);

        carrinho.addItem(book);
        carrinho.addItem(book);
        carrinho.addItem(book2);
        carrinho.addItem(book3);

        try {
            carrinho.addItem(notSavedBook);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("O carrinho deve ter o preço correto");
            if (!(carrinho.getTotal().equals(CART_REAL_PRICE)))
                throw new Exception();
            System.out.println("O preço está correto");
        } catch (Exception e) {
            System.out.println("O valor do carrinho está incorreto");
        }

    }
}