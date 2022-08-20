const btn = document.querySelectorAll("button")
// console.log(btn)
btn.forEach(function(button,index){
    button.addEventListener("click",function(event){{
var btnItem = event.target
var product = btnItem.parentElement
var productImage = product.querySelector("img").src
var productName = product.querySelector("H1").innerText
var productPrice = product.querySelector("span").innerText
// console.log(productPrice,productImage,productName)
addcart(productPrice,productImage,productName)
  
    }})
})
function  addcart(productPrice,productImage,productName){ 
    var addtr = document.createElement("tr")
    var cartItem = document.querySelectorAll("tbody tr")
    for (var i=0;i<cartItem.length;i++){
    var productT = document.querySelectorAll(".title")
    if(productT[i].innerHTML==productName){
        alert("san phan cua ban da co trong gio hang")
          return 
    }
    }
    var trcontent ='<tr><td  style="display:flex ; align-items: center;"><img  style="width:70px;" src='+productImage+' alt=""><span class="title">'+productName+'</span></td><td><p><span class="price">'+productPrice+'</span><sup>đ</sup></p></td> <td><input style="width: 30px; outline: none; "    type="number" value="1" min="1"> </td><td style="cursor:pointer;"> <span class="cart-delete">xoa</span></td></tr>'
    addtr.innerHTML = trcontent
    var carttabe = document.querySelector("tbody")
    carttabe.append(addtr)

    carttotal()
    deleteCart()
}
//them tien

function carttotal(){
    var cartItem = document.querySelectorAll("tbody tr")
    var totalC = 0
    // console.log(cartItem.length)
    for (var i=0;i<cartItem.length;i++){
        var inputValue = cartItem[i].querySelector("input").value
        // console.log(inputValue)
        var productPrice = cartItem[i].querySelector(".price").innerHTML
        // console.log(productPrice)
        totalA = inputValue*productPrice*1000   
        // totalB = totalA.toLocaleString('de-DE')
        // console.log(totalC)
        totalC = totalC + totalA 
        // totalD = totalC.toLocaleString('de-DE')  


    }
    var cartTotalA = document.querySelector(".price-total span")
    var cartPrice = document.querySelector(".cart-icon span")
    cartTotalA.innerHTML=totalC.toLocaleString('de-DE')
    cartPrice.innerHTML=totalC.toLocaleString('de-DE')
    inputchange()
    
}
// delete
function deleteCart(){
    var cartItem = document.querySelectorAll("tbody tr")
    for (var i=0;i<cartItem.length;i++){
        var productT = document.querySelectorAll(".cart-delete")
        productT[i].addEventListener("click",function(event){
            var cartdelete = event.target
            var cartItemR = cartdelete.parentElement.parentElement
            cartItemR.remove()
            carttotal()
            // console.log(cartItemR)
        })
       
      
        }
}
function inputchange(){
    var cartItem = document.querySelectorAll("tbody tr")
    for (var i=0;i<cartItem.length;i++){
        var inputvalue = cartItem[i].querySelector("input")
    inputvalue,addEventListener("change",function(){
        carttotal()
    })
       
      
        }
}
const cartbtn = document.querySelector(".fa-times")
const cartshow = document.querySelector(".fa-shopping-cart")
cartshow.addEventListener("click",function(){
    document.querySelector(".cart").style.right="0"
})
cartbtn.addEventListener("click",function(){
    document.querySelector(".cart").style.right="-100%"
})