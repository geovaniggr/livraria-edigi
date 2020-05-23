package br.com.alura.edigi.model;

import java.time.LocalDateTime;

public class Author {

    private LocalDateTime createdAt;
    private String email;
    private String name;

    public Author(String name, String email) {
        setName(name);
        setEmail(email);
        this.createdAt = LocalDateTime.now();
    }

    private void setName(String name){
        if( name == null || name.isEmpty()) 
            throw new IllegalArgumentException("O nome não pode ser vazio!");
            
        this.name = name;
    }

    private void setEmail(String email){
        if( email == null || email.isEmpty())
            throw new IllegalArgumentException("O email não pode ser vazio");

        if( !(email.matches("^([\\w-]\\.?)+@([\\w-]+\\.)+([A-Za-z]{2,4})+$")))
            throw new IllegalArgumentException("O formato do email não é valido!");

        this.email = email;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "Author [nome=" + name + ", email=" + email + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + email.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass()) return false;
        if(this == obj) return true;

        Author comparado = (Author) obj;
        return this.email.equals(comparado.email);
   }
}