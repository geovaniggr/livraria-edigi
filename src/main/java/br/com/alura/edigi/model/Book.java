package br.com.alura.edigi.model;

import java.math.BigDecimal;

public class Book {

    private String title;
    private String synopsis;
    private String tableOfContents;
    private String isbn;
    private BigDecimal price;
    private Integer numberOfPages;
    private Integer edition = 1;
    private Author author;
    private Category category;

    public Book(String title, String synopsis, String tableOfContents, String isbn, BigDecimal price,
            Integer numberOfPages, Author author, Category category) {
        setTitle(title);
        setSynopsis(synopsis);
        setTableOfContents(tableOfContents);
        setIsbn(isbn);
        setPrice(price);
        setNumberOfPages(numberOfPages);
        setAuthor(author);
        setCategory(category);
    }

    private void setTitle(String title) {
        if (title == null || title.isEmpty())
            throw new IllegalArgumentException("O nome não pode ser vazio");

        this.title = title;
    }

    private void setSynopsis(String synopsis) {
        if (synopsis == null || synopsis.isEmpty())
            throw new IllegalArgumentException("O resumo não pode ser vazio");

        if (synopsis.length() < 500)
            throw new IllegalArgumentException("O resumo não pode ser menor de 500 caracteres");

        this.synopsis = synopsis;
    }

    private void setTableOfContents(String tableOfContents) {
        if (tableOfContents == null || tableOfContents.isEmpty())
            throw new IllegalArgumentException("O sumário não pode ser vazio");

        this.tableOfContents = tableOfContents;
    }

    private void setIsbn(String isbn) {
        if (isbn == null || isbn.isEmpty())
            throw new IllegalArgumentException("O ISBN não pode ser vazio");

        if (!isbn.matches("^978\\-\\d{2}\\-\\d{5}\\-\\d{2}\\-\\d$"))
            throw new IllegalArgumentException("O formato do ISBN é invalido");

        this.isbn = isbn;
    }

    private void setPrice(BigDecimal price) {
        if (price == null || price.doubleValue() < 0)
            throw new IllegalArgumentException("O preço não pode ser menor que 0");

        this.price = price;
    }

    private void setNumberOfPages(Integer numberOfPages) {
        if (numberOfPages == null || numberOfPages.intValue() <= 0)
            throw new IllegalArgumentException("O número de páginas deve ser maior que 0");

        this.numberOfPages = numberOfPages;
    }

    private void setAuthor(Author author) {
        if (author == null)
            throw new IllegalArgumentException("O autor não pode ser nulo");

        this.author = author;
    }

    private void setCategory(Category category) {
        if (category == null)
            throw new IllegalArgumentException("A categoria não pode ser vazia");

        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;

        Book other = (Book) obj;
        return this.title.equals(other.title) || this.isbn.equals(other.isbn);
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Book [ category=" + category + ", edition=" + edition + ", isbn=" + isbn + ", numberOfPages="
                + numberOfPages + ", price=" + price + ", synopsis=" + synopsis + ", tableOfContents=" + tableOfContents
                + ", title=" + title + "]";
    }

    public String toView() {
        return String.format(
                "-----------------\nTitulo: %s \n Autor: %s\n Edicão: %s\nCategoria: %s\n Preço: %f\n Número de Páginas: %d\n Resumo: %s\n Sumário: %s\n--------------\n",
                title, author.getName(), edition, category.getName(), price, numberOfPages, synopsis, tableOfContents);
    }

}