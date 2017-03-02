package Agent;

import Environnement.*;

public class Mouton extends Prey {

	public Mouton(Map world) {
		super(world);
		this.posX = (int)(Math.random()*world.getWidth());
		this.posY = (int)(Math.random()*world.getHeight());
		this.direction = (int)(Math.random()*4);
		this.champDeVision = 2;
	}
	
	//Regarde les alentours de la proie et engage la fuite de la proe vers une zone (peut etre) safe
	public void fuite(){
		for(Agent a: world.getAgents()){
			if(a.isPred){
				//Si le predateur se trouve dans le champ de vision
				if(a.getPosX() >= this.posX - champDeVision && a.getPosX() <= this.posX + champDeVision &&
						a.getPosY() >= this.posY - this.champDeVision && a.getPosY() <= this.posY + this.champDeVision){
					//TODO
					//Faire un algo intéressant pour la fuite avec une part d'aleatoire
					
					//Pour l'instant le mouton prend juste la direction du predateur proche pour l'eviter
					if(a.getPosX() < this.posX){
						this.direction = 1;
					}else if(a.getPosX() > this.posX){
						this.direction = 3;
					}else if(a.getPosY() < this.posY){
						this.direction = 2;
					}else if(a.getPosY() > this.posY){
						this.direction = 0;
					}
				}
			}
		}
	}
	

	@Override
	public void Step() {
		// TODO Auto-generated method stub
		if ( Math.random() > 0.5 ) // au hasard
			direction = (direction+1) %4;
		else
			direction = (direction-1+4) %4;
		
		
		fuite();
		//Si le loup ne peut pas se deplacer dans la direction actuelle, on essaie les autres directions
		if(isObstacleDirection(direction)){
			if ( Math.random() > 0.5 ){ // au hasard
				for(int i=0; i<3; i++){
					direction = (direction+1) %4;
					if(!isObstacleDirection(direction)){
						break;
					}
				}
			}
			else{
				for(int i=0; i<3; i++){
					direction = (direction-1+4) %4;
					if(!isObstacleDirection(direction)){
						break;
					}
				}
			}	
		}
		
		// met a jour: la position de l'agent (depend de l'orientation)
		if(!isObstacleDirection(direction)){
			 switch ( direction ) 
			 {
	         	case 0: // nord
	         		posY = ( posY - 1 + world.getHeight() ) % world.getHeight();
	         		break;
	         	case 1:	// est
	         		posX = ( posX + 1 + world.getWidth() ) % world.getWidth();
	 				break;
	         	case 2:	// sud
	         		posY = ( posY + 1 + world.getHeight() ) % world.getHeight();
	 				break;
	         	case 3:	// ouest
	         		posX = ( posX - 1 + world.getWidth() ) % world.getWidth();
	 				break;
			 }
		}
	}
}
