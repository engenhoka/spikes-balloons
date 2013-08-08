package engenhoka.spike;

import java.util.Random;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

class Balloon {
	
	private static Random random = new Random(System.nanoTime());

	private TranslateTransition translateTransition;
	private Circle balloon;

	private IntegerProperty score;

	public Balloon(int x, IntegerProperty score) {
		this.score = score;
		this.balloon = new Circle(20, Color.RED);
		
		balloon.setTranslateX(x);
		balloon.setTranslateY(20);

		buildTransition(balloon);
		configEvents(balloon);
	}

	public TranslateTransition getTranslateTransition() {
		return translateTransition;
	}

	public Node getNode() {
		return balloon;
	}

	private void configEvents(final Circle balloon) {
		balloon.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				balloon.setFill(Color.BLUE);
			}
		});

		balloon.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				balloon.setFill(Color.RED);
			}
		});

		balloon.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				balloon.setFill(Color.GREEN);
				score.set(score.add(1).get());
			}
		});
	}

	private void buildTransition(final Circle balloon) {
		translateTransition = new TranslateTransition(Duration.seconds(randomTime()), balloon);
		translateTransition.setFromY(BalloonsMain.HEIGHT);
		translateTransition.setToY(0);
		translateTransition.setCycleCount(Timeline.INDEFINITE);
		translateTransition.setAutoReverse(true);
	}

	// between 4 and 9
	private int randomTime() {
		return 4 + random.nextInt(5);
	}

}