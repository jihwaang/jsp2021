'use strict';
window.addEventListener('load', () => {
  let section = document.querySelector('#ex10');
  let btnRequest = section.querySelector('.btn-request');
  let pager = section.querySelector('.pager');
  let submit = section.querySelector('.search-form input[type="submit"]');
  let pageElement = pager.querySelector('a');
  console.log(`>> first pageElement : ${pageElement}`);
  submit.addEventListener('click', (e) => {
	e.preventDefault();
	let category = section.querySelector('.search-form select[name="f"]').value;
	let keyword = section.querySelector('.search-form input[name="q"]').value;
	console.log(`category ==> ${category}  \nkeyword ==> ${keyword}`);
	bind(`../api/notice/list?f=${category}&q=${keyword}`);
  });
  
  pager.addEventListener('click', (e) => {
	e.preventDefault();
	if(e.target.tagName.toLowerCase() !== 'a') return;
	let pageNumber = e.target.innerText;
	console.log(`>>> ${pageNumber}`);
	let url = `../api/notice/list?p=${pageNumber}`;
	console.log(`>>> ${url}`);
	bind(url);
	
	/*let as = pager.querySelectorAll('a');
	console.log(as);
	for(let elem of as){
		elem.classList.remove('text-strong');
	}*/
	pageElement.classList.remove('text-strong');
	e.target.classList.add('text-strong');
	pageElement = e.target;
	/*let request = new XMLHttpRequest();
	request.addEventListener('load', (e) => {
		console.log(`>>> ajax response`);
		console.log(request.responseText);
		let list = JSON.parse(request.responseText);		
	});
	request.open('GET', url, true);
	request.send(null);*/
  });
  //btnRequest.addEventListener('click', () => {
  //});
    bind(`http://localhost:8080/api/notice/list`);

function bind(url) {
	let request = new XMLHttpRequest();
	
	request.addEventListener('load', () => {
		console.log(request.responseText);
		let list = JSON.parse(request.responseText);
		let tbody = section.querySelector('tbody');
		tbody.innerHTML = '';
		//let emptyTr = tbody.querySelector('.empty');
		//if(emptyTr) emptyTr.remove();
		for(let notice of list){
			let tr = `<tr>
						<td>${notice.id}</td>
						<td>${notice.title}</td>
				  	  </tr>`;
			tbody.insertAdjacentHTML('beforeend', tr);			
		}
		
		screen.remove();
	});
	request.addEventListener('abort', () => {
		console.log('==> abort requested');
		screen.remove();
	});
	console.log('url in bind function ==> ' + url);
	request.open('GET', url, true);
    request.send(null);
	
	let screen = document.createElement('div');
	
	screen.style.width = '100%';
	screen.style.height = '100%';
	screen.style.backgroundColor = 'black';
	screen.style.position = 'absolute';
	screen.style.top = '0px';
	screen.style.left = '0px';
	screen.style.opacity = '0';
	screen.style.transition = '.5s';
	
	screen.style.display = 'flex';
	screen.style.alignItems = 'center';
	screen.style.justifyContent = 'center';
	
	let img = document.createElement('img');
	img.src = '../images/ajax-loader.gif';
	screen.append(img);
	
	let closeButton = document.createElement('input');
	closeButton.value = '닫기';
	closeButton.type = 'button';
	closeButton.style.position = 'absolute';
	//closeButton.style.alignSelf = 'flex-start';
	closeButton.style.top = '0';
	closeButton.style.right = '0';
	screen.append(closeButton);
	
	closeButton.addEventListener('click', (e) => {
		request.abort();
	});
	
	
	setTimeout(function(){
		screen.style.opacity = '0.7';
	}, 0);
	
	section.append(screen);

	
  }
});
// -----------------------------------------------
window.addEventListener('load', () => {
  const section = document.querySelector('#ex9');
  const upload = section.querySelector('.upload-box');
  const btnSel = upload.querySelector('.btn-sel');
  const btnFile = upload.querySelector('.btn-file');
  btnSel.addEventListener('click', (e) => {
    console.log('click');
    let event = new MouseEvent('click', {
      'view': window,
      'blubbles': true,
      'cancelable': true
    });
    btnFile.dispatchEvent(event);
  });

  upload.addEventListener('dragenter', (e) => {
    console.log("enter");
    upload.classList.add('drag-on');
  });
  
  upload.addEventListener('dragover', (e) => {
    e.preventDefault();
    console.log(e.dataTransfer.types);
    let valid = e.dataTransfer.types.indexOf('Files');
    console.log(valid);

    if (!valid) {
      upload.style.backgroundColor = 'red';
    } else {
      upload.style.backgroundColor = 'gray';
    }

    console.log(e.dataTransfer.files[0]);
    console.log("over");
  });
  
  upload.addEventListener('dragleave', (e) => {
    console.log("leave");
    upload.classList.remove('drag-on');
  });

  upload.addEventListener('drop', (e) => {
    e.preventDefault();
    console.log("drop");
    let fileName = e.dataTransfer.files[0].name;
    console.log(fileName);
    console.log(e.dataTransfer.files[0].size);
    let text = document.createElement('div');
    text.innerText = fileName;
    upload.append(text);
    file.files = e.dataTransfer.files;
    upload.classList.remove('drag-on');
  });

});

