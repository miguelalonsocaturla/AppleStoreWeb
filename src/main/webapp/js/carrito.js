let carrito = [];
let savecarrito = [];
function cargar() {
    carrito = JSON.parse(localStorage.getItem("savecarrito"));

   	if(carrito){
        for (let i = 0; i < carrito.length; i++) {
            if (carrito[i] == null) {
                delete carrito[i];
            }
    	}	 
    actualizarCarrito(); 
	}else{
		carrito=[];
	}
}



function eliminar(codigoproducto) {
    
    
    delete carrito[codigoproducto];
    savecarrito=[];
    carrito.forEach(LocalCarrito, savecarrito);
	localStorage.setItem('savecarrito', JSON.stringify(savecarrito));
	cargar();
	Cargar('./html/carrito.jsp', 'inicio');
}
function LocalCarrito(item, codigoproducto) {

    savecarrito[codigoproducto] = {};
    savecarrito[codigoproducto]["nombre"] = item["nombre"];
    savecarrito[codigoproducto]["cantidad"] = item["cantidad"];
    savecarrito[codigoproducto]["precio"] = item["precio"];
    savecarrito[codigoproducto]["img"] = item["img"];
}
function comprar(elemento) {
	
	cantidad=1;
    if (carrito[elemento.id] == null) {
        carrito[elemento.id] = [];
        carrito[elemento.id]["nombre"] = document.getElementById("nombre" + elemento.id).textContent;
        carrito[elemento.id]["cantidad"] = cantidad;
        carrito[elemento.id]["precio"] = (document.getElementById("precio" + elemento.id).textContent.slice(0, -1) * parseInt(cantidad).toString());
        carrito[elemento.id]["img"] = document.getElementById("imagen" + elemento.id).src;
        alert("Has añadido el producto: "+carrito[elemento.id]["nombre"]+" a tu carrito.");
    }
    else {
        carrito[elemento.id]["precio"] += carrito[elemento.id]["precio"] / carrito[elemento.id]["cantidad"] * parseInt(cantidad);
        carrito[elemento.id]["cantidad"] = parseInt(carrito[elemento.id]["cantidad"]) + parseInt(cantidad);
        alert("Has añadido una unidad mas a tu carrito del producto: "+carrito[elemento.id]["nombre"]);
    }
    carrito.forEach(LocalCarrito, savecarrito);
    localStorage.setItem('savecarrito', JSON.stringify(savecarrito));
    actualizarCarrito();
}

 
function modificarcompra(codigoproducto) {
	var cantidad=document.getElementById('modificarcompra'+codigoproducto).value;
	if(cantidad=='0'){
		eliminar(codigoproducto);
	}
	carrito[codigoproducto]["precio"] = (carrito[codigoproducto]["precio"] / carrito[codigoproducto]["cantidad"]) * parseInt(cantidad);
	carrito[codigoproducto]["cantidad"] = parseInt(cantidad);
    

    carrito.forEach(LocalCarrito, savecarrito);
    localStorage.setItem('savecarrito', JSON.stringify(savecarrito));
    alert("Ahora tienes "+cantidad+" unidades del producto: "+carrito[codigoproducto]["nombre"]+" en tu carrito");
    Cargar('./html/carrito.jsp', 'inicio');
}

 
function actualizarCarrito() {
    let listaProductos = document.getElementById("productosindice");
	
    listaProductos.innerHTML='';

    carrito.forEach(rellenarinicio, listaProductos);
    var suma = 0;
    var nproductos = 0;

    for (var producto in carrito) {
        if (producto != null) {
            suma = suma + carrito[producto]["precio"];
            nproductos = nproductos + parseInt(carrito[producto]["cantidad"]);
        }
    }

    document.getElementById("resumenindice").textContent = nproductos + " productos | " + suma + "€";

    if (document.getElementById("preciototal") != null) {
        document.getElementById("preciototal").textContent = suma + "€";
       
    } 
}
 
function rellenarinicio(item, codigoproducto) {

    var li = document.createElement('li');
    li.setAttribute('id', codigoproducto);

    var div = document.createElement('div');
    div.classList.add("form-inline");
    
    var a = document.createElement('a');
    a.textContent = item["nombre"] + " - " + item["cantidad"] + " - " + (parseInt(item["precio"])).toString() + "€";
    
    div.appendChild(a);
    li.appendChild(div);
    this.appendChild(li);
}

function borrarCarro(){
	carrito = [];
	actualizarCarrito();
	
	
}

