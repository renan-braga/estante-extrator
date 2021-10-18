package main.java.controller;

import main.java.model.Livro;
import main.java.pageobjects.EstanteBuscaPgObject;
import main.java.poi.GerenciadorArquivoExcel;

public class ExtratorEstante{
	private String pathOrigem;
	private GerenciadorArquivoExcel gerenciadorArquivoExcel;
	private EstanteBuscaPgObject estanteBusca;

	private ExtratorEstante(String pathOrigem) {
		try {
			this.gerenciadorArquivoExcel = new GerenciadorArquivoExcel(pathOrigem);
			this.estanteBusca = new EstanteBuscaPgObject();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		this.pathOrigem = pathOrigem;
	}

	public static ExtratorEstante getInstance(String pathOrigem) {
		return new ExtratorEstante(pathOrigem);
	}

	public int numeroLinhas() {
		return this.gerenciadorArquivoExcel.retornaNumeroTotalLinhas();
	}

	public Livro proximoLivro() {
		Livro livro = this.gerenciadorArquivoExcel.proximaLinha();
		try {
			livro.setImg(this.estanteBusca.retornaImagemBusca(livro.getTitulo()));
		} catch (Exception e) {
			e.printStackTrace();
		} 

		this.gerenciadorArquivoExcel.registraCapa(livro.getImg());
		return livro;
	}

	public String getPathOrigem() {
		return this.pathOrigem;
	}

	public void setPathOrigem(String pathOrigem) {
		this.pathOrigem = pathOrigem;
	}

	public void finalizar() {
		this.gerenciadorArquivoExcel.finalizar();
		this.estanteBusca.finalizar();
	}
}


