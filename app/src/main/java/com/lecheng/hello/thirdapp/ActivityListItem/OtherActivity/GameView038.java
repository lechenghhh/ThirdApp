package com.lecheng.hello.thirdapp.ActivityListItem.OtherActivity;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import com.lecheng.hello.thirdapp.ActivityListItem.Aty038Game2048;


public class GameView038 extends GridLayout {

	public GameView038(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public GameView038(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public GameView038(Context context, AttributeSet attrs) {
	    super(context, attrs);
		// TODO Auto-generated constructor stub
	    initGameView();
	}
    private void initGameView(){
    	
    	setColumnCount(4);
    	setBackgroundColor(0xffbbada0);
    	setOnTouchListener(new OnTouchListener() {
			
    		private float startX,startY,offsetX,offsetY;
    		
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch(arg1.getAction()){
				case MotionEvent.ACTION_DOWN:
				    startX=arg1.getX();
				    startY=arg1.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX=arg1.getX()-startX;
					offsetY=arg1.getY()-startY;
					
					if(Math.abs(offsetX)>Math.abs(offsetY)){
						if(offsetX<-5){
							swipleft();
						}else if(offsetX>5){
							swipright();
						}
					}else{
						if(offsetY<-5){
							swipup();
						}else if(offsetY>5){
							swipdown();
						}
					}
							
					break;
				}
				return true;
			}

		});
    }
    
    private void addRandomNum(){
    	
    	emptyPoints.clear();
    	for(int y=0;y<4;y++){
    		for(int x=0;x<4;x++){
    			if(cardsMap[x][y].getNum()<=0){
    				emptyPoints.add(new Point(x,y));
    			}
    		}
    	}
    	Point p=emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
    	cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
    }
    
    private void swipdown() {
    	boolean merge=false;
    	for(int x=0;x<4;x++){
			for(int y=3;y>=0;y--){
				for(int y1=y-1;y1>=0;y1--){
					if(cardsMap[x][y1].getNum()>0){
						if(cardsMap[x][y].getNum()<=0){
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							y++;merge=true;
						}else if(cardsMap[x][y].equals(cardsMap[x][y1])){
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x][y1].setNum(0);
							Aty038Game2048.getMainActivity().addScore(cardsMap[x][y].getNum());
							merge=true;
						}break;
					}
				}
			}
		}if(merge){
			addRandomNum();checkComplete();
		}
	}

	private void swipup() {
		boolean merge=false;
		for(int x=0;x<4;x++){
			for(int y=0;y<4;y++){
				for(int y1=y+1;y1<4;y1++){
					if(cardsMap[x][y1].getNum()>0){
						if(cardsMap[x][y].getNum()<=0){
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							y--;merge=true;
						}else if(cardsMap[x][y].equals(cardsMap[x][y1])){
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x][y1].setNum(0);
							Aty038Game2048.getMainActivity().addScore(cardsMap[x][y].getNum());
							merge=true;
						}break;
					}
				}
			}
		}if(merge){
			addRandomNum();checkComplete();
		}
	}

	private void swipright() {
		boolean merge=false;
		for(int y=0;y<4;y++){
			for(int x=3;x>=0;x--){
				for(int x1=x-1;x1>=0;x1--){
					if(cardsMap[x1][y].getNum()>0){
						if(cardsMap[x][y].getNum()<=0){
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							x++;merge=true;
						}else if(cardsMap[x][y].equals(cardsMap[x1][y])){
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x1][y].setNum(0);
							Aty038Game2048.getMainActivity().addScore(cardsMap[x][y].getNum());
							merge=true;
						}break;
					}
				}
			}
		}if(merge){
			addRandomNum();checkComplete();
		}
	}

	private void swipleft() {
		boolean merge=false;
		for(int y=0;y<4;y++){
			for(int x=0;x<4;x++){
				for(int x1=x+1;x1<4;x1++){
					if(cardsMap[x1][y].getNum()>0){
						if(cardsMap[x][y].getNum()<=0){
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							x--;
							merge=true;
						}else if(cardsMap[x][y].equals(cardsMap[x1][y])){
							cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
							cardsMap[x1][y].setNum(0);
							Aty038Game2048.getMainActivity().addScore(cardsMap[x][y].getNum());
							merge=true;
						}break;
					}
				}
			}
		}if(merge){
			addRandomNum();
			checkComplete();
		}
	}
	
	private void checkComplete(){
		boolean complete = true;
		ALL:
		for(int y=0;y<4;y++){
			for(int x=0;x<4;x++){
				if(cardsMap[x][y].getNum()==0||
						(x>0&&cardsMap[x][y].equals(cardsMap[x-1][y]))||
						(x<3&&cardsMap[x][y].equals(cardsMap[x+1][y]))||
						(y>0&&cardsMap[x][y].equals(cardsMap[x][y-1]))||
						(y<3&&cardsMap[x][y].equals(cardsMap[x][y+1]))){
					complete=false;
					break ALL;
				}
			}
		}	if(complete){
			  new AlertDialog.Builder(getContext()).setTitle("Hi!").setMessage
			  ("Game Over!").setPositiveButton
			  ("Restart", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					startGame();
				}
			}).show();
				
				
			
		}
	}
	
	private Card038[][] cardsMap=new Card038[4][4];
	private List<Point> emptyPoints=new ArrayList<Point>();
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		int cardWidth=(Math.min(w, h)-10)/4;
		addCards(cardWidth,cardWidth);
		startGame();
	}
    private void addCards(int cardWidth,int cardHeight){
    	Card038 c;
    	for(int y=0;y<4;y++){
    		for(int x=0;x<4;x++){
    			c=new Card038(getContext());
    			c.setNum(0);
    			addView(c,cardWidth,cardHeight);
    			cardsMap[x][y]=c;
    		}
    	}
    }
    private void startGame(){
    	Aty038Game2048.getMainActivity().clearScore();
    	for(int y=0;y<4;y++){
    		for(int x=0;x<4;x++){
    			cardsMap[x][y].setNum(0);
    		}
    	}
    	addRandomNum();
    	addRandomNum();
    }
    
}
