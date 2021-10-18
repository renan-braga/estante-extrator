package main.java.poi;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import main.java.model.Livro;
import main.java.model.LivrosIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;










public class GerenciadorArquivoExcel
{
	public int IMAGEM;
	public int TITULO;
	public int ISBN;
	public int AUTOR;
	private int ANO;
	private Sheet sheet;
	private Workbook workbook;
	private String pathDestino;
	private LivrosIterator livrosIterator;

	public GerenciadorArquivoExcel(String pathOrigem) throws Exception {
		this.workbook = WorkbookFactory.create(new BufferedInputStream(new FileInputStream(pathOrigem)));
		this.pathDestino = pathOrigem;
		this.sheet = this.workbook.getSheetAt(0);

		this.TITULO = procuraColuna(new String[] { "titulo", "título" });
		this.AUTOR = procuraColuna(new String[] { "autor", "autor" });
		this.ANO = procuraColuna(new String[] { "ano", "ano" });

		this.IMAGEM = procuraColuna(new String[] { "Imagem", "imagem" });
		if (this.IMAGEM == -1) {
			this.IMAGEM = primeiraColunaVazia();
			registrarCabecalhoImagem("Imagem");
		} 
		instanciaIterator();
	}

	public int retornaNumeroTotalLinhas() {
		return this.sheet.getLastRowNum();
	}

	public void instanciaIterator() {
		ArrayList<Livro> listaLivros = new ArrayList<>();
		for (int i = 0; i < retornaNumeroTotalLinhas(); i++) {
			String titulo = retornaDadoLinhaColuna(i, this.TITULO);
			String autor = retornaDadoLinhaColuna(i, this.AUTOR);
			String ano = retornaDadoLinhaColuna(i, this.ANO);
			listaLivros.add(new Livro(titulo, autor, ano, ""));
		} 
		this.livrosIterator = new LivrosIterator(listaLivros);
	}


	public String retornaDadoLinhaColuna(int linha, int coluna) {
		if (this.sheet.getRow(linha) != null && this.sheet.getRow(linha).getCell(coluna) != null) {
			this.sheet.getRow(linha).getCell(coluna).setCellType(CellType.STRING);
			if (this.sheet.getRow(linha).getCell(coluna).getStringCellValue() != null)
				return this.sheet.getRow(linha).getCell(coluna).getStringCellValue(); 
		} 
		return "";
	}

	public void gravaDadoLinhaColuna(int linha, int coluna, String dado) throws IOException {
		this.sheet.getRow(linha).createCell(coluna).setCellValue(dado);
	}

	public void registraCapa(String img) {
		try {
			gravaDadoLinhaColuna(this.livrosIterator.atual(), this.IMAGEM, img);
			salvaPlanilha(this.pathDestino);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public void registraCapaExcel(int linha, String imagem) throws IOException {
		gravaDadoLinhaColuna(linha, this.IMAGEM, imagem);
	}

	public void registrarCabecalhoImagem(String titulo) throws IOException {
		gravaDadoLinhaColuna(0, this.IMAGEM, titulo);
	}

	public void salvaPlanilha(String path) throws FileNotFoundException, IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(path);
		this.workbook.write(fileOutputStream);
		fileOutputStream.close();
	}

	public Sheet getSheet() {
		return this.sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public Livro proximaLinha() {
		return this.livrosIterator.next();
	}

	public void finalizar() {
		try {
			this.workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private int procuraColuna(String... buscas) throws Exception {
		Iterator<Cell> cellIterator = this.sheet.getRow(0).cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			String value = cell.getStringCellValue(); byte b; int i; String[] arrayOfString;
			for (i = (arrayOfString = buscas).length, b = 0; b < i; ) { String busca = arrayOfString[b];
			if (StringUtils.containsIgnoreCase(value, busca))
				return cell.getColumnIndex();  b++; }

		} 
		return -1;
	}

	private int primeiraColunaVazia() {
		return this.sheet.getRow(0).getLastCellNum();
	}
}


