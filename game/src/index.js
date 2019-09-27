import Slot from "./slot.js";
import Button from "./button.js";


let canvas = document.getElementById("gameScreen");
let ctx = canvas.getContext("2d");


const GAME_WIDTH = 800;
const GAME_HEIGHT = 600;


ctx.clearRect(0,0,GAME_WIDTH,GAME_HEIGHT);


// TITLE: Lucky7!

var gradient = ctx.createLinearGradient(canvas.width/2-85, 0, canvas.width/2+85, 0);
gradient.addColorStop("0"," red");
gradient.addColorStop("0.3", "purple");
gradient.addColorStop("1.0", "blue");

ctx.font = "50px Georgia";
ctx.fillStyle = gradient;
ctx.fillText("Lucky7!", canvas.width/2 - 85, 100);


// SLOTS

let slot1 = new Slot(125, 175, 150, 150, canvas);
let slot2 = new Slot(325, 175, 150, 150, canvas);
let slot3 = new Slot(525, 175, 150, 150, canvas);

drawSlots();


// BUTTONS

var btnExitX = 450;
var btnExitY = 450;
var btnExitWidth = 50;
var btnExitHeight = 30;

var btnRestartX = 450;
var btnRestartY = 400;
var btnRestartWidth = 80;
var btnRestartHeight = 30;

var btnSpinX = 625;
var btnSpinY = 400;
var btnSpinWidth = 50;
var btnSpinHeight = 30;

let btnExit = new Button(btnExitX,btnExitY,btnExitWidth,btnExitHeight);
let btnRestart = new Button(btnRestartX,btnRestartY,btnRestartWidth,btnRestartHeight);
let btnSpin = new Button(btnSpinX,btnSpinY,btnSpinWidth,btnSpinHeight);

drawButtons();




// AMMOUNT OF COINS/ATTEMPTS

let ammountOfCoins = 0;
let ammountOfAttempts = 0;

drawData();



canvas.addEventListener("click", function(event) {

    if (
        event.x >= btnExitX + 10 && 
        event.x <= btnExitX + btnExitWidth + 10 &&
        event.y >= btnExitY + 10 && 
        event.y <= btnExitY + btnExitHeight + 10
      ) {
        // Exit button pressed:
        console.log("Exit button pressed!");

        ammountOfCoins = 0;
        ammountOfAttempts = 0;
        updateData();
        alert("Exit game...")
    }

      if (
        event.x >= btnRestartX + 10 && 
        event.x <= btnRestartX + btnRestartWidth + 10 &&
        event.y >= btnRestartY + 10 && 
        event.y <= btnRestartY + btnRestartHeight + 10
      ) {
        // Restart button pressed:
        console.log("Restart button pressed!");

        ammountOfCoins = 0;
        ammountOfAttempts = 0;
        updateData();
      }

      if (
        event.x >= btnSpinX + 10 && 
        event.x <= btnSpinX + btnSpinWidth + 10 &&
        event.y >= btnSpinY + 10 && 
        event.y <= btnSpinY + btnSpinHeight + 10
      ) {
        // Spin button pressed:
        console.log("Spin button pressed!")

        slot1.setValue(getRandomIntInclusive(0,7));
        slot2.setValue(getRandomIntInclusive(0,7));
        slot3.setValue(getRandomIntInclusive(0,7));

        ammountOfAttempts++;

        if(slot1.getValue() + slot2.getValue() + slot3.getValue() == 0){
            ammountOfCoins = 0;
        } else if(slot1.getValue() + slot2.getValue() + slot3.getValue() == 21){
            alert("LUCKY 7!\nCongratulation, you have earned " + ammountOfCoins + 
            " coins with " + ammountOfAttempts + " attempts!");
            ammountOfAttempts = 0;
            ammountOfCoins = 0;
            alert("Exit game...");
        } else{
            if(slot1.getValue() != 0 && slot1.getValue() != 7) ammountOfCoins++;
            if(slot2.getValue() != 0 && slot2.getValue() != 7) ammountOfCoins++;
            if(slot3.getValue() != 0 && slot3.getValue() != 7) ammountOfCoins++;
        }

        updateSlots();
        updateData();
      }
});




// FUNCTIONS

function drawButtons(){                                                 // DRAW BUTTONS
    ctx.font = "20px Georgia";
    ctx.fillStyle = "grey";
    btnExit.draw(ctx);
    btnRestart.draw(ctx);
    btnSpin.draw(ctx);
    
    ctx.fillStyle = "black";
    ctx.fillText("Exit", btnExitX+6, btnExitY+22, btnExitWidth);
    ctx.fillText("Restart", btnRestartX+6, btnRestartY+22, btnRestartWidth);
    ctx.fillText("Spin", btnSpinX+6, btnSpinY+22, btnSpinWidth);
}

function drawSlots(){                                                   // DRAW SLOTS
    slot1.draw(ctx);
    slot2.draw(ctx);
    slot3.draw(ctx);    
}

function drawData(){                                                    // DRAW DATA (AMMOUNT OF COINS/ATTEMPTS)
    ctx.font = "20px Georgia";
    ctx.fillStyle = "black";
    ctx.fillText("Coins: " + ammountOfCoins, 125, 415, 80);
    ctx.fillText("Attempts: " + ammountOfAttempts, 125, 445, 80);
}

function updateSlots(){                                                 // UPDATE SLOTS
    ctx.save();
    ctx.clearRect(110, 160, 580, 180);
    slot1.draw(ctx);
    slot2.draw(ctx);
    slot3.draw(ctx);
    ctx.restore();    
}

function updateData(){                                                  // UPDATE DATA (AMMOUNT OF COINS/ATTEMPTS)
    ctx.save();
    ctx.clearRect(110, 390, 200, 80);
    ctx.font = "20px Georgia";
    ctx.fillStyle = "black";
    ctx.fillText("Coins: " + ammountOfCoins, 125, 415, 80);
    ctx.fillText("Attempts: " + ammountOfAttempts, 125, 445, 80);
    ctx.restore();    
}

function getRandomIntInclusive(min, max) {                          // from https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math/random
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min; //The maximum is inclusive and the minimum is inclusive 
  }