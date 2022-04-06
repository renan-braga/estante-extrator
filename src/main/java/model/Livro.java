package main.java.model;

public class Livro
{
	private String titulo;
	private String autor;
	private String ano;
	private String img;

	public Livro(String titulo, String autor, String ano, String imagem) {
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
		this.img = imagem;
	}



	public void formatarCampos() {}


	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAno() {
		return this.ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}


