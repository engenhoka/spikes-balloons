package engenhoka.spike;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BalloonsMain extends Application {

	static final int HEIGHT = 500;
	private static final int WIDTH = 500;

	public static void main(String[] args) {
		launch(args);
	}

	private IntegerProperty score = new SimpleIntegerProperty(0);
	private Balloon ballons[];

	private void init(Stage primaryStage) {
		Group root = new Group();
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));

		drawLines(root);
		drawBalloons(root);
		drawScore(root);
		spikeImage(root);
	}

	private void drawScore(Group root) {
		final Text text = new Text(400, 25, "Hits: 0");
		root.getChildren().add(text);

		score.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				text.setText("Hits: " + newValue);
			}

		});
	}

	private void drawBalloons(Group root) {
		ballons = new Balloon[5];
		ballons[0] = new Balloon(30, score);
		ballons[1] = new Balloon(90, score);
		ballons[2] = new Balloon(150, score);
		ballons[3] = new Balloon(210, score);
		ballons[4] = new Balloon(270, score);

		for (Balloon b : ballons)
			root.getChildren().add(b.getNode());
	}

	private void spikeImage(Group root) {
		Image redBallon = new Image(BalloonsMain.class.getResourceAsStream("balloon.png"), 50, 50, true, true);
		ImageView image = new ImageView(redBallon);
		image.setX(350);
		image.setY(70);
		root.getChildren().add(image);
	}

	private void drawLines(Group root) {
		Line bottomLine = new Line(10, HEIGHT - 50, WIDTH - 10, HEIGHT - 50);
		bottomLine.setStrokeWidth(1);
		bottomLine.setStroke(Color.DARKGREY);
		root.getChildren().add(bottomLine);

		Line topLine = new Line(10, 50, WIDTH - 10, 50);
		topLine.setStrokeWidth(1);
		topLine.setStroke(Color.DARKGREY);
		root.getChildren().add(topLine);
	}

	public void play() {
		for (Balloon b : ballons)
			b.getTranslateTransition().play();
	}

	@Override
	public void stop() {
		for (Balloon b : ballons)
			b.getTranslateTransition().stop();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		primaryStage.show();
		play();
	}
}
