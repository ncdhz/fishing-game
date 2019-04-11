package com.github.ncdhz.fish.game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.github.ncdhz.fish.RunFishingGame;
import com.github.ncdhz.fish.tools.BackgoundImage;
import com.github.ncdhz.fish.tools.ControlButton;
import com.github.ncdhz.fish.tools.ControlFraction;
import com.github.ncdhz.fish.tools.FishAnimation;
import com.github.ncdhz.fish.tools.FishProduceTool;
import com.github.ncdhz.fish.tools.Shell;
import com.github.ncdhz.fish.tools.Control;

public class Game {
	private Stage stage;
	private Control control;
	private static Integer cannonNam=1;
	private float marginBottomLeft;
	public static Integer money=0;
	public static ControlFraction controlFraction;
	private Music music;
	private Sound soundPao;
	private Sound soundAnNiu;
	public static Sound soundWang;
	public static Sound soundBY;
	private BackgoundImage startBackgoundImage;
	private ImageButton settingGame;
	private ImageButton startGame;
	private ImageButton up_1ImageButton;
	private ImageButton up_2ImageButton;
	private Image imageTopChinese;
	private Image up_2Chinese;
	private TextField numTextField;
	private CheckBox  checkBoxIs1;
	private CheckBox  checkBoxIs2;
	private Image imageTop;
	private int isNotStart = 0;
	private Image exitButtonImage;
	private Image backImage;
	private Image backTitleImage;
	private Image backButtonImage;
	private BackgoundImage mainBackgoundImage;
	private BackgoundImage mainBackgoundImageBar;
	private Actor mainBackgoundactor;
	private ImageButton ImageButtonLeft;
	private ImageButton ImageButtonRight;
	private static boolean backgroundMusic;
	private FishAnimation shark1FishAnimation;
	static {
		Preferences prefs = Gdx.app.getPreferences("MyPreferences");
		money = prefs.getInteger("money");
		backgroundMusic = prefs.getBoolean("backgroundMusic");
	}
	public Game(Stage stage) {
		this.stage = stage;
	}
	public void create() {
		frist();
	}
	/**
	 * 创建开始界面
	 */
	private void frist(){
		Texture startbackgroundTexture = new Texture("start.jpg");
		startBackgoundImage = new BackgoundImage(startbackgroundTexture,0, 0, RunFishingGame.DY_WIDTH, RunFishingGame.DY_HEIGHT);
		stage.addActor(startBackgoundImage);
		shark1FishAnimation = new FishAnimation("fish7.png",100*RunFishingGame.RATE,80*RunFishingGame.RATE,10,1,7,0);
		stage.addActor(shark1FishAnimation);
		ControlButton settingGameButton= new ControlButton("setting_1.png","setting_2.png");
		settingGame = settingGameButton.getImageButton();
		settingGame.setWidth(settingGame.getWidth()*0.8f);
		settingGame.setHeight(settingGame.getHeight()*0.8f);
		settingGame.setPosition(60*RunFishingGame.RATE,
				RunFishingGame.DY_HEIGHT-70*RunFishingGame.RATE);
		stage.addActor(settingGame);
		settingGame.addListener(new ChangeListener()  {
			 @Override
			 public void changed(ChangeEvent event, Actor actor) {
				 if(actor!=null)
					 actor.remove();
				 if(startGame!=null)
					 startGame.remove();
				 if(shark1FishAnimation!=null) {
					 shark1FishAnimation.remove();
				 }
				 setting();
			 }
			 });
		
		ControlButton startGameButton= new ControlButton("startGame.png","startGame1.png");
		startGame = startGameButton.getImageButton();
		startGame.setWidth(startGame.getWidth()*0.7f);
		startGame.setHeight(startGame.getHeight()*0.7f);
		startGame.setPosition(RunFishingGame.DY_WIDTH/2f-startGame.getWidth()/2,
				RunFishingGame.DY_HEIGHT/2f-startGame.getHeight()*RunFishingGame.RATE*0.7f-140*RunFishingGame.RATE);
		stage.addActor(startGame);
		startGame.addListener(new ChangeListener()  {
			 @Override
			 public void changed(ChangeEvent event, Actor actor) {
				 if(startBackgoundImage!=null)
					 startBackgoundImage.remove();
				 if(settingGame!=null)
					 settingGame.remove();
				 if(actor!=null)
					 actor.remove();
				 if(shark1FishAnimation!=null) {
					 shark1FishAnimation.remove();
				 }
				 createJoinMain();
			 }
			 });
	}
	/**
	 * 创建设置界面
	 */
	private void setting() {
		int widthTop = (int)(837*RunFishingGame.RATE*0.85);
		int heightTop = (int)(437*RunFishingGame.RATE*0.85);
		int heightUp = (int)(51*RunFishingGame.RATE);
		Texture textureTop = new Texture(widthTop,heightTop,Format.RGBA8888);
		Pixmap pixmapTop = new Pixmap(widthTop,heightTop,Format.RGBA8888);
		pixmapTop.setColor(0, 0, 0, 0.76f);
		pixmapTop.fillRectangle(0, 0, widthTop, heightTop);
		pixmapTop.setColor(Color.BLACK);
		pixmapTop.fillRectangle(0, heightTop-heightUp, widthTop, heightUp);
		textureTop.draw(pixmapTop, 0, 0);
		pixmapTop.dispose();
		imageTop = new Image(textureTop);
		imageTop.setPosition((RunFishingGame.DY_WIDTH -widthTop)/2f,(RunFishingGame.DY_HEIGHT -heightTop)/2f);
		stage.addActor(imageTop);

		imageTopChinese = new Image(new Texture("frame/top.png"));
		imageTopChinese.setWidth(imageTopChinese.getWidth()*RunFishingGame.RATE*0.85f);
		imageTopChinese.setHeight(imageTopChinese.getHeight()*RunFishingGame.RATE*0.85f);
		imageTopChinese.setPosition((RunFishingGame.DY_WIDTH -widthTop)/2f+30*RunFishingGame.RATE,(RunFishingGame.DY_HEIGHT -heightTop)/2f+80*RunFishingGame.RATE);
		stage.addActor(imageTopChinese);
		
		up_2Chinese = new Image(new Texture("frame/up_2.png"));
		up_2Chinese.setWidth(up_2Chinese.getWidth()*RunFishingGame.RATE*0.85f);
		up_2Chinese.setHeight(up_2Chinese.getHeight()*RunFishingGame.RATE*0.85f);
		up_2Chinese.setPosition(RunFishingGame.DY_WIDTH/2-up_2Chinese.getWidth()/2, (RunFishingGame.DY_HEIGHT -heightTop+heightUp-up_2Chinese.getHeight())/2f);
		stage.addActor(up_2Chinese);

		BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("frame/text.fnt"),Gdx.files.internal("frame/text.png"),false);
		bitmapFont.getData().setScale(1*RunFishingGame.RATE);
		TextFieldStyle textFieldStyle = new TextFieldStyle();
		textFieldStyle.font = bitmapFont;
		textFieldStyle.fontColor = Color.RED;
		numTextField = new TextField(money.toString(),textFieldStyle);
		numTextField.setWidth(RunFishingGame.TEXT_FIELD_WIDTH*0.85f*RunFishingGame.RATE);
		numTextField.setHeight(RunFishingGame.TEXT_FIELD_HEIGHT*0.85f*RunFishingGame.RATE);
		numTextField.setPosition(RunFishingGame.DY_WIDTH/2+widthTop/2-240*RunFishingGame.RATE,RunFishingGame.DY_HEIGHT/2+heightTop/2-48*RunFishingGame.RATE);
		numTextField.setMaxLength(5);
		numTextField.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				String text = numTextField.getText();
				if(!"".equals(text)) {
					String regEx="[^0-9$]";  
					Pattern p = Pattern.compile(regEx);  
					Matcher m = p.matcher(text);  
					numTextField.setText(m.replaceAll("").trim().replaceFirst("^0*", ""));
				}
				return false;
			}
	
		});
		stage.addActor(numTextField);
		
		
		TextureRegion checkboxOnTexture =new TextureRegion(new Texture("frame/is.png")) ;
		TextureRegion checkboxOffTexture = new TextureRegion(new Texture("frame/isNot.png"));
		CheckBoxStyle checkBoxStyle = new CheckBoxStyle();
		checkBoxStyle.font = bitmapFont;
		checkBoxStyle.fontColor=Color.BLACK;
		checkBoxStyle.checkboxOn = new TextureRegionDrawable(checkboxOnTexture);
		checkBoxStyle.checkboxOff = new TextureRegionDrawable(checkboxOffTexture);
		checkBoxIs1= new CheckBox("", checkBoxStyle);
		checkBoxIs1.setPosition(RunFishingGame.DY_WIDTH/2-120*RunFishingGame.RATE,RunFishingGame.DY_HEIGHT/2+heightTop/2-62*RunFishingGame.RATE);
		stage.addActor(checkBoxIs1);
		checkBoxIs2 = new CheckBox("", checkBoxStyle);
		checkBoxIs2.setPosition(RunFishingGame.DY_WIDTH/2-60*RunFishingGame.RATE,RunFishingGame.DY_HEIGHT/2+heightTop/2-62*RunFishingGame.RATE);
		stage.addActor(checkBoxIs2);
		checkBoxIs1.setChecked(backgroundMusic);
		checkBoxIs2.setChecked(!backgroundMusic);
		checkBoxIs1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	if(checkBoxIs1.isChecked()) {
            		backgroundMusic = true;
            		checkBoxIs2.setChecked(false);
            	}else {
            		backgroundMusic = false;
            		checkBoxIs2.setChecked(true);
            		
            	}
            }
        });
		checkBoxIs2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	if(checkBoxIs2.isChecked()) {
            		backgroundMusic = false;
            		checkBoxIs1.setChecked(false);
            	}else {
            		backgroundMusic = true;
            		checkBoxIs1.setChecked(true);
            	}
            }
        });
		up_1ImageButton= new ControlButton("frame/up_1.png","frame/up_1_1.png").getImageButton();
		up_2ImageButton= new ControlButton("frame/up_3.png","frame/up_3_3.png").getImageButton();
		up_1ImageButton.setWidth(up_1ImageButton.getWidth()*0.85f);
		up_1ImageButton.setHeight(up_1ImageButton.getHeight()*0.85f);
		up_2ImageButton.setWidth(up_1ImageButton.getWidth()*0.85f);
		up_2ImageButton.setHeight(up_1ImageButton.getHeight()*0.85f);
		up_1ImageButton.setPosition(RunFishingGame.DY_WIDTH/2 -widthTop/4f-up_1ImageButton.getWidth()/2, (RunFishingGame.DY_HEIGHT -heightTop+heightUp-up_1ImageButton.getHeight())/2f);
		up_2ImageButton.setPosition(RunFishingGame.DY_WIDTH/2+widthTop/4f-up_1ImageButton.getWidth()/2, (RunFishingGame.DY_HEIGHT -heightTop+heightUp-up_2ImageButton.getHeight())/2f);
		up_1ImageButton.addListener(new ChangeListener()  {
			 @Override
			 public void changed(ChangeEvent event, Actor actor) {
				 disposeSetting();
				 frist();
			 }
			 });
		up_2ImageButton.addListener(new ChangeListener()  {
			 @Override
			 public void changed(ChangeEvent event, Actor actor) {
				String text=numTextField.getText();
				if(text.equals("")) {
					money=0;
				}else {
					money = Integer.parseInt(text);
				}
				
				Preferences prefs = Gdx.app.getPreferences("MyPreferences");
				prefs.putBoolean("backgroundMusic", backgroundMusic);
				prefs.putInteger("money",money);
				prefs.flush();
				disposeSetting();
				frist();
			 }
			 });
		
		stage.addActor(up_1ImageButton);
		stage.addActor(up_2ImageButton);
	}

	/**
	 * 创建捕鱼界面
	 */
	private void createJoinMain() {
		
		isNotStart=1;
		Gdx.input.setCatchBackKey(true);
		soundBY = Gdx.audio.newSound(Gdx.files.internal("by.mp3"));
		if(backgroundMusic) {
			music = Gdx.audio.newMusic(Gdx.files.internal("bj.mp3"));
			music.play();
			music.setLooping(true);
		}
		soundPao = Gdx.audio.newSound(Gdx.files.internal("pao.mp3"));
		soundAnNiu=Gdx.audio.newSound(Gdx.files.internal("aniu.mp3"));
		soundWang=Gdx.audio.newSound(Gdx.files.internal("kaiwang.mp3"));
		
		
		Texture backgroundTexture = new Texture("game_bg_2_hd.jpg");
		mainBackgoundImage = new BackgoundImage(backgroundTexture, 0, 0, RunFishingGame.DY_WIDTH, RunFishingGame.DY_HEIGHT);
		stage.addActor(mainBackgoundImage);
		Texture backgroundTextureBar = new Texture("bottom-bar.png");
		marginBottomLeft =(RunFishingGame.DY_WIDTH- backgroundTextureBar.getWidth()*RunFishingGame.RATE)/2;
		mainBackgoundImageBar = new BackgoundImage(backgroundTextureBar,
				marginBottomLeft, 0,
				backgroundTextureBar.getWidth()*RunFishingGame.RATE, backgroundTextureBar.getHeight()*RunFishingGame.RATE);
		stage.addActor(mainBackgoundImageBar);
		
		/**
		 * 分数
		 */
		controlFraction=new ControlFraction(marginBottomLeft+135*RunFishingGame.RATE,4,money);
		stage.addActor(controlFraction);
		
		/**
		 *鱼层
		 * 点击层
		 */
		mainBackgoundactor = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				super.draw(batch, parentAlpha);
				new FishProduceTool(stage).fish();
			}
		};
		mainBackgoundactor.setWidth(RunFishingGame.DY_WIDTH);
		mainBackgoundactor.setHeight(RunFishingGame.DY_HEIGHT);
		stage.addActor(mainBackgoundactor);
		mainBackgoundactor.setZIndex(100);
		/**
		 * Bar按钮
		 */
		ControlButton controlButtonLeft= new ControlButton("cannon_minus.png","cannon_minus_down.png");
		ControlButton controlButtonRight= new ControlButton("cannon_plus.png","cannon_plus_down.png");
		ImageButtonLeft= controlButtonLeft.getImageButton();
		ImageButtonRight= controlButtonRight.getImageButton();
		ImageButtonLeft.setPosition(marginBottomLeft+340*RunFishingGame.RATE, 5*RunFishingGame.RATE);
		ImageButtonRight.setPosition(marginBottomLeft+475*RunFishingGame.RATE, 5*RunFishingGame.RATE);
		stage.addActor(ImageButtonLeft);
		stage.addActor(ImageButtonRight);
		ImageButtonLeft.addListener(new ChangeListener()  {
			 @Override
			 public void changed(ChangeEvent event, Actor actor) {
				 soundAnNiu.play();
				 if(cannonNam==1)
					 cannonNam=7;
				 else
					 cannonNam--;
				 if(control!=null) {
					 control.remove();
					 control = new Control("cannon"+cannonNam+".png",marginBottomLeft+390*RunFishingGame.RATE,8*RunFishingGame.RATE, cannonNam);
					 stage.addActor(control);
				 }
				 
			 	}
			 });
		ImageButtonRight.addListener(new ChangeListener() {
			 @Override
			 public void changed(ChangeEvent event, Actor actor) {
				 soundAnNiu.play();
				 if(cannonNam<7)
					 cannonNam++;
				 else
					 cannonNam=1;
				 if(control!=null) {
					 control.remove();
					 control = new Control("cannon"+cannonNam+".png",marginBottomLeft+390*RunFishingGame.RATE,8*RunFishingGame.RATE, cannonNam);
					 stage.addActor(control);
				 }
			 }
			 });
		/**
		 * 炮台初始化
		 */
		control = new Control("cannon"+cannonNam+".png",marginBottomLeft+390*RunFishingGame.RATE,8*RunFishingGame.RATE, cannonNam);
		stage.addActor(control);
		
		mainBackgoundactor.addListener(new InputListener() {
			@Override
	        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(y>18&&money-cannonNam>0) {
					float controlFractionWidth = control.getWidth()/2;
					float rotation =(float)(180/Math.PI*Math.atan((marginBottomLeft+390*RunFishingGame.RATE+controlFractionWidth-x)/(y-18*RunFishingGame.RATE)));
					control.setRotation(rotation);
					control.setStateTime(0);
					money=money-cannonNam;
					controlFraction.setFraction(money);
					Texture shellTexture=new Texture("bullet"+cannonNam+".png");
					
					Shell shell = new Shell(shellTexture,
							(float)(marginBottomLeft+390*RunFishingGame.RATE+controlFractionWidth-shellTexture.getWidth()*RunFishingGame.RATE/2),
							(float)(10*RunFishingGame.RATE+control.getHeight()),cannonNam,stage);
					
					shell.setRotation(rotation);
					stage.addActor(shell);
					MoveByAction action = Actions.moveBy(0,1000*RunFishingGame.RATE,2.5f);
					shell.addAction(action);
					
					soundPao.play();
				}
				return false;
			}
		});
	} 
	/**
	 * 关闭设置界面的资源
	 */
	private void disposeSetting() {
		 if(startBackgoundImage!=null)
			 startBackgoundImage.remove();
		 if(up_2Chinese!=null)
			 up_2Chinese.remove();
		 if(up_2ImageButton!=null)
			 up_2ImageButton.remove();
		 if(checkBoxIs1!=null)
			 checkBoxIs1.remove();
		 if(checkBoxIs2!=null)
			 checkBoxIs2.remove();
		 if(numTextField!=null)
			 numTextField.remove();
		 if(imageTopChinese!=null)
			 imageTopChinese.remove();
		 if(imageTop!=null)
			 imageTop.remove();
		 if(up_1ImageButton!=null)
			 up_1ImageButton.remove();
	}
	/**
	 * 循环更新刷新界面
	 */
	public void update() {
		
		if(Gdx.input.isKeyPressed(Input.Keys.BACK)&&isNotStart==1) {
			isNotStart=0;
			backListen();
		}
		stage.act();
		stage.draw();
	}
	
	/**
	 * 用于监听返回事件并处理返回事件
	 */
	private void backListen() {
		int backHeight = (int) (125*RunFishingGame.RATE);
		int	backWidth =(int) (320*RunFishingGame.RATE);
		Texture backTexture = new Texture(backWidth,backHeight,Format.RGBA8888);
		Pixmap backPixmap = new Pixmap(backWidth, backHeight, Format.RGBA8888);
		backPixmap.setColor(0, 0, 0, 0.82f);
		backPixmap.fillRectangle(0, 0, backWidth, backHeight);
		backTexture.draw(backPixmap, 0, 0);
		backPixmap.dispose();
		backImage = new Image(backTexture);
		backImage.setPosition((RunFishingGame.DY_WIDTH-backWidth)/2f, (RunFishingGame.DY_HEIGHT-backHeight)/2f);
		stage.addActor(backImage);
		backImage.setZIndex(200);
		
		backTitleImage = new Image(new Texture("back/back_title.png"));
		float backTitleWidth = backTitleImage.getWidth()*RunFishingGame.RATE;
		float backTitleHeight = backTitleImage.getHeight()*RunFishingGame.RATE;
		backTitleImage.setSize(backTitleWidth,backTitleHeight);
		backTitleImage.setPosition((RunFishingGame.DY_WIDTH-backTitleWidth)/2f, (RunFishingGame.DY_HEIGHT-backTitleHeight)/2f);
		stage.addActor(backTitleImage);
		backTitleImage.setZIndex(200);
		
		backButtonImage = new Image(new Texture("back/back.png"));
		float backButtonWidth = backButtonImage.getWidth()*RunFishingGame.RATE;
		float backButtonHeight = backButtonImage.getHeight()*RunFishingGame.RATE;
		backButtonImage.setSize(backButtonWidth,backButtonHeight);
		backButtonImage.setPosition((RunFishingGame.DY_WIDTH-backButtonWidth)/2f-80*RunFishingGame.RATE, (RunFishingGame.DY_HEIGHT-backButtonHeight)/2f-26*RunFishingGame.RATE);
		stage.addActor(backButtonImage);
		backButtonImage.setZIndex(200);
		
		backButtonImage.addListener(new ClickListener() {

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				disposeBackListen();
				frist();
			}
			
		});
		
		exitButtonImage = new Image(new Texture("back/exit_game.png"));
		float exitButtonWidth = exitButtonImage.getWidth()*RunFishingGame.RATE;
		float exitButtonHeight = exitButtonImage.getHeight()*RunFishingGame.RATE;
		exitButtonImage.setSize(exitButtonWidth,exitButtonHeight);
		exitButtonImage.setPosition((RunFishingGame.DY_WIDTH-exitButtonWidth)/2f+80*RunFishingGame.RATE, (RunFishingGame.DY_HEIGHT-exitButtonHeight)/2f-26*RunFishingGame.RATE);
		stage.addActor(exitButtonImage);
		exitButtonImage.setZIndex(102);
		
		exitButtonImage.addListener(new ClickListener() {

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				disposeBackListen();
				Gdx.app.exit();
			}
			
		});
		
	}
	private void disposeBackListen(){
		if(exitButtonImage!=null)
			exitButtonImage.remove();
		if(backImage!=null)
			backImage.remove();
		if(backTitleImage!=null)
			backTitleImage.remove();
		if(backButtonImage!=null)
			backButtonImage.remove();
		if(soundBY!=null)
			soundBY.dispose();
		if(soundPao!=null)
			soundPao.dispose();
		if(soundAnNiu!=null)
			soundAnNiu.dispose();
		if(music!=null)
			music.dispose();
		if(soundWang!=null)
			soundWang.dispose();
		if(mainBackgoundactor!=null) {
			mainBackgoundactor.remove();
		}
		for(int i=0;i<FishProduceTool.fishAnimationList.size();) {
			FishProduceTool.fishAnimationList.get(i).remove();
			FishProduceTool.fishAnimationList.remove(i);
		}
		if(mainBackgoundImage!=null) {
			mainBackgoundImage.remove();
		}
		if(mainBackgoundImageBar!=null) {
			mainBackgoundImageBar.remove();
		}
		if(controlFraction!=null) {
			controlFraction.remove();
		}
		if(ImageButtonLeft!=null) {
			ImageButtonLeft.remove();
		}
		if(ImageButtonRight!=null) {
			ImageButtonRight.remove();
		}
		if(control!=null) {
			control.remove();
		}
	}
	/**
	 * 用于释放资源占用
	 */
	public void dispose () {
		if(stage!=null)
			stage.dispose();
		if(music!=null)
			music.dispose();
		if(soundPao!=null)
			soundPao.dispose();
		if(soundAnNiu!=null)
			soundAnNiu.dispose();
		if(soundWang!=null)
			soundWang.dispose();
		if(soundBY!=null)
			soundBY.dispose();
	}
}
