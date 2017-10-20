package com.mygdx.scenemanagementdemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Random;

public class HitDetection extends ApplicationAdapter {

	class Jet extends Actor {
		private TextureRegion _texture;

		public Jet(TextureRegion texture) {
			_texture = texture;
			setBounds(
					getX(), getY(),
					_texture.getRegionWidth(),
					_texture.getRegionHeight()
			);

			this.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event,
										 float x, float y,
										 int pointer,
										 int button) {
					System.out.println("Touched " + getName());
					setVisible(false);
					return true;
				}
			});
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(_texture,
					getX(), getY(),
					getOriginX(), getOriginY(),
					getWidth(), getHeight(),
					getScaleX(), getScaleY(),
					getRotation());
		}

//		@Override
//		public Actor hit(float x, float y, boolean touchable) {
//
//			// If gone, do nothing.
//			if (!this.isVisible() ||
//					this.getTouchable() == Touchable.disabled) {
//				return null;
//			}
//
//			// Get center.
//			float centerX = getWidth() / 2;
//			float centerY = getHeight() / 2;
//
//			// Radius of circle
//			float radius =
//					(float) Math.sqrt(
//							centerX * centerX +
//							centerY * centerY
//					);
//
//			// Distance of point hit from center
//			float distance =
//					(float) Math.sqrt(
//							((centerX - x) * (centerX - x)) +
//							((centerY - y) * (centerY - y))
//					);
//
//			// If distance is < radius, it's a hit.
//			if (distance <= radius) {
//				return this;
//			}
//
//			// Otherwise it isn't.
//			return null;
//		}
	}

	private Jet[] jets;
	private Stage stage;

	@Override
	public void create () {
		stage = new Stage();

		final TextureRegion jetTexture = new TextureRegion(new Texture("data/jet.png"));

		jets = new Jet[10];

		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			jets[i] = new Jet(jetTexture);

			// Assign position to random value within screen boundaries.
			jets[i].setPosition(
					random.nextInt(
							Gdx.graphics.getWidth() - (int) jets[i].getWidth()
					),
					random.nextInt(
							Gdx.graphics.getHeight() - (int) jets[i].getHeight()
					)
			);

			// Give this jet a name
			jets[i].setName(Integer.toString(i));

			// Add it to the stage
			stage.addActor(jets[i]);
		}

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose () {
		stage.dispose();
	}
}
