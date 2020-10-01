package br.com.alura.edigi.model;

import java.time.LocalDateTime;

public class Category {

    private String name;
    private LocalDateTime createdAt;

    public Category(String name) {
       setName(name); 
    }

    public Category(String name, LocalDateTime createdAt) {
        setName(name);
        this.createdAt = createdAt;
    }

    private void setName(String name) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("O nome da categoria não pode ser vazio");

        this.name = name;
    }

    public String getName(){
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
        if(this == obj) return true;
        if(this.getClass() != obj.getClass()) return false;

        Category comparado = (Category) obj;
        return this.name.equals(comparado.name);
	}

    @Override
    public String toString() {
        return "Category [name=" + name + "]";
    }

}
