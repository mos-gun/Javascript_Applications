export default class Slot {


  constructor(x, y, width, height, canvas) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.canvas = canvas;

    this.value = 0;
    this.path = "";
  }

  draw(ctx) {
    
    const xa = this.x;
    const ya = this.y;
    const wa = this.width;
    const ha = this.height;

    if (this.value == 0) this.path = "res/slot_pirate.png";
    else if (this.value == 7) this.path = "res/slot_seven.png";
    else this.path = "res/slot_coin.png";

    var imgSlot = new Image(this.width, this.height);

    imgSlot.onload = function () {
      ctx.drawImage(imgSlot, xa, ya, wa, ha);
     }
    imgSlot.src = this.path;

    ctx.strokeRect(this.x - 5, this.y - 5, this.width + 10, this.height + 10);
  }

  
setValue(value) {
  this.value = value;
}

getValue() {
  return this.value;
}

}