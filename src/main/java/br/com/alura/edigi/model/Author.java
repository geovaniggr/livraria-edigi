package br.com.alura.edigi.model;

import static br.com.alura.edigi.validators.ValidationUtils.isNull;
import static br.com.alura.edigi.validators.ValidationUtils.isValidEmail;

import java.time.LocalDateTime;

public class Author {

    private LocalDateTime created_at;
    private String email;
    private String name;

    public Author(String name, String email) {
        setName(name);
        setEmail(email);
        this.created_at = LocalDateTime.now();
    }

    private void setName(String name){
        if(isNull(name)) throw new IllegalArgumentException("O nome não pode ser vazio");

        this.name = name;
    }

    private void setEmail(String email){
        if(isNull(email)) throw new IllegalArgumentException("O email não pode ser vazio");
        if(!isValidEmail(email)) throw new IllegalArgumentException("O email não tem formato valido");

        this.email = email;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass()) return false;
        if(this == obj) return true;

        Author comparado = (Author) obj;

        return this.email.equals(comparado.email);
    }

    @Override
    public String toString() {
        return "Author [nome=" + name + ", email=" + email + "]";
    }

}