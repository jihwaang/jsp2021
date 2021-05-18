const asideTitle = document.querySelector('#aside-title');
const a1 = document.querySelector('#a1');
asideTitle.onclick = (_this) => {
  let x = prompt('x:');
  let y = prompt('y:');
  x = parseInt(x);
  y = parseInt(y);
  asideTitle.innerText = x + y;
  a1.value = x + y;
};
