function showAlert() {
    alert("The button was clicked!");
}

var colors = ['#ff0000', '#00ff00', '#0000ff', '#ffffff', '#fff000', '#fff00f'];
var random_color = colors[Math.floor(Math.random() * colors.length)];
document.getElementById('title').style.color = random_color;