window.addEventListener('load', () => {
  const section = document.querySelector('#ex8');
  const product = section.querySelector('.product');
  product.addEventListener('click', (e) =>{
    let btn = e.target;
    let item = e.target.parentNode;
    if (!btn.classList.contains('up') &&
       !btn.classList.contains('down') &&
       !btn.classList.contains('check')) return; 
    
    if (btn.classList.contains('up')) {
      console.log('up');
      let input = item.querySelector('input');
      input.value = parseInt(input.value)+1;
    } else if (btn.classList.contains('down')) {
      console.log('down');
      let ref = item.nextElementSibling;
      item.replaceWith(item.nextElementSibling);
      product.insertBefore(item, ref);
      let input = item.querySelector('input');
      input.value = parseInt(input.value)-1;
    } else if (btn.classList.contains('check')) {
      console.log('check');
      item.classList.toggle('current');
    }
  });
});
// ---------------------------------- 
window.addEventListener('load', () => {
  const section = document.querySelector('#ex7');
  const accordion = section.querySelector('.accordion');
  accordion.addEventListener('click', function(e){
    if(!e.target.classList.contains('title')) return;
      let _this = e.target;
      let content = _this.nextElementSibling;
      content.classList.toggle('d-none');
  });
});
// ------------------------------------- 
window.addEventListener('load', () => {
  const section = document.querySelector('#ex6');
  const container = section.querySelector('.container');
  const btnDel = section.querySelector('.btn-del');
  const btnClone = section.querySelector('.btn-clone');
  const btnChange = section.querySelector('.btn-change');

  let selected = null;

  container.addEventListener('click', (e) => {
    let _this = e.target
    if (!_this.classList.contains('box')) return;
    if (selected !== null 
        && selected !== _this)
        selected.classList.remove('selected');
    selected = _this;
    selected.classList.toggle('selected');
  });


  // const boxes = container.querySelectorAll('.box');
  // for(let i = 0; i < boxes.length; i++){
  //     boxes[i].addEventListener('click', function(e){
  //         selected = boxes[i];
  //         console.log(selected);
  //     });
  // }

  btnDel.addEventListener('click', () => {
    console.log('click');
    if (selected) selected.remove();
  });
});
// ----------------------------------------------------

