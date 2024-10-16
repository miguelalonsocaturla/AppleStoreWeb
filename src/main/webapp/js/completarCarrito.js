


	var list = document.getElementById("productos");
	list.innerHTML='';
	carrito.forEach(rellenarTablaCarrito, list);



function rellenarTablaCarrito(item, codigoproducto) {
    var tr = document.createElement('tr');
    tr.setAttribute('id', codigoproducto);

    var td = document.createElement('td');
    td.textContent=item["nombre"];
  	var img =  document.createElement('img')
    img.setAttribute('src' , item['img'] );
    img.setAttribute('width','60');
    td.appendChild(img);
    
    var td2 = document.createElement('td');
    var input = document.createElement('input');
    input.setAttribute('type', 'number');
    input.setAttribute('min', '0');
    input.setAttribute('max', '20');
    input.value= item["cantidad"];
    input.setAttribute('id', 'modificarcompra' + codigoproducto);
    input.setAttribute('onchange','modificarcompra('+codigoproducto+')');
    td2.appendChild(input);
    
    var td3 = document.createElement('td');
    td3.textContent=(item["precio"]/item["cantidad"]);
    
    var td4 = document.createElement('td');
    
    td4.textContent=item["precio"];
    var td5 = document.createElement('td');
    var but = document.createElement('button');
	but.setAttribute('class','btn btn-danger');
	but.setAttribute('onclick', 'eliminar('+codigoproducto+')');
	but.textContent='eliminar';
	td5.appendChild(but);
	
	tr.appendChild(td);
	tr.appendChild(td2);
	tr.appendChild(td3);
	tr.appendChild(td4);
	tr.appendChild(td5);
	this.appendChild(tr);
}