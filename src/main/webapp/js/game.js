// create new scene

let WIDTH = 640;
let HEIGHT = 640;
let gameScene = new Phaser.Scene('Game');
let sprites = [];
let velocity  = 2;
let svgHorizontalRatio = 726;
let svgVerticalRatio = 628;


let hexagonUnit = 2;
let hexagonVerticalDim = 11;
let hexagonHorizontalDim = 6;
let hexagonList = {}

function getVerticalDimentions(baseHexagons){
	let verticalDimentios = baseHexagons*2 - 1;
	let accumulatorIncrement = 1;
	let horizontalDimentios = [];
	let offSetHander = [];
	let valueToBePushed = baseHexagons - 1;
	Array(verticalDimentios).fill().forEach((_,i) => {
		valueToBePushed += accumulatorIncrement;
		horizontalDimentios.push(valueToBePushed);
		offSetHander.push(Math.sign(accumulatorIncrement));
		if (i >= baseHexagons-1){
			accumulatorIncrement = -1;
		}

	});
	return [horizontalDimentios,offSetHander] ;
}



function createBoard(scene){
	dims = 8;
	[horDims,offSetHander] =  getVerticalDimentions(dims);
	console.log(horDims);
	console.log(offSetHander);
	Array(horDims.length).fill().forEach((_,i)=> {
		Array(horDims[i]).fill().forEach((_,j)=> {
			let degradeScale = 1/32;
			let degradedHorizontal = svgHorizontalRatio*degradeScale;
			let degradedVertical = svgVerticalRatio*degradeScale;
			let baseHor = (dims+1)*degradedHorizontal/2;
			let baseVert = 20;
			let horizontalScaling = degradedHorizontal*j;
			let offs  = ( i < dims)? i : (dims-i)%dims + dims -2 ;
			let hex = scene.add.sprite(baseHor + horizontalScaling  - offs*degradedHorizontal/2
				,baseVert + i*degradedVertical ,'player');
			hex.setScale(degradeScale,degradeScale);
			hex.setAngle(90);
			hexagonList[[0,i]] = 5;
		});
		
	});
}

// load assets
gameScene.preload = function() {
	this.load.image('background', './assets/juliaImg.png');
	this.load.image('player', './assets/hexagon.svg');
};

// called after the preload ends

gameScene.create = function(){
	

	
	let bg = this.add.sprite(0,0,'background');
	
	
	bg.setPosition(WIDTH/2,HEIGHT/2);
	
	/*let pl = this.add.sprite(180,180,'player');
	pl.setScale(0.25,0.25);
	
	let pl2 = this.add.sprite(380,180,'player');
	pl2.setScale(0.125,0.125);
	pl2.setAngle(90);
	pl2.filpX = true; // these two doesnt work yet
	pl2.filpY = true;
	console.log(this)
	sprites.push(bg);
	sprites.push(pl);
	sprites.push(pl2)*/
	createBoard(this);
};



// up to 60 frames per second

gameScene.update = function(){
	// if(sprites[2].x > WIDTH){
	// 	velocity  = -2;
	// } else if (sprites[2].x < 0){
	// 	velocity = 2;
	// }

	// sprites[2].x += velocity;
	
};



// set the configuration of the game
let config = {
	type : Phaser.AUTO,
	width : WIDTH,
	height : HEIGHT,
	scene : gameScene
	
};

// create a game, pass the configureation
let game = new Phaser.Game(config);