window.addEventListener('load', () => {
  const section = document.querySelector('#ex5');
  const container = section.querySelector('.container');
  const btnAdd = section.querySelector('.btn-add');
  const btnDel = section.querySelector('.btn-del');
  const btnClone = section.querySelector('.btn-clone');
  const btnChange = section.querySelector('.btn-change');
  const idInput = section.querySelector('.id-input');
  const colorInput = section.querySelector('.color-input');
  btnAdd.addEventListener('click', () => {
    console.log('btnAdd clicked');
    //let img = document.createElement('img');
    //img.src = '../images/1.jpg';
    //img.setAttribute('src', '../images/1.jpg');
    //container.appendChild(img);
    let circleDiv = document.createElement('div');
    //let txt = document.createTextNode('1');
    circleDiv.style.backgroundColor = colorInput.value;
    circleDiv.style.width = '100px';
    circleDiv.style.height = '100px';
    circleDiv.style.borderRadius = '50%';
    circleDiv.style.textAlign = 'center';
    circleDiv.style.color = '#fff';
    circleDiv.style.lineHeight = '100px';
    //circleDiv.appendChild(txt);

    circleDiv.append(idInput.value);
    //container.appendChild(circleDiv);
    container.append(circleDiv);
    console.log(idInput.value);
    console.log(colorInput.value);
  });

  btnDel.addEventListener('click', () => {
    let div = container.querySelector('div:last-child');
    div.remove();
    console.log('btnDel clicked');
  });

  btnClone.addEventListener('click', () => {
    let div = container.querySelector('div:first-child');
    let clone = div.cloneNode();
    container.append(clone);
    console.log('btnClone clicked');
    console.log(idInput.value);
    console.log(colorInput.value);
  });

  btnChange.addEventListener('click', () => {
    console.log('change clicked');
    let prev = container.querySelector('div:first-child');
    let next = prev.nextElementSibling;
    // let _new = container.replaceChild(prev, next);
    //container.insertBefore(_new, prev);
    //prev.insertAdjacentElement('beforebegin', _new);
    container.insertBefore(next, prev);
  });
});

window.addEventListener('load', () => {
  const section = document.querySelector('#ex4');
  const container = section.querySelector('#ex4 .container');
  const boxes = section.querySelectorAll('.box');
  const button = section.querySelector('input[value=click]');

  button.onclick = function () {
    let box = boxes[0];
    let wholeWidth = parseInt(
      window.getComputedStyle(container).getPropertyValue('width')
    );
    let circleWidth = parseInt(
      window.getComputedStyle(box).getPropertyValue('width')
    );
    let diff = wholeWidth - circleWidth;
    let left = parseInt(window.getComputedStyle(box).getPropertyValue('left'));
    console.log(wholeWidth);
    console.log(circleWidth);
    console.log(diff);
    console.log(left);
    let timer = window.setInterval(function () {
      if (left >= diff) clearInterval(timer);

      box.style.left = left++ + 'px';
    }, 17);
  };
});

window.addEventListener('load', () => {
  const section = document.querySelector('#ex3');
  const span = section.querySelector('.span');
  const button = section.querySelector('.btn');

  button.onclick = function () {
    window.setInterval(function () {
      let spanValue = parseInt(span.innerText);
      if (spanValue > 0) span.innerText = --spanValue;
    }, 1000);
  };
});

window.addEventListener('load', () => {
  const section = document.getElementById('ex2');
  const submitButton = section.getElementsByClassName('submit-button')[0];
  const inputX = document.getElementsByName('x')[0];
  const inputY = document.getElementsByName('y')[0];
  const result = section.getElementsByClassName('result')[0];
  submitButton.addEventListener('click', (e) => {
    e.preventDefault();
    let x = parseInt(inputX.value);
    let y = parseInt(inputY.value);
    let sum = x + y;
    result.innerText = sum;
  });
});

// /------------------/
window.addEventListener('load', () => {
  const section = document.getElementById('ex1');
  const submitButton = section.getElementsByClassName('submit-button')[0];
  submitButton.addEventListener('click', (e) => {
    e.preventDefault();
    console.log('test');
  });
});
