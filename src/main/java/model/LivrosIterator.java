package main.java.model;

import java.util.ArrayList;
import java.util.ListIterator;

public class LivrosIterator
implements ListIterator<Livro> {
	private ArrayList<Livro> lista;
	private int posicao;

	public LivrosIterator(ArrayList<Livro> lista) {
		this.lista = lista;
		this.posicao = 0;
	}


	public boolean hasNext() {
		return (this.lista.size() - 1 <= this.posicao);
	}


	public Livro next() {
		return this.lista.get(++this.posicao);
	}


	public boolean hasPrevious() {
		return (this.posicao > 0);
	}


	public Livro previous() {
		return hasPrevious() ? this.lista.get(this.posicao - 1) : null;
	}


	public int nextIndex() {
		return (this.posicao + 1 < this.lista.size()) ? (this.posicao + 1) : -1;
	}


	public int previousIndex() {
		return (this.posicao - 1 >= 0) ? (this.posicao - 1) : -1;
	}


	public void remove() {
		this.lista.remove(this.posicao);
	}


	public void set(Livro livro) {
		this.lista.set(this.posicao, livro);
	}


	public void add(Livro livro) {
		this.lista.add(livro);
	}

	public int atual() {
		return this.posicao;
	}
}


