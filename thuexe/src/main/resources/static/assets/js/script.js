//------------------------------slidebar----------
const itemsliderbar = document.querySelectorAll(".cartegory-left-li")
itemsliderbar.forEach(function(menu,index){
    menu.addEventListener("click",function(){
        menu.classList.toggle("block")
    })
})
//--------------------product------------
const bigImg = document.querySelector(".product-content-left-big-img img")
const smallImg = document.querySelectorAll(".product-content-left-small-img img")
smallImg.forEach(function(imgItem,X){
    imgItem.addEventListener("click", function(){
        bigImg.src = imgItem.src
    })
})



const baohanh = document.querySelector(".baohanh")
const chitet = document.querySelector(".chitiet")
if(baohanh){
    baohanh.addEventListener("click", function(){
        document.querySelector(".product-content-right-bottom-content-chitiet").style.display = "none" 
        document.querySelector(".product-content-right-bottom-content-baohanh").style.display = "block" 
    })
}
if(chitiet){
    baohanh.addEventListener("click", function(){
        document.querySelector(".product-content-right-bottom-content-chitiet").style.display = "block" 
        document.querySelector(".product-content-right-bottom-content-baohanh").style.display = "none" 
    })
}