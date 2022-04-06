package main.java;

import java.io.File;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import main.java.controller.ExtratorEstante;
import main.java.model.Livro;






public class TelaPrincipalController{
	@FXML
	private TextField txtOrigem;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Text txtProgress;
	private Task<Void> task;

	@FXML
	public void procurarOrigem() {
		this.txtOrigem.setText(escolherCaminho());
	}

	@FXML
	public void iniciarExtracao() throws InterruptedException {
		this.progressBar.setVisible(true);
		this.task = new Task<Void>()
		{
			protected Void call() throws Exception {
				updateMessage("Iniciando extração...");
				ExtratorEstante extrator = ExtratorEstante.getInstance(TelaPrincipalController.this.txtOrigem.getText());
				int numeroLinhas = extrator.numeroLinhas();
				for (int i = 1; i < numeroLinhas; i++) {
					if (isCancelled()) {
						updateMessage("Processo Cancelado");
						extrator.finalizar();
					} 
					updateProgress(i, (numeroLinhas - 1));
					Livro livro = extrator.proximoLivro();
					updateMessage(String.valueOf(i) + " de " + (numeroLinhas - 1) + " - " + livro.getTitulo());
				} 
				updateMessage("Finalizado");
				return null;
			}
		};
		this.txtProgress.textProperty().bind(this.task.messageProperty());
		this.progressBar.progressProperty().bind(this.task.progressProperty());
		(new Thread(this.task)).start();
	}

	@FXML
	public void cancelarExtracao() {
		this.task.cancel();
	}

	private String escolherCaminho() {
		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Escolha o arquivo");
		File arquivo = fileChooser.showOpenDialog(null);
		return (arquivo != null) ? arquivo.getAbsolutePath() : "";
	}
}


